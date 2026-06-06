package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.*;
import com.bootcamp.facturacion.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository repo;
    private final ProductoRepository productoRepo;
    private final ClienteRepository clienteRepo;
    private final ComercioRepository comercioRepo;
    private final CorrelativoDteService correlativoService;

    public VentaService(VentaRepository repo,
                        ProductoRepository productoRepo,
                        ClienteRepository clienteRepo,
                        ComercioRepository comercioRepo,
                        CorrelativoDteService correlativoService) {
        this.repo = repo;
        this.productoRepo = productoRepo;
        this.clienteRepo = clienteRepo;
        this.comercioRepo = comercioRepo;
        this.correlativoService = correlativoService;
    }

    public List<Venta> listadoVentas() {
        return repo.findAll();
    }

    public Venta unaVenta(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta con ID " + id + " no encontrada"));
    }

    @Transactional
    public Venta guardar(Venta venta) {
        // 1. Resolver comercio (debe existir en BD)
        venta.setComercio(resolverComercio(venta.getComercio()));

        // 2. Resolver cliente (debe existir en BD)
        venta.setCliente(resolverCliente(venta.getCliente()));

        // 3. Generar número de control
        venta.setNumeroControl(generarNumeroControl(venta));

        // 4. Defaults
        if (venta.getFecha() == null) {
            venta.setFecha(LocalDateTime.now());
        }
        if (venta.getJsonVenta() == null) {
            venta.setJsonVenta("");
        }

        // 5. Resolver productos en cada detalle
        resolverDetalles(venta);

        return repo.save(venta);
    }

    public Venta actualizar(Venta venta) {
        return repo.save(venta);
    }

    // ======================== Métodos privados ========================

    /**
     * Busca el comercio por ID o por NIT. Debe existir previamente en la BD.
     */
    private Comercio resolverComercio(Comercio comercio) {
        if (comercio == null) {
            throw new RuntimeException("Debe indicar el comercio (por id o nit)");
        }

        // Buscar por ID
        if (comercio.getId() != null) {
            return comercioRepo.findById(comercio.getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Comercio con ID " + comercio.getId() + " no encontrado"));
        }

        // Buscar por NIT
        if (comercio.getNit() != null && !comercio.getNit().isBlank()) {
            return comercioRepo.findByNit(comercio.getNit())
                    .orElseThrow(() -> new RuntimeException(
                            "Comercio con NIT " + comercio.getNit() + " no encontrado"));
        }

        throw new RuntimeException("Debe indicar el id o nit del comercio");
    }

    /**
     * Busca el cliente por ID o por numDocumento. Debe existir previamente en la BD.
     */
    private Cliente resolverCliente(Cliente cliente) {
        if (cliente == null) {
            throw new RuntimeException("Debe indicar el cliente (por id o numDocumento)");
        }

        // Buscar por ID
        if (cliente.getId() != null) {
            return clienteRepo.findById(cliente.getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Cliente con ID " + cliente.getId() + " no encontrado"));
        }

        // Buscar por número de documento
        if (cliente.getNumDocumento() != null && !cliente.getNumDocumento().isBlank()) {
            return clienteRepo.findByNumDocumento(cliente.getNumDocumento())
                    .orElseThrow(() -> new RuntimeException(
                            "Cliente con documento " + cliente.getNumDocumento() + " no encontrado"));
        }

        throw new RuntimeException("Debe indicar el id o numDocumento del cliente");
    }

    /**
     * Genera el número de control DTE usando el servicio de correlativos.
     */
    private String generarNumeroControl(Venta venta) {
        Comercio comercio = venta.getComercio();
        String codEstable = comercio.getCodEstableMH() != null ? comercio.getCodEstableMH() : "M001";
        String codPuntoVenta = comercio.getCodPuntoVentaMH() != null ? comercio.getCodPuntoVentaMH() : "P001";
        String tipoDte = venta.getTipoDte() != null ? venta.getTipoDte() : "01";
        String ambiente = venta.getAmbiente() != null ? venta.getAmbiente() : "00";

        return correlativoService.obtenerSiguienteNumeroControl(tipoDte, ambiente, codEstable, codPuntoVenta);
    }

    /**
     * Vincula cada detalle con la venta y resuelve el producto por código.
     */
    private void resolverDetalles(Venta venta) {
        if (venta.getDetallesVenta() == null || venta.getDetallesVenta().isEmpty()) {
            throw new RuntimeException("La venta debe tener al menos un detalle");
        }

        for (DetalleVenta detalle : venta.getDetallesVenta()) {
            detalle.setVenta(venta);

            if (detalle.codigo == null || detalle.codigo.isBlank()) {
                throw new RuntimeException("El código de producto no puede ser nulo o vacío en el detalle #" + detalle.numItem);
            }

            Producto producto = productoRepo.findByCodigo(detalle.codigo)
                    .orElseThrow(() -> new RuntimeException(
                            "Producto con código '" + detalle.codigo + "' no encontrado"));
            detalle.setProducto(producto);
        }
    }
}
