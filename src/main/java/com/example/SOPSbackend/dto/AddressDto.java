package com.example.SOPSbackend.dto;

import com.example.SOPSbackend.model.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @NotBlank(message = "Required field is empty")
    private String city;

    @NotBlank(message = "Required field is empty")
    private String zipCode;

    @NotBlank(message = "Required field is empty")
    private String street;

    @NotBlank(message = "Required field is empty")
    private String houseNumber;

    private String localNumber;

    public AddressDto(AddressEntity addressEntity) {
        city = addressEntity.getCity();
        zipCode = addressEntity.getZipCode();
        street = addressEntity.getStreet();
        houseNumber = addressEntity.getHouseNumber();
        localNumber = addressEntity.getLocalNumber();
    }
}
