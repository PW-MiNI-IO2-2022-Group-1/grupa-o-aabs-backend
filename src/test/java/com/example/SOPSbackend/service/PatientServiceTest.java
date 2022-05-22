package com.example.SOPSbackend.service;

import com.example.SOPSbackend.dto.AddressDto;
import com.example.SOPSbackend.dto.EditPatientAccountDto;
import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.exception.AlreadyReservedException;
import com.example.SOPSbackend.exception.UserAlreadyExistException;
import com.example.SOPSbackend.model.*;
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

import java.time.LocalDateTime;
import java.util.*;

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

    @Nested
    public class getVaccines {

        private List<String> vaccines;
        private List<VaccineEntity> result;
        private List<VaccineEntity> returnedValue;
        @Captor
        private ArgumentCaptor<List<String>> vaccineListArgumentCaptor;

        @BeforeEach
        void setUp() {
            vaccines = Arrays.asList("COVID-19", "COVID-21");
            returnedValue = Arrays.asList(new VaccineEntity("vaccineName", "COVID-19", 2));
            Mockito.when(vaccineRepository.findByDiseaseIn(vaccines)).thenReturn(
                    returnedValue
            );

            result = underTest.getVaccines(vaccines);
        }

        @Test
        public void shouldCallFindByDiseaseInWithDataPassedInParameter() {
            Mockito.verify(vaccineRepository).findByDiseaseIn(vaccineListArgumentCaptor.capture());

            assertThat(vaccineListArgumentCaptor.getValue()).isEqualTo(vaccines);
        }

        @Test
        public void shouldReturnDataFromFindByDiseaseIn() {
            assertThat(result).isEqualTo(returnedValue);
        }
    }

    @Test
    public void getAvailableVaccinationSlots_shouldReturnDataFromFindAvailableSlots() {
        //given
        List<VaccinationSlotEntity> returnedValue = Arrays.asList(
                new VaccinationSlotEntity(new DoctorEntity(), LocalDateTime.now()));
        Mockito.when(vaccinationSlotRepository.findAvailableSlots()).thenReturn(returnedValue);

        //when
        List<VaccinationSlotEntity> result = underTest.getAvailableVaccinationSlots();

        //then
        Mockito.verify(vaccinationSlotRepository).findAvailableSlots();
        assertThat(result).isEqualTo(returnedValue);
    }

    @Nested
    public class reserveVaccinationSlot {

        @Captor
        private ArgumentCaptor<VaccinationEntity> vaccinationArgumentCaptor;
        private long vaccineId;
        private long vaccinationSlotId;
        private PatientEntity patient;
        private VaccineEntity vaccine;
        private VaccinationSlotEntity vaccinationSlot;

        @BeforeEach
        void setUp() {
            vaccineId = 3;
            vaccinationSlotId = 7;
            patient = new PatientEntity("firstName", null, null, null, null, null);
            vaccine = new VaccineEntity("vaccineName", "COVID-19", 1);
            vaccinationSlot = new VaccinationSlotEntity(new DoctorEntity(), LocalDateTime.now());

            Mockito.when(vaccineRepository.findById(vaccineId))
                    .thenReturn(Optional.of(vaccine));
            Mockito.when(vaccinationSlotRepository.findById(vaccinationSlotId))
                    .thenReturn(Optional.of(vaccinationSlot));
        }

        @Test
        public void whenValidDataIsPassed_shouldSaveNewVaccinationWithDataPassedInParameters() throws AlreadyReservedException {
            underTest.reserveVaccinationSlot(vaccineId, vaccinationSlotId, patient);

            Mockito.verify(vaccinationRepository).save(vaccinationArgumentCaptor.capture());
            assertThat(vaccinationArgumentCaptor.getValue().getPatient()).isEqualTo(patient);
            assertThat(vaccinationArgumentCaptor.getValue().getVaccine()).isEqualTo(vaccine);
            assertThat(vaccinationArgumentCaptor.getValue().getVaccinationSlot()).isEqualTo(vaccinationSlot);
            assertThat(vaccinationArgumentCaptor.getValue().getStatus()).isEqualTo("Planned");
        }

        @Test
        public void whenNotExistingVaccineIdIsPassed_shouldThrowNoSuchElementExceptionWithProperMessage() {
            Mockito.when(vaccineRepository.findById(vaccineId))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> underTest.reserveVaccinationSlot(vaccineId, vaccinationSlotId, patient))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage("Vaccine not found");
        }

        @Test
        public void whenNotExistingVaccinationSlotIdIsPassed_shouldThrowNoSuchElementExceptionWithProperMessage() {
            Mockito.when(vaccinationSlotRepository.findById(vaccinationSlotId))
                    .thenReturn(Optional.empty());

            assertThatThrownBy(() -> underTest.reserveVaccinationSlot(vaccineId, vaccinationSlotId, patient))
                    .isInstanceOf(NoSuchElementException.class)
                    .hasMessage("Vaccination slot not found");
        }

        @Test
        public void whenAlreadyReservedVaccinationSlotIsPassed_shouldThrowAlreadyReservedExceptionWithProperMessage() {
            Mockito.when(vaccinationRepository.findByVaccinationSlot(vaccinationSlot))
                    .thenReturn(new VaccinationEntity());

            assertThatThrownBy(() -> underTest.reserveVaccinationSlot(vaccineId, vaccinationSlotId, patient))
                    .isInstanceOf(AlreadyReservedException.class)
                    .hasMessage("Vaccination slot is already reserved");
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