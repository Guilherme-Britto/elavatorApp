package br.com.guilherme.elevator.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ElevatorUsageDTO {
    @JsonProperty("elevador")
    private char elevador;

    @JsonProperty("andar")
    private int andar;

    @JsonProperty("turno")
    private char turno;

    public ElevatorUsageDTO() {}

    public char getElevador() {
        return elevador;
    }

    public int getAndar() {
        return andar;
    }

    public char getTurno() {
        return turno;
    }
}
