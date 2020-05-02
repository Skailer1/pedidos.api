package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.model.Pedido;
import co.com.unibague.pedidos.repository.PedidoRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("pedidoService")
public class PedidoService implements IPedidoService
{
    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Pedido crear(Pedido pedido) throws YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!pedido.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
           } else if (pedidoRepository.findById(pedido.getId()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un pedido con ese id");
        } else {
            pedido.setFechaCreacion(new Date());
            pedido.setFechaActualizacion(new Date());
            pedido.setActivo(true);
            return pedidoRepository.save(pedido);
        }
    }

    @Override
    public Pedido actualizar(Long id, Pedido pedido) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!pedido.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!pedidoRepository.findById(id).isPresent()) {
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
    public List<Pedido> listarTodos() throws NoExisteEntidadExcepcion {
        List<Pedido> pedidos = pedidoRepository.findByActivo(true);
        if (pedidos.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay pedidos registrados");
        } else {
            return pedidos;
        }
    }

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



}
