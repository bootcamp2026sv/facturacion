package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.models.*;
import com.bootcamp.facturacion.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository repo;
    private final ProductoRepository productoRepo;
    private final ClienteRepository clienteRepo;
    private final ComercioRepository comercioRepo;
    private final CorrelativoDteService correlativoService;
    private final ActividadEconomicaRepository actividadRepo;
    private final MunicipioRepository municipioRepo;
    private final DistritoRepository distritoRepo;

    public VentaService(VentaRepository repo,
                        ProductoRepository productoRepo,
                        ClienteRepository clienteRepo,
                        ComercioRepository comercioRepo,
                        CorrelativoDteService correlativoService,
                        ActividadEconomicaRepository actividadRepo,
                        MunicipioRepository municipioRepo,
                        DistritoRepository distritoRepo) {
        this.repo = repo;
        this.productoRepo = productoRepo;
        this.clienteRepo = clienteRepo;
        this.comercioRepo = comercioRepo;
        this.correlativoService = correlativoService;
        this.actividadRepo = actividadRepo;
        this.municipioRepo = municipioRepo;
        this.distritoRepo = distritoRepo;
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
        return guardar(venta, true);
    }

    @Transactional
    public Venta guardar(Venta venta, boolean validar) {
        recalcularTotales(venta);

        if (validar) {
            // 1. Resolver comercio (debe existir en BD)
            venta.setComercio(resolverComercio(venta.getComercio()));

            // 2. Resolver cliente (debe existir en BD)
            venta.setCliente(resolverCliente(venta.getCliente()));

            // 3. Generar número de control
            venta.setNumeroControl(generarNumeroControl(venta));

            // 4. Defaults
            venta.setFecha(LocalDateTime.now());
            if (venta.getJsonVenta() == null) {
                venta.setJsonVenta("");
            }
            venta.setCodigoGeneracion(java.util.UUID.randomUUID().toString().toUpperCase());

            // 5. Resolver productos en cada detalle
            resolverDetalles(venta);
        } else {
            // Camino rápido / directo por ID:
            // 1. Resolver relaciones directas por ID en BD para evitar transient exceptions y traer codigos MH
            if (venta.getComercio() != null && venta.getComercio().getId() != null) {
                comercioRepo.findById(venta.getComercio().getId()).ifPresent(venta::setComercio);
            }
            if (venta.getCliente() != null && venta.getCliente().getId() != null) {
                clienteRepo.findById(venta.getCliente().getId()).ifPresent(venta::setCliente);
            }

            // 2. Resolver detalles y sus productos asociados por ID
            if (venta.getDetallesVenta() != null) {
                for (DetalleVenta detalle : venta.getDetallesVenta()) {
                    detalle.setVenta(venta);
                    if (detalle.getProducto() != null && detalle.getProducto().getId() != null) {
                        productoRepo.findById(detalle.getProducto().getId()).ifPresent(detalle::setProducto);
                    }
                }
            }

            // 3. Generar datos únicos obligatorios de la venta
            venta.setNumeroControl(generarNumeroControl(venta));
            venta.setCodigoGeneracion(java.util.UUID.randomUUID().toString().toUpperCase());
            venta.setFecha(LocalDateTime.now());
            if (venta.getJsonVenta() == null) {
                venta.setJsonVenta("");
            }
        }

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
            Optional<Comercio> optComercio = comercioRepo.findByNit(comercio.getNit());
            if (optComercio.isPresent()) {
                return optComercio.get();
            }
        }

        // Si no existe, lo creamos/guardamos usando los datos enviados
        if (comercio.getActividadEconomica() != null) {
            String codAct = comercio.getActividadEconomica().getCodActividad();
            if (codAct != null && !codAct.isBlank()) {
                ActividadEconomica ae = actividadRepo.findByCodActividad(codAct).orElse(null);
                if (ae == null) {
                    ae = actividadRepo.save(comercio.getActividadEconomica());
                }
                comercio.setActividadEconomica(ae);
            }
        }
        if (comercio.getMunicipio() != null) {
            String codMun = comercio.getMunicipio().getCodigo();
            if (codMun != null && !codMun.isBlank()) {
                Municipio mun = municipioRepo.findByCodigo(codMun).orElse(null);
                comercio.setMunicipio(mun);
            } else {
                comercio.setMunicipio(null);
            }
        }
        if (comercio.getNombre() == null || comercio.getNombre().isBlank()) {
            comercio.setNombre("Comercio S.A. de C.V.");
        }
        if (comercio.getNombreComercial() == null || comercio.getNombreComercial().isBlank()) {
            comercio.setNombreComercial("Comercio");
        }
        return comercioRepo.save(comercio);
    }

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
            Optional<Cliente> optCliente = clienteRepo.findByNumDocumento(cliente.getNumDocumento());
            if (optCliente.isPresent()) {
                return optCliente.get();
            }
        }

        // Si el documento es nulo o vacío, intentamos ubicar un cliente genérico o el primero con documento vacío
        Optional<Cliente> genericCliente = clienteRepo.findFirstByNumDocumentoIsNull()
                .or(() -> clienteRepo.findFirstByNumDocumento(""));
        
        if (genericCliente.isPresent()) {
            return genericCliente.get();
        }

        // Si no existe, lo creamos
        if (cliente.getActividadEconomica() != null) {
            String codAct = cliente.getActividadEconomica().getCodActividad();
            if (codAct != null && !codAct.isBlank()) {
                ActividadEconomica ae = actividadRepo.findByCodActividad(codAct).orElse(null);
                if (ae == null) {
                    ae = actividadRepo.save(cliente.getActividadEconomica());
                }
                cliente.setActividadEconomica(ae);
            }
        }
        if (cliente.getDistrito() != null) {
            String codDist = cliente.getDistrito().getCodigo();
            if (codDist != null && !codDist.isBlank()) {
                Distrito dist = distritoRepo.findByCodigo(codDist).orElse(null);
                cliente.setDistrito(dist);
            } else {
                cliente.setDistrito(null);
            }
        }
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            cliente.setNombre("Cliente Genérico");
        }
        cliente.setActivo(true);
        return clienteRepo.save(cliente);
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

    /**
     * Recalcula todos los totales de la venta a partir de los detalles de la venta,
     * recalculando previamente cada línea de detalle con su cantidad, precio unitario y descuento.
     */
    private void recalcularTotales(Venta venta) {
        java.math.BigDecimal totalExento = java.math.BigDecimal.ZERO;
        java.math.BigDecimal totalNoSujeto = java.math.BigDecimal.ZERO;
        java.math.BigDecimal totalGravado = java.math.BigDecimal.ZERO;
        java.math.BigDecimal totalNoGravado = java.math.BigDecimal.ZERO;
        java.math.BigDecimal totalDescuento = java.math.BigDecimal.ZERO;
        java.math.BigDecimal totalIva = java.math.BigDecimal.ZERO;

        if (venta.getDetallesVenta() != null) {
            for (DetalleVenta detalle : venta.getDetallesVenta()) {
                // 1. Asegurar valores no nulos
                java.math.BigDecimal cantidad = detalle.cantidad != null ? detalle.cantidad : java.math.BigDecimal.ONE;
                java.math.BigDecimal precioUni = detalle.precioUni != null ? detalle.precioUni : java.math.BigDecimal.ZERO;
                java.math.BigDecimal montoDescu = detalle.montoDescu != null ? detalle.montoDescu : java.math.BigDecimal.ZERO;
                java.math.BigDecimal noGravado = detalle.noGravado != null ? detalle.noGravado : java.math.BigDecimal.ZERO;

                // 2. Calcular subtotal de la línea (cantidad * precioUni - montoDescu)
                java.math.BigDecimal subtotal = cantidad.multiply(precioUni).subtract(montoDescu);
                if (subtotal.compareTo(java.math.BigDecimal.ZERO) < 0) {
                    subtotal = java.math.BigDecimal.ZERO;
                }

                // 3. Determinar categoría fiscal de la línea y asignarle el subtotal correspondiente
                boolean esExenta = detalle.ventaExenta != null && detalle.ventaExenta.compareTo(java.math.BigDecimal.ZERO) > 0;
                boolean esNoSujeta = detalle.ventaNoSuj != null && detalle.ventaNoSuj.compareTo(java.math.BigDecimal.ZERO) > 0;

                if (esExenta) {
                    detalle.ventaExenta = subtotal.setScale(4, java.math.RoundingMode.HALF_UP);
                    detalle.ventaNoSuj = java.math.BigDecimal.ZERO;
                    detalle.ventaGravada = java.math.BigDecimal.ZERO;
                    detalle.ivaItem = java.math.BigDecimal.ZERO;
                } else if (esNoSujeta) {
                    detalle.ventaNoSuj = subtotal.setScale(4, java.math.RoundingMode.HALF_UP);
                    detalle.ventaExenta = java.math.BigDecimal.ZERO;
                    detalle.ventaGravada = java.math.BigDecimal.ZERO;
                    detalle.ivaItem = java.math.BigDecimal.ZERO;
                } else {
                    // Por defecto es Gravada
                    detalle.ventaGravada = subtotal.setScale(4, java.math.RoundingMode.HALF_UP);
                    detalle.ventaExenta = java.math.BigDecimal.ZERO;
                    detalle.ventaNoSuj = java.math.BigDecimal.ZERO;

                    // IVA del ítem (13% del subtotal gravado)
                    java.math.BigDecimal iva = subtotal.multiply(new java.math.BigDecimal("0.13"));
                    detalle.ivaItem = iva.setScale(4, java.math.RoundingMode.HALF_UP);
                }

                // Asegurar formato de escala en otros campos
                detalle.cantidad = cantidad.setScale(4, java.math.RoundingMode.HALF_UP);
                detalle.precioUni = precioUni.setScale(4, java.math.RoundingMode.HALF_UP);
                detalle.montoDescu = montoDescu.setScale(4, java.math.RoundingMode.HALF_UP);
                detalle.noGravado = noGravado.setScale(4, java.math.RoundingMode.HALF_UP);
                if (detalle.psv == null) {
                    detalle.psv = precioUni.setScale(4, java.math.RoundingMode.HALF_UP);
                }

                // 4. Acumular a los totales de la venta
                totalExento = totalExento.add(detalle.ventaExenta);
                totalNoSujeto = totalNoSujeto.add(detalle.ventaNoSuj);
                totalGravado = totalGravado.add(detalle.ventaGravada);
                totalNoGravado = totalNoGravado.add(detalle.noGravado);
                totalDescuento = totalDescuento.add(detalle.montoDescu);
                totalIva = totalIva.add(detalle.ivaItem);
            }
        }

        // Asignar totales a la venta con formato de escala
        venta.setTotalExento(totalExento.setScale(4, java.math.RoundingMode.HALF_UP));
        venta.setTotalNoSujeto(totalNoSujeto.setScale(4, java.math.RoundingMode.HALF_UP));
        venta.setTotalGravado(totalGravado.setScale(4, java.math.RoundingMode.HALF_UP));
        venta.setTotalNoGravado(totalNoGravado.setScale(4, java.math.RoundingMode.HALF_UP));
        venta.setTotalDescuento(totalDescuento.setScale(4, java.math.RoundingMode.HALF_UP));
        venta.setTotalIva(totalIva.setScale(4, java.math.RoundingMode.HALF_UP));

        // Calcular totalGeneral:
        // En DTE "03" (Crédito Fiscal), el totalGeneral es la suma de los subtotales más el IVA.
        // En otros DTEs (como Factura Consumidor Final "01"), el totalGravado ya incluye el IVA.
        java.math.BigDecimal totalGeneral = totalGravado.add(totalExento).add(totalNoSujeto);
        if ("03".equals(venta.getTipoDte())) {
            totalGeneral = totalGeneral.add(totalIva);
        }
        venta.setTotalGeneral(totalGeneral.setScale(4, java.math.RoundingMode.HALF_UP));
    }
}
