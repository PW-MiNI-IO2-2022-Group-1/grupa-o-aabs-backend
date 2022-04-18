package com.example.SOPSbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPatientAccountDto extends EditPatientDto {
    @NotBlank(message = "Required field is empty")
    private String password;
}
