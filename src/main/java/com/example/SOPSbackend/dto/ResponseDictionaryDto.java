package com.example.SOPSbackend.dto;

import com.example.SOPSbackend.model.VaccinationEntity;
import com.example.SOPSbackend.model.VaccinationSlotEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDictionaryDto {
    @NotNull
    private Long id;
    @NotNull
    private String date;
    private ResponseVaccinationDto vaccination;
    
    public ResponseDictionaryDto(VaccinationSlotEntity vaccinationSlotEntity, VaccinationEntity vaccination)
    {
        this.id = vaccinationSlotEntity.getId();
        this.date = vaccinationSlotEntity.getDate().format(DateTimeFormatter.ISO_DATE_TIME);
        this.vaccination = vaccination == null? null : new ResponseVaccinationDto(vaccination);
    }

}
