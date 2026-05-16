package ui;

import enums.RolUsuario;
import model.Usuario;

import javax.swing.*;
import java.awt.*;

public class UsuarioFormFrame extends JFrame {

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;

    private JPasswordField txtContrasena;

    private JComboBox<RolUsuario> cmbRol;

    private JButton btnGuardar;

public UsuarioFormFrame() {

    JPanel formulario = new JPanel(new GridLayout(0, 2, 10, 10));
    formulario.setBorder(BorderFactory.createEmptyBorder(16,18,16,18));

    txtNombre = new JTextField(20);
    txtApellido = new JTextField(20);
    txtCorreo = new JTextField(20);
    txtContrasena = new JPasswordField(20);
    cmbRol = new JComboBox<>(RolUsuario.values());
    btnGuardar = new JButton("Guardar Usuario");

    formulario.add(new JLabel("Nombre"));
    formulario.add(txtNombre);

    formulario.add(new JLabel("Apellido"));
    formulario.add(txtApellido);

    formulario.add(new JLabel("Correo"));
    formulario.add(txtCorreo);

    formulario.add(new JLabel("Contraseña"));
    formulario.add(txtContrasena);

    formulario.add(new JLabel("Rol"));
    formulario.add(cmbRol);

    formulario.add(new JLabel(""));
    formulario.add(btnGuardar);

    setTitle("Crear Usuario");
    setSize(500, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    add(formulario);

    btnGuardar.addActionListener(e -> guardarUsuario());

    setVisible(true);
}


    private void guardarUsuario() {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String password = new String( txtContrasena.getPassword());
        RolUsuario rol = (RolUsuario) cmbRol.getSelectedItem();
        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuario.setContrasena(password);
        usuario.setRol(rol);
        usuario.setActivo(true);

        JOptionPane.showMessageDialog(this, "Usuario listo para guardar");

        // usuarioDAO.crear(usuario);
    }
}