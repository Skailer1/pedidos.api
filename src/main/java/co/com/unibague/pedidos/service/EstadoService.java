package co.com.unibague.pedidos.service;


import co.com.unibague.pedidos.dto.EstadoDTO;
import co.com.unibague.pedidos.model.EstadoPedido;
import co.com.unibague.pedidos.repository.EstadoRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("estadoService")
public class EstadoService implements IEstadoService
{
    @Autowired
    private EstadoRepository estadoRepository;

    public EstadoPedido crear(EstadoPedido estado) throws /*YaExisteEntidadExcepcion,*/ DataIncorrectaExcepcion {
        if (!estado.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
       //  } else if (estadoRepository.findByDescripcion(estado.getDescripcionEstado()).isPresent()) {
       //     throw new YaExisteEntidadExcepcion("Ya existe un estado con ese id");
        } else {
            estado.setFechaCreacion(new Date());
            estado.setFechaActualizacion(new Date());
            estado.setActivo(true);
            return estadoRepository.save(estado);
        }
    }


    @Override
    public EstadoPedido actualizar(Long id, EstadoDTO estado) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        EstadoPedido estadoPedido = estado.covertirEstado();
        if (!estadoPedido.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!estadoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe estado con esa descripcion");
        } else if (!estadoRepository.findById(id).get().isActivo()) {
                throw new EntidadInactivaExcepcion("El estado que se desea actualizar esta inactivo");
        }
        else {
            EstadoPedido estadoBuscado = buscarPorId(id);
            estadoBuscado.setDescripcionEstado(estado.getDescripcionEstado());
            estadoBuscado.setFechaActualizacion(new Date());
            return estadoRepository.save(estadoBuscado);
        }
    }


    @Override
    public List<EstadoPedido> listarTodos() throws NoExisteEntidadExcepcion {
        List<EstadoPedido> estados = (List<EstadoPedido>) estadoRepository.findAll();
        if (estados.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay estados registrados");
        } else {
            return estados;
        }
    }


    @Override
    public EstadoPedido buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<EstadoPedido> estadoPorId = estadoRepository.findById(id);
        if (!estadoPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un estado con ese id");
        }
        EstadoPedido estadoBuscado = estadoPorId.get();
        if (!estadoBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El estado se encuentra inactivo");
        } else {
            return estadoBuscado;
        }
    }





}
