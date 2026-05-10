public class Incidencia {
    private int id;
    private String categoria;
    private String descripcionTipo;
    private int frecuencia;

    public Incidencia(int id, String categoria, String descripcionTipo, int frecuencia) {
        setId(id);
        setCategoria(categoria);
        setDescripcionTipo(descripcionTipo);
        setFrecuencia(frecuencia);
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
}
