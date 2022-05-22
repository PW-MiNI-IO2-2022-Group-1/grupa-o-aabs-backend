package com.example.SOPSbackend.repository;

import com.example.SOPSbackend.model.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<VaccineEntity, Long> {
    public List<VaccineEntity> findByDiseaseIn(Collection<String> diseases);
}
