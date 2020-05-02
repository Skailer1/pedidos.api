package co.com.unibague.pedidos.model;

import java.io.Serializable;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EstadoPorPedidoPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "estado_id")
    private long estadoId;
    @Basic(optional = false)
    @Column(name = "pedido_id")
    private long pedidoId;
}
