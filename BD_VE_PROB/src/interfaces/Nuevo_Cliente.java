package interfaces;

import conex.conexion;
import conex.consultas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Nuevo_Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombres;
	private JTextField textApellidos;
	private JTextField textDNI;
	private JTextField textGenero;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textCorreoElectronico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nuevo_Cliente frame = new Nuevo_Cliente();
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
	public Nuevo_Cliente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("  Regresar");
		btnNewButton.setIcon(new ImageIcon(Nuevo_Cliente.class.getResource("/imagenes/atras.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorproductos frameGespro = new gestorproductos();
				frameGespro.setVisible(false);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(99, 436, 189, 46);
		contentPane.add(btnNewButton);

		JButton btnGuardar = new JButton("     Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtiene las credenciales ingresadas por el usuario
				String Nombres = textNombres.getText();
				String Apellidos = textApellidos.getText();
				String DNI = textDNI.getText();
				String Genero = textGenero.getText();
				String Direccion = textDireccion.getText();
				String Telefono = textTelefono.getText();
				String Correo = textCorreoElectronico.getText();

				conexion conexionBD = new conexion();

				Connection conexion = conexionBD.obtenerConexion();

				consultas consultasBD = new consultas();
				if (consultasBD.registrarCliente(Nombres, Apellidos, DNI, Genero, Direccion, Telefono, Correo, conexion)) {
		            JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");
 
		            textNombres.setText("");
		            textApellidos.setText("");
		            textDNI.setText("");
		            textGenero.setText("");
		            textDireccion.setText("");
		            textTelefono.setText("");
		            textCorreoElectronico.setText("");
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al registrar cliente");
		        }

		        try {
		            if (conexion != null) {
		                conexion.close();
		            }
		        } catch (SQLException ex) {
		            System.out.println("Error al cerrar la conexión: " + ex);
		        }
			}
		});
		btnGuardar.setIcon(new ImageIcon(Nuevo_Cliente.class.getResource("/imagenes/user-add.png")));
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Lucida Sans", Font.PLAIN, 19));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBounds(324, 436, 189, 46);
		contentPane.add(btnGuardar);

		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(64, 128, 128));
		lblNombre.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(43, 76, 144, 37);
		contentPane.add(lblNombre);

		JLabel lblAgregarNuevoCliente = new JLabel("Agregar Nuevo Cliente");
		lblAgregarNuevoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarNuevoCliente.setForeground(new Color(64, 128, 128));
		lblAgregarNuevoCliente.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
		lblAgregarNuevoCliente.setBackground(Color.WHITE);
		lblAgregarNuevoCliente.setBounds(132, 11, 357, 48);
		contentPane.add(lblAgregarNuevoCliente);

		JLabel lblApellido = new JLabel("Apellido :");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setForeground(new Color(64, 128, 128));
		lblApellido.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblApellido.setBackground(Color.WHITE);
		lblApellido.setBounds(43, 124, 144, 37);
		contentPane.add(lblApellido);

		JLabel lblDNI = new JLabel("DNI :");
		lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
		lblDNI.setForeground(new Color(64, 128, 128));
		lblDNI.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblDNI.setBackground(Color.WHITE);
		lblDNI.setBounds(43, 172, 144, 37);
		contentPane.add(lblDNI);

		JLabel lblGenero = new JLabel("Genero :");
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setForeground(new Color(64, 128, 128));
		lblGenero.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblGenero.setBackground(Color.WHITE);
		lblGenero.setBounds(43, 220, 144, 37);
		contentPane.add(lblGenero);

		JLabel lblDireccion = new JLabel("Direccion :");
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setForeground(new Color(64, 128, 128));
		lblDireccion.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblDireccion.setBackground(Color.WHITE);
		lblDireccion.setBounds(43, 268, 144, 37);
		contentPane.add(lblDireccion);

		JLabel lblTelefono = new JLabel("Telefono :");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setForeground(new Color(64, 128, 128));
		lblTelefono.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblTelefono.setBackground(Color.WHITE);
		lblTelefono.setBounds(43, 316, 144, 37);
		contentPane.add(lblTelefono);

		JLabel lblCorreoElectronico = new JLabel("Correo Electronico :");
		lblCorreoElectronico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreoElectronico.setForeground(new Color(64, 128, 128));
		lblCorreoElectronico.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblCorreoElectronico.setBackground(Color.WHITE);
		lblCorreoElectronico.setBounds(10, 369, 210, 37);
		contentPane.add(lblCorreoElectronico);

		textNombres = new JTextField();
		textNombres.setHorizontalAlignment(SwingConstants.CENTER);
		textNombres.setForeground(Color.BLACK);
		textNombres.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textNombres.setColumns(10);
		textNombres.setBackground(Color.LIGHT_GRAY);
		textNombres.setBounds(230, 77, 222, 37);
		contentPane.add(textNombres);

		textApellidos = new JTextField();
		textApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		textApellidos.setForeground(Color.BLACK);
		textApellidos.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textApellidos.setColumns(10);
		textApellidos.setBackground(Color.LIGHT_GRAY);
		textApellidos.setBounds(230, 125, 222, 37);
		contentPane.add(textApellidos);

		textDNI = new JTextField();
		textDNI.setHorizontalAlignment(SwingConstants.CENTER);
		textDNI.setForeground(Color.BLACK);
		textDNI.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textDNI.setColumns(10);
		textDNI.setBackground(Color.LIGHT_GRAY);
		textDNI.setBounds(230, 173, 222, 37);
		contentPane.add(textDNI);

		textGenero = new JTextField();
		textGenero.setHorizontalAlignment(SwingConstants.CENTER);
		textGenero.setForeground(Color.BLACK);
		textGenero.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textGenero.setColumns(10);
		textGenero.setBackground(Color.LIGHT_GRAY);
		textGenero.setBounds(230, 221, 222, 37);
		contentPane.add(textGenero);

		textDireccion = new JTextField();
		textDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		textDireccion.setForeground(Color.BLACK);
		textDireccion.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textDireccion.setColumns(10);
		textDireccion.setBackground(Color.LIGHT_GRAY);
		textDireccion.setBounds(230, 269, 222, 37);
		contentPane.add(textDireccion);

		textTelefono = new JTextField();
		textTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		textTelefono.setForeground(Color.BLACK);
		textTelefono.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textTelefono.setColumns(10);
		textTelefono.setBackground(Color.LIGHT_GRAY);
		textTelefono.setBounds(230, 317, 222, 37);
		contentPane.add(textTelefono);

		textCorreoElectronico = new JTextField();
		textCorreoElectronico.setHorizontalAlignment(SwingConstants.CENTER);
		textCorreoElectronico.setForeground(Color.BLACK);
		textCorreoElectronico.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		textCorreoElectronico.setColumns(10);
		textCorreoElectronico.setBackground(Color.LIGHT_GRAY);
		textCorreoElectronico.setBounds(230, 369, 357, 37);
		contentPane.add(textCorreoElectronico);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Nuevo_Cliente.class.getResource("/imagenes/Fondaso.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 602, 514);
		contentPane.add(lblNewLabel);

		setSize(618, 553);
		setLocationRelativeTo(null);
	}
}
