package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long>, JpaSpecificationExecutor<VaccinationEntity> {

    @Query("SELECT v FROM VaccinationEntity v WHERE v.vaccinationSlot IN :vsList")
    List<VaccinationEntity> findMatchingVaccinations(
            @Param("vsList") List<VaccinationSlotEntity> vsList);

    VaccinationEntity findByVaccinationSlot(VaccinationSlotEntity vaccinationSlotEntity);

    Page<VaccinationEntity> findByPatient(PatientEntity patientEntity, Pageable pageable);

    @Query("SELECT ve.disease, ve.name, COUNT(ve) FROM VaccinationEntity v INNER JOIN VaccineEntity ve ON v.vaccine = ve " +
            "WHERE v.status = 'Completed' AND " +
            "(v.vaccinationSlot.date >=  :startDate) " +
            "AND (v.vaccinationSlot.date <=  :endDate)" +
            "GROUP BY ve.name, ve.disease ")
    List<Object[]> getReportData(
            @Param("startDate")LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT v FROM VaccinationEntity v WHERE " +
            "(:patientId IS NULL OR v.patient.id = :patientId) AND " +
            "(:doctorId IS NULL OR v.vaccinationSlot.doctor.id = :doctorId) AND " +
            "(:disease IS NULL OR v.vaccine.disease = :disease)",
            countQuery = "SELECT COUNT(v) FROM VaccinationEntity v WHERE " +
                    "(:patientId IS NULL OR v.patient.id = :patientId) AND " +
                    "(:doctorId IS NULL OR v.vaccinationSlot.doctor.id = :doctorId) AND " +
                    "(:disease IS NULL OR v.vaccine.disease = :disease)")
    Page<VaccinationEntity> getVaccinationsByParams(
            @Param("disease") String disease,
            @Param("doctorId") Long doctorId,
            @Param("patientId") Long patientId,
            Pageable pageable
    );
    @Query("SELECT COUNT(v) FROM VaccinationEntity v WHERE v.vaccine.disease = :disease")
    Long existsByDiseaseName(@Param("disease") String disease);
}
