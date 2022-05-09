package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.dto.AddressDto;
import com.example.SOPSbackend.dto.NewPatientAfterRegistrationDto;
import com.example.SOPSbackend.dto.NewPatientRegistrationDto;
import com.example.SOPSbackend.dto.PatientWithoutPasswordDto;
import com.example.SOPSbackend.model.AddressEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.repository.PatientRepository;
import com.example.SOPSbackend.security.config.BasicSecurityConfig;
import com.example.SOPSbackend.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.internal.Objects;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private PatientController underTest;
    @Mock
    private PatientService patientService;
    private PatientEntity patient;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    BasicSecurityConfig basicSecurityConfig;

    @Nested
    class integrationTests {
        @Nested
        class register {
            private Map<String, Object> body;

            @BeforeEach
            void setUp() {
                body = new HashMap<>(Map.of(
                        "firstName", "firstName3",
                        "lastName", "lastName3",
                        "password", "password3",
                        "pesel", "003005039978",
                        "email", "patient3@email.com",
                        "address", Map.of(
                                "city", "city3",
                                "zipCode", "00-003",
                                "street", "street3",
                                "houseNumber", "houseNumber3",
                                "localNumber", "localNumber3"
                        )
                ));
            }

            @Test
            void whenPeselAlreadyExists_shouldResponseWithStatus409() throws Exception {
                body.put("pesel", "82110392536");
                mockMvc.perform(post("/patient/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body)))
                        .andDo(print())
                        .andExpect(status().is(409))
                        .andReturn();

            }

            @Test
            void whenEmailAlreadyExists_shouldResponseWithStatus409() throws Exception {
                body.put("email", "patient1@email.com");
                mockMvc.perform(post("/patient/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body)))
                        .andDo(print())
                        .andExpect(status().is(409))
                        .andReturn();

            }

            //TODO: validation tests

            @Nested
            @Transactional
            class whenAllDataIsValid {
                private NewPatientRegistrationDto newPatient;
                private MvcResult mvcResult;
                private PatientWithoutPasswordDto returnedPatient;

                @BeforeEach
                void setUp() throws Exception {
                    newPatient = objectMapper.readValue(
                            objectMapper.writeValueAsString(body),
                            NewPatientRegistrationDto.class
                    );

                    //when
                    mvcResult = mockMvc.perform(post("/patient/registration")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(body)))
                            .andDo(print())
                            .andReturn();
                    returnedPatient = objectMapper.readValue(
                            mvcResult.getResponse().getContentAsString(),
                            PatientWithoutPasswordDto.class
                    );
                }

                @Test
                void shouldResponseWithRegisteredPatient() {
                    assertThat(mvcResult.getResponse().getStatus()).isEqualTo(201);
                    assertThat(returnedPatient.getFirstName()).isEqualTo(newPatient.getFirstName());
                    assertThat(returnedPatient.getLastName()).isEqualTo(newPatient.getLastName());
                    assertThat(returnedPatient.getEmail()).isEqualTo(newPatient.getEmail());
                    assertThat(returnedPatient.getPesel()).isEqualTo(newPatient.getPesel());
                    assertThat(new AddressDto(returnedPatient.getAddress())).usingRecursiveComparison()
                            .isEqualTo(newPatient.getAddress());
                }

                @Test
                void shouldSaveNewPatientWithEncodedPassword() {
                    Optional<PatientEntity> savedPatient =
                            patientRepository.findById(Long.valueOf(returnedPatient.getId()));
                    assertThat(savedPatient.isPresent()).isTrue();
                    assertThat(new PatientWithoutPasswordDto(savedPatient.get())).usingRecursiveComparison()
                            .isEqualTo(returnedPatient);
                    assertThat(
                            basicSecurityConfig.passwordEncoder().matches(
                                    body.get("password").toString(),
                                    savedPatient.get().getPassword()
                            )
                    ).isTrue();
                }


            }
        }

        @Nested
        @Transactional
        @WithUserDetails(value = "patient1@email.com", userDetailsServiceBeanName = "patientUserService")
        class account {

            @Test
            void shouldResponseUpdatedPatientWithEncodedPassword() throws Exception {
                HashMap<String, Object> body = new HashMap<>(Map.of(
                        "firstName", "firstName3",
                        "lastName", "lastName3",
                        "password", "password3",
                        "address", Map.of(
                                "city", "city3",
                                "zipCode", "00-003",
                                "street", "street3",
                                "houseNumber", "houseNumber3",
                                "localNumber", "localNumber3"
                        )
                ));

                MvcResult mvcResult = mockMvc.perform(put("/patient/account")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(body)))
                        .andDo(print())
                        .andExpect(status().is(200))
                        .andReturn();

                PatientEntity user = patientRepository.findByEmailIgnoreCase("patient1@email.com").get();
                assertThat(objectMapper.readValue(
                        mvcResult.getResponse().getContentAsString(),
                        PatientWithoutPasswordDto.class
                )).usingRecursiveComparison().isEqualTo(new PatientWithoutPasswordDto(user));
                assertThat(user.getFirstName()).isEqualTo(body.get("firstName"));
                assertThat(user.getLastName()).isEqualTo(body.get("lastName"));
                assertThat(
                        basicSecurityConfig.passwordEncoder().matches(
                                body.get("password").toString(),
                                user.getPassword()
                        )
                ).isTrue();
            }
        }
    }

    @Nested
    class unitTests {

        @BeforeEach
        public void setUp() {
            underTest = new PatientController(patientService);
            patient = Mockito.mock(PatientEntity.class);
            mockMvc = MockMvcBuilders.standaloneSetup(underTest)
                    .setCustomArgumentResolvers(new FakeAuthArgumentResolver(patient))
                    .build();
        }

        private Map<String, Object> getValidRequestBody() {
            return Map.of(
                    "firstName", "test",
                    "lastName", "test",
                    "password", "test",
                    "address", Map.of(
                            "city", "test",
                            "zipCode", "12-134",
                            "street", "test",
                            "houseNumber", "12"
                    )
            );
        }

        private PatientEntity getValidPatient() {
            PatientEntity patient = new PatientEntity();
            patient.setFirstName("John");
            patient.setLastName("Doe");
            patient.setPesel("12345678910");
            patient.setEmail("john.doe@gmail.com");
            patient.setPassword("password");
            AddressEntity address = new AddressEntity();
            patient.setAddress(address);
            address.setCity("New York");
            address.setZipCode("12-123");
            address.setStreet("23rd");
            address.setHouseNumber("32");
            return patient;
        }

        private ResultActions performEditAccountPut(String body) throws Exception {
            return mockMvc.perform(put("/patient/account")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body));
        }

        @Test
        public void controller_shouldInitialize() {
            assertThat(underTest).isNotNull();
        }

        @Test
        public void editAccount_ShouldReturnOkResponse_WhenRequestBodyIsValid() throws Exception {
            PatientEntity mockPatient = getValidPatient();
            Mockito.when(patientService.editAccount(eq(patient), any())).thenReturn(mockPatient);
            String body = objectMapper.writeValueAsString(getValidRequestBody());
            performEditAccountPut(body).andExpectAll(
                    status().isOk(),
                    (MvcResult result) -> {
                        String correctBody = objectMapper.writeValueAsString(mockPatient);
                        assertEquals(correctBody, result.getResponse().getContentAsString());
                    }
            );
        }

        @Test
        public void editAccount_ShouldReturnOkResponse_WhenRequestBodyIsValidButIncomplete() throws Exception {
            PatientEntity mockPatient = getValidPatient();
            Mockito.when(patientService.editAccount(eq(patient), any())).thenReturn(mockPatient);
            HashMap<String, Object> bodyDict = new HashMap<>(getValidRequestBody());
            bodyDict.remove("firstName");
            bodyDict.remove("password");
            bodyDict.remove("address");
            String body = objectMapper.writeValueAsString(bodyDict);
            performEditAccountPut(body).andExpectAll(
                    status().isOk(),
                    (MvcResult result) -> {
                        String correctBody = objectMapper.writeValueAsString(mockPatient);
                        assertEquals(correctBody, result.getResponse().getContentAsString());
                    }
            );
        }
    }
}