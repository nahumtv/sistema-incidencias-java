package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import config.ConnectionDB;
import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
    @Override
    public boolean crear(Usuario usuario){
        System.out.println("Usuario ===> " + usuario.getNombre() + usuario.getApellido() + usuario.getCorreo() + usuario.getContrasena() + usuario.getRol().name() + usuario.isActivo() );
        String query = """
            INSERT INTO usuarios(
                nombre, apellido, correo, contrasena, rol, activo
            )
            VALUES(?, ?, ?, ?, ?::rol_usuario,?)
        """;

        try (

            Connection conn = ConnectionDB.establecerConexion();
            PreparedStatement ps = conn.prepareStatement(query);
        )
        {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContrasena());
            ps.setString(5, usuario.getRol().name());
            ps.setBoolean(6, usuario.isActivo());

            int filas = ps.executeUpdate();
            System.out.println(" Usuario creado " + filas);

            if(filas > 0) {
                return true;
            }

        } catch (Exception e) {
           System.out.println("Hubo un error al crear un usuario " + e.getMessage()); 
        }

        return false;

    }

     @Override
     public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        return usuarios;
     }

     @Override
     public void actualizar(Usuario usuario){

     }

     @Override
     public Optional<Usuario> burcarPorId(int id){

        return Optional.empty();
     }

     @Override
     public void eliminar(int id){

     }
    
}
