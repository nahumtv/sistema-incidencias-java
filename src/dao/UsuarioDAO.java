package dao;

import java.util.List;
import java.util.Optional;

import model.Usuario;

public interface UsuarioDAO {
    boolean crear(Usuario usuario);
    Optional<Usuario> burcarPorId(int id);
    Optional<Usuario> buscarPorCorreo(String correo);
    List<Usuario> listar();
    void actualizar(Usuario usuario);
    boolean eliminar(int id);

}
