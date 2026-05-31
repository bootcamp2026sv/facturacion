package com.bootcamp.facturacion;

import com.bootcamp.facturacion.models.CorrelativoDte;
import com.bootcamp.facturacion.repository.CorrelativoDteRepository;
import com.bootcamp.facturacion.services.CorrelativoDteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CorrelativoDteServiceTest {

    @Mock
    private CorrelativoDteRepository repository;

    @InjectMocks
    private CorrelativoDteService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerarSiguienteNumeroControl_CuandoExisteCorrelativo() {
        // GIVEN
        int anioActual = LocalDate.now().getYear();
        CorrelativoDte correlativoExistente = CorrelativoDte.builder()
                .id(1L)
                .tipoDte("01")
                .ambiente("00")
                .anio(anioActual)
                .codEstable("M001")
                .codPuntoVenta("P001")
                .ultimoValor(0L)
                .build();

        when(repository.obtenerCorrelativoConBloqueo(eq("01"), eq("00"), eq(anioActual), eq("M001"), eq("P001")))
                .thenReturn(Optional.of(correlativoExistente));
        
        when(repository.save(any(CorrelativoDte.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        String numeroControl = service.obtenerSiguienteNumeroControl("01", "00", "M001", "P001");

        // THEN
        // El valor 843 incrementado es 844 -> padded to 15 digits is "000000000000844"
        // Formato: DTE-[Tipo]-[Establecimiento][PuntoVenta]-[15 dígitos correlativo]
        assertEquals("DTE-01-M001P001-000000000000001", numeroControl);
    }

    @Test
    void testGenerarSiguienteNumeroControl_CuandoNoExisteCorrelativo_DebeIniciarEnUno() {
        // GIVEN
        int anioActual = LocalDate.now().getYear();

        when(repository.obtenerCorrelativoConBloqueo(eq("03"), eq("01"), eq(anioActual), eq("M001"), eq("P001")))
                .thenReturn(Optional.empty());
        
        when(repository.save(any(CorrelativoDte.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        String numeroControl = service.obtenerSiguienteNumeroControl("03", "01", "M001", "P001");

        // THEN
        // El valor debe iniciar en 1 -> padded to 15 digits is "000000000000001"
        assertEquals("DTE-03-M001P001-000000000000001", numeroControl);
    }
}
