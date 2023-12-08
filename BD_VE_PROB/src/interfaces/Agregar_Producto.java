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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Agregar_Producto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textPrecio;
	private JTextField textCantidad;
	private JTextField textMarca;
	private JTextField textDescripcion;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar_Producto frame = new Agregar_Producto();
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
	public Agregar_Producto() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("  Regresar");
		btnNewButton.setIcon(new ImageIcon(Agregar_Producto.class.getResource("/imagenes/atras.png")));
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
		btnNewButton.setBounds(57, 396, 180, 45);
		contentPane.add(btnNewButton);

		JLabel lblAgregarNuevoProducto = new JLabel("Agregar Nuevo Producto");
		lblAgregarNuevoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarNuevoProducto.setForeground(new Color(64, 128, 128));
		lblAgregarNuevoProducto.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
		lblAgregarNuevoProducto.setBackground(Color.WHITE);
		lblAgregarNuevoProducto.setBounds(75, 11, 357, 48);
		contentPane.add(lblAgregarNuevoProducto);

		JButton btnGuardar = new JButton("     Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve data from text fields
				String nombre = textNombre.getText();
				float precio = Float.parseFloat(textPrecio.getText());
				int cantidad = Integer.parseInt(textCantidad.getText());
				String marca = textMarca.getText();
				String descripcion = textDescripcion.getText();

				// Call the method to add the product
				consultas consultasObj = new consultas();
				boolean result = consultasObj.agregarProducto(nombre, precio, cantidad, marca, descripcion);

				if (result) {
					JOptionPane.showMessageDialog(null, "Producto registrado correctamente.");

					// Limpiar los JTextField
					textNombre.setText("");
					textPrecio.setText("");
					textCantidad.setText("");
					textMarca.setText("");
					textDescripcion.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "Error al registrar Producto.");
				}
			}
		});
		btnGuardar.setIcon(new ImageIcon(Agregar_Producto.class.getResource("/imagenes/user-add.png")));
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Lucida Sans", Font.PLAIN, 19));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBounds(253, 394, 189, 46);
		contentPane.add(btnGuardar);

		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(64, 128, 128));
		lblNombre.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(57, 89, 144, 37);
		contentPane.add(lblNombre);

		JLabel lblMarca = new JLabel("Marca :");
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarca.setForeground(new Color(64, 128, 128));
		lblMarca.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblMarca.setBackground(Color.WHITE);
		lblMarca.setBounds(57, 233, 144, 37);
		contentPane.add(lblMarca);

		JLabel lblPrecio = new JLabel("Precio :");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setForeground(new Color(64, 128, 128));
		lblPrecio.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblPrecio.setBackground(Color.WHITE);
		lblPrecio.setBounds(57, 137, 144, 37);
		contentPane.add(lblPrecio);

		JLabel lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setForeground(new Color(64, 128, 128));
		lblCantidad.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblCantidad.setBackground(Color.WHITE);
		lblCantidad.setBounds(57, 185, 144, 37);
		contentPane.add(lblCantidad);

		JLabel lblDescripcion = new JLabel("Descripcion :");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setForeground(new Color(64, 128, 128));
		lblDescripcion.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblDescripcion.setBackground(Color.WHITE);
		lblDescripcion.setBounds(57, 279, 144, 37);
		contentPane.add(lblDescripcion);

		textNombre = new JTextField();
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setForeground(Color.BLACK);
		textNombre.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textNombre.setColumns(10);
		textNombre.setBackground(Color.LIGHT_GRAY);
		textNombre.setBounds(210, 89, 222, 37);
		contentPane.add(textNombre);

		textPrecio = new JTextField();
		textPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		textPrecio.setForeground(Color.BLACK);
		textPrecio.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textPrecio.setColumns(10);
		textPrecio.setBackground(Color.LIGHT_GRAY);
		textPrecio.setBounds(211, 137, 222, 37);
		contentPane.add(textPrecio);

		textCantidad = new JTextField();
		textCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		textCantidad.setForeground(Color.BLACK);
		textCantidad.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textCantidad.setColumns(10);
		textCantidad.setBackground(Color.LIGHT_GRAY);
		textCantidad.setBounds(210, 185, 222, 37);
		contentPane.add(textCantidad);

		textMarca = new JTextField();
		textMarca.setHorizontalAlignment(SwingConstants.CENTER);
		textMarca.setForeground(Color.BLACK);
		textMarca.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textMarca.setColumns(10);
		textMarca.setBackground(Color.LIGHT_GRAY);
		textMarca.setBounds(210, 233, 222, 37);
		contentPane.add(textMarca);

		textDescripcion = new JTextField();
		textDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		textDescripcion.setForeground(Color.BLACK);
		textDescripcion.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textDescripcion.setColumns(10);
		textDescripcion.setBackground(Color.LIGHT_GRAY);
		textDescripcion.setBounds(211, 279, 222, 37);
		contentPane.add(textDescripcion);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Agregar_Producto.class.getResource("/imagenes/Fondaso.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 38, 37);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(210, 327, 222, 37);
		contentPane.add(textField);

		JLabel lblCategoria = new JLabel("Categoria :");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setForeground(new Color(64, 128, 128));
		lblCategoria.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblCategoria.setBackground(Color.WHITE);
		lblCategoria.setBounds(57, 327, 144, 37);
		contentPane.add(lblCategoria);

		setSize(519, 503);
		setLocationRelativeTo(null);
	}
}
