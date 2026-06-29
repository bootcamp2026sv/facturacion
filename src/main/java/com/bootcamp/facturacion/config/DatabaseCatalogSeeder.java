package com.bootcamp.facturacion.config;

import com.bootcamp.facturacion.enums.TipoTributacion;
import com.bootcamp.facturacion.models.*;
import com.bootcamp.facturacion.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class DatabaseCatalogSeeder implements CommandLineRunner {

    private final ActividadEconomicaRepository aeRepo;
    private final DepartamentoRepository deptoRepo;
    private final MunicipioRepository munRepo;
    private final DistritoRepository distRepo;
    private final UnidadDeMedidaRepository udmRepo;
    private final CategoriaRepository catRepo;
    private final ClienteRepository clienteRepo;
    private final ComercioRepository comercioRepo;
    private final ProductoRepository productoRepo;

    public DatabaseCatalogSeeder(
            ActividadEconomicaRepository aeRepo,
            DepartamentoRepository deptoRepo,
            MunicipioRepository munRepo,
            DistritoRepository distRepo,
            UnidadDeMedidaRepository udmRepo,
            CategoriaRepository catRepo,
            ClienteRepository clienteRepo,
            ComercioRepository comercioRepo,
            ProductoRepository productoRepo) {
        this.aeRepo = aeRepo;
        this.deptoRepo = deptoRepo;
        this.munRepo = munRepo;
        this.distRepo = distRepo;
        this.udmRepo = udmRepo;
        this.catRepo = catRepo;
        this.clienteRepo = clienteRepo;
        this.comercioRepo = comercioRepo;
        this.productoRepo = productoRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println(">>> Iniciando el seeder de catálogos y datos iniciales...");

        // 1. Actividad Económica
        ActividadEconomica ae = null;
        if (aeRepo.count() == 0) {
            ae = ActividadEconomica.builder()
                    .codActividad("62010")
                    .descActividad("Actividades de programación informática")
                    .activo(true)
                    .build();
            ae = aeRepo.save(ae);
            aeRepo.save(ActividadEconomica.builder()
                    .codActividad("47110")
                    .descActividad("Comercio al por menor en comercios no especializados")
                    .activo(true)
                    .build());
            System.out.println("   -> Actividades Económicas inicializadas.");
        } else {
            ae = aeRepo.findAll().get(0);
        }

        // 2. Geografía (Departamento -> Municipio -> Distrito)
        Distrito distritoSS = null;
        Municipio municipioSS = null;
        if (deptoRepo.count() == 0) {
            // Departamento
            Departamento depto = Departamento.builder()
                    .Codigo("06")
                    .Nombre("San Salvador")
                    .municipios(new ArrayList<>())
                    .build();
            depto = deptoRepo.save(depto);

            // Municipio
            Municipio mun = Municipio.builder()
                    .Codigo("14")
                    .Nombre("San Salvador Centro")
                    .departamento(depto)
                    .distritos(new ArrayList<>())
                    .build();
            mun = munRepo.save(mun);
            municipioSS = mun;

            // Distrito
            Distrito dist = Distrito.builder()
                    .Codigo("01")
                    .Nombre("San Salvador")
                    .municipio(mun)
                    .build();
            distritoSS = distRepo.save(dist);

            distRepo.save(Distrito.builder()
                    .Codigo("02")
                    .Nombre("Mejicanos")
                    .municipio(mun)
                    .build());

            // Agregar a listas para mantener consistencia de JPA
            depto.getMunicipios().add(mun);
            mun.getDistritos().add(distritoSS);

            System.out.println("   -> Geografía de El Salvador (San Salvador Centro) inicializada.");
        } else {
            List<Distrito> distritos = distRepo.findAll();
            if (!distritos.isEmpty()) {
                distritoSS = distritos.get(0);
                municipioSS = distritoSS.getMunicipio();
            }
        }

        // 3. Unidad de Medida
        UnidadDeMedida udm = null;
        if (udmRepo.count() == 0) {
            udm = UnidadDeMedida.builder()
                    .codUnidad(59)
                    .descUnidad("Unidad")
                    .build();
            udm = udmRepo.save(udm);
            udmRepo.save(UnidadDeMedida.builder()
                    .codUnidad(58)
                    .descUnidad("Kilogramo")
                    .build());
            System.out.println("   -> Unidades de Medida inicializadas.");
        } else {
            udm = udmRepo.findAll().get(0);
        }

        // 4. Categoría
        Categoria catElectrónica = null;
        Categoria catBebidas = null;
        Categoria catAlimentos = null;
        if (catRepo.count() == 0) {
            catElectrónica = Categoria.builder().nombre("Electrónica").descripcion("Dispositivos y tecnología").activo(true).build();
            catBebidas = Categoria.builder().nombre("Bebidas").descripcion("Refrescos y líquidos").activo(true).build();
            catAlimentos = Categoria.builder().nombre("Alimentos").descripcion("Productos alimenticios").activo(true).build();

            catElectrónica = catRepo.save(catElectrónica);
            catBebidas = catRepo.save(catBebidas);
            catAlimentos = catRepo.save(catAlimentos);

            catRepo.save(Categoria.builder().nombre("Limpieza").descripcion("Artículos para aseo").activo(true).build());
            System.out.println("   -> Categorías inicializadas.");
        } else {
            List<Categoria> cats = catRepo.findAll();
            catElectrónica = cats.stream().filter(c -> c.getNombre().equals("Electrónica")).findFirst().orElse(cats.get(0));
            catBebidas = cats.stream().filter(c -> c.getNombre().equals("Bebidas")).findFirst().orElse(cats.get(0));
            catAlimentos = cats.stream().filter(c -> c.getNombre().equals("Alimentos")).findFirst().orElse(cats.get(0));
        }

        // 5. Cliente (Consumidor Final)
        if (clienteRepo.count() == 0) {
            Cliente cliente = Cliente.builder()
                    .tipoDocumento(13) // DUI
                    .numDocumento("000000000")
                    .nombre("Consumidor")
                    .apellidos("Final")
                    .nombreComercial("Consumidor Final")
                    .telefono("2222-2222")
                    .correo("consumidor@final.com")
                    .granContribuyente(false)
                    .complementoDireccion("San Salvador Centro")
                    .activo(true)
                    .distrito(distritoSS)
                    .build();
            clienteRepo.save(cliente);
            System.out.println("   -> Cliente por defecto (Consumidor Final) inicializado.");
        }

        // 6. Comercio (Emisor)
        if (comercioRepo.count() == 0) {
            Comercio comercio = Comercio.builder()
                    .nit("06140101011012")
                    .nrc("1234567")
                    .nombre("Mi Negocio S.A. de C.V.")
                    .nombreComercial("Mi Tiendita")
                    .tipoEstablecimiento(2) // Casa Matriz
                    .telefono("22223333")
                    .codEstableMH("M001")
                    .codPuntoVentaMH("P001")
                    .correo("contacto@mitiendita.com")
                    .granContribuyente(false)
                    .complementoDireccion("San Salvador, El Salvador")
                    .distrito(distritoSS)
                    .actividadEconomica(ae)
                    .build();
            comercioRepo.save(comercio);
            System.out.println("   -> Comercio por defecto (Emisor) inicializado.");
        }

        // 7. Productos
        if (productoRepo.count() == 0) {
            productoRepo.save(Producto.builder()
                    .codigo("PROD-001")
                    .nombre("Laptop HP ProBook")
                    .costo(new BigDecimal("500.0000"))
                    .precioSinIVA(new BigDecimal("600.0000"))
                    .precioConIVA(new BigDecimal("678.0000"))
                    .precioRebajado(BigDecimal.ZERO)
                    .existencia(new BigDecimal("50.0000"))
                    .consignacion(false)
                    .marca("HP")
                    .descripcion("Laptop HP de 15.6 pulgadas con 16GB de RAM")
                    .stockMinimo(new BigDecimal("5.0000"))
                    .activo(true)
                    .uniMedida(udm)
                    .categoria(catElectrónica)
                    .tipoTributacion(TipoTributacion.GRAVADO)
                    .build());

            productoRepo.save(Producto.builder()
                    .codigo("PROD-002")
                    .nombre("Coca Cola 2 Litros")
                    .costo(new BigDecimal("1.5000"))
                    .precioSinIVA(new BigDecimal("2.0000"))
                    .precioConIVA(new BigDecimal("2.2600"))
                    .precioRebajado(BigDecimal.ZERO)
                    .existencia(new BigDecimal("200.0000"))
                    .consignacion(false)
                    .marca("Coca Cola")
                    .descripcion("Bebida gaseosa refrescante")
                    .stockMinimo(new BigDecimal("10.0000"))
                    .activo(true)
                    .uniMedida(udm)
                    .categoria(catBebidas)
                    .tipoTributacion(TipoTributacion.GRAVADO)
                    .build());

            productoRepo.save(Producto.builder()
                    .codigo("PROD-003")
                    .nombre("Pan Integral Molde")
                    .costo(new BigDecimal("1.0000"))
                    .precioSinIVA(new BigDecimal("1.5000"))
                    .precioConIVA(new BigDecimal("1.5000")) // Exento, no tiene IVA
                    .precioRebajado(BigDecimal.ZERO)
                    .existencia(new BigDecimal("100.0000"))
                    .consignacion(false)
                    .marca("Bimbo")
                    .descripcion("Pan de caja integral")
                    .stockMinimo(new BigDecimal("5.0000"))
                    .activo(true)
                    .uniMedida(udm)
                    .categoria(catAlimentos)
                    .tipoTributacion(TipoTributacion.EXENTO)
                    .build());

            System.out.println("   -> Productos iniciales (GRAVADO, EXENTO) cargados.");
        }

        System.out.println(">>> Seeder completado con éxito.");
    }
}
