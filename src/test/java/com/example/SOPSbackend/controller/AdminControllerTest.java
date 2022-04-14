package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.AdminEntity;
import com.example.SOPSbackend.security.BasicUserDetails;
import com.example.SOPSbackend.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.util.function.Function;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class AdminControllerTest {
    private MockMvc mockMvc;
    private AdminController controller;
    private AdminService adminService;
    private AdminEntity admin;

    @BeforeEach
    public void setUp() {
        admin = Mockito.mock(AdminEntity.class);
        adminService = Mockito.mock(AdminService.class);
        when(adminService.getAllDoctors(0)).thenReturn(new PageImpl<>(USE_ME_TestDataForMocks.DOCTOR_ENTITIES));
        when(adminService.addDoctor(any())).thenReturn(USE_ME_TestDataForMocks.DOCTOR_ENTITIES.get(0));
        controller = new AdminController(adminService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new FakeAuthArgumentResolver(admin))
                .build();
    }

    private void setUpSecurityContext() {
        admin = Mockito.mock(AdminEntity.class);
        BasicUserDetails user = new BasicUserDetails(admin);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);

        SecurityContext context = Mockito.mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);
    }

    private final Function<URI, MockHttpServletRequestBuilder> GET = MockMvcRequestBuilders::get;
    private final Function<URI, MockHttpServletRequestBuilder> POST = MockMvcRequestBuilders::post;
    private final Function<URI, MockHttpServletRequestBuilder> PUT = MockMvcRequestBuilders::put;
    private final Function<URI, MockHttpServletRequestBuilder> DELETE = MockMvcRequestBuilders::delete;

    private ResultActions performOnAdmin(Function<URI, MockHttpServletRequestBuilder> method,
                                         String endpoint,
                                         String data) throws Exception {
        return mockMvc.perform(method.apply(URI.create("/admin/" + endpoint))
                .contentType(MediaType.APPLICATION_JSON)
                .content(data));
    }

    private ResultActions performOnDoctors(Function<URI, MockHttpServletRequestBuilder> method,
                                           String endpoint,
                                           String data) throws Exception {
        return performOnAdmin(method, "doctors/" + endpoint, data);
    }

    @Test
    public void controller_shouldInitialize() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void showDoctors_shouldReturnOkResponse_always() throws Exception {
        setUpSecurityContext();

        performOnDoctors(GET, "", "").andExpectAll(
                status().is(HttpStatus.OK.value())
        );
    }

    @Test
    public void createDoctor_shouldReturnOkResponse_whenDoctorDoesntAlreadyExists() throws Exception {
        setUpSecurityContext();

        performOnDoctors(POST, "", "{\n" +
                "  \"firstName\": \"Dr John\",\n" +
                "  \"lastName\": \"Doe\",\n" +
                "  \"email\": \"john.2doe2@doctor.com\",\n" +
                "  \"password\": \"123\"\n" +
                "}").andExpectAll(
                status().is(HttpStatus.OK.value())
        );
    }


//    @Test
//    public void createDoctor_shouldReturnFail_whenDoctorAlreadyExists() throws Exception {
//        setUpSecurityContext();
//
//        performOnDoctors(POST, "", "{\n" +
//                "  \"firstName\": \"Dr John\",\n" +
//                "  \"lastName\": \"Doe\",\n" +
//                "  \"email\": \"john.2doe2@doctor.com\",\n" +
//                "  \"password\": \"123\"\n" +
//                "}");
//        performOnDoctors(POST, "", "{\n" +
//                "  \"firstName\": \"Dr John\",\n" +
//                "  \"lastName\": \"Doe\",\n" +
//                "  \"email\": \"john.2doe2@doctor.com\",\n" +
//                "  \"password\": \"123\"\n" +
//                "}").andExpectAll(
//                status().is(HttpStatus.OK.value()),
//                jsonPath("$.firstName").value("Dr John")
//        );
//    }
}
