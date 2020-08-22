package co.com.unibague.pedidos.model;

import co.com.unibague.pedidos.dto.ProductoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "producto", schema = "public")

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "cantidad_en_stock")
    private int cantidadEnStock;
    @Basic(optional = false)
    @Column(name = "imagen_url")
    private String imagenUrl;
    @Basic(optional = false)
    @Column(name = "costo")
    private double costo;
    @Basic(optional = false)
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "nombre_producto")
    private String nombreProducto;
    @Basic(optional = false)
    @Column(name = "is_activo")
    private boolean isActivo;
    @Basic(optional = false)
    @Column(name = "iva")
    private double iva;
    @JoinColumn(name = "tipo_producto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoProducto tipoProducto;

  /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<DetallePedido> detallePedidoList; */

    public boolean sonCamposValidos() {
        return cantidadEnStock > 0 &&
                costo > 0 &&
                imagenUrl != null &&
                nombreProducto != null &&
                iva > 0;
    }


    public static Iterable<Producto> convertirListaDTOAProducto(List<ProductoDTO> listaConvertir) {
        List<Producto> productosConver = new ArrayList<>();
        for (ProductoDTO producto : listaConvertir) {
            productosConver.add(producto.covertirProducto());
        }
        return productosConver;
    }
}
