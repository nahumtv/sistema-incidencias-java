package ui;

import model.Usuario;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private Usuario usuario;
    private JPanel izqPanel;
    private JPanel tituloPanel;
    private JPanel contenidoPanel;

    private JButton btnUsuarios;
    private JButton btnTickets;
    private JButton btnIncidencias;
    private JButton btnReportes;
    private JButton btnSalir;

    // Label user
    private JLabel lblBienvenida;

    // public DashboardFrame(Usuario usuario) {
    public DashboardFrame() {

        // this.usuario = usuario;

        // lado izquierdo
        izqPanel = new JPanel();
        izqPanel.setLayout(new GridLayout(10, 1, 10, 10));

        btnUsuarios = new JButton("Usuarios");
        btnTickets = new JButton("Tickets");
        btnIncidencias = new JButton("Incidencias");
        btnReportes = new JButton("Reportes");
        btnSalir = new JButton("Cerrar Sesión");

        izqPanel.add(btnUsuarios);
        izqPanel.add(btnTickets);
        izqPanel.add(btnIncidencias);
        izqPanel.add(btnReportes);
        izqPanel.add(btnSalir);

        // titulo de dashboard
        tituloPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // lblBienvenida = new JLabel("Bienvenido " + usuario.getNombre() + " - " + usuario.getRol());
        lblBienvenida = new JLabel("Bienvenido " );
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 18));

        tituloPanel.add(lblBienvenida);

        // contenido
        contenidoPanel = new JPanel();
        contenidoPanel.setLayout(new BorderLayout());
        JLabel lblHome = new JLabel("Panel Principal", SwingConstants.CENTER);
        lblHome.setFont(new Font("Arial", Font.BOLD, 24));
        contenidoPanel.add(lblHome, BorderLayout.CENTER);

        setTitle("Dashboard");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // lado izquierdo
        izqPanel.setPreferredSize(new Dimension(200, 0));

        add(tituloPanel, BorderLayout.NORTH);
        add(izqPanel, BorderLayout.WEST);
        add(contenidoPanel, BorderLayout.CENTER);
        setVisible(true);

        btnUsuarios.addActionListener(e -> abrirUsuarios());
        btnTickets.addActionListener(e -> abrirTickets());
        btnIncidencias.addActionListener(e -> abrirIncidencias());
        btnReportes.addActionListener(e -> abrirReportes());
        btnSalir.addActionListener(e -> cerrarSesion());
    }

    private void abrirUsuarios() {
        JFrame frame = new UsuarioFormFrame();

        frame.setVisible(true);
    }

    private void abrirTickets() {

        // JFrame frame =
                // new TicketFormFrame(usuario);

        // frame.setVisible(true);
    }

    private void abrirIncidencias() {
        JOptionPane.showMessageDialog(this, "Módulo incidencias próximamente");
    }

    private void abrirReportes() {
        JOptionPane.showMessageDialog(this, "Módulo reportes próximamente");
    }

    private void cerrarSesion() {
        dispose();

        new LoginFrame().setVisible(true);
    }
}