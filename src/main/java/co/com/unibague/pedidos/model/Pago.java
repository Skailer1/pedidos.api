package co.com.unibague.pedidos.model;
import co.com.unibague.pedidos.model.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pago", schema = "public")
public class Pago implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "total_pago")
    private double totalPago;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_pago")
    private TipoPago tipoPago;
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
     @JoinColumn(name = "datos_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Datos datosId;
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleado empleadoId;
    @JoinColumn(name = "pedido_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pedido pedidoId;


    public boolean sonCamposValidos() {
        return totalPago >0 &&
                tipoPago !=null;
    }
}
