package com.marsol.balanzaappweb.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsol.balanzaappweb.controller.ScaleController;
import com.marsol.balanzaappweb.exception.GlobalExceptionHandler;
import com.marsol.balanzaappweb.model.Scale;
import com.marsol.balanzaappweb.service.ScaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(controllers = ScaleController.class)
@Import(GlobalExceptionHandler.class)
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
public class ScaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ScaleService service;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void crear_ScaleExitosa_retorna200() throws Exception {
        Scale scale = Scale.builder()
                .id(1L)
                .store(1)
                .departamento(1)
                .ipBalanza("10.0.0.1")
                .build();

        when(service.createScale(any(Scale.class))).thenReturn(scale);
        MvcResult result = mockMvc.perform(post("/api/scales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(scale)))
                .andExpect(status().isOk()) //Verifica el codigo http de la respuesta sea 200 OK
                .andExpect(jsonPath("$.id").value(1L)) //Usa la notación jsonPath para acceder a los campos del JSON devuelto por el controlador. $ indica el objeto raiz del json y .id indica el campo id de ese objeto
                .andExpect(jsonPath("$.ipBalanza").value("10.0.0.1"))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println(responseBody);
    }

    @Test
    void crear_ScaleConDatosVacios_retorna400() throws Exception {
        Scale scale = Scale.builder().build();

        MvcResult result = mockMvc.perform(post("/api/scales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(scale)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        System.out.println(responseBody);
    }
}
