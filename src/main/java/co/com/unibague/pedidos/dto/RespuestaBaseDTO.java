package co.com.unibague.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespuestaBaseDTO {

    private int codigoEstado;
    private String mensaje;
    private boolean isCorrecto;


}
