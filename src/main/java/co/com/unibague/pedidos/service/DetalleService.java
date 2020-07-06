package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.dto.GuardarDetalleDTO;
import co.com.unibague.pedidos.model.DetallePedido;
import co.com.unibague.pedidos.model.DetallePedidoPK;
import co.com.unibague.pedidos.model.Pedido;
import co.com.unibague.pedidos.model.Producto;
import co.com.unibague.pedidos.repository.DetalleRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IDetalleService;
import co.com.unibague.pedidos.service.impl.IPedidoService;
import co.com.unibague.pedidos.service.impl.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service("detalleService")
public class DetalleService implements IDetalleService
{
    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private IProductoService productoService;
    @Autowired
    private IPedidoService pedidoService;





    @Override
    public DetallePedido crear(GuardarDetalleDTO detalle) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion,  YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        DetallePedido detallePedido = detalle.getDetalle().covertirDetalle();
        Pedido pedidoPorId = pedidoService.buscarPorId(detalle.getPedido());
        detallePedido.setPedido(pedidoPorId);
        Producto productoPorId = productoService.buscarPorId(detalle.getProducto());
        detallePedido.setProducto(productoPorId);
        if (!detallePedido.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
         } else if (detalleRepository.findByFechaCreacion(detallePedido.getFechaCreacion()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un detalle con ese id");
        } else {
            detallePedido.setFechaCreacion(new Date());
            detallePedido.setFechaActualizacion(new Date());
            detallePedido.setActivo(true);
            return detalleRepository.save(detallePedido);
        }
    }





    /*@Override
    public DetallePedido actualizar(Long id, DetallePedido detalle) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!detalle.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else {
            DetallePedido detalleBuscado = buscarPorId(id);
            detalleBuscado.setCantidad(detalle.getCantidad());
            detalleBuscado.setFechaActualizacion(new Date());
            return detalleRepository.save(detalleBuscado);
        }
    }

     */


   /* @Override
    public boolean eliminar(DetallePedidoPK detallePedidoPK) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        DetallePedido detallePorId = buscarPorId(detallePedidoPK);
        if (detallePorId != null) {
            detallePorId.setActivo(false);
            detallePorId.setFechaActualizacion(new Date());
            detalleRepository.save(detallePorId);
            resultado = true;
        }
        return resultado;
    }

    */


  /*  @Override
    public DetallePedido buscarPorId(DetallePedidoPK detallePedidoPK) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<DetallePedido> detallePorId = detalleRepository.findById(id);
        if (!detallePorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un detalle con ese id");
        }
        DetallePedido detalleBuscado = detallePorId.get();
        if (!detalleBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El detalle se encuentra inactivo");
        } else {
            return detalleBuscado;
        }
    }

   */

}
