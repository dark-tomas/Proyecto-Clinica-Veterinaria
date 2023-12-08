package interfaces;

import conex.conexion;

import conex.consultas;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Mostrar_HistorialMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_HistorialMedico = new JTable();
	private int IdHistorialMedico;
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
					Administrar_HistorialMedico frame = new Administrar_HistorialMedico();
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
	public Mostrar_HistorialMedico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministrarHistorialMed = new JLabel("Historial Medico De las Mascotas");
		lblAdministrarHistorialMed.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarHistorialMed.setForeground(new Color(64, 128, 128));
		lblAdministrarHistorialMed.setFont(new Font("Lucida Sans", Font.BOLD, 30));
		lblAdministrarHistorialMed.setBackground(Color.WHITE);
		lblAdministrarHistorialMed.setBounds(39, 0, 864, 48);
		contentPane.add(lblAdministrarHistorialMed);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(39, 54, 864, 320);
		contentPane.add(scrollPane_1);
		
		
		Administrar_HistorialMedico.jtable_HistorialMedico.setModel(new DefaultTableModel());
		jtable_HistorialMedico.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_HistorialMedico.jtable_HistorialMedico);
		
		
		setSize(958, 451);
		setLocationRelativeTo(null);
		
		
		conexion = new conexion();

		this.cargarTablas();
	}
	
	private void cargarTablas() {
	    Connection con = conexion.obtenerConexion();
	    DefaultTableModel model;

	    if (Administrar_HistorialMedico.jtable_HistorialMedico.getModel().getColumnCount() == 0) {
	        // Initialize the model only if it's not already set
	        model = new DefaultTableModel();
	        Administrar_HistorialMedico.jtable_HistorialMedico.setModel(model);

	        model.addColumn("IdHistorialMedico");
	        model.addColumn("PesoKG");
	        model.addColumn("Tamaño");
	        model.addColumn("Edad");
	        model.addColumn("Estado");
	        model.addColumn("Alergias");
	        model.addColumn("Tratamientos");
	        model.addColumn("Cirugia");
	        model.addColumn("FechaRegistro");
	        model.addColumn("IdAdopMascota");
	    } else {
	        // Clear the existing rows
	        model = (DefaultTableModel) Administrar_HistorialMedico.jtable_HistorialMedico.getModel();
	        model.setRowCount(0);
	    }

	    String sql = "SELECT IdHistorialMedico, PesoKG, Tamaño, Edad, Estado, Alergias, Tratamientos, Cirugia, FechaRegistro, IdAdopMascota FROM HistorialMedico;";
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
	        System.out.println("Error al llenar la tabla Historial: " + e);
	    }

	    jtable_HistorialMedico.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int fila_point = jtable_HistorialMedico.rowAtPoint(e.getPoint());
	            int columna_point = 0;

	            if (fila_point > -1) {
	            	IdHistorialMedico = (int) model.getValueAt(fila_point, columna_point);
	                EnviarDatosHistorialSeleccionado(IdHistorialMedico);
	            }
	        }
	    });
	}

	private void EnviarDatosHistorialSeleccionado(int IdHistorialMedico) {
	    try {
	        Connection con = conexion.obtenerConexion();
	        PreparedStatement pst = con.prepareStatement("SELECT * FROM HistorialMedico WHERE IdHistorialMedico = ?");
	        pst.setInt(1, IdHistorialMedico);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	        	
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}

}
