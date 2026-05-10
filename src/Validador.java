import java.util.Date;

public class Validador {
    private Validador() {
    }

    public static void mayorACero(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser mayor a 0");
        }
    }

    public static void noNegativo(int valor, String campo) {
        if (valor < 0) {
            throw new IllegalArgumentException("El campo " + campo + " no puede ser negativo");
        }
    }

    public static String textoObligatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
        return valor.trim();
    }

    public static void correoValido(String correo) {
        String limpio = textoObligatorio(correo, "correo");
        if (!limpio.contains("@") || limpio.startsWith("@") || limpio.endsWith("@")) {
            throw new IllegalArgumentException("El correo no es valido");
        }
    }

    public static void longitudMinima(String valor, int minimo, String campo) {
        if (valor == null || valor.length() < minimo) {
            throw new IllegalArgumentException("El campo " + campo + " debe tener al menos " + minimo + " caracteres");
        }
    }

    public static <T> void noNulo(T valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio");
        }
    }

    public static Date fechaNoNula(Date valor, String campo) {
        noNulo(valor, campo);
        return new Date(valor.getTime());
    }
}
