import javax.swing.*;
import java.awt.event.*;

public class Login {
    public static void main(String[] args) {
        new LoginFrame();
    }

    public static class LoginFrame extends JFrame {
        public LoginFrame() {
            setTitle("Login");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(null);

            JLabel userLabel = new JLabel("Usuario:");
            userLabel.setBounds(20, 20, 80, 25);
            add(userLabel);

            JTextField usernameField = new JTextField();
            usernameField.setBounds(100, 20, 160, 25);
            add(usernameField);

            JLabel passLabel = new JLabel("Contraseña:");
            passLabel.setBounds(20, 60, 80, 25);
            add(passLabel);

            JPasswordField passwordField = new JPasswordField();
            passwordField.setBounds(100, 60, 160, 25);
            add(passwordField);

            JButton loginButton = new JButton("Entrar");
            loginButton.setBounds(40, 100, 90, 25);
            add(loginButton);

            JButton registerButton = new JButton("Registrarse");
            registerButton.setBounds(140, 100, 110, 25);
            add(registerButton);

            // Acción del botón Entrar
            loginButton.addActionListener(e -> {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Debe ingresar su usuario y contraseña. Si no está registrado, debe registrarse.",
                            "Campos vacíos",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean encontrado = false;
                for (Usuario u : Registrarse.listaUsuarios) {
                    if (u.getUsuario().equals(username) && u.getContraseña().equals(password)) {
                        JOptionPane.showMessageDialog(this,
                                "¡Bienvenido, " + u.getNombre() + " " + u.getApellido() + "!",
                                "Acceso concedido",
                                JOptionPane.INFORMATION_MESSAGE);
                        dispose();

                        Principal pantalla = new Principal();

                        // Agregar todos los usuarios a la tabla
                        for (Usuario user : Registrarse.listaUsuarios) {
                            pantalla.agregarUsuario(
                                    user.getNombre(),
                                    user.getApellido(),
                                    user.getTelefono(),
                                    user.getCorreo(),
                                    user.getUsuario()
                            );
                        }

                        pantalla.setVisible(true);
                        encontrado = true;
                        break;
                    }
                }

                if (!encontrado) {
                    JOptionPane.showMessageDialog(this,
                            "Usuario o contraseña incorrectos.",
                            "Error de acceso",
                            JOptionPane.ERROR_MESSAGE);
                }
            });

            // Acción del botón Registrarse
            registerButton.addActionListener(e -> {
                new Registrarse(); // Abre la nueva ventana de registro
            });

            setVisible(true);
        }
    }
}


