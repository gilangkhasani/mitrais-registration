package id.mitrais.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.mitrais.registration.dto.RegistrationDto;
import id.mitrais.registration.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = RegistrationController.class)
@ActiveProfiles("test")
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationService registrationService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<RegistrationDto> registrationList;

    @BeforeEach
    void setUp() {
        this.registrationList = new ArrayList<>();
        RegistrationDto data = new RegistrationDto();
        data.setFirstName("Gilang");
        data.setLastName("Khasani");
        data.setEmail("gilang@xxxx.com");
        data.setMobileNumber("081111111111");
        this.registrationList.add(data);
        this.registrationList.add(data);
        this.registrationList.add(data);

    }

    @Test
    void shouldFetchAllRegistrations() throws Exception {

        given(registrationService.getAllData()).willReturn(registrationList);

        this.mockMvc.perform(get("/api/registration"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(registrationList.size())));
    }

    @Test
    void shouldFetchOneRegistrationById() throws Exception {
        final Long id = 1L;
        final RegistrationDto data = new RegistrationDto();
        data.setId(1L);
        data.setFirstName("Gilang");
        data.setLastName("Khasani");
        data.setEmail("gilang@xxxx.com");
        data.setMobileNumber("081111111111");

        given(registrationService.getById(id)).willReturn(data);

        this.mockMvc.perform(get("/api/registration/{id}", id))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }

    @Test
    void shouldReturn404WhenFindRegistrationById() throws Exception {
        final Long id = 1L;
        given(registrationService.getById(id)).willReturn(null);

        this.mockMvc.perform(get("/api/registration/{id}", id))
                .andExpect(status().is(200));
    }

    @Test
    void shouldCreateNewRegistration() throws Exception {

        given(registrationService.create(any(RegistrationDto.class))).willAnswer((invocation) -> invocation.getArgument(0));

        RegistrationDto data = new RegistrationDto();
        data.setFirstName("Gilang");
        data.setLastName("Khasani");
        data.setEmail("gilang@xxxx.com");
        data.setMobileNumber("081111111111");

        this.mockMvc.perform(post("/api/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data)))
                .andExpect(status().isCreated())
                .andDo(print())
        ;
    }

}
