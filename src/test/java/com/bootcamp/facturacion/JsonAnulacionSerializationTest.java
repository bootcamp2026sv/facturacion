package com.bootcamp.facturacion;

import com.bootcamp.facturacion.jsondteDTO.anulacion.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonAnulacionSerializationTest {

    @Test
    void testAnulacionSerializationStructure() throws Exception {
        // 1. Instanciar Identificación
        IdentificacionAnulacion identificacion = IdentificacionAnulacion.builder()
                .version(3)
                .ambiente("00")
                .codigoGeneracion("805F142D-6917-4127-80B9-20C232A5947B")
                .fecEmi(LocalDate.of(2026, 5, 29))
                .horEmi(LocalTime.of(21, 43, 36))
                .fusion(null)
                .build();

        // 2. Instanciar Emisor
        EmisorAnulacion emisor = EmisorAnulacion.builder()
                .nit("001084323")
                .nombre("demo facturame")
                .codEstableMH("M001")
                .codEstable("M001")
                .codPuntoVentaMH("P001")
                .codPuntoVenta("P001")
                .telefono("70000000")
                .correo("jguardadosv@gmail.com")
                .build();

        // 3. Instanciar Documento (DTE a anular)
        DocumentoAnulacion documento = DocumentoAnulacion.builder()
                .tipoDte("01")
                .codigoGeneracion("05B2E163-AEEB-4489-9970-6DA23340A80E")
                .selloRecibido("202662B6C551C0F4471997DF681B7FB76C2DVV6B")
                .numeroControl("DTE-01-M001P001-000000000000849")
                .fecEmi(LocalDate.of(2026, 5, 29))
                .codigoGeneracionR(null)
                .tipoDocumento(null)
                .numDocumento(null)
                .nombre("Cliente Final")
                .telefono("70000000")
                .correo("jguardadosv@gmail.com")
                .build();

        // 4. Instanciar Motivo
        MotivoAnulacion motivo = MotivoAnulacion.builder()
                .tipoAnulacion(2)
                .motivoAnulacion("Anulación solicitada por el cliente")
                .nombreResponsable("CAJERO")
                .tipDocResponsable("37")
                .numDocResponsable("200001")
                .nombreSolicita("Cliente")
                .tipDocSolicita("37")
                .numDocSolicita("00001")
                .build();

        // 5. Instanciar JSON Anulación Principal
        JsonAnulacion anulacion = JsonAnulacion.builder()
                .identificacion(identificacion)
                .emisor(emisor)
                .documento(documento)
                .motivo(motivo)
                .build();

        // Serializar a JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(anulacion);
        
        System.out.println("JSON GENERADO PARA ANULACION:");
        System.out.println(json);

        assertNotNull(json);
        assertTrue(json.contains("\"version\" : 3"));
        assertTrue(json.contains("\"codigoGeneracion\" : \"805F142D-6917-4127-80B9-20C232A5947B\""));
        assertTrue(json.contains("\"fecEmi\" : \"2026-05-29\""));
        assertTrue(json.contains("\"horEmi\" : \"21:43:36\""));
        assertTrue(json.contains("\"selloRecibido\" : \"202662B6C551C0F4471997DF681B7FB76C2DVV6B\""));
        assertTrue(json.contains("\"tipoAnulacion\" : 2"));
        assertTrue(json.contains("\"motivoAnulacion\" : \"Anulación solicitada por el cliente\""));
    }
}
