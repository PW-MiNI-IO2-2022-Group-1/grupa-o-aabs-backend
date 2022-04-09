package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.AddressEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class PatientControllerTest {
    private MockMvc mockMvc;
    private PatientController controller;
    private PatientService patientService;
    private PatientEntity patient;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        patient = Mockito.mock(PatientEntity.class);
        patientService = Mockito.mock(PatientService.class);
        controller = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
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
        assertThat(controller).isNotNull();
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
    public void editAccount_ShouldReturnErrorResponse_WhenRequestBodyIsInvalid() throws Exception {
        HashMap<String, Object> bodyDict = new HashMap<>(getValidRequestBody());
        bodyDict.remove("firstName");
        String body = objectMapper.writeValueAsString(bodyDict);
        performEditAccountPut(body).andExpectAll(
            status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()),
            jsonPath("$.success").value(false),
            jsonPath("$.data.firstName").value("Required field is empty")
        );
    }
}