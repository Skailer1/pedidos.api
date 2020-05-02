package co.com.unibague.pedidos.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseEntity implements Serializable
{
    private LocalDateTime darFecha;
    private LocalDateTime actualizarFecha;
    private boolean esActivo;
}
