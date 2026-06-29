package com.bootcamp.facturacion;

import com.bootcamp.facturacion.jsondteDTO.DireccionV2DTO;
import com.bootcamp.facturacion.jsondteDTO.PagosV2DTO;
import com.bootcamp.facturacion.jsondteDTO.dte03v4.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonDte03SerializationTest {

    @Test
    void testCCFSerializationStructure() throws Exception {
        // 1. Instanciar Identificacion
        Identificacion03 identificacion = Identificacion03.builder()
                .version(4)
                .ambiente("00")
                .tipoDte("03")
                .numeroControl("DTE-03-M001P001-000000000000827")
                .codigoGeneracion("CCB8FBEB-553B-4F66-BDA1-44F7EE45ADCE")
                .tipoModelo(1)
                .tipoOperacion(1)
                .tipoContingencia(null)
                .motivoContin(null)
                .fecEmi(LocalDate.of(2026, 5, 27))
                .horEmi(LocalTime.of(8, 51, 31))
                .tipoMoneda("USD")
                .build();

        // 2. Instanciar Emisor
        Emisor03 emisor = Emisor03.builder()
                .nit("053380001")
                .nrc("2773203")
                .nombre("demo facturame")
                .codActividad("47739")
                .descActividad("Venta al por menor de otros productos n.c.p.")
                .nombreComercial("demo facturame")
                .direccion(DireccionV2DTO.builder()
                        .departamento("04")
                        .municipio("35")
                        .distrito("16")
                        .complemento("nv concepcion")
                        .build())
                .telefono("70000000")
                .codEstable("M001")
                .codPuntoVenta("P001")
                .correo("jguardadosv@gmail.com")
                .build();

        // 3. Instanciar Receptor
        Receptor03 receptor = Receptor03.builder()
                .nit("020527284")
                .nrc("1138993")
                .nombre("Marcos Antonio Rivas Escobar")
                .codActividad("56107")
                .descActividad("Actividades varias de restaurantes")
                .nombreComercial("Marcos Antonio Rivas Escobar")
                .direccion(DireccionV2DTO.builder()
                        .departamento("04")
                        .municipio("35")
                        .distrito("16")
                        .complemento("nv concepcion")
                        .build())
                .telefono("76230990")
                .correo("jguardadosv@gmail.com")
                .build();

        // 4. Instanciar Cuerpo de Documento
        CuerpoDocumento03 item = CuerpoDocumento03.builder()
                .numItem(1)
                .tipoItem(1)
                .numeroDocumento(null)
                .cantidad(BigDecimal.ONE)
                .codigo("F0002")
                .codTributo(null)
                .uniMedida(59)
                .descripcion("coca cola lata 2 up")
                .precioUni(new BigDecimal("0.88"))
                .montoDescu(BigDecimal.ZERO)
                .ventaNoSuj(BigDecimal.ZERO)
                .ventaExenta(BigDecimal.ZERO)
                .ventaGravada(new BigDecimal("0.88"))
                .psv(new BigDecimal("0.88"))
                .noGravado(BigDecimal.ZERO)
                .tributos(Collections.singletonList("20"))
                .build();

        // 5. Instanciar Resumen
        Resumen03 resumen = Resumen03.builder()
                .totalNoSuj(BigDecimal.ZERO)
                .totalExenta(BigDecimal.ZERO)
                .totalGravada(new BigDecimal("0.88"))
                .subTotalVentas(new BigDecimal("0.88"))
                .descuNoSuj(BigDecimal.ZERO)
                .descuExenta(BigDecimal.ZERO)
                .descuGravada(BigDecimal.ZERO)
                .porcentajeDescuento(BigDecimal.ZERO)
                .totalDescu(BigDecimal.ZERO)
                .tributos(Collections.singletonList(ResumenTributo03.builder()
                        .codigo("20")
                        .descripcion("Impuesto al Valor Agregado 13%")
                        .valor(new BigDecimal("0.12"))
                        .build()))
                .subTotal(new BigDecimal("0.88"))
                .ivaRete(BigDecimal.ZERO)
                .ivaPerci(BigDecimal.ZERO)
                .montoTotalOperacion(BigDecimal.ONE)
                .totalNoGravado(BigDecimal.ZERO)
                .totalPagar(BigDecimal.ONE)
                .totalLetras("UNO DOLAR CON 00/100 CENTAVOS")
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

        // 6. Instanciar JSON DTE 03 principal
        JsonDte03V4 dte = JsonDte03V4.builder()
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

        // Serializar con Jackson
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dte);
        
        System.out.println("JSON GENERADO PARA DTE 03 v4:");
        System.out.println(json);

        assertNotNull(json);
        assertTrue(json.contains("\"version\" : 4"));
        assertTrue(json.contains("\"tipoDte\" : \"03\""));
        assertTrue(json.contains("\"fecEmi\" : \"2026-05-27\""));
        assertTrue(json.contains("\"horEmi\" : \"08:51:31\""));
        assertTrue(json.contains("\"ivaPerci\" : 0"));
        assertTrue(json.contains("\"totalNoGravado\" : 0"));
        assertTrue(json.contains("\"tributos\" : [ \"20\" ]"));
        assertTrue(json.contains("\"descripcion\" : \"Impuesto al Valor Agregado 13%\""));
    }
}
