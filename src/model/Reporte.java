package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import enums.EstadoTicket;
import enums.TipoReporte;
import utils.Validador;

public class Reporte {
    private int id;
    private TipoReporte tipo;
    private LocalDateTime fechaDesde;
    private LocalDateTime fechaHasta;
    private int generadoPor;
    private LocalDateTime fechaGeneracion;

    public Reporte(int id, TipoReporte tipo, LocalDateTime fechaDesde, LocalDateTime fechaHasta, int generadoPor, LocalDateTime fechaGeneracion) {
        setId(id);
        setFechaDesde(fechaDesde);
        setFechaHasta(fechaHasta);
        setGeneradoPor(generadoPor);
        setFechaGeneracion(fechaGeneracion);
    }

    public ArrayList<Incidencia> generarTop5Frecuentes() {
        Map<String, Incidencia> porCategoria = new HashMap<>();
        // for (Ticket ticket : ticketsFiltradosPorPeriodo()) {
            // Incidencia incidencia = ticket.getIncidencia();
            // String categoria = incidencia.getCategoria();
            // if (!porCategoria.containsKey(categoria)) {
            //     porCategoria.put(categoria, new Incidencia(incidencia.getId(), categoria, incidencia.getDescripcionTipo(), 1));
            // } else {
            //     porCategoria.get(categoria).incrementarFrecuencia();
            // }
        // }
        ArrayList<Incidencia> resultado = new ArrayList<>(porCategoria.values());
        resultado.sort(Comparator.comparingInt(Incidencia::getFrecuencia).reversed());
        return resultado.size() > 5 ? new ArrayList<>(resultado.subList(0, 5)) : resultado;
    }

    public double calcularPromedioResolucion() {
        // ArrayList<Ticket> filtrados = ticketsFiltradosPorPeriodo();
        long suma = 0L;
        int cantidad = 0;
        // for (Ticket ticket : filtrados) {
        //     long tiempo = ticket.calcularTiempoResolucion();
        //     if (tiempo >= 0) {
        //         suma += tiempo;
        //         cantidad++;
        //     }
        // }
        if (cantidad == 0) {
            return 0.0;
        }
        return (double) suma / cantidad;
    }

    // public int getTotalTickets() {
    //     // return ticketsFiltradosPorPeriodo().size();
    // }

    public int getTotalPorEstado(EstadoTicket estado) {
        Validador.noNulo(estado, "estado");
        int total = 0;
        // for (Ticket ticket : ticketsFiltradosPorPeriodo()) {
        //     if (ticket.getEstado() == estado) {
        //         total++;
        //     }
        // }
        return total;
    }

    // private ArrayList<Ticket> ticketsFiltradosPorPeriodo() {
    //     ArrayList<Ticket> filtrados = new ArrayList<>();
    //     for (Ticket ticket : tickets) {
    //         Date fecha = ticket.getFechaCreacion();
    //         boolean desdeOk = !fecha.before(fechaDesde);
    //         boolean hastaOk = !fecha.after(fechaHasta);
    //         if (desdeOk && hastaOk) {
    //             filtrados.add(ticket);
    //         }
    //     }
    //     return filtrados;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Validador.mayorACero(id, "id");
        this.id = id;
    }

    public TipoReporte getTipo() {
        return tipo;
    }

    public void setTipo(TipoReporte tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDateTime fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDateTime getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDateTime fechaHasta) {
        Validador.noNulo(fechaHasta, "fechaHasta");
        if (this.fechaDesde != null && fechaHasta.isBefore(this.fechaDesde)) {
            throw new IllegalArgumentException("La fecha hasta no puede ser menor a fecha desde");
        }
        // this.fechaHasta = new Date(fechaHasta.getTime());
    }

    public int getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneradoPor(int generadoPor) {
        this.generadoPor = generadoPor;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = Validador.fechaNoNula(fechaGeneracion, "fechaGeneracion");
    }

    // public ArrayList<Ticket> getTickets() {
    //     return new ArrayList<>(tickets);
    // }
}
