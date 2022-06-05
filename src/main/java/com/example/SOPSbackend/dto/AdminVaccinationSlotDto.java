package com.example.SOPSbackend.dto;

import com.example.SOPSbackend.model.VaccinationSlotEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AdminVaccinationSlotDto {
    private LocalDateTime date;
    private Long id;

    public AdminVaccinationSlotDto(VaccinationSlotEntity vs) {
        date = vs.getDate();
        id = vs.getId();
    }
}
