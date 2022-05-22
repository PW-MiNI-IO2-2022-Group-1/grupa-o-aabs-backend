package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.DoctorEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Everyone is welcome to use this data for their tests. Please add more data and correct flawed data (leave a comment when you
 * change stuff).
 */
public class USE_ME_TestDataForMocks {
    public static final List<DoctorEntity> DOCTOR_ENTITIES = generateTestDoctorEntities();

    private static List<DoctorEntity> generateTestDoctorEntities() {
        List<DoctorEntity> ret = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            ret.add(new DoctorEntity("TestName" + i,
                    "TestSurename" + i,
                    "test" + i + "@test.com",
                    "$2a$10$eseXwT7.FyU3RshBR2q0eeRiagaEWZ9jOn7qTepTcIjpWYg7Ji7Ze" + i));
        }
        return ret;
    }
}
