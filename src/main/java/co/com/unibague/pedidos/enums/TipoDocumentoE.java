package co.com.unibague.pedidos.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum TipoDocumentoE
{
    @JsonProperty("-")
    VACIO("-"),
    @JsonProperty("PASAPORTE")
    MASCULINO("PASAPORTE"),
    @JsonProperty("TARJETA DE IDENTIDAD")
    FEMENINO("TARJETA DE IDENTIDAD"),
    @JsonProperty("CEDULA DE CIUDADANIA")
    CEDULACIUDADANIA("CEDULA DE CIUDADANIA"),
    @JsonProperty("TARJETA DE IDENTIDAD")
    CEDULACIUDADANIAEXTRANJERA("TARJETA DE IDENTIDAD");

    @Getter
    private String name;

    TipoDocumentoE (String name) {
        this.name = name;
    }
}
