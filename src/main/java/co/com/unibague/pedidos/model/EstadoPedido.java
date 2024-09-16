package co.com.unibague.pedidos.model;

import co.com.unibague.pedidos.model.enums.DescripcionEstado;
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
@Table(name = "estado_pedido", schema = "public")

public class EstadoPedido implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "descripcion_estado")
    private DescripcionEstado descripcionEstado = DescripcionEstado.VACIO;
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
    private List<EstadoPorPedidoRepository> estadoPorPedidoList; */

    public boolean sonCamposValidos() {
        return descripcionEstado != DescripcionEstado.VACIO ;
    }
}
