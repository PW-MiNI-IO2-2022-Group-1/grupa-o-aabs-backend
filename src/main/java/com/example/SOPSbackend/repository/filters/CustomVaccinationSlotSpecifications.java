package com.example.SOPSbackend.repository.filters;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import com.example.SOPSbackend.model.converter.VaccinationSlotStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;

public class CustomVaccinationSlotSpecifications {

    public static Specification<VaccinationSlotEntity> findByDoctor(DoctorEntity doctor) {
        return (root, query, cb) -> cb.equal(root.get("doctor"), doctor);
    }
    public static Specification<VaccinationSlotEntity> findReservedVaccinationSlots() {
        return (root, query, cb) -> {
            Join<VaccinationSlotEntity, VaccinationEntity> vaccination = root.join("id", JoinType.INNER);
            vaccination.get("vaccinationSlot");
            return cb.equal(vaccination.get("vaccinationSlot"),root);
        };
    }
    public static Specification<VaccinationSlotEntity> findNotReservedVaccinationSlots() {
        return (root, query, cb) -> {
            Join<VaccinationSlotEntity, VaccinationEntity> vaccination = root.join("id", JoinType.LEFT);
            vaccination.get("vaccinationSlot");
            return cb.equal(vaccination.get("vaccinationSlot"),root);
        };
    }
    public static Specification<VaccinationSlotEntity> findAfter(LocalDateTime startDate) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("date"), startDate);
    }
    public static Specification<VaccinationSlotEntity> findBefore(LocalDateTime endDate) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("date"), endDate);
    }
}
