package co.com.unibague.pedidos.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum Sexo
{
    @JsonProperty("-")
    VACIO("-"),
    @JsonProperty("MASCULINO")
    MASCULINO("MASCULINO"),
    @JsonProperty("FEENINO")
    FEMENINO("FEMENINO");

    @Getter
    private String name;

    Sexo(String name) {
        this.name = name;
    }
}
