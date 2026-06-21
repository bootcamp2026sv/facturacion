package com.bootcamp.facturacion.services;

import com.bootcamp.facturacion.dto.ProductoDTO;
import com.bootcamp.facturacion.models.Producto;
import com.bootcamp.facturacion.models.Categoria;
import com.bootcamp.facturacion.models.UnidadDeMedida;
import com.bootcamp.facturacion.enums.TipoTributacion;
import com.bootcamp.facturacion.repository.ProductoRepository;
import com.bootcamp.facturacion.repository.UnidadDeMedidaRepository;
import com.bootcamp.facturacion.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;
    private final UnidadDeMedidaRepository unimedidaRepo;
    private final CategoriaRepository categoriaRepo;

    public ProductoService(ProductoRepository repo, UnidadDeMedidaRepository unimedidaRepo, CategoriaRepository categoriaRepo) {
        this.repo = repo;
        this.unimedidaRepo = unimedidaRepo;
        this.categoriaRepo = categoriaRepo;
    }

    public List<Producto> listadoProductos(){
        return repo.findAll();
    }

    public Producto unProducto(Long id){
        return repo.findById(id).get();
    }

    public Producto guardar(ProductoDTO dto){
        Producto producto = new Producto();
        mapDtoToEntity(dto, producto);

        UnidadDeMedida um = null;
        if (dto.getUnimedidaId() != null) {
            um = unimedidaRepo.findById(dto.getUnimedidaId()).orElse(null);
        }

        if (um == null) {
            List<UnidadDeMedida> units = unimedidaRepo.findAll();
            if (units.isEmpty()) {
                um = UnidadDeMedida.builder()
                        .codUnidad(59)
                        .descUnidad("Unidad")
                        .build();
                um = unimedidaRepo.save(um);
            } else {
                um = units.get(0);
            }
        }

        producto.setUniMedida(um);
        return repo.save(producto);
    }

    public void eliminarProducto(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No se encontró el producto con el ID: " + id);
        }
        repo.deleteById(id);
    }

    public Producto actualizar(ProductoDTO dto) {
        Producto producto = repo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + dto.getId()));
        
        mapDtoToEntity(dto, producto);

        UnidadDeMedida um = null;
        if (dto.getUnimedidaId() != null) {
            um = unimedidaRepo.findById(dto.getUnimedidaId()).orElse(null);
        }

        if (um != null) {
            producto.setUniMedida(um);
        }

        return repo.save(producto);
    }

    private void mapDtoToEntity(ProductoDTO dto, Producto producto) {
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setCosto(dto.getCosto());
        producto.setPrecioConIVA(dto.getPrecioConIVA());
        producto.setPrecioSinIVA(dto.getPrecioSinIVA());
        producto.setPrecioRebajado(dto.getPrecioRebajado());
        producto.setExistencia(dto.getExistencia());
        producto.setConsignacion(dto.isConsignacion());
        producto.setMarca(dto.getMarca());
        // Resolver categoría
        Categoria cat = null;
        if (dto.getCategoriaId() != null) {
            cat = categoriaRepo.findById(dto.getCategoriaId()).orElse(null);
        } else if (dto.getCategoria() != null && !dto.getCategoria().trim().isEmpty()) {
            cat = categoriaRepo.findByNombre(dto.getCategoria()).orElse(null);
            if (cat == null) {
                cat = Categoria.builder()
                        .nombre(dto.getCategoria())
                        .descripcion("Creada automáticamente al registrar producto")
                        .activo(true)
                        .build();
                cat = categoriaRepo.save(cat);
            }
        }
        producto.setCategoria(cat);

        // Mapear TipoTributacion
        if (dto.getTipoTributacion() != null) {
            try {
                producto.setTipoTributacion(TipoTributacion.valueOf(dto.getTipoTributacion().toUpperCase()));
            } catch (IllegalArgumentException e) {
                producto.setTipoTributacion(TipoTributacion.GRAVADO); // valor por defecto
            }
        } else {
            producto.setTipoTributacion(TipoTributacion.GRAVADO);
        }

        producto.setDescripcion(dto.getDescripcion());
        producto.setStockMinimo(dto.getStockMinimo());
        producto.setActivo(dto.isActivo());
    }

}
