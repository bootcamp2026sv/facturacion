package com.bootcamp.facturacion;


import com.bootcamp.facturacion.controllers.DepartamentoController;
import com.bootcamp.facturacion.models.Departamento;
import com.bootcamp.facturacion.models.Municipio;
import com.bootcamp.facturacion.services.DepartamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class DepartamentoControllerTest {


    private MockMvc mockMvc;


    @Mock
    private DepartamentoService servicio;


    @InjectMocks
    private DepartamentoController controller;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    void listadoDepartamentos_DebeDevolverListaYStatus200() throws Exception {
        // GIVEN
        List<Municipio> sinMunicipios = new ArrayList<>();


        Departamento dep1 = new Departamento(1L, "06", "San Salvador", sinMunicipios);
        Departamento dep2 = new Departamento(2L, "07", "La Libertad", sinMunicipios);

        List<Departamento> listaFalsa = Arrays.asList(dep1, dep2);


        when(servicio.listadoDepartamentos()).thenReturn(listaFalsa);


        // WHEN & THEN
        mockMvc.perform(get("/api/v1/departamentos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].Nombre").value("San Salvador"))
                .andExpect(jsonPath("$[0].Codigo").value("06"))
                .andExpect(jsonPath("$[1].Nombre").value("La Libertad"))
                .andExpect(jsonPath("$[1].Codigo").value("07"));
    }


    @Test
    void listadoDepartamentos_CuandoNoHayDatos_DebeRetornarListaVacia() throws Exception {
        // GIVEN
        when(servicio.listadoDepartamentos()).thenReturn(List.of());


        // WHEN & THEN
        mockMvc.perform(get("/api/v1/departamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
