import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Registrarse extends JFrame {
    private JTextField txtNombre, txtApellido, txtUsuario, txtTelefono, txtCorreo;
    private JPasswordField txtContraseña, txtConfirmar;

    // Lista estática para almacenar usuarios en memoria
    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public Registrarse() {
        setTitle("Registro de Usuario");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10)); // 8 filas, 2 columnas
        txtUsuario = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();
        txtContraseña = new JPasswordField();
        txtConfirmar = new JPasswordField();

        add(new JLabel("Nombre de usuario:"));
        add(txtUsuario);
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Apellido:"));
        add(txtApellido);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(new JLabel("Correo electrónico:"));
        add(txtCorreo);
        add(new JLabel("Contraseña:"));
        add(txtContraseña);
        add(new JLabel("Confirmar contraseña:"));
        add(txtConfirmar);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnRegistrar);
        add(btnCancelar);

        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void registrarUsuario() {
        String usuario = txtUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contraseña = new String(txtContraseña.getPassword());
        String confirmar = new String(txtConfirmar.getPassword());

        if (usuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                telefono.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || confirmar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!contraseña.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si el usuario ya existe
        for (Usuario u : listaUsuarios) {
            if (u.getUsuario().equals(usuario)) {
                JOptionPane.showMessageDialog(this, "El nombre de usuario ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Crear y guardar el nuevo usuario
        Usuario nuevo = new Usuario(nombre, apellido, usuario, telefono, correo, contraseña);
        listaUsuarios.add(nuevo);

        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
        dispose();
    }
}
