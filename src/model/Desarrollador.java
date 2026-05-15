package model;
import java.util.ArrayList;

import enums.EstadoTicket;
import enums.RolUsuario;
import utils.Validador;

public class Desarrollador extends Usuario {
    private String especialidad;
    private int cargaActual;
    private boolean disponible;

    private final ArrayList<Ticket> ticketsAsignados;

    public Desarrollador(int id, String nombre, String apellido, String correo, String contrasena, RolUsuario rol, String especialidad, boolean activo) {
        super(id, nombre, apellido, correo, contrasena, rol, activo);
        setEspecialidad(especialidad);
        this.cargaActual = 0;
        this.ticketsAsignados = new ArrayList<>();
    }

    public void actualizarEstadoTicket(Ticket ticket, EstadoTicket nuevoEstado, String comentario) {
        validarTicketAsignado(ticket);
        ticket.setEstado(nuevoEstado);
    }

    public ArrayList<Ticket> listarTicketsAsignados() {
        return ticketsAsignados;
    }

    public int obtenerCargaActual() {
        this.cargaActual = ticketsAsignados.size();
        return cargaActual;
    }

    public void marcarResuelto(Ticket ticket, String comentario) {
        validarTicketAsignado(ticket);
        ticket.setEstado(EstadoTicket.RESUELTO);
    }

    private void validarTicketAsignado(Ticket ticket) {
        Validador.noNulo(ticket, "ticket");
        if (ticket.getDesarrolladorId() != this.getId()) {
            throw new IllegalArgumentException("El ticket no esta asignado al desarrollador");
        }
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = Validador.textoObligatorio(especialidad, "especialidad");
    }

    public int getCargaActual() {
        return cargaActual;
    }

    public void setCargaActual(int cargaActual) {
        Validador.noNegativo(cargaActual, "cargaActual");
        this.cargaActual = cargaActual;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
