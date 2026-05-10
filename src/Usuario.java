public abstract class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String rol;

    public Usuario(int id, String nombre, String apellido, String correo, String contrasena, String rol) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setContrasena(contrasena);
        setRol(rol);
    }

    public boolean autenticar(String correo, String contrasena) {
        if (correo == null || contrasena == null) {
            return false;
        }
        return this.correo.equals(correo) && this.contrasena.equals(contrasena);
    }

    public String obtenerNombreCompleto() {
        return nombre + " " + apellido;
    }

    public void cambiarContrasena(String nueva) {
        setContrasena(nueva);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Validador.mayorACero(id, "id");
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = Validador.textoObligatorio(nombre, "nombre");
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = Validador.textoObligatorio(apellido, "apellido");
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        Validador.correoValido(correo);
        this.correo = correo.trim();
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = Validador.textoObligatorio(contrasena, "contrasena");
        Validador.longitudMinima(this.contrasena, 6, "contrasena");
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = Validador.textoObligatorio(rol, "rol");
    }
}
