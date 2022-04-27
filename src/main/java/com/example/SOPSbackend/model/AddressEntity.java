package com.example.SOPSbackend.model;

import com.example.SOPSbackend.dto.AddressDto;
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

    public AddressEntity(AddressDto address) {
        city = address.getCity();
        zipCode = address.getZipCode();
        street = address.getStreet();
        houseNumber = address.getHouseNumber();
        localNumber = address.getLocalNumber();
    }

    public AddressEntity(String city, String zipCode, String street, String houseNumber, String localNumber) {
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.localNumber = localNumber;
    }

    public void update(AddressDto newAddress) {
        if(newAddress.getCity() != null)  city = newAddress.getCity();
        if(newAddress.getStreet() != null) street = newAddress.getStreet();
        if(newAddress.getZipCode() != null) zipCode = newAddress.getZipCode();
        if(newAddress.getHouseNumber() != null) houseNumber = newAddress.getHouseNumber();
        if(newAddress.getLocalNumber() != null) localNumber = newAddress.getLocalNumber();
    }
}

