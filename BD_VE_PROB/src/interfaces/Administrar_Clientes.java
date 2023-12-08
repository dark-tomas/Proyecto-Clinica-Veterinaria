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

public class Administrar_Clientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_cliente = new JTable();
	private JTextField textNombres;
	private JTextField textApellidos;
	private JTextField textDNI;
	private JTextField textGenero;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textCorreo;
	private int id_cliente;
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
					Administrar_Clientes frame = new Administrar_Clientes();
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
	public Administrar_Clientes() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdministrarClientes = new JLabel("Administrar Clientes");
		lblAdministrarClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarClientes.setForeground(new Color(64, 128, 128));
		lblAdministrarClientes.setFont(new Font("Lucida Sans", Font.PLAIN, 30));
		lblAdministrarClientes.setBackground(Color.WHITE);
		lblAdministrarClientes.setBounds(314, 0, 475, 48);
		contentPane.add(lblAdministrarClientes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(38, 59, 853, 320);
		contentPane.add(scrollPane_1);
		
		Administrar_Clientes.jtable_cliente.setModel(new DefaultTableModel());
		jtable_cliente.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_Clientes.jtable_cliente);
		
		JButton btnActualizar = new JButton("Actualizar Cliente");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarCliente();
			}
			private void actualizarCliente() {
				// Get values from JTextFields
		        String Nombres = textNombres.getText();
		        String Apellidos = textApellidos.getText();
		        String DNI = textDNI.getText();
		        String Genero = textGenero.getText();
		        String Direccion = textDireccion.getText();
		        String Telefono = textTelefono.getText();
		        String Correo = textCorreo.getText();

		        // Check if all required fields are filled
		        if (Nombres.isEmpty() || Apellidos.isEmpty() || DNI.isEmpty() || Genero.isEmpty()
		                || Direccion.isEmpty() || Telefono.isEmpty() ||  Correo.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Update the user in the database
		        consultas consulta = new consultas();
		        boolean resultado = consulta.actualizarCliente(id_cliente, Nombres, Apellidos, DNI, Genero, Direccion , Telefono, 
		        		Correo);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            // Refresh the table after updating
		            DefaultTableModel model = (DefaultTableModel) jtable_cliente.getModel();
		            model.setRowCount(0);
		            cargarTablas();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al actualizar el Cliente", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizar.setBounds(919, 111, 140, 87);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar Cliente");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCliente();
			}
			private void eliminarCliente() {
		        int selectedRow = jtable_cliente.getSelectedRow();

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente a eliminar", "Advertencia",
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        DefaultTableModel model = (DefaultTableModel) jtable_cliente.getModel();
		        int id_cliente = (int) model.getValueAt(selectedRow, 0); // Assuming the first column is IdUsuarios

		        consultas consulta = new consultas();
		        boolean resultado = consulta.eliminarCliente(id_cliente);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            // Remove the selected row from the table
		            model.removeRow(selectedRow);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al eliminar el Cliente", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
			
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(919, 234, 140, 87);
		contentPane.add(btnEliminar);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(38, 390, 944, 160);
		contentPane.add(panel);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNombres.setBounds(10, 22, 95, 20);
		panel.add(lblNombres);
		
		textNombres = new JTextField();
		textNombres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNombres.setColumns(10);
		textNombres.setBounds(115, 18, 178, 30);
		panel.add(textNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblApellidos.setBounds(10, 69, 95, 23);
		panel.add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textApellidos.setColumns(10);
		textApellidos.setBounds(115, 66, 178, 30);
		panel.add(textApellidos);
		
		textGenero = new JTextField();
		textGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textGenero.setColumns(10);
		textGenero.setBounds(115, 109, 178, 30);
		panel.add(textGenero);
		
		JLabel lblDNI = new JLabel("DNI :");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDNI.setBounds(338, 113, 60, 20);
		panel.add(lblDNI);
		
		textDNI = new JTextField();
		textDNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDNI.setColumns(10);
		textDNI.setBounds(408, 109, 170, 30);
		panel.add(textDNI);
		
		JLabel lblGenero = new JLabel("Genero :");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGenero.setBounds(15, 113, 90, 20);
		panel.add(lblGenero);
		
		JLabel lblDireccion = new JLabel("Direccion :");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDireccion.setBounds(303, 70, 120, 20);
		panel.add(lblDireccion);
		
		textDireccion = new JTextField();
		textDireccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDireccion.setColumns(10);
		textDireccion.setBounds(408, 66, 170, 30);
		panel.add(textDireccion);
		
		textTelefono = new JTextField();
		textTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTelefono.setColumns(10);
		textTelefono.setBounds(408, 18, 170, 30);
		panel.add(textTelefono);
		
		JLabel lblTelefono = new JLabel("Telefono :");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTelefono.setBounds(303, 21, 95, 22);
		panel.add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCorreo.setBounds(730, 36, 102, 20);
		panel.add(lblCorreo);
		
		textCorreo = new JTextField();
		textCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		textCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		textCorreo.setColumns(10);
		textCorreo.setBounds(611, 67, 295, 30);
		panel.add(textCorreo);

		setSize(1100, 600);
		setLocationRelativeTo(null);
		
		conexion = new conexion();

		this.cargarTablas();
	}
	
	private void cargarTablas() {
	    Connection con = conexion.obtenerConexion();
	    DefaultTableModel model;

	    if (Administrar_Clientes.jtable_cliente.getModel().getColumnCount() == 0) {
	        // Initialize the model only if it's not already set
	        model = new DefaultTableModel();
	        Administrar_Clientes.jtable_cliente.setModel(model);

	        model.addColumn("id_cliente");
	        model.addColumn("Nombres");
	        model.addColumn("Apellidos");
	        model.addColumn("DNI");
	        model.addColumn("Genero");
	        model.addColumn("Direccion");
	        model.addColumn("Telefono");
	        model.addColumn("Correo");
	    } else {
	        // Clear the existing rows
	        model = (DefaultTableModel) Administrar_Clientes.jtable_cliente.getModel();
	        model.setRowCount(0);
	    }

	    String sql = "SELECT id_cliente, Nombres, Apellidos, DNI, Genero, Direccion, Telefono, Correo FROM Cliente;";
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
	        System.out.println("Error al llenar la tabla Clientes: " + e);
	    }

	    jtable_cliente.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int fila_point = jtable_cliente.rowAtPoint(e.getPoint());
	            int columna_point = 0;

	            if (fila_point > -1) {
	            	id_cliente = (int) model.getValueAt(fila_point, columna_point);
	                EnviarDatosClienteSeleccionado(id_cliente);
	            }
	        }
	    });
	}
	
	
	private void EnviarDatosClienteSeleccionado(int id_cliente) {
	    try {
	        Connection con = conexion.obtenerConexion();
	        PreparedStatement pst = con.prepareStatement("SELECT * FROM Cliente WHERE id_cliente = ?");
	        pst.setInt(1, id_cliente);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	        	textNombres.setText(rs.getString("Nombres"));
	        	textApellidos.setText(rs.getString("Apellidos"));
	        	textDNI.setText(rs.getString("DNI"));
	        	textGenero.setText(rs.getString("Genero"));
	        	textDireccion.setText(rs.getString("Direccion"));
	        	textTelefono.setText(rs.getString("Telefono"));
	        	textCorreo.setText(rs.getString("Correo"));
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}

}
