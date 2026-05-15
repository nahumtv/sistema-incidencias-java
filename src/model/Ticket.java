package model;
import java.time.LocalDateTime;

import enums.EstadoTicket;
import enums.NivelSeveridad;
import utils.Validador;

public class Ticket {
    private int id;
    private String descripcion;
    private EstadoTicket estado;
    private NivelSeveridad severidad;
    private String plataforma;
    private String pacienteAfectado;
    private LocalDateTime fechaResolucion;
    private int creadoPor; 
    private Integer desarrolladorId;
    private int incidenciaId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Ticket(int id, String descripcion, NivelSeveridad severidad, String plataforma, String pacienteAfectado, int incidenciaId, int creadoPor) {
        setId(id);
        setDescripcion(descripcion);
        setSeveridad(severidad);
        setPlataforma(plataforma);
        setPacienteAfectado(pacienteAfectado);
        setCreadoPor(creadoPor);
        setIncidenciaId(incidenciaId);
        setEstado(EstadoTicket.ABIERTO);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Validador.mayorACero(id, "id");
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = Validador.textoObligatorio(descripcion, "descripcion");
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public void setEstado(EstadoTicket estado) {
        Validador.noNulo(estado, "estado");
        this.estado = estado;
    }

    public NivelSeveridad getSeveridad() {
        return severidad;
    }

    public void setSeveridad(NivelSeveridad severidad) {
        Validador.noNulo(severidad, "severidad");
        this.severidad = severidad;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = Validador.textoObligatorio(plataforma, "plataforma");
    }

    public String getPacienteAfectado() {
        return pacienteAfectado;
    }

    public void setPacienteAfectado(String pacienteAfectado) {
        this.pacienteAfectado = Validador.textoObligatorio(pacienteAfectado, "pacienteAfectado");
    }

    public Integer getDesarrolladorId() {
        return desarrolladorId;
    }

    public void setDesarrolladorId(Integer desarrolladorId) {
        this.desarrolladorId = desarrolladorId;
    }

    public int getIncidenciaId() {
        return incidenciaId;
    }

    public void setIncidenciaId(int incidenciaId) {
        this.incidenciaId = incidenciaId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public int getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }
}
