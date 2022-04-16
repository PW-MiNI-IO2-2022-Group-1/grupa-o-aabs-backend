package com.example.SOPSbackend.repository.filters;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class CustomVaccinationSlotSpecifications {

    public static Specification<VaccinationSlotEntity> findByDoctor(DoctorEntity doctor) {
        return (root, query, cb) -> cb.equal(root.get("doctor"), doctor);
    }
    public static Specification<VaccinationSlotEntity> findAfter(LocalDateTime startDate) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("date"), startDate);
    }
    public static Specification<VaccinationSlotEntity> findBefore(LocalDateTime endDate) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("date"), endDate);
    }
}
