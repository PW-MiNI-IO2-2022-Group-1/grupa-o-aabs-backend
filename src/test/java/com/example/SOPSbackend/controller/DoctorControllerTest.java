package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    private ResultActions performPostOnVaccinationSlots(String data) throws Exception {
        return mockMvc.perform(post("/doctor/vaccination-slots")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(data));
    }

    @Test
    public void createNewVaccinationSlot_shouldReturnErrorResponse_whenDateIsInvalid() throws Exception {
        performPostOnVaccinationSlots("{\"date\": \"data=february\"}").andExpectAll(
            status().is(422),
            jsonPath("$.success").value(false),
            jsonPath("$.data.date").exists()
        );
    }


    @Test
    public void createNewVaccinationSlot_shouldReturnErrorResponse_whenRequestBodyIsMalformed() throws Exception {
        performPostOnVaccinationSlots("json to nawet nie jest on").andExpectAll(
            status().is(422),
            jsonPath("$.success").value(false)
        );
    }

    @Test
    public void createNewVaccinationSlot_shouldReturnCorrectReponse_whenRequestIsAcceptable()
            throws Exception {
        var dateStr = "2133-08-24T14:15:22Z";

        performPostOnVaccinationSlots("{\"date\": \"" + dateStr + "\"}").andExpectAll(
            status().isOk(),
            jsonPath("$.success").value(true)
        );

        var date = Instant.parse(dateStr);
        Mockito.verify(doctorService).addVaccinationSlot(null, date);
    }
}