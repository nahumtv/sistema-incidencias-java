import java.util.ArrayList;
import java.util.Date;

public class Ticket {
    private int id;
    private String descripcion;
    private EstadoTicket estado;
    private NivelSeveridad severidad;
    private String plataforma;
    private String pacienteAfectado;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    private Date fechaResolucion;
    private Desarrollador desarrolladorAsignado;
    private Incidencia incidencia;
    private final ArrayList<HistorialCambio> historialCambios;

    public Ticket(int id, String descripcion, NivelSeveridad severidad, String plataforma, String pacienteAfectado, Incidencia incidencia) {
        setId(id);
        setDescripcion(descripcion);
        setSeveridad(severidad);
        setPlataforma(plataforma);
        setPacienteAfectado(pacienteAfectado);
        setIncidencia(incidencia);
        this.estado = EstadoTicket.PENDIENTE;
        this.fechaCreacion = new Date();
        this.fechaActualizacion = new Date(this.fechaCreacion.getTime());
        this.historialCambios = new ArrayList<>();
    }

    public void cambiarEstado(EstadoTicket nuevoEstado, String comentario) {
        Validador.noNulo(nuevoEstado, "nuevoEstado");
        Validador.textoObligatorio(comentario, "comentario");
        EstadoTicket estadoAnterior = this.estado;
        this.estado = nuevoEstado;
        this.fechaActualizacion = new Date();
        if (nuevoEstado == EstadoTicket.RESUELTO || nuevoEstado == EstadoTicket.CERRADO) {
            this.fechaResolucion = new Date(this.fechaActualizacion.getTime());
        }
        String autor = desarrolladorAsignado != null ? desarrolladorAsignado.obtenerNombreCompleto() : "Sistema";
        HistorialCambio cambio = new HistorialCambio(
                historialCambios.size() + 1,
                estadoAnterior,
                nuevoEstado,
                comentario,
                autor,
                this.fechaActualizacion
        );
        historialCambios.add(cambio);
    }

    public void asignarDesarrollador(Desarrollador dev) {
        Validador.noNulo(dev, "desarrollador");
        this.desarrolladorAsignado = dev;
        if (this.estado == EstadoTicket.PENDIENTE) {
            cambiarEstado(EstadoTicket.EN_PROCESO, "Ticket asignado a desarrollador");
        }
    }

    public boolean esCriticoSinAtender() {
        if (severidad != NivelSeveridad.CRITICO || estado != EstadoTicket.PENDIENTE) {
            return false;
        }
        long dosHoras = 2L * 60L * 60L * 1000L;
        long transcurrido = System.currentTimeMillis() - fechaCreacion.getTime();
        return transcurrido > dosHoras;
    }

    public long calcularTiempoResolucion() {
        if (fechaResolucion == null) {
            return -1L;
        }
        return fechaResolucion.getTime() - fechaCreacion.getTime();
    }

    public HistorialCambio obtenerUltimaAccion() {
        if (historialCambios.isEmpty()) {
            return null;
        }
        return historialCambios.get(historialCambios.size() - 1);
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

    public Date getFechaCreacion() {
        return new Date(fechaCreacion.getTime());
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = Validador.fechaNoNula(fechaCreacion, "fechaCreacion");
    }

    public Date getFechaActualizacion() {
        return new Date(fechaActualizacion.getTime());
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = Validador.fechaNoNula(fechaActualizacion, "fechaActualizacion");
    }

    public Date getFechaResolucion() {
        return fechaResolucion == null ? null : new Date(fechaResolucion.getTime());
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = Validador.fechaNoNula(fechaResolucion, "fechaResolucion");
    }

    public Desarrollador getDesarrolladorAsignado() {
        return desarrolladorAsignado;
    }

    public void setDesarrolladorAsignado(Desarrollador desarrolladorAsignado) {
        Validador.noNulo(desarrolladorAsignado, "desarrolladorAsignado");
        this.desarrolladorAsignado = desarrolladorAsignado;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        Validador.noNulo(incidencia, "incidencia");
        this.incidencia = incidencia;
    }

    public ArrayList<HistorialCambio> getHistorialCambios() {
        return new ArrayList<>(historialCambios);
    }
}
