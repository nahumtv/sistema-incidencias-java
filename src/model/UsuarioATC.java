package model;
import java.util.ArrayList;

import enums.RolUsuario;
import enums.TurnoATC;
import utils.Validador;

public class UsuarioATC extends Usuario {
    private static int secuenciaTicket = 1000;
    private TurnoATC turno;



    private int totalTicketsRegistrados;
    private final ArrayList<Ticket> ticketsRegistrados;

    public UsuarioATC(int id, String nombre, String apellido, String correo, String contrasena, RolUsuario rol, TurnoATC turno, boolean activo) {
        super(id, nombre, apellido, correo, contrasena, rol, activo);
        setId(id);
        setTurno(turno);
        this.totalTicketsRegistrados = 0;
        this.ticketsRegistrados = new ArrayList<>();
    }

    public Ticket registrarTicket(Incidencia incidencia, String descripcion, NivelSeveridad severidad) {
        Validador.noNulo(incidencia, "incidencia");
        Ticket ticket = new Ticket(++secuenciaTicket, descripcion, severidad, "no-especificada", "no-especificado", incidencia);
        ticketsRegistrados.add(ticket);
        totalTicketsRegistrados = ticketsRegistrados.size();
        incidencia.incrementarFrecuencia();
        return ticket;
    }

    public void asignarTicket(Ticket ticket, Desarrollador desarrollador) {
        Validador.noNulo(ticket, "ticket");
        Validador.noNulo(desarrollador, "desarrollador");
        ticket.asignarDesarrollador(desarrollador);
    }

    public String consultarEstadoTicket(int idTicket) {
        for (Ticket ticket : ticketsRegistrados) {
            if (ticket.getId() == idTicket) {
                return ticket.getEstado().name();
            }
        }
        throw new IllegalArgumentException("No existe ticket con ese id");
    }

    public ArrayList<Ticket> listarTicketsPropios() {
        return ticketsRegistrados;
    }

    public TurnoATC getTurno() {
        return turno;
    }

    public void setTurno(TurnoATC turno) {
        this.turno = turno;
    }

    public int getTotalTicketsRegistrados() {
        return totalTicketsRegistrados;
    }
}
