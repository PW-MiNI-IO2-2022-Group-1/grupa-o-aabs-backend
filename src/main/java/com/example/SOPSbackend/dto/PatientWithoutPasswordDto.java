package com.example.SOPSbackend.dto;

import com.example.SOPSbackend.model.AddressEntity;
import com.example.SOPSbackend.model.BasicUserEntity;
import com.example.SOPSbackend.model.PatientEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientWithoutPasswordDto extends BasicUserWithoutPasswordDto {
    private String pesel;
    private AddressEntity address;

    public PatientWithoutPasswordDto(PatientEntity patientEntity) {
        super(patientEntity);
        pesel = patientEntity.getPesel();
        address = patientEntity.getAddress();
    }
}
