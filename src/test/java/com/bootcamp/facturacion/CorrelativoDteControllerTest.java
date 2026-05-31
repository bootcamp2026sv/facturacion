package com.bootcamp.facturacion;

import com.bootcamp.facturacion.controllers.CorrelativoDteController;
import com.bootcamp.facturacion.services.CorrelativoDteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CorrelativoDteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CorrelativoDteService correlativoDteService;

    @InjectMocks
    private CorrelativoDteController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void obtenerSiguienteNumeroControl_DebeRetornarEstadoOkYValoresCorrectos() throws Exception {
        // GIVEN
        String tipoDte = "01";
        String ambiente = "00";
        String codEstable = "M001";
        String codPuntoVenta = "P001";
        String expectedControlNumber = "DTE-01-M001P001-0000000000000844";

        when(correlativoDteService.obtenerSiguienteNumeroControl(tipoDte, ambiente, codEstable, codPuntoVenta))
                .thenReturn(expectedControlNumber);

        // WHEN & THEN
        mockMvc.perform(get("/api/v1/auth/correlativos/siguiente")
                        .param("tipoDte", tipoDte)
                        .param("ambiente", ambiente)
                        .param("codEstable", codEstable)
                        .param("codPuntoVenta", codPuntoVenta))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoDte").value(tipoDte))
                .andExpect(jsonPath("$.ambiente").value(ambiente))
                .andExpect(jsonPath("$.codEstable").value(codEstable))
                .andExpect(jsonPath("$.codPuntoVenta").value(codPuntoVenta))
                .andExpect(jsonPath("$.numeroControl").value(expectedControlNumber));
    }
}
