package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.PatientEntity;
import com.example.SOPSbackend.security.BasicUserDetails;
import com.example.SOPSbackend.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@MockBean(DoctorService.class)
class DoctorControllerTest {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorController controller;

    @Autowired
    private MockMvc mockMvc;

    private DoctorEntity doctor;

    private void setUpSecurityContext() {
        doctor = Mockito.mock(DoctorEntity.class);
        BasicUserDetails user = new BasicUserDetails(doctor);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);

        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);
    }

    private ResultActions performPostOnVaccinationSlots(String data) throws Exception {
        PatientEntity patient = new PatientEntity();
        BasicUserDetails user = new BasicUserDetails(patient);
        return mockMvc.perform(post("/doctor/vaccination-slots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data));
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void createNewVaccinationSlot_shouldReturnErrorResponse_whenDateIsInvalid() throws Exception {
        performPostOnVaccinationSlots("{\"date\": \"data=february\"}").andExpectAll(
            status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()),
            jsonPath("$.success").value(false),
            jsonPath("$.data.date").exists()
        );
    }

    @Test
    public void createNewVaccinationSlot_shouldReturnErrorResponse_whenRequestBodyIsMalformed() throws Exception {
        performPostOnVaccinationSlots("json to nawet nie jest on").andExpectAll(
            status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()),
            jsonPath("$.success").value(false)
        );
    }

    @Test
    public void createNewVaccinationSlot_shouldReturnCorrectReponse_whenRequestIsAcceptable()
            throws Exception {
        var dateStr = "2133-08-24T14:15:22Z";

        setUpSecurityContext();

        performPostOnVaccinationSlots("{\"date\": \"" + dateStr + "\"}").andExpectAll(
            status().isOk(),
            jsonPath("$.success").value(true)
        );

        var date = Instant.parse(dateStr);
        Mockito.verify(doctorService).addVaccinationSlot(doctor, date);
    }
}