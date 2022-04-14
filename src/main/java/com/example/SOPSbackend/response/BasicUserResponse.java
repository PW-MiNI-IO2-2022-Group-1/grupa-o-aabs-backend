package com.example.SOPSbackend.response;

import com.example.SOPSbackend.model.BasicUserEntity;
import lombok.Getter;

@Getter
public class BasicUserResponse {
    private final long id;
    private final String firstName, lastName, email;

    public BasicUserResponse(BasicUserEntity userEntity) {
        this.id = userEntity.getId();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.email = userEntity.getEmail();
    }
}
