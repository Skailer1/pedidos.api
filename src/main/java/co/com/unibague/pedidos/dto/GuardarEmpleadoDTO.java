package co.com.unibague.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardarEmpleadoDTO {

   /* private long tipoDocumento;
    private long tipoEmpleado;*/
    private long usuarioId;
    private EmpleadoDTO empleado;

}
