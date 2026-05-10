import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reporte {
    private int id;
    private Date fechaDesde;
    private Date fechaHasta;
    private String generadoPor;
    private Date fechaGeneracion;
    private final ArrayList<Ticket> tickets;

    public Reporte(int id, Date fechaDesde, Date fechaHasta, String generadoPor, Date fechaGeneracion, ArrayList<Ticket> tickets) {
        setId(id);
        setFechaDesde(fechaDesde);
        setFechaHasta(fechaHasta);
        setGeneradoPor(generadoPor);
        setFechaGeneracion(fechaGeneracion);
        Validador.noNulo(tickets, "tickets");
        this.tickets = new ArrayList<>(tickets);
    }

    public ArrayList<Incidencia> generarTop5Frecuentes() {
        Map<String, Incidencia> porCategoria = new HashMap<>();
        for (Ticket ticket : ticketsFiltradosPorPeriodo()) {
            Incidencia incidencia = ticket.getIncidencia();
            String categoria = incidencia.getCategoria();
            if (!porCategoria.containsKey(categoria)) {
                porCategoria.put(categoria, new Incidencia(incidencia.getId(), categoria, incidencia.getDescripcionTipo(), 1));
            } else {
                porCategoria.get(categoria).incrementarFrecuencia();
            }
        }
        ArrayList<Incidencia> resultado = new ArrayList<>(porCategoria.values());
        resultado.sort(Comparator.comparingInt(Incidencia::getFrecuencia).reversed());
        return resultado.size() > 5 ? new ArrayList<>(resultado.subList(0, 5)) : resultado;
    }

    public double calcularPromedioResolucion() {
        ArrayList<Ticket> filtrados = ticketsFiltradosPorPeriodo();
        long suma = 0L;
        int cantidad = 0;
        for (Ticket ticket : filtrados) {
            long tiempo = ticket.calcularTiempoResolucion();
            if (tiempo >= 0) {
                suma += tiempo;
                cantidad++;
            }
        }
        if (cantidad == 0) {
            return 0.0;
        }
        return (double) suma / cantidad;
    }

    public int getTotalTickets() {
        return ticketsFiltradosPorPeriodo().size();
    }

    public int getTotalPorEstado(EstadoTicket estado) {
        Validador.noNulo(estado, "estado");
        int total = 0;
        for (Ticket ticket : ticketsFiltradosPorPeriodo()) {
            if (ticket.getEstado() == estado) {
                total++;
            }
        }
        return total;
    }

    private ArrayList<Ticket> ticketsFiltradosPorPeriodo() {
        ArrayList<Ticket> filtrados = new ArrayList<>();
        for (Ticket ticket : tickets) {
            Date fecha = ticket.getFechaCreacion();
            boolean desdeOk = !fecha.before(fechaDesde);
            boolean hastaOk = !fecha.after(fechaHasta);
            if (desdeOk && hastaOk) {
                filtrados.add(ticket);
            }
        }
        return filtrados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Validador.mayorACero(id, "id");
        this.id = id;
    }

    public Date getFechaDesde() {
        return new Date(fechaDesde.getTime());
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = Validador.fechaNoNula(fechaDesde, "fechaDesde");
    }

    public Date getFechaHasta() {
        return new Date(fechaHasta.getTime());
    }

    public void setFechaHasta(Date fechaHasta) {
        Validador.noNulo(fechaHasta, "fechaHasta");
        if (this.fechaDesde != null && fechaHasta.before(this.fechaDesde)) {
            throw new IllegalArgumentException("La fecha hasta no puede ser menor a fecha desde");
        }
        this.fechaHasta = new Date(fechaHasta.getTime());
    }

    public String getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneradoPor(String generadoPor) {
        this.generadoPor = Validador.textoObligatorio(generadoPor, "generadoPor");
    }

    public Date getFechaGeneracion() {
        return new Date(fechaGeneracion.getTime());
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = Validador.fechaNoNula(fechaGeneracion, "fechaGeneracion");
    }

    public ArrayList<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }
}
