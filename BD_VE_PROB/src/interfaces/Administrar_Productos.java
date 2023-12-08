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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class Administrar_Productos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_productos = new JTable();
	private JTextField textNombreProducto;
	private JTextField textPrecio;
	private JTextField textCantidad;
	private JTextField textMarca;
	private JTextField textDescripcion;
	private JTextField textFechaVencimiento;
	private JTextField textFechaIngreso;
	private int IdProductos;
	public static JScrollPane scrollPane = new JScrollPane();
	
	private conexion conexion;
	private JTextField textField;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrar_Productos frame = new Administrar_Productos();
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
	public Administrar_Productos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdministrarProductos = new JLabel("Administrar Productos");
		lblAdministrarProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarProductos.setForeground(new Color(64, 128, 128));
		lblAdministrarProductos.setFont(new Font("Lucida Sans", Font.PLAIN, 30));
		lblAdministrarProductos.setBackground(Color.WHITE);
		lblAdministrarProductos.setBounds(329, 0, 475, 48);
		contentPane.add(lblAdministrarProductos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(22, 74, 857, 320);
		contentPane.add(scrollPane_1);
		
		Administrar_Productos.jtable_productos.setModel(new DefaultTableModel());
		jtable_productos.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_Productos.jtable_productos);
		
		JButton btnActualizarProducto = new JButton("Actualizar Producto");
		btnActualizarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarProducto();
			}
			
			private void actualizarProducto() {
				// Get values from JTextFields
		        String Nombre = textNombreProducto.getText();
		        String Precio = textPrecio.getText();
		        String Cantidad = textCantidad.getText();
		        String Marca = textMarca.getText();
		        String Descripcion = textDescripcion.getText();
		        String FechaVencimiento = textFechaVencimiento.getText();
		        String FechaIngreso = textFechaIngreso.getText();

		        // Check if all required fields are filled
		        if (Nombre.isEmpty() || Precio.isEmpty() || Cantidad.isEmpty() || Marca.isEmpty()
		                || Descripcion.isEmpty() || FechaVencimiento.isEmpty() ||  FechaIngreso.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Update the user in the database
		        consultas consulta = new consultas();
		        boolean resultado = consulta.actualizarProducto(IdProductos, Nombre, Precio, Cantidad, Marca, Descripcion, FechaVencimiento, 
		        		FechaIngreso);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            // Refresh the table after updating
		            DefaultTableModel model = (DefaultTableModel) jtable_productos.getModel();
		            model.setRowCount(0);
		            cargarTablas();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario", "Error",
		                    JOptionPane.ERROR_MESSAGE);
			}
		}
		});
		btnActualizarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizarProducto.setBounds(889, 175, 163, 34);
		contentPane.add(btnActualizarProducto);
		
		JButton btnEliminarProducto = new JButton("Eliminar Producto");
		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProducto();
			}
			
			private void eliminarProducto() {
		        int selectedRow = jtable_productos.getSelectedRow();

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, seleccione un Producto a eliminar", "Advertencia",
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        DefaultTableModel model = (DefaultTableModel) jtable_productos.getModel();
		        int IdProductos = (int) model.getValueAt(selectedRow, 0); // Assuming the first column is IdUsuarios

		        consultas consulta = new consultas();
		        boolean resultado = consulta.eliminarProducto(IdProductos);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            // Remove the selected row from the table
		            model.removeRow(selectedRow);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al eliminar el Producto", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnEliminarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminarProducto.setBounds(889, 239, 163, 33);
		contentPane.add(btnEliminarProducto);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(22, 410, 1030, 120);
		contentPane.add(panel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNombre.setBounds(25, 20, 80, 20);
		panel.add(lblNombre);
		
		textNombreProducto = new JTextField();
		textNombreProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNombreProducto.setColumns(10);
		textNombreProducto.setBounds(115, 18, 170, 30);
		panel.add(textNombreProducto);
		
		JLabel lblMarca = new JLabel("Marca :");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMarca.setBounds(25, 68, 80, 23);
		panel.add(lblMarca);
		
		textMarca = new JTextField();
		textMarca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textMarca.setColumns(10);
		textMarca.setBounds(115, 66, 170, 30);
		panel.add(textMarca);
		
		textCantidad = new JTextField();
		textCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCantidad.setColumns(10);
		textCantidad.setBounds(391, 65, 150, 30);
		panel.add(textCantidad);
		
		JLabel lblPrecio = new JLabel("Precio :");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrecio.setBounds(314, 20, 120, 20);
		panel.add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPrecio.setColumns(10);
		textPrecio.setBounds(391, 16, 150, 30);
		panel.add(textPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCantidad.setBounds(295, 69, 102, 20);
		panel.add(lblCantidad);
		
		JLabel lblNewLabel_3 = new JLabel("Descripcion :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(579, 70, 129, 20);
		panel.add(lblNewLabel_3);
		
		textDescripcion = new JTextField();
		textDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(705, 65, 285, 30);
		panel.add(textDescripcion);
		
		textFechaVencimiento = new JTextField();
		textFechaVencimiento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFechaVencimiento.setColumns(10);
		textFechaVencimiento.setBounds(615, 32, 194, 29);
		panel.add(textFechaVencimiento);
		
		JLabel lblFechaVencimiento = new JLabel("Fecha Vencimiento :");
		lblFechaVencimiento.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFechaVencimiento.setBounds(615, 10, 184, 22);
		panel.add(lblFechaVencimiento);
		
		JLabel lblFechaIngreso = new JLabel("Fecha Ingreso :");
		lblFechaIngreso.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFechaIngreso.setBounds(842, 11, 150, 20);
		panel.add(lblFechaIngreso);
		
		textFechaIngreso = new JTextField();
		textFechaIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		textFechaIngreso.setFont(new Font("Tahoma", Font.BOLD, 13));
		textFechaIngreso.setColumns(10);
		textFechaIngreso.setBounds(842, 32, 164, 30);
		panel.add(textFechaIngreso);

		setSize(1100, 600);
		setLocationRelativeTo(null);
		
		conexion = new conexion();
		this.cargarTablas();
	}
	
	private void cargarTablas() {
	    Connection con = conexion.obtenerConexion();
	    DefaultTableModel model;

	    if (Administrar_Productos.jtable_productos.getModel().getColumnCount() == 0) {
	        // Initialize the model only if it's not already set
	        model = new DefaultTableModel();
	        Administrar_Productos.jtable_productos.setModel(model);

	        model.addColumn("IdProductos");
	        model.addColumn("Nombre");
	        model.addColumn("Precio");
	        model.addColumn("Cantidad");
	        model.addColumn("Marca");
	        model.addColumn("Descripcion");
	        model.addColumn("FechaVencimiento");
	        model.addColumn("FechaIngreso");
	    } else {
	        // Clear the existing rows
	        model = (DefaultTableModel) Administrar_Productos.jtable_productos.getModel();
	        model.setRowCount(0);
	    }

	    String sql = "SELECT IdProductos, Nombre, Precio, Cantidad, Marca, Descripcion, FechaVencimiento, FechaIngreso FROM Productos;";
	    java.sql.Statement st;
	    try {
	        st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            Object fila[] = new Object[8];

	            for (int i = 0; i < 8; i++) {
	                fila[i] = rs.getObject(i + 1);
	            }
	            model.addRow(fila);
	        }

	        con.close();

	    } catch (SQLException e) {
	        System.out.println("Error al llenar la tabla Usuarios: " + e);
	    }

	    jtable_productos.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int fila_point = jtable_productos.rowAtPoint(e.getPoint());
	            int columna_point = 0;

	            if (fila_point > -1) {
	            	IdProductos = (int) model.getValueAt(fila_point, columna_point);
	                EnviarDatosProductoSeleccionado(IdProductos);
	            }
	        }
	    });
	}
	
	
	private void EnviarDatosProductoSeleccionado(int IdProductos) {
	    try {
	        Connection con = conexion.obtenerConexion();
	        PreparedStatement pst = con.prepareStatement("SELECT * FROM Productos WHERE IdProductos = ?");
	        pst.setInt(1, IdProductos);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	        	textNombreProducto.setText(rs.getString("Nombre"));
	        	textPrecio.setText(rs.getString("Precio"));
	        	textCantidad.setText(rs.getString("Cantidad"));
	        	textMarca.setText(rs.getString("Marca"));
	        	textDescripcion.setText(rs.getString("Descripcion"));
	        	textFechaVencimiento.setText(rs.getString("FechaVencimiento"));
	        	textFechaIngreso.setText(rs.getString("FechaIngreso"));
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}

}
