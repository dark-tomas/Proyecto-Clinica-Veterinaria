package interfaces;

import conex.DetalleVenta;
import conex.conexion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ventas extends JFrame {

	private DefaultTableModel modeloDatosProductos;
	// lista para el detalle de venta de los prdocutos
	ArrayList<DetalleVenta> listaProductos = new ArrayList<>();
	private DetalleVenta producto;

	private int idProducto = 0;
	private String nombre = "";
	private int cantidadProductoBBDD = 0;
	private double precioUnitario = 0;

	private double NewIgv = 0;
	private int cantidad = 0; // cantidad de productos a comprar
	private double subtotal = 0.0; // precio * cantidad
	private double descuento = 0.0;
	private double IGV = 0.18;
	private double totalPagar = 0.0;

	private int auxIDDetalle = 1; // id del detalle de venta

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_cliente_buscar;
	private JTextField txt_cantidad;
	public static JTable jtable_productos = new JTable();
	private JTextField txt_Subtotal;
	private JTextField txt_Descuento;
	private JTextField txt_IGV;
	private JTextField txt_totalpagar;
	private JTextField txt_Efectivo;
	private JTextField txt_cambio;
	private conexion conexion;
	private JComboBox<String> comboBox_cliente;
	private JComboBox<String> comboBox_Producto;
	private JButton btn_CalcularCambio;
	int idArrayList = 0;

	// Variables para calculos globales
	private double subtotalGeneral = 0.0;
	private double descuentoGeneral = 0.0;
	private double IGVGeneral = 0.0;
	private double TotalPagarGeneral = 0.0;
	// fin de variables de calculos globales

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ventas frame = new ventas();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void inicializarTablaPro() {
		modeloDatosProductos = new DefaultTableModel();
		// Añadir columnas
		modeloDatosProductos.addColumn("N°");
		modeloDatosProductos.addColumn("Nombre");
		modeloDatosProductos.addColumn("Cantidad");
		modeloDatosProductos.addColumn("Precio_unidad");
		modeloDatosProductos.addColumn("Subtotal");
		modeloDatosProductos.addColumn("Descuento");
		modeloDatosProductos.addColumn("IGV");
		modeloDatosProductos.addColumn("Total");
		modeloDatosProductos.addColumn("Accion");

		this.jtable_productos.setModel(modeloDatosProductos);

	}

	// Metodo para presentar la informacion de la tabla DetalleVenta
	private void listaTablaProductos() {
		this.modeloDatosProductos.setRowCount(listaProductos.size());
		for (int i = 0; i < listaProductos.size(); i++) {
			this.modeloDatosProductos.setValueAt(i + 1, i, 0);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getNombre(), i, 1);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getCantidad(), i, 2);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getPrecioUnitario(), i, 3);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getSubTotal(), i, 4);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getDescuento(), i, 5);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getNewIgv(), i, 6);
			this.modeloDatosProductos.setValueAt(listaProductos.get(i).getTotalPagar(), i, 7);
			this.modeloDatosProductos.setValueAt("Eliminar", i, 8); // Aqui luego tener un boton de eliminar
		}
		// Anadir al Jtable
		jtable_productos.setModel(modeloDatosProductos);
	}

	/**
	 * Create the frame.
	 */
	public ventas() {

		setSize(new Dimension(800, 600));
		setTitle("Facturacion");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 671);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Venta Productos");
		lblTitulo.setBounds(488, 5, 308, 70);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Lucida Sans", Font.BOLD, 35));
		contentPane.add(lblTitulo);

		JLabel lblNewLabel = new JLabel("Cliente:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(6, 100, 80, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProducto.setBounds(6, 140, 80, 20);
		contentPane.add(lblProducto);

		JLabel lblProducto_1 = new JLabel("Cantidad:");
		lblProducto_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProducto_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProducto_1.setBounds(265, 140, 80, 20);
		contentPane.add(lblProducto_1);

		txt_cliente_buscar = new JTextField();
		txt_cliente_buscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_cliente_buscar.setBounds(254, 100, 166, 22);
		contentPane.add(txt_cliente_buscar);
		txt_cliente_buscar.setColumns(10);

		this.comboBox_cliente = new JComboBox();
		comboBox_cliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_cliente.setModel(new DefaultComboBoxModel<>(new String[] { "Selecciones Cliente:" }));
		comboBox_cliente.setBounds(100, 100, 155, 22);
		contentPane.add(comboBox_cliente);

		txt_cantidad = new JTextField();
		txt_cantidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_cantidad.setColumns(10);
		txt_cantidad.setBounds(358, 140, 60, 22);
		contentPane.add(txt_cantidad);

		JButton btnAñadir_Producto = new JButton("Añadir Producto");
		btnAñadir_Producto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String combo = comboBox_Producto.getSelectedItem().toString();
				// Validar seleccion de producto
				if (combo.equalsIgnoreCase("Seleccione Producto:")) {
					JOptionPane.showMessageDialog(null, "Seleccione Producto:");
				} else {
					// Validar que ingrese una cantidad
					if (!txt_cantidad.getText().isEmpty()) {
						// Validad que no ingrese caracteres no numericos
						boolean validacion = validar(txt_cantidad.getText());
						if (validacion == true) {
							// Validar que la cantidad sea mayor a cero
							if (Integer.parseInt(txt_cantidad.getText()) > 0) {
								cantidad = Integer.parseInt(txt_cantidad.getText());
								// ejecutar metodo para obtener datos del producto

								DatosDelProducto();
								// validar que los productos seleccionados no sea mayor al stock de la base de
								// datos
								if (cantidad <= cantidadProductoBBDD) {
									subtotal = precioUnitario * cantidad;
									NewIgv = subtotal * IGV;
									totalPagar = subtotal + NewIgv;

									// redonder decimales
									subtotal = (double) Math.round(subtotal * 100) / 100;
									totalPagar = (double) Math.round(totalPagar * 100) / 100;
									NewIgv = (double) Math.round(NewIgv * 100) / 100;

									// Se crea un nuevo producto
									producto = new DetalleVenta(auxIDDetalle, // idDetalle
											1, // idCabezera
											idProducto, nombre, Integer.parseInt(txt_cantidad.getText()),
											precioUnitario, subtotal, descuento, NewIgv, totalPagar, 1 // estado
									);
									// Añadir a la lista
									listaProductos.add(producto);
									JOptionPane.showMessageDialog(null, "Producto agregado");
									auxIDDetalle++;
									txt_cantidad.setText("");// Limpiar el campo
									// volver a cargar combo productos
									CargarComboProductos();
									CalcularTotalPagar();
									txt_Efectivo.setEnabled(true);
									btn_CalcularCambio.setEnabled(true);

								} else {
									JOptionPane.showMessageDialog(null, "La cantidad supera el stock");
								}

							} else {
								JOptionPane.showMessageDialog(null, "La cantidad no puede ser cero(0), ni negativo");

							}

						} else {
							JOptionPane.showMessageDialog(null, "No se admiten caracteres no numericos");

						}

					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una cantidad");
					}
				}
				// Llamar al método
				listaTablaProductos();

			}
		});
		btnAñadir_Producto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAñadir_Producto.setBounds(416, 139, 140, 24);
		contentPane.add(btnAñadir_Producto);

		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clienteBuscar = txt_cliente_buscar.getText().trim();
				Connection cn = null;
				cn = conexion.obtenerConexion();
				String sql = "Select Nombres, Apellidos from Cliente where DNI= '" + clienteBuscar + "'";
				Statement st;
				try {
					st = cn.createStatement();
					ResultSet rs = st.executeQuery(sql);

					if (rs.next()) {
						comboBox_cliente.setSelectedItem(rs.getString("Nombres") + " " + rs.getString("Apellidos"));
					} else {
						comboBox_cliente.setSelectedItem("Seleccione cliente");
						JOptionPane.showMessageDialog(null, "DNI de cliente no encontrada o incorrecta");

					}
					txt_cliente_buscar.setText("");
					cn.close();
				} catch (SQLException e1) {
					System.out.println("Error al buscar clientes: " + e1);
				}
			}
		});
		btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscarCliente.setBounds(416, 99, 80, 24);
		contentPane.add(btnBuscarCliente);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(15, 190, 900, 300);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 10, 875, 275);
		panel.add(scrollPane);

		jtable_productos = new JTable();
		jtable_productos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int fila_point = jtable_productos.rowAtPoint(evt.getPoint());
				int columna_point = 0;

				if (fila_point > -1) {
					idArrayList = (int) modeloDatosProductos.getValueAt(fila_point, columna_point);
				}
				int opcion = JOptionPane.showConfirmDialog(null, "¿Eliminar Producto?");
				// opciones de confir dialog-(si=0; no=1;cancel=2; close =-1)
				switch (opcion) {
				case 0: // Eliminar
					listaProductos.remove(idArrayList - 1);
					CalcularTotalPagar();
					listaTablaProductos();
					break;

				case 1:
					break;

				default:
					break;

				}

			}
		});
		scrollPane.setViewportView(jtable_productos);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(925, 190, 350, 290);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("SubTotal:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 20, 70, 20);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Descuento:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 50, 70, 20);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("IGV:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(10, 80, 62, 20);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblnew = new JLabel("Total a Pagar:");
		lblnew.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnew.setBounds(10, 110, 90, 20);
		panel_1.add(lblnew);

		JLabel lblNewLabel_1_3_1 = new JLabel("Efectivo:");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3_1.setBounds(10, 150, 90, 20);
		panel_1.add(lblNewLabel_1_3_1);

		JLabel lblNewLabel_1_3_2 = new JLabel("Cambio:");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3_2.setBounds(10, 180, 90, 20);
		panel_1.add(lblNewLabel_1_3_2);

		txt_Subtotal = new JTextField();
		txt_Subtotal.setEnabled(false);
		txt_Subtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_Subtotal.setBounds(110, 20, 120, 20);
		panel_1.add(txt_Subtotal);
		txt_Subtotal.setColumns(10);
		txt_Subtotal.setText("0.00");

		txt_Descuento = new JTextField();
		txt_Descuento.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_Descuento.setEnabled(false);
		txt_Descuento.setColumns(10);
		txt_Descuento.setBounds(110, 50, 120, 20);
		panel_1.add(txt_Descuento);
		txt_Descuento.setText("0.00");

		txt_IGV = new JTextField();
		txt_IGV.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_IGV.setEnabled(false);
		txt_IGV.setColumns(10);
		txt_IGV.setBounds(110, 80, 120, 20);
		panel_1.add(txt_IGV);
		txt_IGV.setText("0.00");

		txt_totalpagar = new JTextField();
		txt_totalpagar.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_totalpagar.setEnabled(false);
		txt_totalpagar.setColumns(10);
		txt_totalpagar.setBounds(110, 110, 120, 20);
		panel_1.add(txt_totalpagar);
		txt_totalpagar.setText("0.00");

		txt_Efectivo = new JTextField();
		txt_Efectivo.setEnabled(false);
		txt_Efectivo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_Efectivo.setColumns(10);
		txt_Efectivo.setBounds(110, 150, 120, 20);
		panel_1.add(txt_Efectivo);

		txt_cambio = new JTextField();
		txt_cambio.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_cambio.setEnabled(false);
		txt_cambio.setColumns(10);
		txt_cambio.setBounds(110, 180, 120, 20);
		panel_1.add(txt_cambio);

		btn_CalcularCambio = new JButton("Calcular Cambio");
		btn_CalcularCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txt_Efectivo.getText().isEmpty()) {
					// validacion que el usuario no ingrese otros caracteres no numericos

					boolean validacion = validarDouble(txt_Efectivo.getText());
					if (validacion == true) {
						// validacion que el efectivo sea mayor a cero y total a pagar
						double efc = Double.parseDouble(txt_Efectivo.getText().trim());
						double top = Double.parseDouble(txt_totalpagar.getText().trim());

						if (efc < top) {
							JOptionPane.showMessageDialog(null, "El dinero en efectivo no es suficiente");
						} else {
							double cambio = efc - top;
							double cambi = (double) Math.round(cambio * 100) / 100;
							String camb = String.valueOf(cambi);
							txt_cambio.setText(camb);

						}

					} else {
						JOptionPane.showMessageDialog(null, "No se admiten caracteres no numericos");

					}

				} else {
					JOptionPane.showMessageDialog(null, "Ingrese dinero en efectivo para calcular cambio");

				}
			}
		});
		btn_CalcularCambio.setEnabled(false);
		btn_CalcularCambio.setBackground(new Color(51, 255, 255));
		btn_CalcularCambio.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_CalcularCambio.setBounds(120, 220, 130, 50);
		panel_1.add(btn_CalcularCambio);

		JButton btn_RegistrarVenta = new JButton("Registrar Venta");
		btn_RegistrarVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_RegistrarVenta.setBackground(new Color(51, 255, 255));
		btn_RegistrarVenta.setBounds(1040, 500, 170, 80);
		contentPane.add(btn_RegistrarVenta);

		this.comboBox_Producto = new JComboBox();
		comboBox_Producto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_Producto.setBounds(100, 140, 155, 22);
		contentPane.add(comboBox_Producto);

		setSize(1300, 675);
		setLocationRelativeTo(null);

		conexion = new conexion();
		CargarComboClientes();
		CargarComboProductos();
		inicializarTablaPro();

	}

	/*
	 * Metodo para cargar clientes en el JComboBox
	 */
	private void CargarComboClientes() {
		Connection cn = null;
		try {
			cn = conexion.obtenerConexion();
			String sql = "SELECT * FROM Cliente";
			try (java.sql.Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
				comboBox_cliente.removeAllItems();
				comboBox_cliente.addItem("Selecciones Cliente");

				while (rs.next()) {
					comboBox_cliente.addItem(rs.getString("Nombres") + " " + rs.getString("Apellidos"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al cargar clientes: " + e);
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void CargarComboProductos() {
		Connection cn = null;
		try {
			cn = conexion.obtenerConexion();
			String sql = "SELECT * FROM Productos";
			try (java.sql.Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
				comboBox_Producto.removeAllItems();
				comboBox_Producto.addItem("Seleccione Producto:");

				while (rs.next()) {
					comboBox_Producto.addItem(rs.getString("Nombre"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al cargar productos: " + e);
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Metodo para validar que el usuario no iingrese caracteres no numericos
	 */

	private boolean validar(String valor) {
		try {
			int num = Integer.parseInt(valor);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/*
	 * Metodo para validar que el usuario no iingrese caracteres no numericos
	 */

	private boolean validarDouble(String valor) {
		try {
			double num = Double.parseDouble(valor);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*
	 * Metodo para mostrar los datos del producto
	 */
	private void DatosDelProducto() {
		try {
			String sql = "Select * from Productos where nombre = '" + this.comboBox_Producto.getSelectedItem() + "'";
			Connection cn = null;
			cn = conexion.obtenerConexion();
			Statement st;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				idProducto = rs.getInt("IdProductos");
				nombre = rs.getString("Nombre");
				precioUnitario = rs.getFloat("Precio");
				cantidadProductoBBDD = rs.getInt("Cantidad");

			}

		} catch (SQLException e) {
			System.out.println("Error al tener datos del producto: " + e);
		}
	}

	/*
	 * Metodo para calcular el total a pagar de todos los productos agregados
	 */
	private void CalcularTotalPagar() {
		subtotalGeneral = 0;
		descuentoGeneral = 0;
		IGVGeneral = 0;
		TotalPagarGeneral = 0;

		for (DetalleVenta elemento : listaProductos) {
			subtotalGeneral += elemento.getSubTotal();
			descuentoGeneral += elemento.getDescuento();
			IGVGeneral += elemento.getNewIgv();
			TotalPagarGeneral += elemento.getTotalPagar();
		}
		// redonder decimales
		subtotalGeneral = (double) Math.round(subtotalGeneral * 100) / 100;
		IGVGeneral = (double) Math.round(IGVGeneral * 100) / 100;
		descuentoGeneral = (double) Math.round(descuentoGeneral * 100) / 100;
		TotalPagarGeneral = (double) Math.round(TotalPagarGeneral * 100) / 100;

		// enviar datos a la vista
		txt_Subtotal.setText(String.valueOf(subtotalGeneral));
		txt_IGV.setText(String.valueOf(IGVGeneral));
		txt_Descuento.setText(String.valueOf(descuentoGeneral));
		txt_totalpagar.setText(String.valueOf(TotalPagarGeneral));
	}

}