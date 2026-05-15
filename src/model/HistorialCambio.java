package model;
import java.time.LocalDateTime;
import java.util.Date;

import enums.EstadoTicket;
import utils.Validador;

public class HistorialCambio {
   private int id;
    private int ticketId;
    private int autorId;
    private EstadoTicket estadoAnterior;
    private EstadoTicket estadoNuevo;
    private String comentario;
    private LocalDateTime fechaCambio;

    public HistorialCambio(int id, int ticketId, int autorId, EstadoTicket estadoAnterior, EstadoTicket estadoNuevo, String comentario, LocalDateTime fechaCambio) {
        setId(id);
        setEstadoAnterior(estadoAnterior);
        setEstadoNuevo(estadoNuevo);
        setComentario(comentario);
        setAutorId(autorId);
        setFechaCambio(fechaCambio);
    }

    public String getResumen() {
        return String.format("%s -> %s por %s", estadoAnterior, estadoNuevo);
    }

    public long getDuracionEstado() {
        long ahora = System.currentTimeMillis();
        return Math.max(0L, ahora - fechaCambio.getDayOfMonth());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Validador.mayorACero(id, "id");
        this.id = id;
    }

    public EstadoTicket getEstadoAnterior() {
        return estadoAnterior;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public void setEstadoAnterior(EstadoTicket estadoAnterior) {
        Validador.noNulo(estadoAnterior, "estadoAnterior");
        this.estadoAnterior = estadoAnterior;
    }

    public EstadoTicket getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(EstadoTicket estadoNuevo) {
        Validador.noNulo(estadoNuevo, "estadoNuevo");
        this.estadoNuevo = estadoNuevo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = Validador.textoObligatorio(comentario, "comentario");
    }

    public Date getFechaCambio() {
        return new Date(fechaCambio.getDayOfMonth());
    }

    public void setFechaCambio(LocalDateTime fechaCambio) {
        this.fechaCambio = fechaCambio;
    }
}
