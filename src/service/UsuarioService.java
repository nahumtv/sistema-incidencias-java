package service;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import model.Usuario;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {

        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public boolean crear(Usuario usuario) {

        boolean usuarioCreado = usuarioDAO.crear(usuario);

        if (!usuarioCreado) {
            System.out.println("Usuaro no creado");
            return false;
        }

        return true;
    }
}