package co.com.unibague.pedidos.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

public class EstadoPorPedido implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstadoPorPedidoPK estadoPorPedidoPK;
    @Basic(optional = false)
    @Column(name = "fecha_estado")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date fechaEstado;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "is_activo")
    private boolean isActivo;
    @JoinColumn(name = "estado_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EstadoPedido estadoPedido;
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;

    public boolean sonCamposValidos() {
        return fechaEstado != null;
    }
}
