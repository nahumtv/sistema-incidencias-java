package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import model.Usuario;
import service.AuthService;

// import dao.UsuarioDAO;
// import dao.impl.UsuarioDAOImpl;
// import model.Usuario;

public class LoginFrame extends JFrame {
    private JLabel lblTitulo;
    private JLabel lblCorreo;
    private JTextField txtCorreo;
    private JLabel lblContrasena;
    private JPasswordField txtContrasena;
    private JButton btnLogin;
    private JButton btnLimpiar;
    private JLabel lblEstado;
    private AuthService authService;

    public LoginFrame() {
        authService = new AuthService();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        setTitle("Inicia Sesion");
        setSize(550, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Norte titulo

        lblTitulo = new JLabel("Ingresa usuario y contraseña", SwingConstants.CENTER);

        add(lblTitulo, BorderLayout.NORTH);

        // Formulario de inicio de sesion centro

        JPanel formulario = new JPanel(new GridLayout(3,1,4,4));

        formulario.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));

        lblCorreo = new JLabel("Correo");
        formulario.add(lblCorreo);
        txtCorreo = new JTextField();

        lblContrasena = new JLabel("Contrasena");
        formulario.add(lblContrasena);
        txtContrasena = new JPasswordField();


        formulario.add(txtCorreo);
        formulario.add(txtContrasena);

        add(formulario, BorderLayout.CENTER);

        // Sur
        btnLogin = new JButton("Iniciar Sesion");
        btnLogin.setBackground(new Color(6, 90, 150));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFocusPainted(false);

        // JPanel
        JPanel panelIzq = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 8));
        lblEstado = new JLabel("");
        panelIzq.add(lblEstado);

        JPanel panelDer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        btnLimpiar = new JButton("Limpiar");
        panelDer.add(btnLimpiar);

        JPanel panelSur = new JPanel(new FlowLayout());
 // sur
        panelSur.add(btnLogin, BorderLayout.CENTER);
        panelSur.add(panelIzq);
        panelSur.add(panelDer);

        add(panelSur, BorderLayout.SOUTH);

        btnLimpiar.addActionListener(e-> {
            lblEstado.setText("");
            txtCorreo.setText("");
            txtContrasena.setText("");

        });

        btnLogin.addActionListener(e -> {
            String correo = txtCorreo.getText().trim();
            String contrasena = new String(txtContrasena.getPassword());

            if(correo.isEmpty() || contrasena.isEmpty()) {
                lblEstado.setText("⚠️ Completa el correo y la contrasena");
                lblEstado.setForeground(Color.RED);
                return;
            }
            try {
                Optional<Usuario> opcionalUsuario = authService.login(correo, contrasena);

                if(opcionalUsuario.isEmpty()) {
                    lblEstado.setText("Usuario o contrasena incorrecto ");
                    lblEstado.setForeground(Color.RED);
                    return;
                } 

                lblEstado.setText("Listo !!");
                lblEstado.setForeground(Color.GREEN);
                Usuario usuario = opcionalUsuario.get();

                JOptionPane.showMessageDialog(
                        this,
                        "Bienvenido " + usuario.getNombre()
                );

                dispose();

                new DashboardFrame(usuario);


            } catch (Exception ex) {
                System.out.print("Error en inicio de sesion => " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
