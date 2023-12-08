package interfaces;

import conex.conexion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conex.conexion;
import conex.consultas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Nuevo_Usuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JPasswordField passwordContraseña;
	private JTextField textApellido;
	private JTextField textDNI;
	private JTextField textTelefono;
	private JTextField textCargo;
	private JTextField textTipoUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nuevo_Usuario frame = new Nuevo_Usuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Nuevo_Usuario() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(64, 128, 128));
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(36, 58, 144, 37);
		contentPane.add(lblNombre);

		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseña.setForeground(new Color(64, 128, 128));
		lblContraseña.setFont(new Font("Dialog", Font.BOLD, 20));
		lblContraseña.setBackground(Color.WHITE);
		lblContraseña.setBounds(36, 154, 144, 46);
		contentPane.add(lblContraseña);

		textNombre = new JTextField();
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setForeground(Color.BLACK);
		textNombre.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textNombre.setColumns(10);
		textNombre.setBackground(Color.LIGHT_GRAY);
		textNombre.setBounds(193, 59, 242, 37);
		contentPane.add(textNombre);

		passwordContraseña = new JPasswordField();
		passwordContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		passwordContraseña.setForeground(Color.BLACK);
		passwordContraseña.setBackground(Color.LIGHT_GRAY);
		passwordContraseña.setBounds(193, 163, 242, 37);
		contentPane.add(passwordContraseña);

		JLabel lblRegistroNuevoUsuario = new JLabel("Registro nuevo Usuario");
		lblRegistroNuevoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroNuevoUsuario.setForeground(new Color(64, 128, 128));
		lblRegistroNuevoUsuario.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblRegistroNuevoUsuario.setBackground(Color.WHITE);
		lblRegistroNuevoUsuario.setBounds(65, 0, 357, 48);
		contentPane.add(lblRegistroNuevoUsuario);

		JButton btnGuardar = new JButton("     Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtiene las credenciales ingresadas por el usuario
				String Nombre = textNombre.getText();
				String Apellido = textApellido.getText();
				String Contraseña = new String(passwordContraseña.getPassword());
				String DNI = textDNI.getText();
				String Telefono = textTelefono.getText();
				String Cargo = textCargo.getText();
				String id_tipo_usuario = textTipoUsuario.getText();

				conexion conexionBD = new conexion();

				Connection conexion = conexionBD.obtenerConexion();

				consultas consultasBD = new consultas();
				if (consultasBD.registrarUsuario(Nombre, Apellido, Contraseña, DNI, Telefono, Cargo, id_tipo_usuario,
						conexion)) {

					JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
					
					textNombre.setText("");
					passwordContraseña.setText("");
					textApellido.setText("");
					textDNI.setText("");
					textTelefono.setText("");
					textCargo.setText("");
					textTipoUsuario.setText("");
					
					
				} else {
					// Error en el registro
					JOptionPane.showMessageDialog(null, "Error al registrar usuario");
				}

				// Cierra la conexión
				try {
					if (conexion != null) {
						conexion.close();
					}
				} catch (SQLException ex) {
					System.out.println("Error al cerrar la conexión: " + ex);
				}

			}
		});
		btnGuardar.setIcon(new ImageIcon(Nuevo_Usuario.class.getResource("/imagenes/user-add.png")));
		btnGuardar.setHorizontalAlignment(SwingConstants.LEFT);
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBounds(246, 458, 189, 46);
		contentPane.add(btnGuardar);

		JButton btnNewButton = new JButton("  Regresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorproductos frameGespro = new gestorproductos();
				frameGespro.setVisible(false);
				dispose();

			}
		});
		btnNewButton.setIcon(new ImageIcon(Nuevo_Usuario.class.getResource("/imagenes/atras.png")));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(36, 458, 172, 45);
		contentPane.add(btnNewButton);

		JLabel lblApellido = new JLabel("Apellido :");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setForeground(new Color(64, 128, 128));
		lblApellido.setFont(new Font("Dialog", Font.BOLD, 20));
		lblApellido.setBackground(Color.WHITE);
		lblApellido.setBounds(36, 106, 144, 37);
		contentPane.add(lblApellido);

		textApellido = new JTextField();
		textApellido.setHorizontalAlignment(SwingConstants.CENTER);
		textApellido.setForeground(Color.BLACK);
		textApellido.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textApellido.setColumns(10);
		textApellido.setBackground(Color.LIGHT_GRAY);
		textApellido.setBounds(193, 107, 242, 37);
		contentPane.add(textApellido);

		JLabel lblDNI = new JLabel("DNI :");
		lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
		lblDNI.setForeground(new Color(64, 128, 128));
		lblDNI.setFont(new Font("Dialog", Font.BOLD, 20));
		lblDNI.setBackground(Color.WHITE);
		lblDNI.setBounds(36, 217, 144, 37);
		contentPane.add(lblDNI);

		textDNI = new JTextField();
		textDNI.setHorizontalAlignment(SwingConstants.CENTER);
		textDNI.setForeground(Color.BLACK);
		textDNI.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textDNI.setColumns(10);
		textDNI.setBackground(Color.LIGHT_GRAY);
		textDNI.setBounds(193, 211, 242, 37);
		contentPane.add(textDNI);

		textTelefono = new JTextField();
		textTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		textTelefono.setForeground(Color.BLACK);
		textTelefono.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textTelefono.setColumns(10);
		textTelefono.setBackground(Color.LIGHT_GRAY);
		textTelefono.setBounds(193, 267, 242, 37);
		contentPane.add(textTelefono);

		JLabel lblTelefono = new JLabel("Telefono :");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setForeground(new Color(64, 128, 128));
		lblTelefono.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTelefono.setBackground(Color.WHITE);
		lblTelefono.setBounds(36, 265, 144, 37);
		contentPane.add(lblTelefono);

		JLabel lblCargo = new JLabel("Cargo :");
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setForeground(new Color(64, 128, 128));
		lblCargo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCargo.setBackground(Color.WHITE);
		lblCargo.setBounds(36, 326, 144, 37);
		contentPane.add(lblCargo);

		textCargo = new JTextField();
		textCargo.setHorizontalAlignment(SwingConstants.CENTER);
		textCargo.setForeground(Color.BLACK);
		textCargo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textCargo.setColumns(10);
		textCargo.setBackground(Color.LIGHT_GRAY);
		textCargo.setBounds(193, 326, 242, 37);
		contentPane.add(textCargo);

		JLabel lblTipoUsuario = new JLabel("Tipo :");
		lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoUsuario.setForeground(new Color(64, 128, 128));
		lblTipoUsuario.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTipoUsuario.setBackground(Color.WHITE);
		lblTipoUsuario.setBounds(36, 389, 144, 37);
		contentPane.add(lblTipoUsuario);

		textTipoUsuario = new JTextField();
		textTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textTipoUsuario.setForeground(Color.BLACK);
		textTipoUsuario.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textTipoUsuario.setColumns(10);
		textTipoUsuario.setBackground(Color.LIGHT_GRAY);
		textTipoUsuario.setBounds(193, 390, 242, 37);
		contentPane.add(textTipoUsuario);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Nuevo_Usuario.class.getResource("/imagenes/Fondaso.png")));
		lblNewLabel.setBounds(0, 0, 494, 535);
		contentPane.add(lblNewLabel);

		setSize(510, 574);
		setLocationRelativeTo(null);
	}
}
