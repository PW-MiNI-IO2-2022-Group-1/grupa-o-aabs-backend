package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.ResponseDictionaryDto;
import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.VaccinationRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
import com.example.SOPSbackend.repository.filters.CustomVaccinationSlotSpecifications;
import com.example.SOPSbackend.security.AuthorizationResult;
import com.example.SOPSbackend.security.Credentials;
import com.example.SOPSbackend.security.Role;
import com.example.SOPSbackend.security.TokenService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.InvalidParameterException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class DoctorService {
    private static final int NEW_SLOT_MIN_TIME_DIFF = 15;
    private static final int ITEMS_PER_PAGE = 30;
    private final DoctorRepository doctorRepository;
    private final VaccinationSlotRepository vaccinationSlotRepository;
    private final VaccinationRepository vaccinationRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public DoctorService(DoctorRepository doctorRepository,
                         VaccinationSlotRepository vaccinationSlotRepository,
                         VaccinationRepository vaccinationRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.doctorRepository = doctorRepository;
        this.vaccinationSlotRepository = vaccinationSlotRepository;
        this.vaccinationRepository = vaccinationRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public AuthorizationResult logIn(Credentials credentials) {
        DoctorEntity doctor = doctorRepository.findByEmailIgnoreCase(credentials.getEmail()).orElse(null);
        if(doctor == null || !passwordEncoder.matches(credentials.getPassword(), doctor.getPassword()))
            return AuthorizationResult.failedAuthorization();

        String token = tokenService.getToken(Role.DOCTOR, doctor);
        return AuthorizationResult.successfulAuthorization(token, doctor);
    }

    public void addVaccinationSlot(DoctorEntity doctor, Instant date) {
        LocalDateTime transformedDate = transformVaccinationSlotDate(date);

        if (!isVaccinationSlotDateValid(transformedDate))
            throw new InternalValidationException(Map.of("date", "Invalid date value: date should not be set in past"));

        VaccinationSlotEntity newSlot = new VaccinationSlotEntity(doctor, transformedDate);
        if (vaccinationSlotRepository.findResultsByDateAndDoctor(transformedDate, doctor).stream().findAny().orElse(null) != null)
            throw new InternalValidationException(Map.of("date", "Duplicate date"));

        vaccinationSlotRepository.save(newSlot);
    }

    @Transactional
    public void deleteVaccinationSlot(DoctorEntity doctor, Long id) {
        vaccinationSlotRepository.deleteByIdAndDoctor(id, doctor);
    }

    public Page<ResponseDictionaryDto> getVaccinationSlots(DoctorEntity doctor, int page, String startDate, String endDate, String onlyReserved) {
        Pageable thisPage = PageRequest.of(page - 1, ITEMS_PER_PAGE, Sort.by("date"));
        Page<VaccinationSlotEntity> mySlots;
        LocalDateTime sDate, eDate;
        if (startDate == null) sDate = null;
        else sDate = LocalDateTime.ofInstant(Instant.parse(startDate), ZoneId.of("UTC"));
        if (endDate == null) eDate = null;
        else {
            eDate = LocalDateTime.ofInstant(Instant.parse(endDate), ZoneId.of("UTC"));
        }

        if (onlyReserved != null) {
            if (onlyReserved.equals("0")) {
                if(sDate == null || sDate.isBefore(LocalDateTime.now()))
                    sDate = LocalDateTime.now();
                mySlots = vaccinationSlotRepository.findNotReserved(doctor,
                        sDate,
                        eDate,
                        thisPage);
            }
            else if (onlyReserved.equals("1"))
                mySlots = vaccinationSlotRepository.findReserved(doctor,
                        sDate,
                        eDate,
                        thisPage);
            else throw new InvalidParameterException();
        } else {
            Specification<VaccinationSlotEntity> mySpec = Specification.where(CustomVaccinationSlotSpecifications.findByDoctor(doctor));
            if (startDate != null)
                mySpec = mySpec.and(CustomVaccinationSlotSpecifications.findAfter(sDate));
            if (endDate != null)
                mySpec = mySpec.and(CustomVaccinationSlotSpecifications.findBefore(eDate));
            mySlots = vaccinationSlotRepository.findAll(mySpec, thisPage);
        }

        var myVaccinations = vaccinationRepository.findMatchingVaccinations(mySlots.toList());
        return mySlots.map((vs) -> new ResponseDictionaryDto(vs,
                myVaccinations.stream()
                        .filter((v) -> v.getVaccinationSlot().equals(vs))
                        .findAny()
                        .orElse(null)));
    }

    public void vaccinatePatient(
            Long vaccinationSlotId,
            String vaccinationStatus,
            DoctorEntity doctor) throws AuthenticationException {
        Optional<VaccinationSlotEntity> vaccinationSlot =
                vaccinationSlotRepository.findById(vaccinationSlotId);
        if (!vaccinationSlot.isPresent()) {
            throw new NoSuchElementException("No such vaccination slot");
        }

        if (vaccinationSlot.get().getDoctor().getId() != doctor.getId()) {
            throw new AuthenticationException("Vaccination slot was not created by this doctor");
        }

        VaccinationEntity vaccination
                = vaccinationRepository.findByVaccinationSlot(vaccinationSlot.get());
        if (vaccination == null) {
            throw new InternalValidationException(
                    Map.of("vaccinationSlotId", "Vaccination slot is not reserved")
            );
        }

        if (!vaccinationStatus.contentEquals("COMPLETED")
                && !vaccinationStatus.contentEquals("CANCELED")) {
            throw new InternalValidationException(
                    Map.of("status", "Not allowed value. Allowed value: \"COMPLETED\" or \"CANCELED\"")
            );
        }

        vaccination.setStatus(
                vaccinationStatus.contentEquals("COMPLETED") ? "Completed" : "Canceled"
        );
        vaccinationRepository.save(vaccination);
    }

    private LocalDateTime transformVaccinationSlotDate(Instant date) {
        Instant truncatedDate = date.truncatedTo(ChronoUnit.MINUTES);
        return LocalDateTime.ofInstant(truncatedDate, ZoneId.of("UTC"));
    }

    private boolean isVaccinationSlotDateValid(LocalDateTime slotDate) {
        LocalDateTime currentDate = LocalDateTime.now(ZoneId.of("UTC"));
        long minuteDifference = ChronoUnit.MINUTES.between(currentDate, slotDate);
        return (minuteDifference >= NEW_SLOT_MIN_TIME_DIFF);
    }
}
