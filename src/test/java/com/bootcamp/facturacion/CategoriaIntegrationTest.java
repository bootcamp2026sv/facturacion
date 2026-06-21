package com.bootcamp.facturacion;

import com.bootcamp.facturacion.dto.ProductoDTO;
import com.bootcamp.facturacion.enums.TipoTributacion;
import com.bootcamp.facturacion.models.Categoria;
import com.bootcamp.facturacion.models.Producto;
import com.bootcamp.facturacion.models.UnidadDeMedida;
import com.bootcamp.facturacion.repository.CategoriaRepository;
import com.bootcamp.facturacion.repository.UnidadDeMedidaRepository;
import com.bootcamp.facturacion.services.CategoriaService;
import com.bootcamp.facturacion.services.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CategoriaIntegrationTest {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UnidadDeMedidaRepository unimedidaRepository;

    private UnidadDeMedida defaultUnidad;

    @BeforeEach
    public void setUp() {
        // Asegurar que exista al menos una unidad de medida para crear productos
        List<UnidadDeMedida> units = unimedidaRepository.findAll();
        if (units.isEmpty()) {
            defaultUnidad = UnidadDeMedida.builder()
                    .codUnidad(1)
                    .descUnidad("Unidad")
                    .build();
            defaultUnidad = unimedidaRepository.save(defaultUnidad);
        } else {
            defaultUnidad = units.get(0);
        }
    }

    @Test
    public void testCRUDCategoria() {
        // 1. Crear y Guardar
        Categoria cat = Categoria.builder()
                .nombre("Test Categoria CRUD")
                .descripcion("Descripción de prueba")
                .activo(true)
                .build();

        Categoria guardada = categoriaService.guardar(cat);
        assertNotNull(guardada.getId());
        assertEquals("Test Categoria CRUD", guardada.getNombre());
        assertEquals("Descripción de prueba", guardada.getDescripcion());
        assertTrue(guardada.isActivo());

        // 2. Obtener una
        Categoria obtenida = categoriaService.unaCategoria(guardada.getId());
        assertEquals("Test Categoria CRUD", obtenida.getNombre());

        // 3. Actualizar
        obtenida.setDescripcion("Nueva descripción");
        Categoria actualizada = categoriaService.actualizar(obtenida);
        assertEquals("Nueva descripción", actualizada.getDescripcion());

        // 4. Listar
        List<Categoria> lista = categoriaService.listadoCategorias();
        assertFalse(lista.isEmpty());
        assertTrue(lista.stream().anyMatch(c -> c.getNombre().equals("Test Categoria CRUD")));

        // 5. Eliminar
        categoriaService.eliminarCategoria(actualizada.getId());
        assertThrows(RuntimeException.class, () -> categoriaService.unaCategoria(actualizada.getId()));
    }

    @Test
    public void testAsociarProductoPorId() {
        // Crear categoría
        Categoria cat = Categoria.builder()
                .nombre("Test ID Cat")
                .descripcion("Cat para asociar por ID")
                .activo(true)
                .build();
        cat = categoriaService.guardar(cat);

        // Crear DTO de Producto con categoriaId
        ProductoDTO dto = ProductoDTO.builder()
                .codigo("TEST-ID-PROD")
                .nombre("Producto de prueba ID")
                .costo(new BigDecimal("10.00"))
                .precioConIVA(new BigDecimal("11.30"))
                .precioSinIVA(new BigDecimal("10.00"))
                .precioRebajado(BigDecimal.ZERO)
                .existencia(BigDecimal.TEN)
                .consignacion(false)
                .marca("Marca Test")
                .descripcion("Prueba de asociación por ID")
                .stockMinimo(BigDecimal.ONE)
                .activo(true)
                .unimedidaId(defaultUnidad.getId())
                .categoriaId(cat.getId())
                .tipoTributacion("EXENTO")
                .build();

        Producto guardado = productoService.guardar(dto);
        assertNotNull(guardado.getId());
        assertNotNull(guardado.getCategoria());
        assertEquals(cat.getId(), guardado.getCategoria().getId());
        assertEquals(TipoTributacion.EXENTO, guardado.getTipoTributacion());
    }

    @Test
    public void testAsociarProductoPorNombreFallback() {
        String catNombre = "Categoria Test Fallback";

        // Crear DTO de Producto con nombre de categoría pero categoriaId nulo
        ProductoDTO dto = ProductoDTO.builder()
                .codigo("TEST-FALLBACK-PROD")
                .nombre("Producto de prueba Fallback")
                .costo(new BigDecimal("20.00"))
                .precioConIVA(new BigDecimal("22.60"))
                .precioSinIVA(new BigDecimal("20.00"))
                .precioRebajado(BigDecimal.ZERO)
                .existencia(BigDecimal.TEN)
                .consignacion(false)
                .marca("Marca Test")
                .categoria(catNombre) // Nombre de categoría para el fallback dinámico
                .descripcion("Prueba de asociación por Nombre")
                .stockMinimo(BigDecimal.ONE)
                .activo(true)
                .unimedidaId(defaultUnidad.getId())
                .tipoTributacion("NO_SUJETO")
                .build();

        Producto guardado = productoService.guardar(dto);
        assertNotNull(guardado.getId());
        assertNotNull(guardado.getCategoria());
        assertEquals(catNombre, guardado.getCategoria().getNombre());
        assertEquals(TipoTributacion.NO_SUJETO, guardado.getTipoTributacion());

        // Verificar que la categoría se creó en base de datos
        Categoria catEnBd = categoriaRepository.findByNombre(catNombre).orElse(null);
        assertNotNull(catEnBd);
        assertEquals(catNombre, catEnBd.getNombre());
    }
}
