package co.com.unibague.pedidos.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "datos", schema = "public")
    public class Datos implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @Basic(optional = false)
        @Column(name = "id")
        private Long id;
        @Basic(optional = false)
        @Column(name = "nombre_empresa")
        private String nombreEmpresa;
        @Basic(optional = false)
        @Column(name = "razon_social")
        private String razonSocial;
        @Basic(optional = false)
        @Column(name = "nit")
        private long nit;
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
    /*   @OneToMany(cascade = CascadeType.ALL, mappedBy = "datosId")
        private List<Pago> pagoList; */

        public boolean sonCamposValidos() {
            return nombreEmpresa != null &&
                    razonSocial != null &&
                    nit >0;
        }
}
