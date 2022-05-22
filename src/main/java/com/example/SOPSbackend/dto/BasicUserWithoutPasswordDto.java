package com.example.SOPSbackend.dto;

import com.example.SOPSbackend.model.BasicUserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasicUserWithoutPasswordDto {
    private String id;
    private String firstName, lastName, email;

    public BasicUserWithoutPasswordDto(BasicUserEntity userEntity) {
        this.id = String.valueOf(userEntity.getId());
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.email = userEntity.getEmail();
    }
}
