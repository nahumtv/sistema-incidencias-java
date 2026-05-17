package service;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import model.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {

        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public boolean crear(Usuario usuario) {

        boolean usuarioCreado = usuarioDAO.crear(usuario);

        if (!usuarioCreado) {
            System.out.println("Usuario no creado");
            return false;
        }

        return true;
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    public Optional<Usuario> buscarPorId(int id) {
        return usuarioDAO.burcarPorId(id);
    }

    public boolean actualizar(Usuario usuario) {
        try {
            usuarioDAO.actualizar(usuario);

            return true;

        } catch (Exception ex) {
            System.out.println("Error al actualizar usuario: " + ex.getMessage());

            return false;
        }
    }

    public boolean eliminar(int id) {
        return usuarioDAO.eliminar(id);
    }
}