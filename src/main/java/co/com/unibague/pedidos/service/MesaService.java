package co.com.unibague.pedidos.service;

import co.com.unibague.pedidos.model.Mesa;
import co.com.unibague.pedidos.repository.MesaRepository;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.impl.IMesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("mesaService")
public class MesaService implements IMesaService
{
    @Autowired
    private MesaRepository mesaRepository;

    @Override
    public List<Mesa> listarTodos() throws NoExisteEntidadExcepcion {
        List<Mesa> mesas = mesaRepository.findAll();
        if (mesas.isEmpty()) {
            throw new NoExisteEntidadExcepcion("No hay tipoEmpleados registrados");
        } else {
            return mesas;
        }
    }

    @Override
    public Mesa buscarPorId(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion {
        Optional<Mesa> mesaPorId = mesaRepository.findById(id);
        if (!mesaPorId.isPresent()) {
            throw new NoExisteEntidadExcepcion("No existe una mesa con ese id");
        }
        Mesa mesaBuscada = mesaPorId.get();
        if (!mesaBuscada.isActivo()) {
            throw new EntidadInactivaExcepcion("La mesa se encuentra inactiva");
        } else {
            return mesaBuscada;
        }
    }


}
