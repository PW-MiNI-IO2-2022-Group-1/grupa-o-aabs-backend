package com.example.SOPSbackend.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class NewPatientRegistrationDto {
    // TODO: 31/03/2022 Filip - zmniejszyc zakres widocznosci pol - jezeli to samo jest mozliwe dla zmian tomka - zrobic.
    @NotNull String firstName, lastName, pesel, email, password;
    @NotNull Address address;

    public static class Address {
        @NotNull @Getter String city, zipCode, street, houseNumber, localNumber;
    }
}

