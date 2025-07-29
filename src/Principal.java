import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Principal extends JFrame {
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar, btnEliminar, btnCerrarSesion;

    public Principal() {
        setTitle("Pantalla Principal");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnas = {"Nombre", "Apellido", "Teléfono", "Correo", "Usuario"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaUsuarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);

        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnCerrarSesion = new JButton("Cerrar Sesión");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrarSesion);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnCerrarSesion.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sesión cerrada.");
            dispose();
            new Login.LoginFrame();
        });

        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnActualizar.addActionListener(e -> actualizarUsuario());
    }

    public void agregarUsuario(String nombre, String apellido, String telefono, String correo, String usuario) {
        modeloTabla.addRow(new Object[]{nombre, apellido, telefono, correo, usuario});
    }

    private void eliminarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila != -1) {
            String usuario = (String) modeloTabla.getValueAt(fila, 4); // Columna "Usuario"

            // Eliminar de la lista en memoria
            Registrarse.listaUsuarios.removeIf(u -> u.getUsuario().equals(usuario));

            // Eliminar de la tabla
            modeloTabla.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.");
        }
    }

    private void actualizarUsuario() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila != -1) {
            String usuarioOriginal = (String) modeloTabla.getValueAt(fila, 4);

            for (int i = 0; i < tablaUsuarios.getColumnCount(); i++) {
                String nuevoValor = JOptionPane.showInputDialog(this,
                        "Nuevo valor para " + tablaUsuarios.getColumnName(i),
                        modeloTabla.getValueAt(fila, i));
                if (nuevoValor != null && !nuevoValor.trim().isEmpty()) {
                    modeloTabla.setValueAt(nuevoValor, fila, i);
                }
            }

            // Actualizar en la lista en memoria
            for (Usuario u : Registrarse.listaUsuarios) {
                if (u.getUsuario().equals(usuarioOriginal)) {
                    u.setNombre((String) modeloTabla.getValueAt(fila, 0));
                    u.setApellido((String) modeloTabla.getValueAt(fila, 1));
                    u.setTelefono((String) modeloTabla.getValueAt(fila, 2));
                    u.setCorreo((String) modeloTabla.getValueAt(fila, 3));
                    u.setUsuario((String) modeloTabla.getValueAt(fila, 4));
                    break;
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para actualizar.");
        }
    }
}



