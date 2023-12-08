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

public class Mostrara_Productos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_productos = new JTable();
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
	public Mostrara_Productos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdministrarProductos = new JLabel("Catalogo de Productos");
		lblAdministrarProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarProductos.setForeground(new Color(64, 128, 128));
		lblAdministrarProductos.setFont(new Font("Lucida Sans", Font.PLAIN, 30));
		lblAdministrarProductos.setBackground(Color.WHITE);
		lblAdministrarProductos.setBounds(223, 17, 475, 48);
		contentPane.add(lblAdministrarProductos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(40, 76, 857, 320);
		contentPane.add(scrollPane_1);
		
		Administrar_Productos.jtable_productos.setModel(new DefaultTableModel());
		jtable_productos.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_Productos.jtable_productos);

		setSize(955, 469);
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
	        	
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}

}
