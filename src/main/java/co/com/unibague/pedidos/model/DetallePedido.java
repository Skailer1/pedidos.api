package co.com.unibague.pedidos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "detalle_pedido", schema = "public",uniqueConstraints=
@UniqueConstraint(columnNames={"pedido_id", "producto_id"}))

public class DetallePedido implements Serializable
{
    private static final long serialVersionUID = 1L;
  //  @EmbeddedId
  //  protected DetallePedidoPK detallePedidoPK;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Pedido pedido;
    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Producto producto;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "valor_unitario")
    private double valorUnitario;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
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

    @Column(name = "pedido_id")
    private  Long pedidoId;

    @Column(name = "producto_id")
    private  Long productoId;


    public boolean sonCamposValidos() {
        return cantidad >0 &&
                valorUnitario >0 &&
                total>0;
    }
}
