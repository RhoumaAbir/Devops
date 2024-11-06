package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Niveau {
    JUNIOR("Junior"),
    SENIOR("SENIOR"),
    EXPERT("Expert");

    private final String value;

    Niveau(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
