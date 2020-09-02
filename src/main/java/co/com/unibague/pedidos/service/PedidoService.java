package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.dto.GuardarPedidoDTO;
import co.com.unibague.pedidos.dto.PedidoDTO;
import co.com.unibague.pedidos.model.*;
import co.com.unibague.pedidos.repository.DetalleRepository;
import co.com.unibague.pedidos.repository.PedidoRepository;
import co.com.unibague.pedidos.service.exception.*;
import co.com.unibague.pedidos.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("pedidoService")
public class PedidoService implements IPedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoRepository productoRepository;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IMesaService mesaService;
    //@Autowired
    //private IEstadoService estadoService;
    @Autowired
    private DetalleRepository detalleRepository;


    //
    @Override
    public Pedido crear(GuardarPedidoDTO guardarPedido) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        guardarPedido.setPedido(new PedidoDTO());
        Pedido pedido = guardarPedido.getPedido().covertirPedido();
        //Iterable<Producto> productoLista = Producto.convertirListaDTOAProducto(guardarPedido.getProductos());
        Usuario usuarioPorId = usuarioService.buscarPorId(guardarPedido.getUsuarioId());
        pedido.setUsuarioId(usuarioPorId);
        Mesa mesaPorId = mesaService.buscarPorId(guardarPedido.getMesaId());
        pedido.setMesaId(mesaPorId);
     //   EstadoPedido estadoPorId = estadoService.buscarPorId(guardarPedido.getEstadoId());
      //  pedido.setEstadoId(estadoPorId);
        if (!pedido.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        }
        if (pedidoRepository.findByFechaPedido(pedido.getFechaPedido()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un pedido con ese id");
        } else {
            pedido.setFechaPedido(new Date());
            pedido.setFechaCreacion(new Date());
            pedido.setFechaActualizacion(new Date());

        }
        List<DetallePedido> detallesPedido = new ArrayList<>();
        final Pedido pedidoCreado = pedidoRepository.save(pedido);
        guardarPedido.getDetalles().forEach(detalleActual -> {
            Producto producto = new Producto();
            producto.setId(detalleActual.getProducto());
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setProductoId(producto.getId());
            detallePedido.setPedido(pedido);
            detallePedido.setPedidoId(pedido.getId());
            detallePedido.setActivo(true);
            detallePedido.setFechaCreacion(new Date());
            detallePedido.setFechaActualizacion(new Date());
            detallePedido.setCantidad(detalleActual.getDetalle().getCantidad());
            detallePedido.setValorUnitario(detalleActual.getDetalle().getValorUnitario());
            double subtotal = detalleActual.getDetalle().getCantidad() * detalleActual.getDetalle().getValorUnitario();
            detallePedido.setTotal(subtotal);
            detallesPedido.add(detallePedido);
        });

        detalleRepository.saveAll(detallesPedido);
        return pedidoCreado;


    }

 //singleton inicializacion / valor objeto
 //utilizar builders
 //si lombok No señor  to builder. set property(setear valors especificos de un objeto
    // Data store guardar entidad
    //plugins validar que datos en cada entidad son obligatorios (relacionar con data store)
    //Bueno señor Si sñor
    //jajaja si

//cambio inyeccion depen
 /*   @Override
    public Pedido actualizar(Long id, Pedido pedido) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!pedido.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        }  if (!pedidoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un pedido con ese id");
        } else if (!pedidoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El pedido que se desea actualizar esta inactivo");
        } else {
            Pedido pedidoBuscado = buscarPorId(id);
            pedidoBuscado.setFechaPedido(pedido.getFechaPedido());
            pedidoBuscado.setFechaPedido(pedido.getFechaPedido());
            pedidoBuscado.setFechaActualizacion(new Date());
            return pedidoRepository.save(pedido);
        }
    }

  */


    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        Pedido pedidoPorId = buscarPorId(id);
        if (!pedidoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe pedido con ese id");
        } else if (!pedidoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El pedido que se desea eliminar esta inactivo");
        } else if (pedidoPorId != null) {
            pedidoPorId.setActivo(false);
            pedidoPorId.setFechaActualizacion(new Date());
            pedidoRepository.save(pedidoPorId);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public double darTotal() {
        return 0;
    }

    @Override
    public void disponibilidadProducto() throws NoExistenUnidadesDisponiblesExcepcion {

    }

    @Override
    public void añadirProductos(Producto producto) {

    }

    @Override
    public void eliminarProducto(Producto producto) {

    }


    public List<Pedido> listarTodos() throws NoExisteEntidadExcepcion {
        List<Pedido> pedidos =  pedidoRepository.findAllByActivo();
        if (pedidos.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay pedidos registrados");
        } else {
            return pedidos;
        }
    }

    public String listarPorPedido(Long pedidoIdP) throws NoExisteEntidadExcepcion {

        List<DetallePedido> detalles = pedidoRepository.findAllByPedido(pedidoIdP);
        if (detalles.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay detalle registrados");
        }
        else {
            return detalles.toString();
        }
    }


//est
  /*  @Override
    public List<Pedido> listarTodos() throws NoExisteEntidadExcepcion {
        List<Pedido> pedidos = pedidoRepository.findByActivoIsTrue(true);
        if (pedidos.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay pedidos registrados");
        } else {
            return pedidos;
        }
    }*/

    @Override
    public Pedido buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<Pedido> pedidoPorId = pedidoRepository.findById(id);
        if (!pedidoPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un pedido con ese id");
        }
        Pedido pedidoBuscado = pedidoPorId.get();
        if (!pedidoBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El pedido se encuentra inactivo");
        } else {
            return pedidoBuscado;
        }
    }



/*
    public double totalPedido (Date fecha) {
    List <ProductoDTO> subTotal = pedidoRepository.findById(id);
    double total = 0.0;
    for (DetallePedido detallePedido : subTotal) {
        total += detallePedido.getTotal();
    }
    return total;
    }

*/



}
