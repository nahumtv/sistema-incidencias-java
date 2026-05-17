package ui;

import enums.RolUsuario;
import model.Usuario;
import service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class UsuarioFormFrame extends JFrame {

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JComboBox<RolUsuario> cmbRol;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JLabel lblEstado;
    private UsuarioService usuarioService;

    public UsuarioFormFrame() {
        usuarioService = new UsuarioService();

        JPanel formulario = new JPanel(new GridLayout(0, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(16,18,16,18));

        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtCorreo = new JTextField(20);
        txtContrasena = new JPasswordField(20);
        cmbRol = new JComboBox<>(RolUsuario.values());
        btnGuardar = new JButton("Guardar Usuario");
        btnLimpiar = new JButton("Limpiar");
        lblEstado = new JLabel("");

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

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));

        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        formulario.add(lblEstado);
        formulario.add(panelBotones);

        setTitle("Crear Usuario");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(formulario);

        btnGuardar.addActionListener(e -> guardarUsuario());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        setVisible(true);
    }


    private void guardarUsuario() {
        try {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String correo = txtCorreo.getText().trim();
            String password = new String( txtContrasena.getPassword());
            RolUsuario rol = (RolUsuario) cmbRol.getSelectedItem();
            Usuario usuario = new Usuario();

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);
            usuario.setContrasena(password);
            usuario.setRol(rol);
            usuario.setActivo(true);

            if(nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                lblEstado.setText("⚠️ Completa todos los campos");
                lblEstado.setForeground(Color.RED);
                return;
            }

            boolean usuarioGuardado =  usuarioService.crear(usuario);

            if(usuarioGuardado) {
                lblEstado.setText("✅ Usuario guardado correctamente");
                lblEstado.setForeground(Color.GREEN);
            }

        } catch (Exception e) {
                lblEstado.setText("⚠️ " + e.getMessage());
                lblEstado.setForeground(Color.RED);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        lblEstado.setText("");
    }
}