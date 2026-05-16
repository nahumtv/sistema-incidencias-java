import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import enums.RolUsuario;
import model.Usuario;

public class App {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        Usuario usuario = new Usuario(
            1, 
            "Nahum", 
            "Trinidad Venancio", 
            "nahum.trinidadv@gmail.com", 
            "prueba123", 
            RolUsuario.ADMIN, 
            true
        );
        usuarioDAO.crear(usuario);
        // try {

        // } catch (Exception e) {
        // }
        // UsuarioATC atc = new UsuarioATC(1, "Ana", "Rojas", "ana@salud.com", "clave123", "ATC", "manana");
        // Desarrollador dev = new Desarrollador(2, "Luis", "Paz", "luis@salud.com", "clave123", "DEV", "Backend");

        // Ticket ticket = atc.registrarTicket(
        //         new Incidencia(1, "videollamada", "videollamada no inicia", 0),
        //         "Paciente no logra iniciar videollamada",
        //         NivelSeveridad.CRITICO
        // );
        // ticket.setPlataforma("web");
        // ticket.setPacienteAfectado("Carlos Perez");
        // System.out.println("1) Ticket registrado: " + ticket.getId() + " - " + ticket.getEstado());

        // atc.asignarTicket(ticket, dev);
        // System.out.println("2) Ticket asignado: " + ticket.getEstado());

        // dev.actualizarEstadoTicket(ticket, EstadoTicket.RESUELTO, "Se corrigio configuracion de permisos");
        // System.out.println("3) Ticket actualizado: " + ticket.getEstado());

        // ArrayList<Ticket> tickets = new ArrayList<>();
        // tickets.add(ticket);

        // Date ahora = new Date();
        // Date inicio = new Date(ahora.getTime() - 7L * 24L * 60L * 60L * 1000L);
        // Reporte reporte = new Reporte(1, inicio, ahora, "Coordinador TI", ahora, tickets);

        // System.out.println("4) Top incidencias: " + reporte.generarTop5Frecuentes().size());
        // System.out.println("5) Historial del ticket: " + ticket.getHistorialCambios().size());
        // for (HistorialCambio cambio : ticket.getHistorialCambios()) {
        //     System.out.println("   - " + cambio.getResumen() + " | " + cambio.getFechaCambio());
        // }
        
    }
}
