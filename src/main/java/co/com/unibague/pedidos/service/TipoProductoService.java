package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.model.TipoProducto;
import co.com.unibague.pedidos.repository.TipoProductoRepository;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.ITipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("tipoProductoService")
public class TipoProductoService implements ITipoProductoService
{
    @Autowired
    private TipoProductoRepository tipoProductoRepository;

    @Override
    public TipoProducto crear(TipoProducto tipoProducto) throws YaExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!tipoProducto.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (tipoProductoRepository.findById(tipoProducto.getId()).isPresent()) {
            throw new YaExisteEntidadExcepcion("Ya existe un tipoProducto con ese id");
        } else {
            tipoProducto.setFechaCreacion(new Date());
            tipoProducto.setFechaActualizacion(new Date());
            tipoProducto.setActivo(true);
            return tipoProductoRepository.save(tipoProducto);
        }
    }

    @Override
    public TipoProducto actualizar(Long id, TipoProducto tipoProducto) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion {
        if (!tipoProducto.sonCamposValidos()) {
            throw new DataIncorrectaExcepcion("Verifique la información enviada");
        } else if (!tipoProductoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipo empleado con ese id");
        } else if (!tipoProductoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El tipo empleado que se desea actualizar esta inactivo");
        } else {
            TipoProducto tipoProductoBuscado = buscarPorId(id);
            tipoProductoBuscado.setDescripcion(tipoProducto.getDescripcion());
            tipoProductoBuscado.setFechaActualizacion(new Date());
            return tipoProductoRepository.save(tipoProductoBuscado);
        }
    }

    @Override
    public boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        boolean resultado = false;
        TipoProducto tipoProductoPorId = buscarPorId(id);
        if (!tipoProductoRepository.findById(id).isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipo producto con ese id");
        } else if (!tipoProductoRepository.findById(id).get().isActivo()) {
            throw new EntidadInactivaExcepcion("El tipo producto que se desea eliminar esta inactivo");
        } else if (tipoProductoPorId != null) {
            tipoProductoPorId.setActivo(false);
            tipoProductoPorId.setFechaActualizacion(new Date());
            tipoProductoRepository.save(tipoProductoPorId);
            resultado = true;
        }
        return resultado;
    }

    @Override
    public List<TipoProducto> listarTodos() throws NoExisteEntidadExcepcion {
        List<TipoProducto> tipoProductos = tipoProductoRepository.findByActivo(true);
        if (tipoProductos.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay tipoProductos registrados");
        } else {
            return tipoProductos;
        }
    }

    @Override
    public TipoProducto buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<TipoProducto> productoPorId = tipoProductoRepository.findById(id);
        if (!productoPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe un tipoProducto con ese id");
        }
        TipoProducto tipoProductoBuscado = productoPorId.get();
        if (!tipoProductoBuscado.isActivo()) {
            throw new EntidadInactivaExcepcion("El tipoProducto se encuentra inactivo");
        } else {
            return tipoProductoBuscado;
        }
    }

}
