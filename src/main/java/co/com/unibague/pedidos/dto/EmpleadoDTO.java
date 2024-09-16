package co.com.unibague.pedidos.dto;

import co.com.unibague.pedidos.model.enums.RH;
import co.com.unibague.pedidos.model.enums.Sexo;
import co.com.unibague.pedidos.model.enums.TipoDocumentoEnum;
import co.com.unibague.pedidos.model.enums.TipoEmpleadoEnum;
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
    private RH rh;
    private Sexo sexo;
    private TipoDocumentoEnum tipoDocumento;
    private TipoEmpleadoEnum tipoEmpleado;
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
                .direccion(direccion)
                .rh(rh)
                .sexo(sexo)
                .tipoDocumento(tipoDocumento)
                .tipoEmpleado(tipoEmpleado)
                .numeroDocumento(numeroDocumento)
                .isActivo(isActivo)
                .fechaActualizacion(new Date())
                .build();
    }
}
