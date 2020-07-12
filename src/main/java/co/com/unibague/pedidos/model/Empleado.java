package co.com.unibague.pedidos.model;

import co.com.unibague.pedidos.enums.RH;
import co.com.unibague.pedidos.enums.Sexo;
import co.com.unibague.pedidos.enums.TipoDocumentoEnum;
import co.com.unibague.pedidos.enums.TipoEmpleadoEnum;
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
@Table(name = "empleado", schema = "public")

public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "telefono")
    private long telefono;
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
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rh")
    private RH rh = RH.VACIO;
    @Basic(optional = false)
    @Column(name = "numero_documento")
    private long numeroDocumento;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "sexo")
    private Sexo sexo = Sexo.VACIO;
    @Basic(optional = false)
    @Column(name = "tipo_documento")
    private TipoDocumentoEnum tipoDocumento;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;
    @Basic(optional = false)
    @Column(name = "tipo_empleado")
    private TipoEmpleadoEnum tipoEmpleado;

  /*  @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Pago> pagoList; */
    /*
     */

   /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoId")
    private List<Pedido> pedidoList;*/

    public boolean sonCamposValidos() {
        return nombre != null &&
                telefono > 0 &&
                rh != RH.VACIO &&
                sexo != Sexo.VACIO &&
                tipoEmpleado != TipoEmpleadoEnum.VACIO &&
                tipoDocumento != TipoDocumentoEnum.VACIO &&
                numeroDocumento > 0 &&
                direccion != null ;
    }
}
