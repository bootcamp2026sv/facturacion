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
}
