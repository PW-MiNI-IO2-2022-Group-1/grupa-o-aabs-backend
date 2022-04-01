package com.example.SOPSbackend.service;

import com.example.SOPSbackend.exception.InternalValidationException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

@Service
public class DoctorService {
    private static final int NEW_SLOT_MIN_TIME_DIFF = 15;

    private final VaccinationSlotRepository vaccinationSlotRepository;

    public DoctorService(DoctorRepository doctorRepository, VaccinationSlotRepository vaccinationSlotRepository) {
        this.vaccinationSlotRepository = vaccinationSlotRepository;
    }

    public void addVaccinationSlot(DoctorEntity doctor, Instant date) {
        LocalDateTime transformedDate = transformVaccinationSlotDate(date);

        if(!isVaccinationSlotDateValid(transformedDate))
            throw new InternalValidationException(new HashMap<>() {{
                put("date", "Invalid date value");
            }});

        VaccinationSlotEntity newSlot = new VaccinationSlotEntity();
        newSlot.setDoctor(doctor);
        newSlot.setDate(transformedDate);
        vaccinationSlotRepository.save(newSlot);
    }

    private LocalDateTime transformVaccinationSlotDate(Instant date) {
        return LocalDateTime.ofInstant(date.truncatedTo(ChronoUnit.MINUTES), ZoneId.of("UTC"));
    }

    private boolean isVaccinationSlotDateValid(LocalDateTime slotDate) {
        long minuteDifference = ChronoUnit.MINUTES.between(LocalDateTime.now(ZoneId.of("UTC")), slotDate);
        return (minuteDifference >= NEW_SLOT_MIN_TIME_DIFF);
    }
}
