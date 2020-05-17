package co.com.unibague.pedidos.service.impl;

import co.com.unibague.pedidos.dto.EmpleadoDTO;
import co.com.unibague.pedidos.dto.GuardarEmpleadoDTO;
import co.com.unibague.pedidos.model.Empleado;
import co.com.unibague.pedidos.service.exception.DataIncorrectaExcepcion;
import co.com.unibague.pedidos.service.exception.EntidadInactivaExcepcion;
import co.com.unibague.pedidos.service.exception.NoExisteEntidadExcepcion;
import co.com.unibague.pedidos.service.exception.YaExisteEntidadExcepcion;

public interface IEmpleadoService
{
    Empleado crear(GuardarEmpleadoDTO guardarEmpleado) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion, YaExisteEntidadExcepcion;

    Empleado actualizar(Long id, EmpleadoDTO empleado) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion, DataIncorrectaExcepcion;

    boolean eliminar(Long id) throws NoExisteEntidadExcepcion, EntidadInactivaExcepcion;

    Empleado buscarPorId(Long id) throws EntidadInactivaExcepcion, NoExisteEntidadExcepcion;
}





