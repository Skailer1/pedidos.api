package co.com.unibague.pedidos.model;

import java.io.Serializable;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DetallePedidoPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "pedido_id")
    private long pedidoId;
    @Basic(optional = false)
    @Column(name = "producto_id")
    private long productoId;
}
