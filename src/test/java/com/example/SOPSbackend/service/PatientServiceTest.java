package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.AddressDto;
import com.example.SOPSbackend.dto.EditPatientAccountDto;
import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.AddressEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.repository.PatientRepository;
import com.example.SOPSbackend.repository.VaccinationRepository;
import com.example.SOPSbackend.repository.VaccinationSlotRepository;
import com.example.SOPSbackend.repository.VaccineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private VaccineRepository vaccineRepository;
    @Mock
    private VaccinationRepository vaccinationRepository;
    @Mock
    private VaccinationSlotRepository vaccinationSlotRepository;

    PatientService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PatientService(patientRepository, passwordEncoder,
                vaccineRepository, vaccinationSlotRepository, vaccinationRepository);
    }

    @Nested
    public class register {

        private NewPatientRegistrationDto newPatient;
        @Captor
        private ArgumentCaptor<PatientEntity> patientArgumentCaptor;

        @BeforeEach
        public void setUp() {
            //given
            newPatient = new NewPatientRegistrationDto(
                    "patientFirstName3",
                    "patientLastName3",
                    "93122659551",
                    "patient3@email.com",
                    "patientPassword3",
                    new AddressDto(
                            "city3",
                            "00-003",
                            "street3",
                            "houseNumber3",
                            "localNumber3"
                    )
            );
        }

        @Nested
        public class whenValidDataIsPassed {

            private PatientEntity savedPatient;
            private PatientEntity result;
            private String encodedPassword = "encodedPassword";

            @BeforeEach
            public void setUp() throws UserAlreadyExistException {
                //given
                //TODO: zrobić test integracyjny sprawdzający czy zostało zwrócone zakodowane hasło
                savedPatient = new PatientEntity(
                        newPatient.getFirstName(), newPatient.getLastName(), newPatient.getEmail(),
                        newPatient.getPassword(), newPatient.getPesel(),
                        new AddressEntity()
                );
                Mockito.when(patientRepository.save(Mockito.any(PatientEntity.class))).thenReturn(
                        savedPatient
                );
                Mockito.when(passwordEncoder.encode("patientPassword3")).thenReturn(encodedPassword);

                //when
                result = underTest.register(newPatient);
            }

            @Test
            public void shouldSaveNewPatientWithDataPassedInParameterAndEncodedPassword() {
                //then
                Mockito.verify(patientRepository).save(patientArgumentCaptor.capture());

                assertThat(patientArgumentCaptor.getValue().getFirstName()).isEqualTo(newPatient.getFirstName());
                assertThat(patientArgumentCaptor.getValue().getLastName()).isEqualTo(newPatient.getLastName());
                assertThat(patientArgumentCaptor.getValue().getPesel()).isEqualTo(newPatient.getPesel());
                assertThat(patientArgumentCaptor.getValue().getEmail()).isEqualTo(newPatient.getEmail());
                assertThat(patientArgumentCaptor.getValue().getPassword()).isEqualTo(encodedPassword);
                assertThat(patientArgumentCaptor.getValue().getAddress().getCity())
                        .isEqualTo(newPatient.getAddress().getCity());
                assertThat(patientArgumentCaptor.getValue().getAddress().getHouseNumber())
                        .isEqualTo(newPatient.getAddress().getHouseNumber());
                assertThat(patientArgumentCaptor.getValue().getAddress().getLocalNumber())
                        .isEqualTo(newPatient.getAddress().getLocalNumber());
                assertThat(patientArgumentCaptor.getValue().getAddress().getStreet())
                        .isEqualTo(newPatient.getAddress().getStreet());
                assertThat(patientArgumentCaptor.getValue().getAddress().getZipCode())
                        .isEqualTo(newPatient.getAddress().getZipCode());
            }

            @Test
            public void shouldReturnSavedData() {
                assertThat(result).isEqualTo(savedPatient);
            }

        }

        @Test
        public void whenExistingPeselIsPassed_shouldThrowUserAlreadyExistExceptionWithProperMessage() {
            Mockito.when(patientRepository.findByPesel(newPatient.getPesel()))
                    .thenReturn(Optional.of(new PatientEntity()));

            //when
            assertThatThrownBy(() -> underTest.register(newPatient))
                    .isInstanceOf(UserAlreadyExistException.class)
                    .hasMessage("User already exists for this pesel");
        }

        @Test
        public void whenExistingEmailIsPassed_shouldThrowUserAlreadyExistExceptionWithProperMessage() {
            Mockito.when(patientRepository.findByEmailIgnoreCase(newPatient.getEmail()))
                    .thenReturn(Optional.of(new PatientEntity()));

            //when
            assertThatThrownBy(() -> underTest.register(newPatient))
                    .isInstanceOf(UserAlreadyExistException.class)
                    .hasMessage("User already exists for this email");
        }
    }



    @Test
    public void editAccount_ShouldUpdateAndSavePatient() {
        PatientEntity patient = Mockito.mock(PatientEntity.class);
        EditPatientAccountDto editPatientAccountDto = new EditPatientAccountDto();
        Mockito.when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        Mockito.when(patientRepository.save(patient)).thenReturn(patient);

        underTest.editAccount(patient, editPatientAccountDto);

        Mockito.verify(patientRepository).findById(patient.getId());
        Mockito.verify(patientRepository).save(patient);
        Mockito.verify(patient).update(editPatientAccountDto, passwordEncoder);
    }


}