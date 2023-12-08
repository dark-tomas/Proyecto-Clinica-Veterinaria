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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Administrar_Proveedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_Proveedor = new JTable();
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textRUC;
	private JTextField textRazonSocial;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textCorreo;
	private JTextField textCategoria;
	private JTextField textFechaCreacion;
	private int IdProveedor;
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
					Administrar_Proveedor frame = new Administrar_Proveedor();
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
	public Administrar_Proveedor() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdministrarProveedores = new JLabel("Administrar Proveedores");
		lblAdministrarProveedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarProveedores.setForeground(new Color(64, 128, 128));
		lblAdministrarProveedores.setFont(new Font("Lucida Sans", Font.PLAIN, 30));
		lblAdministrarProveedores.setBackground(Color.WHITE);
		lblAdministrarProveedores.setBounds(272, 0, 475, 48);
		contentPane.add(lblAdministrarProveedores);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(25, 399, 1030, 151);
		contentPane.add(panel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(25, 20, 80, 20);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNombre.setColumns(10);
		textNombre.setBounds(115, 20, 170, 20);
		panel.add(textNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellido.setBounds(25, 58, 80, 20);
		panel.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textApellido.setColumns(10);
		textApellido.setBounds(115, 59, 170, 20);
		panel.add(textApellido);
		
		JLabel lblRUC = new JLabel("RUC :");
		lblRUC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRUC.setBounds(25, 100, 80, 20);
		panel.add(lblRUC);
		
		textRUC = new JTextField();
		textRUC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textRUC.setColumns(10);
		textRUC.setBounds(115, 102, 170, 20);
		panel.add(textRUC);
		
		JLabel lblRazonSocial = new JLabel("Razon Social :");
		lblRazonSocial.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRazonSocial.setBounds(335, 20, 123, 20);
		panel.add(lblRazonSocial);
		
		textRazonSocial = new JTextField();
		textRazonSocial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textRazonSocial.setColumns(10);
		textRazonSocial.setBounds(468, 21, 170, 20);
		panel.add(textRazonSocial);
		
		JLabel lblDireccion = new JLabel("Direccion  :");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDireccion.setBounds(335, 58, 123, 20);
		panel.add(lblDireccion);
		
		textDireccion = new JTextField();
		textDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textDireccion.setColumns(10);
		textDireccion.setBounds(468, 60, 170, 20);
		panel.add(textDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono  :");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelefono.setBounds(335, 100, 123, 20);
		panel.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTelefono.setColumns(10);
		textTelefono.setBounds(468, 102, 170, 20);
		panel.add(textTelefono);
		
		JLabel lblCorreo = new JLabel("Correo :");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCorreo.setBounds(666, 20, 123, 20);
		panel.add(lblCorreo);
		
		textCorreo = new JTextField();
		textCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textCorreo.setColumns(10);
		textCorreo.setBounds(792, 21, 170, 20);
		panel.add(textCorreo);
		
		JLabel lblCategoria = new JLabel("Categoria :");
		lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCategoria.setBounds(666, 63, 123, 20);
		panel.add(lblCategoria);
		
		JLabel lblFechaCreacion = new JLabel("FechaCreacion :");
		lblFechaCreacion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaCreacion.setBounds(666, 105, 123, 20);
		panel.add(lblFechaCreacion);
		
		textCategoria = new JTextField();
		textCategoria.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textCategoria.setColumns(10);
		textCategoria.setBounds(792, 64, 170, 20);
		panel.add(textCategoria);
		
		textFechaCreacion = new JTextField();
		textFechaCreacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFechaCreacion.setColumns(10);
		textFechaCreacion.setBounds(792, 106, 170, 20);
		panel.add(textFechaCreacion);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(25, 57, 850, 320);
		contentPane.add(scrollPane_1);
		
		Administrar_Proveedor.jtable_Proveedor.setModel(new DefaultTableModel());
		jtable_Proveedor.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_Proveedor.jtable_Proveedor);
		
		JButton btnActualizarProveedor = new JButton("Actualizar Proveedor");
		btnActualizarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarProveedor();
			}
			private void actualizarProveedor() {
				
		        String Nombre = textNombre.getText();
		        String Apellido = textApellido.getText();
		        String RUC = textRUC.getText();
		        String RazonSocial = textRazonSocial.getText();
		        String Direccion = textDireccion.getText();
		        String Telefono = textTelefono.getText();
		        String Correo = textCorreo.getText();
		        String Categoria = textCategoria.getText();
		        String FechaCreacion = textFechaCreacion.getText();

		        // Check if all required fields are filled
		        if (Nombre.isEmpty() || Apellido.isEmpty() || RUC.isEmpty() || RazonSocial.isEmpty()
		                || Direccion.isEmpty() || Telefono.isEmpty() ||  Correo.isEmpty() ||  Categoria.isEmpty() || FechaCreacion.isEmpty())  {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Update the user in the database
		        consultas consulta = new consultas();
		        boolean resultado = consulta.actualizarProveedor(IdProveedor, Nombre, Apellido, RUC, RazonSocial, Direccion, Telefono, 
		        		Correo, Categoria, FechaCreacion);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Proveedor actualizado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            // Refresh the table after updating
		            DefaultTableModel model = (DefaultTableModel) jtable_Proveedor.getModel();
		            model.setRowCount(0);
		            cargarTablas();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
			}
			
		});
		btnActualizarProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizarProveedor.setBounds(901, 88, 157, 86);
		contentPane.add(btnActualizarProveedor);
		
		JButton btnEliminarProveedor = new JButton("Eliminar Proveedor");
		btnEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProveedor();
			}
			private void eliminarProveedor() {
		        int selectedRow = jtable_Proveedor.getSelectedRow();

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario a eliminar", "Advertencia",
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        DefaultTableModel model = (DefaultTableModel) jtable_Proveedor.getModel();
		        int IdProveedor = (int) model.getValueAt(selectedRow, 0); // Assuming the first column is IdUsuarios

		        consultas consulta = new consultas();
		        boolean resultado = consulta.eliminarProveedor(IdProveedor);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Proveedor eliminado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            // Remove the selected row from the table
		            model.removeRow(selectedRow);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al eliminar el Proveedor", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
			
			
		});
		btnEliminarProveedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminarProveedor.setBounds(901, 201, 157, 95);
		contentPane.add(btnEliminarProveedor);

		setSize(1100, 600);
		setLocationRelativeTo(null);
		
		conexion = new conexion();

		this.cargarTablas();
	}
	
	private void cargarTablas() {
	    Connection con = conexion.obtenerConexion();
	    DefaultTableModel model;

	    if (Administrar_Proveedor.jtable_Proveedor.getModel().getColumnCount() == 0) {
	        
	        model = new DefaultTableModel();
	        Administrar_Proveedor.jtable_Proveedor.setModel(model);

	        model.addColumn("IdProveedor");
	        model.addColumn("Nombre");
	        model.addColumn("Apellido");
	        model.addColumn("RUC");
	        model.addColumn("RazonSocial");
	        model.addColumn("Direccion");
	        model.addColumn("Telefono");
	        model.addColumn("Correo");
	        model.addColumn("Categoria");
	        model.addColumn("FechaCreacion");
	    } else {
	        // Clear the existing rows
	        model = (DefaultTableModel) Administrar_Proveedor.jtable_Proveedor.getModel();
	        model.setRowCount(0);
	    }

	    String sql = "SELECT IdProveedor, Nombre, Apellido, RUC, RazonSocial, Direccion, Telefono, Correo, Categoria, FechaCreacion FROM Proveedor;";
	    java.sql.Statement st;
	    try {
	        st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            Object fila[] = new Object[10];

	            for (int i = 0; i < 10; i++) {
	                fila[i] = rs.getObject(i + 1);
	            }
	            model.addRow(fila);
	        }

	        con.close();

	    } catch (SQLException e) {
	        System.out.println("Error al llenar la tabla Usuarios: " + e);
	    }

	    jtable_Proveedor.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int fila_point = jtable_Proveedor.rowAtPoint(e.getPoint());
	            int columna_point = 0;

	            if (fila_point > -1) {
	            	IdProveedor = (int) model.getValueAt(fila_point, columna_point);
	                EnviarDatosProveedorSeleccionado(IdProveedor);
	            }
	        }
	    });
	}

	private void EnviarDatosProveedorSeleccionado(int IdProveedors) {
	    try {
	        Connection con = conexion.obtenerConexion();
	        PreparedStatement pst = con.prepareStatement("SELECT * FROM Proveedor WHERE IdProveedor = ?");
	        pst.setInt(1, IdProveedor);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	        	textNombre.setText(rs.getString("Nombre"));
	        	textApellido.setText(rs.getString("Apellido"));
	        	textRUC.setText(rs.getString("RUC"));
	        	textRazonSocial.setText(rs.getString("RazonSocial"));
	        	textDireccion.setText(rs.getString("Direccion"));
	        	textTelefono.setText(rs.getString("Telefono"));
	        	textCorreo.setText(rs.getString("Correo"));
	        	textCategoria.setText(rs.getString("Categoria"));
	        	textFechaCreacion.setText(rs.getString("FechaCreacion"));
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}

}
