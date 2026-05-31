package com.bootcamp.facturacion;

import com.bootcamp.facturacion.jsondteDTO.DireccionV2DTO;
import com.bootcamp.facturacion.jsondteDTO.dte06.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonDte06SerializationTest {

    @Test
    void testNotaDebitoSerializationStructure() throws Exception {
        // 1. Instanciar Identificacion
        Identificacion06 identificacion = Identificacion06.builder()
                .version(4)
                .ambiente("00")
                .tipoDte("06")
                .numeroControl("DTE-06-M001P001-000000000000646")
                .codigoGeneracion("FFC24E16-0B1B-421C-83FD-C5CDAE932E89")
                .tipoModelo(1)
                .tipoOperacion(1)
                .tipoContingencia(null)
                .motivoContin(null)
                .fusion(null)
                .fecEmi(LocalDate.of(2026, 5, 27))
                .horEmi(LocalTime.of(9, 10, 10))
                .tipoMoneda("USD")
                .build();

        // 2. Instanciar Documento Relacionado
        DocumentoRelacionado06 docRelacionado = DocumentoRelacionado06.builder()
                .tipoDocumento("03")
                .tipoGeneracion(2)
                .numeroDocumento("CCB8FBEB-553B-4F66-BDA1-44F7EE45ADCE")
                .fechaEmision(LocalDate.of(2026, 5, 27))
                .build();

        // 3. Instanciar Emisor
        Emisor06 emisor = Emisor06.builder()
                .nit("053380001")
                .nrc("2773203")
                .nombre("demo facturame")
                .codActividad("47739")
                .descActividad("Venta al por menor de otros productos n.c.p.")
                .nombreComercial("demo facturame")
                .direccion(DireccionV2DTO.builder()
                        .departamento("04")
                        .municipio("35")
                        .distrito("01")
                        .complemento("nv concepcion")
                        .build())
                .telefono("70000000")
                .correo("jguardadosv@gmail.com")
                .build();

        // 4. Instanciar Receptor
        Receptor06 receptor = Receptor06.builder()
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
                .tipoDocumento("36")
                .numDocumento("020527284")
                .build();

        // 5. Instanciar Cuerpo de Documento
        CuerpoDocumento06 item = CuerpoDocumento06.builder()
                .numItem(1)
                .tipoItem(1)
                .numeroDocumento("CCB8FBEB-553B-4F66-BDA1-44F7EE45ADCE")
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
                .tributos(Collections.singletonList("20"))
                .noGravado(BigDecimal.ZERO)
                .ivaPerci(BigDecimal.ZERO)
                .totalIva(BigDecimal.ZERO)
                .ivaRete(BigDecimal.ZERO)
                .build();

        // 6. Instanciar Resumen
        Resumen06 resumen = Resumen06.builder()
                .totalNoSuj(BigDecimal.ZERO)
                .totalExenta(BigDecimal.ZERO)
                .totalGravada(new BigDecimal("0.88"))
                .subTotalVentas(new BigDecimal("0.88"))
                .totalDescu(BigDecimal.ZERO)
                .totalPagar(BigDecimal.ONE)
                .totalNoGravado(BigDecimal.ZERO)
                .observaciones(null)
                .totalIva(BigDecimal.ZERO)
                .tributos(Collections.singletonList(ResumenTributo06.builder()
                        .codigo("20")
                        .descripcion("Impuesto al Valor Agregado 13%")
                        .valor(new BigDecimal("0.12"))
                        .build()))
                .ivaPerci(BigDecimal.ZERO)
                .ivaRete(BigDecimal.ZERO)
                .codigoRetencionMH(null)
                .montoTotalOperacion(BigDecimal.ONE)
                .totalLetras("UNO DOLAR CON 00/100 CENTAVOS")
                .condicionOperacion(1)
                .numPagoElectronico(null)
                .build();

        // 7. Instanciar JSON DTE 06 principal
        JsonDte06 dte = JsonDte06.builder()
                .identificacion(identificacion)
                .documentoRelacionado(Collections.singletonList(docRelacionado))
                .emisor(emisor)
                .receptor(receptor)
                .ventaTercero(null)
                .cuerpoDocumento(Collections.singletonList(item))
                .resumen(resumen)
                .apendice(null)
                .build();

        // Serializar con Jackson
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dte);
        
        System.out.println("JSON GENERADO PARA DTE 06:");
        System.out.println(json);

        assertNotNull(json);
        assertTrue(json.contains("\"version\" : 4"));
        assertTrue(json.contains("\"tipoDte\" : \"06\""));
        assertTrue(json.contains("\"fecEmi\" : \"2026-05-27\""));
        assertTrue(json.contains("\"horEmi\" : \"09:10:10\""));
        assertTrue(json.contains("\"fusion\" : null"));
        assertTrue(json.contains("\"documentoRelacionado\" : [ {"));
        assertTrue(json.contains("\"tipoDocumento\" : \"03\""));
        assertTrue(json.contains("\"tipoDocumento\" : \"36\""));
        assertTrue(json.contains("\"numDocumento\" : \"020527284\""));
        assertTrue(json.contains("\"ivaRete\" : 0"));
        assertTrue(json.contains("\"totalIva\" : 0"));
        assertTrue(json.contains("\"codigoRetencionMH\" : null"));
        assertTrue(json.contains("\"numPagoElectronico\" : null"));
    }
}
