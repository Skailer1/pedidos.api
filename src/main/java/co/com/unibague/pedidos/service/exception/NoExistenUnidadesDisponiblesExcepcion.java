package co.com.unibague.pedidos.service.exception;

import co.com.unibague.pedidos.model.Producto;

public class NoExistenUnidadesDisponiblesExcepcion extends Exception {

    private static final String DEFAULT_MESSAGE = "No se encuentran unidades disponibles";

    public NoExistenUnidadesDisponiblesExcepcion() {
        super(DEFAULT_MESSAGE);
    }

    public NoExistenUnidadesDisponiblesExcepcion(Producto producto) {
        super(String.format("no existen unidades disponibles", producto.getNombreProducto(), producto.getCantidadEnStock()));
    }

}
