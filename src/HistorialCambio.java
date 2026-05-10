import java.util.Date;

public class HistorialCambio {
    private int id;
    private EstadoTicket estadoAnterior;
    private EstadoTicket estadoNuevo;
    private String comentario;
    private String autor;
    private Date fechaCambio;

    public HistorialCambio(int id, EstadoTicket estadoAnterior, EstadoTicket estadoNuevo, String comentario, String autor, Date fechaCambio) {
        setId(id);
        setEstadoAnterior(estadoAnterior);
        setEstadoNuevo(estadoNuevo);
        setComentario(comentario);
        setAutor(autor);
        setFechaCambio(fechaCambio);
    }

    public String getResumen() {
        return String.format("%s -> %s por %s", estadoAnterior, estadoNuevo, autor);
    }

    public long getDuracionEstado() {
        long ahora = System.currentTimeMillis();
        return Math.max(0L, ahora - fechaCambio.getTime());
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = Validador.textoObligatorio(autor, "autor");
    }

    public Date getFechaCambio() {
        return new Date(fechaCambio.getTime());
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = Validador.fechaNoNula(fechaCambio, "fechaCambio");
    }
}
