package com.example.SOPSbackend.model;

import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String houseNumber;

    @Column()
    private String localNumber;

    public AddressEntity(NewPatientRegistrationDto.Address address) {
        city = address.getCity();
        zipCode = address.getZipCode();
        street = address.getStreet();
        houseNumber = address.getHouseNumber();
        localNumber = address.getLocalNumber();
    }
}

