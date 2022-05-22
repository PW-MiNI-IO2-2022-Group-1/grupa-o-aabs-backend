package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VaccinationSlotRepository extends
        JpaRepository<VaccinationSlotEntity, Long>,
        JpaSpecificationExecutor<VaccinationSlotEntity> {
    long deleteByIdAndDoctor(
            @Param("id") Long id,
            @Param("doctor")DoctorEntity doctor
            );
    List<VaccinationSlotEntity> findResultsByDateAndDoctor(
            @Param("date") LocalDateTime date,
            @Param("doctor")DoctorEntity doctor
    );

    @Query(value = "SELECT vs FROM VaccinationSlotEntity vs LEFT JOIN VaccinationEntity v ON v.vaccinationSlot = vs " +
            "WHERE vs.doctor = :doctor AND " +
            "(:startDate IS NULL OR vs.date >= :startDate) AND " +
            "(:endDate IS NULL OR vs.date <= :endDate) " +
            "AND v.id IS NULL",
            countQuery = "SELECT COUNT(vs) FROM VaccinationSlotEntity vs LEFT JOIN VaccinationEntity v ON v.vaccinationSlot = vs " +
            "WHERE vs.doctor = :doctor AND " +
            "(:startDate IS NULL OR vs.date >= :startDate) AND " +
            "(:endDate IS NULL OR vs.date <= :endDate) " +
            "AND v.id IS NULL")
    Page<VaccinationSlotEntity> findNotReserved(
            @Param("doctor") DoctorEntity doctor,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
    @Query(value = "SELECT vs FROM VaccinationSlotEntity vs INNER JOIN VaccinationEntity v ON v.vaccinationSlot = vs " +
            "WHERE vs.doctor = :doctor AND " +
            "(:startDate IS NULL OR vs.date >= :startDate) AND " +
            "(:endDate IS NULL OR vs.date <= :endDate) ",
            countQuery = "SELECT COUNT(vs) FROM VaccinationSlotEntity vs INNER JOIN VaccinationEntity v ON v.vaccinationSlot = vs " +
                    "WHERE vs.doctor = :doctor AND " +
                    "(:startDate IS NULL OR vs.date >= :startDate) AND " +
                    "(:endDate IS NULL OR vs.date <= :endDate) ")
    Page<VaccinationSlotEntity> findReserved(
            @Param("doctor") DoctorEntity doctor,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
    @Query("select s from VaccinationSlotEntity s " +
            "left join VaccinationEntity v on s.id = v.vaccinationSlot.id " +
            "WHERE v.vaccinationSlot is null")
    List<VaccinationSlotEntity> findAvailableSlots();
}
