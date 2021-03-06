package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.enums.RH;
import co.com.unibague.pedidos.enums.Sexo;
import co.com.unibague.pedidos.enums.TipoDocumentoEnum;
import co.com.unibague.pedidos.model.Empleado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDTO {

    private Long id;
    private String nombre;
    private long telefono;
    private String correo;
    private RH rh;
    private Sexo sexo;
    private long numeroDocumento;
    private String direccion;
    private boolean isActivo;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Empleado covertirEmpleado() {
        return Empleado.builder()
                .id(id)
                .nombre(nombre)
                .telefono(telefono)
                .correo(correo)
                .direccion(direccion)
                .rh(rh)
                .sexo(sexo)
                .numeroDocumento(numeroDocumento)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
