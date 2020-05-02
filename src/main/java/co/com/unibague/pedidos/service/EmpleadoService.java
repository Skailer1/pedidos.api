package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.dto.GuardarEmpleadoDTO;
import co.com.unibague.pedidos.model.TipoDocumento;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.ITipoDocumentoService;
import co.com.unibague.pedidos.service.impl.IEmpleadoService;
import co.com.unibague.pedidos.model.Empleado;
import co.com.unibague.pedidos.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("empleadoService")
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private ITipoDocumentoService tipoDocumentoService;
    @Autowired
    private ITipoDocumentoService usuarioService;

    @Override
    public Empleado crear(GuardarEmpleadoDTO guardarEmpleado) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion {
        Empleado empleado = guardarEmpleado.getEmpleado().covertirEmpleado();
        TipoDocumento tipoDocumentoPorId = tipoDocumentoService.buscarPorId(guardarEmpleado.getDocumentoId());
    //    empleado.setTipoDocumento(tipoDocumentoPorId);
        if (!empleado.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (empleadoRepository.findById(empleado.getId()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un empleado con ese ID");
        } else {
            empleado.setActivo(true);
            empleado.setFechaCreacion(new Date());
            empleado.setFechaActualizacion(new Date());
            return empleadoRepository.save(empleado);
        }
    }

    @Override
    public void actualizar(Long id, Empleado empleado) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Empleado empleadoBuscado = buscarPorId(id);
        if (empleadoBuscado == null) {
            throw new NoExisteEntidadExcepcion("No existe un empleado con ese id");
        } else {
            empleadoBuscado.setNombre(empleado.getNombre());
            empleadoBuscado.setTelefono(empleado.getTelefono());
            empleadoBuscado.setCorreo(empleado.getCorreo());
            empleadoBuscado.setDireccion(empleado.getDireccion());
       //     empleadoBuscado.setActivo();
            empleadoBuscado.setFechaCreacion(empleado.getFechaCreacion());
            empleadoBuscado.setFechaActualizacion(empleado.getFechaActualizacion());
            /* empleadoBuscado.setTipoDocumento(empleado.getEmpleadoList());
            empleadoBuscado.setTipoEmpleado(empleado.getTipoEmpleado());
            empleadoBuscado.setUsuarioId(empleado.getUsuarioId());
            empleadoBuscado.setMesaList(empleado.getMesaList()); */
            empleadoRepository.save(empleadoBuscado);

        }
    }

    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        if (!empleadoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un empleado con ese id");
        } else {
            empleadoRepository.delete(empleadoRepository.findById(id).get());
            return true;
        }
    }


    @Override
    public Iterable<Empleado> listarTodos() throws NoExisteEntidadExcepcion{
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion  {
        if (!empleadoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un empleado con ese id");
        } else {
            return empleadoRepository.save(empleadoRepository.findById(id).get());
        }
    }

}
