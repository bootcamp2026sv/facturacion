package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.CorrelativoDte;
import com.bootcamp.facturacion.repository.CorrelativoDteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class CorrelativoDteService {

    private final CorrelativoDteRepository repository;

    public CorrelativoDteService(CorrelativoDteRepository repository) {
        this.repository = repository;
    }
    // o se hace bien o no se hace nada
    @Transactional
    public String obtenerSiguienteNumeroControl(String tipoDte, String ambiente, String codEstable, String codPuntoVenta) {
        int anioActual = LocalDate.now().getYear();

        // 1. Obtener registro de correlativo con bloqueo para control de concurrencia
        CorrelativoDte correlativo = repository.obtenerCorrelativoConBloqueo(tipoDte, ambiente, anioActual, codEstable, codPuntoVenta)
            .orElseGet(() -> {
                // Si es año nuevo o el primer DTE de este tipo/ambiente, se inicia en cero
                CorrelativoDte nuevo = CorrelativoDte.builder()
                        .tipoDte(tipoDte)
                        .ambiente(ambiente)
                        .anio(anioActual)
                        .codEstable(codEstable)
                        .codPuntoVenta(codPuntoVenta)
                        .ultimoValor(0L)
                        .build();
                // Lo guardamos para que se cree el registro en la BD
                return repository.save(nuevo);
            });

        // 2. Incrementar correlativo
        Long siguienteValor = correlativo.getUltimoValor() + 1;
        correlativo.setUltimoValor(siguienteValor);
        repository.save(correlativo);
        // 3. Formatear a los 15 dígitos requeridos por el MH (ej: "000000000000844")
        String correlativoFormateado = String.format("%015d", siguienteValor);

        // 4. Retornar el número de control DTE estructurado
        //DTE-01-M001P001-202500000000001
        return "DTE-" + tipoDte + "-" + codEstable + codPuntoVenta + "-" + correlativoFormateado;
    }
}

//asi lo llamariamos desde donde lo necesitemos
/*
* // Dentro de VentaService.java al registrar la venta:
String numeroControl = correlativoService.obtenerSiguienteNumeroControl(
    venta.getTipoDte(),
    venta.getAmbiente(),
    venta.getComercio().getCodEstableMH(),
    venta.getComercio().getCodPuntoVentaMH()
);
venta.setNumeroControl(numeroControl);
*
* BEGIN
* Inicia la transaccion en la base de datos
*
*
* COMMIT
*
*
* ROLLBACK
*
*
* */