package com.bootcamp.facturacion;

import com.bootcamp.facturacion.jsondteDTO.DireccionV2DTO;
import com.bootcamp.facturacion.jsondteDTO.PagosV2DTO;
import com.bootcamp.facturacion.jsondteDTO.dte01v2.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonDteSerializationTest {

    @Test
    void testSerializationStructure() throws Exception {
        // 1. Instanciar Identificacion
        Identificacion01 identificacion = Identificacion01.builder()
                .version(2)
                .ambiente("00")
                .tipoDte("01")
                .numeroControl("DTE-01-M001P001-000000000000844")
                .codigoGeneracion("C5DE3D38-5732-4822-B57A-DBFCC9A253CD")
                .tipoModelo(1)
                .tipoOperacion(1)
                .tipoContingencia(null)
                .motivoContin(null)
                .fecEmi(LocalDate.of(2026, 5, 27))
                .horEmi(LocalTime.of(9, 11, 9))
                .tipoMoneda("USD")
                .build();

        // 2. Instanciar Emisor
        Emisor01 emisor = Emisor01.builder()
                .nit("053380001")
                .nrc("2773203")
                .nombre("demo facturame")
                .codActividad("47739")
                .descActividad("Venta al por menor de otros productos n.c.p.")
                .nombreComercial("demo facturame")
                .telefono("70000000")
                .codEstable("M001")
                .codPuntoVenta("P001")
                .correo("jguardadosv@gmail.com")
                .direccion(DireccionV2DTO.builder()
                        .departamento("04")
                        .municipio("35")
                        .distrito("16")
                        .complemento("Av principal")
                        .build())
                .build();

        // 3. Instanciar Receptor
        Receptor01 receptor = Receptor01.builder()
                .tipoDocumento("37")
                .numDocumento(null)
                .nrc(null)
                .nombre("Cliente Final")
                .codActividad(null)
                .descActividad(null)
                .direccion(DireccionV2DTO.builder()
                        .departamento("04")
                        .municipio("35")
                        .distrito("16")
                        .complemento("Av principal")
                        .build())
                .telefono("70000000")
                .correo("jguardadosv@gmail.com")
                .build();

        // 4. Instanciar Cuerpo de Documento (Item)
        CuerpoDocumento01 item = CuerpoDocumento01.builder()
                .numItem(1)
                .tipoItem(1)
                .numeroDocumento(null)
                .cantidad(BigDecimal.ONE)
                .codigo("F0002")
                .codTributo(null)
                .uniMedida(59)
                .descripcion("coca cola lata 2 up")
                .precioUni(BigDecimal.ONE)
                .montoDescu(BigDecimal.ZERO)
                .ventaNoSuj(BigDecimal.ZERO)
                .ventaExenta(BigDecimal.ZERO)
                .ventaGravada(BigDecimal.ONE)
                .tributos(null)
                .psv(BigDecimal.ONE)
                .noGravado(BigDecimal.ZERO)
                .ivaItem(new BigDecimal("0.12"))
                .build();

        // 5. Instanciar Resumen
        Resumen01 resumen = Resumen01.builder()
                .totalNoSuj(BigDecimal.ZERO)
                .totalExenta(BigDecimal.ZERO)
                .totalGravada(BigDecimal.ONE)
                .totalNoGravado(BigDecimal.ZERO)
                .subTotalVentas(BigDecimal.ONE)
                .descuNoSuj(BigDecimal.ZERO)
                .descuExenta(BigDecimal.ZERO)
                .descuGravada(BigDecimal.ZERO)
                .porcentajeDescuento(BigDecimal.ZERO)
                .totalDescu(BigDecimal.ZERO)
                .tributos(null)
                .subTotal(BigDecimal.ONE)
                .ivaRete(BigDecimal.ZERO)
                .montoTotalOperacion(BigDecimal.ONE)
                .totalPagar(BigDecimal.ONE)
                .totalLetras("UNO DOLAR CON 00/100 CENTAVOS")
                .totalIva(new BigDecimal("0.12"))
                .saldoFavor(BigDecimal.ZERO)
                .condicionOperacion(1)
                .pagos(Collections.singletonList(PagosV2DTO.builder()
                        .codigo("01")
                        .montoPago(BigDecimal.ONE)
                        .referencia(null)
                        .periodo(null)
                        .plazo(null)
                        .build()))
                .numPagoElectronico("0")
                .observaciones(null)
                .build();

        // 6. Instanciar JSON DTE Principal
        JsonDte01V2 dte = JsonDte01V2.builder()
                .identificacion(identificacion)
                .documentoRelacionado(null)
                .emisor(emisor)
                .receptor(receptor)
                .ventaTercero(null)
                .cuerpoDocumento(Collections.singletonList(item))
                .resumen(resumen)
                .otrosDocumentos(null)
                .apendice(null)
                .build();

        // Serializar a JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Registrar modulo para LocalDate/LocalTime
        
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dte);
        
        System.out.println("JSON GENERADO:");
        System.out.println(json);

        assertNotNull(json);
        assertTrue(json.contains("\"version\" : 2"));
        assertTrue(json.contains("\"fecEmi\" : \"2026-05-27\""));
        assertTrue(json.contains("\"horEmi\" : \"09:11:09\""));
        assertTrue(json.contains("\"cuerpoDocumento\" : ["));
        assertTrue(json.contains("\"departamento\" : \"04\""));
    }
}
