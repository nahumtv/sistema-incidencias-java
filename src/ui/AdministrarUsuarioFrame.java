package ui;

import model.Usuario;
import service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class AdministrarUsuarioFrame extends JFrame {

    private JTextField txtBuscar;

    private JButton btnBuscar;
    private JButton btnListar;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnEliminar;

    private JTable tabla;

    private DefaultTableModel modelo;

    private UsuarioService usuarioService;

    public AdministrarUsuarioFrame() {

        usuarioService = new UsuarioService();

        setTitle("Gestión Usuarios");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtBuscar = new JTextField(10);
        btnBuscar = new JButton("Buscar");
        btnListar = new JButton("Listar");
        btnNuevo = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        topPanel.add(new JLabel("ID"));
        topPanel.add(txtBuscar);
        topPanel.add(btnBuscar);
        topPanel.add(btnListar);
        topPanel.add(btnNuevo);
        topPanel.add(btnEditar);
        topPanel.add(btnEliminar);

        add(topPanel, BorderLayout.NORTH);


        modelo = new DefaultTableModel();
        modelo.addColumn("ID OK");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Correo");
        modelo.addColumn("Rol");

        tabla = new JTable(modelo);

        JScrollPane scrollPane = new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);
        
        // acciones que se puede hacer
        btnListar.addActionListener(e -> listarUsuarios());
        btnBuscar.addActionListener(e -> buscarUsuario());
        btnNuevo.addActionListener(e -> {
            new UsuarioFormFrame().setVisible(true);
        });

        btnEliminar.addActionListener(e -> eliminarUsuario());

        btnEditar.addActionListener(e -> editarUsuario());

        setVisible(true);
    }

    private void listarUsuarios() {
        modelo.setRowCount(0);
        List<Usuario> usuarios = usuarioService.listar();
        for (Usuario usuario : usuarios) {
            modelo.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getCorreo(),
                    usuario.getRol()
            });
        }
    }


    private void buscarUsuario() {

        try {
            int id = Integer.parseInt(txtBuscar.getText());

            Optional<Usuario> opcionalUsuario = usuarioService.buscarPorId(id);
            Usuario usuario = opcionalUsuario.get();
            modelo.setRowCount(0);

            if (usuario != null) {
                modelo.addRow(new Object[]{
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getCorreo(),
                        usuario.getRol()
                });

            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    private void eliminarUsuario() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario");

            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            usuarioService.eliminar(id);

            listarUsuarios();
        }
    }

    private void editarUsuario() {

        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario");

            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);

        Usuario usuario = usuarioService.buscarPorId(id).get();

        if (usuario != null) {
            new UsuarioFormFrame(usuario);
        }
    }
}