package com.example.SOPSbackend.service;

import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.Vaccination;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
import com.example.SOPSbackend.repository.filters.SearchCriteria;
import com.example.SOPSbackend.repository.filters.VaccinationSlotEntitySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Map;


@Service
public class DoctorService {
    private static final int NEW_SLOT_MIN_TIME_DIFF = 15;
    public static final int ITEMS_PER_PAGE = 30;
    private final DoctorRepository doctorRepository;
    private final VaccinationSlotRepository vaccinationSlotRepository;

    public DoctorService(DoctorRepository doctorRepository, VaccinationSlotRepository vaccinationSlotRepository) {
        this.doctorRepository = doctorRepository;
        this.vaccinationSlotRepository = vaccinationSlotRepository;
    }

    public void addVaccinationSlot(DoctorEntity doctor, Instant date) {
        LocalDateTime transformedDate = transformVaccinationSlotDate(date);

        if(!isVaccinationSlotDateValid(transformedDate))
            throw new InternalValidationException(Map.of("date", "Invalid date value"));

        VaccinationSlotEntity newSlot = new VaccinationSlotEntity(doctor, transformedDate);
        vaccinationSlotRepository.save(newSlot);
    }

    public void deleteVaccinationSlot(DoctorEntity doctor, Long id){
        vaccinationSlotRepository.deleteById(id);
    }

    public Page<VaccinationSlotEntity> getVaccinationSlots(DoctorEntity doctor, int page, String startDate, String  endDate, String onlyReserved) {
        Pageable thisPage = PageRequest.of(page, ITEMS_PER_PAGE, Sort.by("date"));
        VaccinationSlotEntitySpecification reserved = new VaccinationSlotEntitySpecification(
                new SearchCriteria("vaccination", "!", onlyReserved));
        Specification<VaccinationSlotEntity> mySpec = Specification.where(reserved);

        if(startDate == null) {
            startDate = Instant.now().toString();
        }
        VaccinationSlotEntitySpecification dateLowerBound = new VaccinationSlotEntitySpecification(
                new SearchCriteria("date", ">", startDate));
        mySpec = mySpec.and(dateLowerBound);
        if(endDate != null) {
            VaccinationSlotEntitySpecification dateUpperBound = new VaccinationSlotEntitySpecification(
                    new SearchCriteria("date", "<", endDate));
            mySpec = mySpec.and(dateUpperBound);
        } // SELECT id, date, vaccination FROM Vaccination v, VaccinationSlot vs WHERE v.vacciationSlot = vs
        return vaccinationSlotRepository.findAll(mySpec, thisPage);
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
