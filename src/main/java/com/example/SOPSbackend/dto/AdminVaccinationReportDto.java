package com.example.SOPSbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminVaccinationReportDto {
    List<ReportDiseaseDto> diseases;
}
