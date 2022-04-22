package com.example.SOPSbackend.response;

import com.example.SOPSbackend.model.BasicUserEntity;
import com.example.SOPSbackend.dto.BasicUserWithoutPasswordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BasicUserOkResponse extends ResponseEntity<Object> {
    public BasicUserOkResponse(BasicUserEntity userEntity) {
        super(new BasicUserWithoutPasswordDto(userEntity), HttpStatus.OK);
    }
}
