package co.com.unibague.pedidos.model;

import co.com.unibague.pedidos.enums.DescripcionEstado;
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
@Table(name = "estado_pedido", schema = "public")

public class EstadoPedido implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "descripcion_estado")
    private int descripcionEstado;
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
  /*  @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoPedido")
    private List<EstadoPorPedido> estadoPorPedidoList; */

    public boolean sonCamposValidos() {
        return descripcionEstado >0;
    }
}
