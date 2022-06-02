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
            "GROUP BY ve.name, ve.disease " +
            "HAVING v.status = 'Completed' AND " +
            "(:sDate IS NULL OR v.vaccinationSlot.date >=  :sDate) AND " +
            "(:eDate IS NULL OR v.vaccinationSlot.date <=  :eDate)")
    List<Object[]> getReportData(
            @Param("startDate")LocalDateTime sDate,
            @Param("endDate") LocalDateTime eDate);
}
