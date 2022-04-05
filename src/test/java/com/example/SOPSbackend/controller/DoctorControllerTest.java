package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.security.BasicUserDetails;
import com.example.SOPSbackend.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class DoctorControllerTest {
    private MockMvc mockMvc;
    private DoctorController controller;
    private DoctorService doctorService;
    private DoctorEntity doctor;

    @BeforeEach
    public void setUp() {
        doctor = Mockito.mock(DoctorEntity.class);
        doctorService = Mockito.mock(DoctorService.class);
        controller = new DoctorController(doctorService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new FakeAuthArgumentResolver(doctor))
                .build();
    }

    private void setUpSecurityContext() {
        doctor = Mockito.mock(DoctorEntity.class);
        BasicUserDetails user = new BasicUserDetails(doctor);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);

        SecurityContext context = Mockito.mock(SecurityContext.class);
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);
    }

    private ResultActions performPostOnVaccinationSlots(String data) throws Exception {
        return mockMvc.perform(post("/doctor/vaccination-slots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data));
    }

    @Test
    public void controller_shouldInitialize() {
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
        Mockito.verify(doctorService).addVaccinationSlot(any(), any());
    }
}