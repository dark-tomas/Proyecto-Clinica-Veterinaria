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

public class Mostrar_Mascotas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_AdopMascota = new JTable();
	private int IdAdopMascota;
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
					Administrar_Mascotas frame = new Administrar_Mascotas();
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
	public Mostrar_Mascotas() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdministrarMascotas = new JLabel("Lista de Mascotas");
		lblAdministrarMascotas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarMascotas.setForeground(new Color(64, 128, 128));
		lblAdministrarMascotas.setFont(new Font("Lucida Sans", Font.PLAIN, 30));
		lblAdministrarMascotas.setBackground(Color.WHITE);
		lblAdministrarMascotas.setBounds(195, -4, 475, 48);
		contentPane.add(lblAdministrarMascotas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(37, 55, 858, 328);
		contentPane.add(scrollPane_1);
		
		Administrar_Mascotas.jtable_AdopMascota.setModel(new DefaultTableModel());
		jtable_AdopMascota.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_Mascotas.jtable_AdopMascota);

		setSize(946, 462);
		setLocationRelativeTo(null);
		
		conexion = new conexion();

		this.cargarTablas();
		
	}
	
	private void cargarTablas() {
	    Connection con = conexion.obtenerConexion();
	    DefaultTableModel model;

	    if (Administrar_Mascotas.jtable_AdopMascota.getModel().getColumnCount() == 0) {
	        
	        model = new DefaultTableModel();
	        Administrar_Mascotas.jtable_AdopMascota.setModel(model);

	        model.addColumn("IdAdopMascota");
	        model.addColumn("Nombre");
	        model.addColumn("Raza");
	        model.addColumn("Disponibilidad");
	        model.addColumn("Pedigree");
	        model.addColumn("FechaIngreso");
	        model.addColumn("FechaAdopcion");
	        model.addColumn("id_TipoMascota");
	        model.addColumn("Genero");
	    } else {
	        // Clear the existing rows
	        model = (DefaultTableModel) Administrar_Mascotas.jtable_AdopMascota.getModel();
	        model.setRowCount(0);
	    }

	    String sql = "SELECT IdAdopMascota, Nombre, Raza, Disponibilidad, Pedigree, FechaIngreso, FechaAdopcion, id_TipoMascota, Genero FROM AdopMascota;";
	    java.sql.Statement st;
	    try {
	        st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            Object fila[] = new Object[9];

	            for (int i = 0; i < 9; i++) {
	                fila[i] = rs.getObject(i + 1);
	            }
	            model.addRow(fila);
	        }

	        con.close();

	    } catch (SQLException e) {
	        System.out.println("Error al llenar la tabla AdopMascota: " + e);
	    }

	    jtable_AdopMascota.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int fila_point = jtable_AdopMascota.rowAtPoint(e.getPoint());
	            int columna_point = 0;

	            if (fila_point > -1) {
	            	IdAdopMascota = (int) model.getValueAt(fila_point, columna_point);
	                EnviarDatosMascotaSeleccionado(IdAdopMascota);
	            }
	        }
	        
	        
	        
	    });
	}
	
	private void EnviarDatosMascotaSeleccionado(int IdAdopMascota) {
	    try {
	        Connection con = conexion.obtenerConexion();
	        PreparedStatement pst = con.prepareStatement("SELECT * FROM AdopMascota WHERE IdAdopMascota = ?");
	        pst.setInt(1, IdAdopMascota);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	        	
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}
	

}

