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
    private Usuario usuarioEditar;

    // si no existe usuario entonces debe crear
    public UsuarioFormFrame() {
        this(null);
    }

    // si existe usuario entonces puede editar
    public UsuarioFormFrame(Usuario usuario) {

        this.usuarioEditar = usuario;

        usuarioService = new UsuarioService();

        JPanel formulario = new JPanel(new GridLayout(0, 2, 10, 10));

        formulario.setBorder(
                BorderFactory.createEmptyBorder(16, 18, 16, 18)
        );

        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtCorreo = new JTextField(20);
        txtContrasena = new JPasswordField(20);

        cmbRol = new JComboBox<>(RolUsuario.values());

        btnGuardar = new JButton();
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

        JPanel panelBotones = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 0)
        );

        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);

        formulario.add(lblEstado);
        formulario.add(panelBotones);

        if (usuarioEditar == null) {
            setTitle("Crear Usuario");
            btnGuardar.setText("Guardar Usuario");
        } else {
            setTitle("Editar Usuario");
            btnGuardar.setText("Actualizar Usuario");
            cargarDatosUsuario();
        }

        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(formulario);

        btnGuardar.addActionListener(e -> guardarUsuario());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        setVisible(true);
    }

    private void cargarDatosUsuario() {

        txtNombre.setText(usuarioEditar.getNombre());
        txtApellido.setText(usuarioEditar.getApellido());
        txtCorreo.setText(usuarioEditar.getCorreo());
        txtContrasena.setText(usuarioEditar.getContrasena());
        cmbRol.setSelectedItem(usuarioEditar.getRol());
    }


    private void guardarUsuario() {

        try {

            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String correo = txtCorreo.getText().trim();
            String password = new String(txtContrasena.getPassword());

            RolUsuario rol = (RolUsuario) cmbRol.getSelectedItem();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                lblEstado.setText("⚠️ Completa todos los campos");
                lblEstado.setForeground(Color.RED);
                return;
            }

            Usuario usuario;

            if (usuarioEditar == null) {
                usuario = new Usuario();
            } else {
                usuario = usuarioEditar;
            }
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);
            usuario.setContrasena(password);
            usuario.setRol(rol);
            usuario.setActivo(true);
            boolean resultado;

            if (usuarioEditar == null) {
                resultado = usuarioService.crear(usuario);
                if (resultado) {
                    lblEstado.setText("✅ Usuario guardado correctamente");
                    lblEstado.setForeground(Color.GREEN);
                    limpiarCampos();
                }
            } else {
                resultado = usuarioService.actualizar(usuario);
                if (resultado) {
                    lblEstado.setText("✅ Usuario actualizado correctamente");
                    lblEstado.setForeground(Color.GREEN);
                }
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
        cmbRol.setSelectedIndex(0);
        lblEstado.setText("");
    }
}