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

public class Mostrar_Clientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_cliente = new JTable();
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
	public Mostrar_Clientes() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdministrarClientes = new JLabel("Lista de Clientes");
		lblAdministrarClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarClientes.setForeground(new Color(64, 128, 128));
		lblAdministrarClientes.setFont(new Font("Lucida Sans", Font.PLAIN, 30));
		lblAdministrarClientes.setBackground(Color.WHITE);
		lblAdministrarClientes.setBounds(205, 0, 475, 48);
		contentPane.add(lblAdministrarClientes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(24, 57, 869, 320);
		contentPane.add(scrollPane_1);
		
		Administrar_Clientes.jtable_cliente.setModel(new DefaultTableModel());
		jtable_cliente.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_Clientes.jtable_cliente);

		setSize(931, 457);
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
	        	
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}

}
