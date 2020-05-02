package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.model.TipoEmpleado;
import co.com.unibague.pedidos.model.TipoProducto;
import co.com.unibague.pedidos.repository.TipoEmpleadoRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.ITipoEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("tipoEmpeadoService")
public class TipoEmpleadoService implements ITipoEmpleadoService
{
    @Autowired
    private TipoEmpleadoRepository tipoEmpleadoRepository;

    @Override
    public TipoEmpleado crear(TipoEmpleado tipoEmpleado) throws YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!tipoEmpleado.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (tipoEmpleadoRepository.findById(tipoEmpleado.getId()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un tipoEmpleado con ese id");
        } else {
            tipoEmpleado.setFechaCreacion(new Date());
            tipoEmpleado.setFechaActualizacion(new Date());
            tipoEmpleado.setActivo(true);
            return tipoEmpleadoRepository.save(tipoEmpleado);
        }
    }

    @Override
    public TipoEmpleado actualizar(Long id, TipoEmpleado tipoEmpleado) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!tipoEmpleado.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!tipoEmpleadoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipo empleado con ese id");
        } else if (!tipoEmpleadoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El tipo empleado que se desea actualizar esta inactivo");
        } else {
            TipoEmpleado tipoEmpleadoBuscado = buscarPorId(id);
            tipoEmpleadoBuscado.setNombre(tipoEmpleado.getNombre());
            tipoEmpleadoBuscado.setFechaActualizacion(new Date());
            return tipoEmpleadoRepository.save(tipoEmpleadoBuscado);
        }
    }

    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        TipoEmpleado tipoEmpleadoPorId = buscarPorId(id);
        if (!tipoEmpleadoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipo empleado con ese id");
        } else if (!tipoEmpleadoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El tipo empleado que se desea eliminar esta inactivo");
        } else if (tipoEmpleadoPorId != null) {
            tipoEmpleadoPorId.setActivo(false);
            tipoEmpleadoPorId.setFechaActualizacion(new Date());
            tipoEmpleadoRepository.save(tipoEmpleadoPorId);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public List<TipoEmpleado> listarTodos() throws NoExisteEntidadExcepcion {
        List<TipoEmpleado> tipoEmpleados = tipoEmpleadoRepository.findByActivo(true);
        if (tipoEmpleados.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay tipoEmpleados registrados");
        } else {
            return tipoEmpleados;
        }
    }

    @Override
    public TipoEmpleado buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<TipoEmpleado> documentoPorId = tipoEmpleadoRepository.findById(id);
        if (!documentoPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipoEmpleado con ese id");
        }
        TipoEmpleado tipoEmpleadoBuscado = documentoPorId.get();
        if (!tipoEmpleadoBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El tipoEmpleado se encuentra inactivo");
        } else {
            return tipoEmpleadoBuscado;
        }
    }



}
