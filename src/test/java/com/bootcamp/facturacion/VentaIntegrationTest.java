package com.bootcamp.facturacion;

import com.bootcamp.facturacion.models.*;
import com.bootcamp.facturacion.services.VentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VentaIntegrationTest {

    @Autowired
    private VentaService ventaService;

    @Test
    public void testCrearVentaExitosamente() {
        Venta venta = new Venta();
        venta.setVersion(1);
        venta.setAmbiente("00");
        venta.setTipoDte("01");
        venta.setCodigoGeneracion("288e60c6-aeb4-414b-9227-9b4c16d35c1e");
        venta.setTipoModelo(1);
        venta.setTipoOperacion(1);
        venta.setFecha(LocalDateTime.now());
        venta.setTipoMoneda("USD");
        venta.setTotalGeneral(new BigDecimal("100.0000"));
        venta.setTotalExento(BigDecimal.ZERO);
        venta.setTotalNoSujeto(BigDecimal.ZERO);
        venta.setTotalGravado(new BigDecimal("100.0000"));
        venta.setTotalNoGravado(BigDecimal.ZERO);
        venta.setTotalDescuento(BigDecimal.ZERO);
        venta.setTotalIva(new BigDecimal("13.0000"));

        // Detalles
        DetalleVenta detalle = new DetalleVenta();
        detalle.numItem = 1;
        detalle.tipoItem = com.bootcamp.facturacion.enums.TipoBien.BIEN;
        detalle.cantidad = BigDecimal.ONE;
        detalle.codigo = "PROD-001";
        detalle.descripcion = "Laptop HP 15.6\"";
        detalle.precioUni = new BigDecimal("600.0000");
        detalle.montoDescu = BigDecimal.ZERO;
        detalle.ventaNoSuj = BigDecimal.ZERO;
        detalle.ventaExenta = BigDecimal.ZERO;
        detalle.ventaGravada = new BigDecimal("600.0000");
        detalle.psv = new BigDecimal("600.0000");
        detalle.noGravado = BigDecimal.ZERO;
        detalle.ivaItem = new BigDecimal("78.0000");

        venta.setDetallesVenta(new ArrayList<>());
        venta.getDetallesVenta().add(detalle);

        // Cliente
        Cliente cliente = new Cliente();
        cliente.setTipoDocumento(13);
        cliente.setNumDocumento("");
        cliente.setNombre("Cliente Genérico");
        cliente.setGranContribuyente(true);

        ActividadEconomica aeCliente = new ActividadEconomica();
        aeCliente.setCodActividad("47100");
        aeCliente.setDescActividad("Venta al por menor");
        aeCliente.setActivo(true);
        cliente.setActividadEconomica(aeCliente);

        venta.setCliente(cliente);

        // Comercio
        Comercio comercio = new Comercio();
        comercio.setNit("06141234590001");
        comercio.setNrc("123456-7");
        comercio.setNombre("Comercio S.A. de C.V.");
        comercio.setNombreComercial("Comercio");
        comercio.setTipoEstablecimiento(2);
        comercio.setTelefono("22000000");
        comercio.setCodEstableMH("M001");
        comercio.setCodPuntoVentaMH("P001");
        comercio.setCorreo("comercio@correo.com");
        comercio.setGranContribuyente(false);
        comercio.setComplementoDireccion("Col. Las Brisas");

        ActividadEconomica aeComercio = new ActividadEconomica();
        aeComercio.setCodActividad("47100");
        aeComercio.setDescActividad("Venta al por menor");
        aeComercio.setActivo(true);
        comercio.setActividadEconomica(aeComercio);

        venta.setComercio(comercio);

        // Guardar
        Venta guardada = ventaService.guardar(venta);

        assertNotNull(guardada.getId());
        assertNotNull(guardada.getNumeroControl());
        assertNotNull(guardada.getCliente().getId());
        assertNotNull(guardada.getComercio().getId());
        assertNotNull(guardada.getDetallesVenta().get(0).getProducto());
        assertEquals("PROD-001", guardada.getDetallesVenta().get(0).getProducto().getCodigo());
    }

    @Autowired
    private com.bootcamp.facturacion.repository.ComercioRepository comercioRepo;
    @Autowired
    private com.bootcamp.facturacion.repository.ClienteRepository clienteRepo;
    @Autowired
    private com.bootcamp.facturacion.repository.ProductoRepository productoRepo;

    @Test
    public void testCrearVentaDirectaSinValidacion() {
        Comercio comercio = comercioRepo.findAll().stream().findFirst().orElseGet(() -> {
            Comercio c = new Comercio();
            c.setNit("12345678901234");
            c.setNrc("123456-7");
            c.setNombre("Comercio Test");
            c.setGranContribuyente(false);
            return comercioRepo.save(c);
        });

        Cliente cliente = clienteRepo.findAll().stream().findFirst().orElseGet(() -> {
            Cliente cl = new Cliente();
            cl.setNombre("Cliente Test");
            cl.setNumDocumento("12345678-9");
            cl.setTipoDocumento(13);
            cl.setGranContribuyente(false);
            cl.setActivo(true);
            return clienteRepo.save(cl);
        });

        Producto producto = productoRepo.findByCodigo("PROD-001").orElseGet(() -> {
            Producto p = new Producto();
            p.setCodigo("PROD-001");
            p.setNombre("Laptop Test");
            p.setPrecioSinIVA(new BigDecimal("600.0000"));
            p.setPrecioConIVA(new BigDecimal("678.0000"));
            return productoRepo.save(p);
        });

        Venta venta = new Venta();
        venta.setVersion(1);
        venta.setAmbiente("00");
        venta.setTipoDte("01");
        venta.setNumeroControl("DTE-01-M001P001-999999999999999");
        venta.setCodigoGeneracion("999e60c6-aeb4-414b-9227-9b4c16d35c1e");
        venta.setTipoModelo(1);
        venta.setTipoOperacion(1);
        venta.setFecha(LocalDateTime.now());
        venta.setTipoMoneda("USD");
        venta.setJsonVenta("Directo");
        venta.setTotalGeneral(new BigDecimal("100.0000"));
        venta.setTotalExento(BigDecimal.ZERO);
        venta.setTotalNoSujeto(BigDecimal.ZERO);
        venta.setTotalGravado(new BigDecimal("100.0000"));
        venta.setTotalNoGravado(BigDecimal.ZERO);
        venta.setTotalDescuento(BigDecimal.ZERO);
        venta.setTotalIva(new BigDecimal("13.0000"));

        DetalleVenta detalle = new DetalleVenta();
        detalle.numItem = 1;
        detalle.tipoItem = com.bootcamp.facturacion.enums.TipoBien.BIEN;
        detalle.cantidad = BigDecimal.ONE;
        detalle.codigo = "PROD-001";
        detalle.descripcion = "Laptop Test";
        detalle.precioUni = new BigDecimal("600.0000");
        detalle.montoDescu = BigDecimal.ZERO;
        detalle.ventaNoSuj = BigDecimal.ZERO;
        detalle.ventaExenta = BigDecimal.ZERO;
        detalle.ventaGravada = new BigDecimal("600.0000");
        detalle.psv = new BigDecimal("600.0000");
        detalle.noGravado = BigDecimal.ZERO;
        detalle.ivaItem = new BigDecimal("78.0000");
        detalle.setProducto(producto);

        venta.setDetallesVenta(new ArrayList<>());
        venta.getDetallesVenta().add(detalle);

        venta.setComercio(comercio);
        venta.setCliente(cliente);

        Venta guardada = ventaService.guardar(venta, false);

        assertNotNull(guardada.getId());
        assertNotNull(guardada.getNumeroControl());
        assertEquals(comercio.getId(), guardada.getComercio().getId());
        assertEquals(cliente.getId(), guardada.getCliente().getId());
        assertEquals(producto.getId(), guardada.getDetallesVenta().get(0).getProducto().getId());
    }
}
