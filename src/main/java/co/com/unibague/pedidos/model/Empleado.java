package co.com.unibague.pedidos.model;

import co.com.unibague.pedidos.enums.TipoDocumentoE;
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
@Table(name = "empleado", schema = "public")

public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "telefono")
    private long telefono;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "is_activo")
    private boolean isActivo;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "rh")
    private int rh;
    @Basic(optional = false)
    @Column(name = "numero_documento")
    private long numeroDocumento;
    @Basic(optional = false)
    @Column(name = "sexo")
    private int sexo;
  /*  @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Pago> pagoList;
    @JoinColumn(name = "tipo_documento", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "tipo_empleado", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoEmpleado tipoEmpleado;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Pedido> pedidoList;*/

    public boolean sonCamposValidos() {
        return nombre != null &&
                direccion != null &&
                telefono >0 &&
                correo != null &&
                rh >0 &&
                direccion != null &&
                sexo >0 &&
                numeroDocumento >0;
    }
}
