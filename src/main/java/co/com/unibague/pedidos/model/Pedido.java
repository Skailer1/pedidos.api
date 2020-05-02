package co.com.unibague.pedidos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pedido", schema = "public")

public class Pedido implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "is_activo")
    private boolean isActivo;
  /*  @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pago pago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<DetallePedido> detallePedidoList;
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado empleadoId;
    @JoinColumn(name = "mesa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Mesa mesaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<EstadoPorPedido> estadoPorPedidoList; */

    public boolean sonCamposValidos() {
        return fechaPedido != null ;
    }

}
