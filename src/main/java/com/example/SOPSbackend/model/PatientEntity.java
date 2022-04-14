package com.example.SOPSbackend.model;

import com.example.SOPSbackend.dto.AddressDto;
import com.example.SOPSbackend.dto.EditPatientAccountDto;
import com.example.SOPSbackend.dto.EditPatientDto;
import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.security.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientEntity extends BasicUserEntity {
    @Column(nullable = false)
    private String pesel;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AddressEntity address;

    public PatientEntity(NewPatientRegistrationDto registration, PasswordEncoder passwordEncoder) {
        super(registration.getFirstName(),
                registration.getLastName(),
                registration.getEmail(),
                passwordEncoder.encode(registration.getPassword()));
        this.pesel = registration.getPesel();
        this.address = new AddressEntity(registration.getAddress());
    }

    @Override
    public Role getRole() {
        return Role.PATIENT;
    }

    public void update(EditPatientAccountDto data, PasswordEncoder passwordEncoder) { // TODO: these methods should be in the "EditPatientAccountDto" and "EditPatientDto" classes (because those are the classes resposnsible for "editing" and updating the Patient. Similar solution should be implemented for doctor. It might be cleaner to decide on one name: either "update" or "edit" a mix of both is confusing (endpoints are called "edit" but database operations are "updates" - but we can stick to one).
        update(data);
        password = passwordEncoder.encode(data.getPassword());
    }

    public PatientEntity update(EditPatientDto data) {
        firstName = data.getFirstName();
        lastName = data.getLastName();

        AddressDto newAddress = data.getAddress();
        address.setCity(newAddress.getCity());
        address.setStreet(newAddress.getStreet());
        address.setZipCode(newAddress.getZipCode());
        address.setHouseNumber(newAddress.getHouseNumber());
        address.setLocalNumber(newAddress.getLocalNumber());

        return this;
    }
}
