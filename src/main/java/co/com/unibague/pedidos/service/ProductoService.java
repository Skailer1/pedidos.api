package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.dto.GuardarProductoDTO;
import co.com.unibague.pedidos.dto.ProductoDTO;
import co.com.unibague.pedidos.model.Producto;
import co.com.unibague.pedidos.model.TipoProducto;
import co.com.unibague.pedidos.repository.ProductoRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IProductoService;
import co.com.unibague.pedidos.service.impl.ITipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service("productoService")
public class ProductoService implements IProductoService
{
    @Autowired
    private ProductoRepository productoRepository;
   /* @Autowired
    private ITipoProductoService tipoProductoService;

    */


    @Override
    public List<Producto> listarTodos() throws NoExisteEntidadExcepcion {
        List<Producto> productos = productoRepository.findAll();
        if (productos.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay productos registrados");
        } else {
            return productos;
        }
    }

    @Override
    public Producto buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<Producto> productoPorId = productoRepository.findById(id);
        if (!productoPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un producto con ese id");
        }
        Producto productoBuscado = productoPorId.get();
        if (!productoBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El producto se encuentra inactivo");
        } else {
            return productoBuscado;
        }
    }

/*
    public int unidadesDisponibles (Date fecha) {
    List <ProductoDTO> subTotal = pedidoRepository.findbyFecha(fecha);
    dobule total = 0.0;
    for (Pedido pedido : subTotal) {
        total += pedido.getTotal();
    }
    return total;
    }


 */


    /*@Override
    public Producto crear(GuardarProductoDTO guardarProducto) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        Producto producto = guardarProducto.getProducto().covertirProducto();
        TipoProducto tipoProductoPorId = tipoProductoService.buscarPorId(guardarProducto.getTipoProductoId());
        producto.setTipoProducto(tipoProductoPorId);
        if (!producto.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
       } else if (productoRepository.findById(producto.getId()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un producto con ese id");
        } else {
            producto.setFechaCreacion(new Date());
            producto.setFechaActualizacion(new Date());
            producto.setActivo(true);
            return productoRepository.save(producto);
        }
    }

    @Override
    public Producto actualizar(Long id, Producto producto) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!producto.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!productoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un producto con ese id");
        } else if (!productoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El producto que se desea actualizar esta inactivo");
        } else {
            Producto productoBuscado = buscarPorId(id);
            productoBuscado.setCantidadEnStock(producto.getCantidadEnStock());
            productoBuscado.setCosto(producto.getCosto());
            productoBuscado.setNombreProducto(producto.getNombreProducto());
            productoBuscado.setIva(producto.getIva());
            productoBuscado.setFechaActualizacion(new Date());
            return productoRepository.save(producto);
        }
    }

    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        Producto productoPorId = buscarPorId(id);
        if (!productoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe producto con ese id");
        } else if (!productoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El producto que se desea eliminar esta inactivo");
        } else if (productoPorId != null) {
            productoPorId.setActivo(false);
            productoPorId.setFechaActualizacion(new Date());
            productoRepository.save(productoPorId);
            resultado = true;
        }
        return resultado;
    }


     */

}
