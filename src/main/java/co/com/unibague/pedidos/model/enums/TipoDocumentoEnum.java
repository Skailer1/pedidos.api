package co.com.unibague.pedidos.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum TipoDocumentoEnum
{
    @JsonProperty("-")
    VACIO("-"),
    @JsonProperty("PA")
    PASAPORTE("PA"),
    @JsonProperty("PE")
    PASAPORTE_EXTRANJERO("PE"),
    @JsonProperty("CC")
    CEDULA_CIUDADANIA("CC");

    @Getter
    private String name;

    TipoDocumentoEnum(String name) {
        this.name = name;
    }
}
