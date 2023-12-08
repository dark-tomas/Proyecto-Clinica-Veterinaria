package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conex.consultas;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Agregar_Proveedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textRazonSocial;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textCorreo;
	private JTextField textCategoria;
	private JTextField textRUC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar_Proveedor frame = new Agregar_Proveedor();
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
	public Agregar_Proveedor() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("  Regresar");
		btnNewButton.setIcon(new ImageIcon(Agregar_Proveedor.class.getResource("/imagenes/atras.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorproductos frameGespro = new gestorproductos();
				frameGespro.setVisible(false);
				dispose();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(69, 518, 172, 45);
		contentPane.add(btnNewButton);

		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(64, 128, 128));
		lblNombre.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(60, 107, 144, 37);
		contentPane.add(lblNombre);

		JLabel lblAgregarProveedor = new JLabel("Agregar Proveedor");
		lblAgregarProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarProveedor.setForeground(new Color(64, 128, 128));
		lblAgregarProveedor.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
		lblAgregarProveedor.setBackground(Color.WHITE);
		lblAgregarProveedor.setBounds(124, 24, 357, 48);
		contentPane.add(lblAgregarProveedor);

		JButton btnGuardar = new JButton("     Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Nombre = textNombre.getText();
				String Apellido = textApellido.getText();
				String RUC = textRUC.getText();
				String RazonSocial = textRazonSocial.getText();
				String Direccion = textDireccion.getText();
				String Telefono = textTelefono.getText();
				String Correo = textCorreo.getText();
				String Categoria = textCategoria.getText();

				consultas consultasObj = new consultas();
				boolean result = consultasObj.agregarProveedor(Nombre, Apellido, RUC, RazonSocial, Direccion, Telefono,
						Correo, Categoria);

				if (result) {
					JOptionPane.showMessageDialog(null, "Proveedor registrado correctamente.");
					textNombre.setText("");
					textApellido.setText("");
					textRUC.setText("");
					textRazonSocial.setText("");
					textDireccion.setText("");
					textTelefono.setText("");
					textCorreo.setText("");
					textCategoria.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "Error al registrar Producto.");
				}
			}
		});
		btnGuardar.setIcon(new ImageIcon(Agregar_Proveedor.class.getResource("/imagenes/user-add.png")));
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Lucida Sans", Font.PLAIN, 19));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBounds(292, 516, 189, 46);
		contentPane.add(btnGuardar);

		JLabel lblApellido = new JLabel("Apellido :");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setForeground(new Color(64, 128, 128));
		lblApellido.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblApellido.setBackground(Color.WHITE);
		lblApellido.setBounds(60, 155, 144, 37);
		contentPane.add(lblApellido);

		JLabel lblRazonSocial = new JLabel("Razon Social :");
		lblRazonSocial.setHorizontalAlignment(SwingConstants.CENTER);
		lblRazonSocial.setForeground(new Color(64, 128, 128));
		lblRazonSocial.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblRazonSocial.setBackground(Color.WHITE);
		lblRazonSocial.setBounds(60, 249, 144, 37);
		contentPane.add(lblRazonSocial);

		JLabel lblDireccion = new JLabel("Direccion :");
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccion.setForeground(new Color(64, 128, 128));
		lblDireccion.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblDireccion.setBackground(Color.WHITE);
		lblDireccion.setBounds(53, 297, 144, 37);
		contentPane.add(lblDireccion);

		JLabel lblTelefono = new JLabel("Telefono :");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setForeground(new Color(64, 128, 128));
		lblTelefono.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblTelefono.setBackground(Color.WHITE);
		lblTelefono.setBounds(60, 345, 144, 37);
		contentPane.add(lblTelefono);

		JLabel lblCorreo = new JLabel("Correo :");
		lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorreo.setForeground(new Color(64, 128, 128));
		lblCorreo.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblCorreo.setBackground(Color.WHITE);
		lblCorreo.setBounds(53, 390, 144, 37);
		contentPane.add(lblCorreo);

		JLabel lblCategoria = new JLabel("Categoria :");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setForeground(new Color(64, 128, 128));
		lblCategoria.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblCategoria.setBackground(Color.WHITE);
		lblCategoria.setBounds(53, 438, 144, 37);
		contentPane.add(lblCategoria);

		textNombre = new JTextField();
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setForeground(Color.BLACK);
		textNombre.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textNombre.setColumns(10);
		textNombre.setBackground(Color.LIGHT_GRAY);
		textNombre.setBounds(231, 107, 222, 37);
		contentPane.add(textNombre);

		textApellido = new JTextField();
		textApellido.setHorizontalAlignment(SwingConstants.CENTER);
		textApellido.setForeground(Color.BLACK);
		textApellido.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textApellido.setColumns(10);
		textApellido.setBackground(Color.LIGHT_GRAY);
		textApellido.setBounds(231, 155, 222, 37);
		contentPane.add(textApellido);

		textRazonSocial = new JTextField();
		textRazonSocial.setHorizontalAlignment(SwingConstants.CENTER);
		textRazonSocial.setForeground(Color.BLACK);
		textRazonSocial.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textRazonSocial.setColumns(10);
		textRazonSocial.setBackground(Color.LIGHT_GRAY);
		textRazonSocial.setBounds(231, 250, 222, 37);
		contentPane.add(textRazonSocial);

		textDireccion = new JTextField();
		textDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		textDireccion.setForeground(Color.BLACK);
		textDireccion.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textDireccion.setColumns(10);
		textDireccion.setBackground(Color.LIGHT_GRAY);
		textDireccion.setBounds(231, 295, 222, 37);
		contentPane.add(textDireccion);

		textTelefono = new JTextField();
		textTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		textTelefono.setForeground(Color.BLACK);
		textTelefono.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textTelefono.setColumns(10);
		textTelefono.setBackground(Color.LIGHT_GRAY);
		textTelefono.setBounds(231, 343, 222, 37);
		contentPane.add(textTelefono);

		textCorreo = new JTextField();
		textCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		textCorreo.setForeground(Color.BLACK);
		textCorreo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textCorreo.setColumns(10);
		textCorreo.setBackground(Color.LIGHT_GRAY);
		textCorreo.setBounds(231, 391, 303, 37);
		contentPane.add(textCorreo);

		textCategoria = new JTextField();
		textCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		textCategoria.setForeground(Color.BLACK);
		textCategoria.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textCategoria.setColumns(10);
		textCategoria.setBackground(Color.LIGHT_GRAY);
		textCategoria.setBounds(231, 439, 222, 37);
		contentPane.add(textCategoria);

		JLabel lblRuc = new JLabel("RUC :");
		lblRuc.setHorizontalAlignment(SwingConstants.CENTER);
		lblRuc.setForeground(new Color(64, 128, 128));
		lblRuc.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblRuc.setBackground(Color.WHITE);
		lblRuc.setBounds(60, 203, 144, 37);
		contentPane.add(lblRuc);

		textRUC = new JTextField();
		textRUC.setHorizontalAlignment(SwingConstants.CENTER);
		textRUC.setForeground(Color.BLACK);
		textRUC.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textRUC.setColumns(10);
		textRUC.setBackground(Color.LIGHT_GRAY);
		textRUC.setBounds(231, 203, 222, 37);
		contentPane.add(textRUC);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Agregar_Proveedor.class.getResource("/imagenes/Fondaso.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 577, 620);
		contentPane.add(lblNewLabel);

		setSize(593, 659);
		setLocationRelativeTo(null);
	}
}
