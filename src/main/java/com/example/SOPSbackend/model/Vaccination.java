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
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(targetEntity = Vaccine.class, optional = false)
    private Vaccination vaccine;

    @JoinColumn(nullable = false)
    @ManyToOne(targetEntity = VaccinationSlot.class, optional = false)
    private VaccinationSlot vaccinationSlot;

    @JoinColumn(nullable = false)
    @ManyToOne(targetEntity = Patient.class)
    private Patient patient;

    @Column(nullable = false)
    private int status;
}