package com.example.SOPSbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReportDiseaseDto {
    String name;
    Integer count;
    List<ReportVaccineDto> vaccines;
}
