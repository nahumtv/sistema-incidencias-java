package service;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import model.Usuario;

import java.util.Optional;

public class AuthService {

    private final UsuarioDAO usuarioDAO;

    public AuthService() {

        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public Optional<Usuario> login(
            String correo,
            String contrasena
    ) {

        Optional<Usuario> optionalUsuario = usuarioDAO.buscarPorCorreo(correo);

        if (optionalUsuario.isEmpty()) {
            System.out.println("No existe usuario");
            return Optional.empty();
        }

        Usuario usuario = optionalUsuario.get();

        if (!usuario.getContrasena().equals(contrasena)) {
            return Optional.empty();
        }

        return Optional.of(usuario);
    }
}