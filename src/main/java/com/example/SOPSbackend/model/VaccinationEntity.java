package com.example.SOPSbackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VaccinationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(targetEntity = VaccineEntity.class, optional = false)
    private VaccineEntity vaccine;

    @JoinColumn(nullable = false)
    @OneToOne(targetEntity = VaccinationSlotEntity.class, optional = false)
    private VaccinationSlotEntity vaccinationSlot;

    @JoinColumn(nullable = false)
    @ManyToOne(targetEntity = PatientEntity.class)
    private PatientEntity patient;

    @Column(nullable = false)
    private int status;
}
