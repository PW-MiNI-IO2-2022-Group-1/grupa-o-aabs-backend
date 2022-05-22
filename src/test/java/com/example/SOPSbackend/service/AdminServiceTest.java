package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.EditDoctorDto;
import com.example.SOPSbackend.dto.NewDoctorDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.repository.DoctorRepository;
import com.example.SOPSbackend.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    AdminService underTest;
    @Mock
    DoctorRepository doctorRepository;
    @Mock
    PatientRepository patientRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        underTest = new AdminService(doctorRepository, patientRepository, passwordEncoder);
    }

    @Captor
    ArgumentCaptor<Pageable> pageableArgumentCaptor;

    @Test
    void getAllDoctors() {
        int pageNumber = 3;
        DoctorEntity doctor = new DoctorEntity();
        doctor.setId(7l);
        Page<DoctorEntity> returnedPage = new PageImpl<>(Arrays.asList(doctor));
        Mockito.when(doctorRepository.findAll(ArgumentMatchers.any(Pageable.class)))
                .thenReturn(returnedPage);

        Page<DoctorEntity> result = underTest.getAllDoctors(pageNumber);

        assertThat(result).isEqualTo(returnedPage);
        Mockito.verify(doctorRepository).findAll(pageableArgumentCaptor.capture());
        assertThat(pageableArgumentCaptor.getValue().getPageNumber()).isEqualTo(pageNumber);
    }

    @Test
    void getAllPatients() {
        int pageNumber = 3;
        PatientEntity patient = new PatientEntity();
        patient.setId(7l);
        Page<PatientEntity> returnedPage = new PageImpl<>(Arrays.asList(patient));
        Mockito.when(patientRepository.findAll(ArgumentMatchers.any(Pageable.class)))
                .thenReturn(returnedPage);

        Page<PatientEntity> result = underTest.getAllPatients(pageNumber);

        assertThat(result).isEqualTo(returnedPage);
        Mockito.verify(patientRepository).findAll(pageableArgumentCaptor.capture());
        assertThat(pageableArgumentCaptor.getValue().getPageNumber()).isEqualTo(pageNumber);
    }

    @Nested
    class addDoctor {

        NewDoctorDto newDoctor;
        @Captor
        ArgumentCaptor<DoctorEntity> doctorArgumentCaptor;

        @BeforeEach
        void setUp() {
            newDoctor = new NewDoctorDto(
                    "firstName",
                    "lastName",
                    "doctor@email.com",
                    "password"
            );
        }

        @Test
        void whenValidDataIsPassed_shouldReturnSavedDoctor() throws UserAlreadyExistException {
            DoctorEntity returnedDoctor = new DoctorEntity();
            returnedDoctor.setId(9l);
            Mockito.when(doctorRepository.save(Mockito.any(DoctorEntity.class)))
                    .thenReturn(returnedDoctor);

            DoctorEntity result = underTest.addDoctor(newDoctor);

            assertThat(result).usingRecursiveComparison().isEqualTo(returnedDoctor);
        }

        @Test
        void whenValidDataIsPassed_shouldCallSaveWithDataPassedInParameterAndEncodedPassword() {
            String encodedPassword = "encodedPassword";
            Mockito.when(passwordEncoder.encode(newDoctor.getPassword()))
                    .thenReturn(encodedPassword);

            assertDoesNotThrow(() -> underTest.addDoctor(newDoctor));

            Mockito.verify(doctorRepository).save(doctorArgumentCaptor.capture());
            assertThat(doctorArgumentCaptor.getValue().getFirstName()).isEqualTo(newDoctor.getFirstName());
            assertThat(doctorArgumentCaptor.getValue().getLastName()).isEqualTo(newDoctor.getLastName());
            assertThat(doctorArgumentCaptor.getValue().getEmail()).isEqualTo(newDoctor.getEmail());
            assertThat(doctorArgumentCaptor.getValue().getPassword()).isEqualTo(encodedPassword);

        }

        @Test
        void whenExistingEmailIsPassed_shouldThrow() {
            Mockito.when(doctorRepository.findByEmailIgnoreCase(newDoctor.getEmail()))
                    .thenReturn(Optional.of(new DoctorEntity()));

            assertThatThrownBy(() -> underTest.addDoctor(newDoctor))
                    .isInstanceOf(UserAlreadyExistException.class)
                    .hasMessage("User already exists for this email");
        }
    }

    @Test
    void updateDoctor_whenValidDataIsPassed_shouldReturnUpdatedDoctor() {
        String id = "5";
        EditDoctorDto editDoctor = new EditDoctorDto(
                "firstName",
                "lastName",
                "doctor@email.com"
        );
        DoctorEntity returnedDoctor = new DoctorEntity();
        returnedDoctor.setId(9l);
        Mockito.when(doctorRepository.getById(Long.valueOf(id)))
                .thenReturn(returnedDoctor);

        DoctorEntity result = underTest.updateDoctor(id, editDoctor);

        assertThat(result).isEqualTo(returnedDoctor);
        assertThat(result.getFirstName()).isEqualTo(editDoctor.getFirstName());
        assertThat(result.getLastName()).isEqualTo(editDoctor.getLastName());
        assertThat(result.getEmail()).isEqualTo(editDoctor.getEmail());
    }

    @Test
    @Disabled //TODO
    void updatePatient() {
    }

    @Test
    @Disabled //TODO
    void getDoctor() {
    }

    @Test
    @Disabled //TODO
    void getPatient() {
    }

    @Test
    @Disabled //TODO
    void deleteDoctor() {
    }

    @Test
    @Disabled //TODO
    void deletePatient() {
    }
}