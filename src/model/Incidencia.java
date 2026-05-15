package model;
import java.time.LocalDateTime;

import utils.Validador;

public class Incidencia {
    private int id;
    private String categoria;
    private String descripcionTipo;
    private int frecuencia;
    private boolean activo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Incidencia(int id, String categoria, String descripcionTipo, int frecuencia, boolean activo) {
        setId(id);
        setCategoria(categoria);
        setDescripcionTipo(descripcionTipo);
        setFrecuencia(frecuencia);
        setActivo(activo);
    }

    public void incrementarFrecuencia() {
        this.frecuencia++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Validador.mayorACero(id, "id");
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = Validador.textoObligatorio(categoria, "categoria");
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = Validador.textoObligatorio(descripcionTipo, "descripcionTipo");
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        Validador.noNegativo(frecuencia, "frecuencia");
        this.frecuencia = frecuencia;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
