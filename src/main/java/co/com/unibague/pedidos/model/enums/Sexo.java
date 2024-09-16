package co.com.unibague.pedidos.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum Sexo
{
    @JsonProperty("-")
    VACIO("-"),
    @JsonProperty("M")
    MASCULINO("M"),
    @JsonProperty("F")
    FEMENINO("F"),
    @JsonProperty("O")
    OTRO("O"),;

    @Getter
    private String name;

    Sexo(String name) {
        this.name = name;
    }
}
