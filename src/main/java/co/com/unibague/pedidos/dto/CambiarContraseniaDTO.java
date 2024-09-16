package co.com.unibague.pedidos.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CambiarContraseniaDTO
{
    private String contraseniaaActual;
    private String contraseniaNueva;

}
