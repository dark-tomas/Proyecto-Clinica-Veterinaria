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

public class Administrar_HistorialMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_HistorialMedico = new JTable();
	private JTextField textPeso;
	private JTextField textTamano;
	private JTextField textEdad;
	private JTextField textEstado;
	private JTextField textAlergias;
	private JTextField textTratamientos;
	private JTextField textCirugia;
	private JTextField textFechaRegistro;
	private JTextField textIdAdopMascota;
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
	public Administrar_HistorialMedico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministrarHistorialMed = new JLabel("Administrador de Historiales Medicos");
		lblAdministrarHistorialMed.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarHistorialMed.setForeground(new Color(64, 128, 128));
		lblAdministrarHistorialMed.setFont(new Font("Lucida Sans", Font.BOLD, 30));
		lblAdministrarHistorialMed.setBackground(Color.WHITE);
		lblAdministrarHistorialMed.setBounds(203, 0, 656, 48);
		contentPane.add(lblAdministrarHistorialMed);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(39, 54, 850, 320);
		contentPane.add(scrollPane_1);
		
		
		Administrar_HistorialMedico.jtable_HistorialMedico.setModel(new DefaultTableModel());
		jtable_HistorialMedico.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_HistorialMedico.jtable_HistorialMedico);
		
		JButton btnActualizarHistorial = new JButton("Actualizar Historial");
		btnActualizarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarHistorial();
			}
			private void actualizarHistorial() {
				// Get values from JTextFields
		        String PesoKG = textPeso.getText();
		        String Tamano = textTamano.getText();
		        String Edad = textEdad.getText();
		        String Estado = textEstado.getText();
		        String Alergias = textAlergias.getText();
		        String Tratamientos = textTratamientos.getText();
		        String Cirugia = textCirugia.getText();
		        String FechaRegistro = textFechaRegistro.getText();
		        String IdAdopMascota = textIdAdopMascota.getText();
		        

		        // Check if all required fields are filled
		        if (PesoKG.isEmpty() || Tamano.isEmpty() || Edad.isEmpty() || Estado.isEmpty()
		                || Alergias.isEmpty() || Tratamientos.isEmpty() ||  Cirugia.isEmpty() ||  FechaRegistro.isEmpty() ||  IdAdopMascota.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Update the user in the database
		        consultas consulta = new consultas();
		        boolean resultado = consulta.actualizarHistorial(IdHistorialMedico, PesoKG, Tamano, Edad, Estado, Alergias, Tratamientos, 
		        		Cirugia,FechaRegistro,IdAdopMascota );

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            // Refresh the table after updating
		            DefaultTableModel model = (DefaultTableModel) jtable_HistorialMedico.getModel();
		            model.setRowCount(0);
		            cargarTablas();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnActualizarHistorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizarHistorial.setBounds(899, 93, 155, 86);
		contentPane.add(btnActualizarHistorial);
		
		JButton btnEliminarHistorial = new JButton("Eliminar Historial");
		btnEliminarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarHistorial();
			}
			
			private void eliminarHistorial() {
		        int selectedRow = jtable_HistorialMedico.getSelectedRow();

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, seleccione un historial a eliminar", "Advertencia",
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        DefaultTableModel model = (DefaultTableModel) jtable_HistorialMedico.getModel();
		        int IdHistorialMedico = (int) model.getValueAt(selectedRow, 0); // Assuming the first column is IdUsuarios

		        consultas consulta = new consultas();
		        boolean resultado = consulta.eliminarHistorial(IdHistorialMedico);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Historial eliminado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            // Remove the selected row from the table
		            model.removeRow(selectedRow);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al eliminar el Historial", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnEliminarHistorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminarHistorial.setBounds(899, 211, 155, 95);
		contentPane.add(btnEliminarHistorial);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(39, 385, 1030, 151);
		contentPane.add(panel);
		
		JLabel lblPesokg = new JLabel("PesoKG :");
		lblPesokg.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPesokg.setBounds(25, 20, 80, 20);
		panel.add(lblPesokg);
		
		textPeso = new JTextField();
		textPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPeso.setColumns(10);
		textPeso.setBounds(115, 20, 170, 20);
		panel.add(textPeso);
		
		JLabel lblTamao = new JLabel("Tamaño:");
		lblTamao.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTamao.setBounds(25, 58, 80, 20);
		panel.add(lblTamao);
		
		textTamano = new JTextField();
		textTamano.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTamano.setColumns(10);
		textTamano.setBounds(115, 59, 170, 20);
		panel.add(textTamano);
		
		JLabel lblEdad = new JLabel("Edad :");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEdad.setBounds(25, 100, 80, 20);
		panel.add(lblEdad);
		
		textEdad = new JTextField();
		textEdad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textEdad.setColumns(10);
		textEdad.setBounds(115, 102, 170, 20);
		panel.add(textEdad);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEstado.setBounds(335, 20, 123, 20);
		panel.add(lblEstado);
		
		textEstado = new JTextField();
		textEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textEstado.setColumns(10);
		textEstado.setBounds(468, 21, 170, 20);
		panel.add(textEstado);
		
		JLabel lblAlergias = new JLabel("Alergias  :");
		lblAlergias.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlergias.setBounds(335, 58, 123, 20);
		panel.add(lblAlergias);
		
		textAlergias = new JTextField();
		textAlergias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textAlergias.setColumns(10);
		textAlergias.setBounds(468, 60, 170, 20);
		panel.add(textAlergias);
		
		JLabel lblTratamientos = new JLabel("Tratamientos :");
		lblTratamientos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTratamientos.setBounds(335, 100, 123, 20);
		panel.add(lblTratamientos);
		
		textTratamientos = new JTextField();
		textTratamientos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTratamientos.setColumns(10);
		textTratamientos.setBounds(468, 102, 170, 20);
		panel.add(textTratamientos);
		
		JLabel lblCirugia = new JLabel("Cirugia :");
		lblCirugia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCirugia.setBounds(666, 20, 123, 20);
		panel.add(lblCirugia);
		
		textCirugia = new JTextField();
		textCirugia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textCirugia.setColumns(10);
		textCirugia.setBounds(792, 21, 170, 20);
		panel.add(textCirugia);
		
		JLabel lblFechaRegistro = new JLabel("Fecha Registro :");
		lblFechaRegistro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaRegistro.setBounds(666, 63, 123, 20);
		panel.add(lblFechaRegistro);
		
		JLabel lblIdadopmascota = new JLabel("IdAdopMascota :");
		lblIdadopmascota.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdadopmascota.setBounds(666, 105, 147, 20);
		panel.add(lblIdadopmascota);
		
		textFechaRegistro = new JTextField();
		textFechaRegistro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFechaRegistro.setColumns(10);
		textFechaRegistro.setBounds(792, 64, 170, 20);
		panel.add(textFechaRegistro);
		
		textIdAdopMascota = new JTextField();
		textIdAdopMascota.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textIdAdopMascota.setColumns(10);
		textIdAdopMascota.setBounds(792, 106, 170, 20);
		panel.add(textIdAdopMascota);
		
		
		setSize(1100, 600);
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
	        	textPeso.setText(rs.getString("PesoKG"));
	        	textTamano.setText(rs.getString("Tamaño"));
	        	textEdad.setText(rs.getString("Edad"));
	        	textEstado.setText(rs.getString("Estado"));
	        	textAlergias.setText(rs.getString("Alergias"));
	        	textTratamientos.setText(rs.getString("Tratamientos"));
	        	textCirugia.setText(rs.getString("Cirugia"));
	        	textFechaRegistro.setText(rs.getString("FechaRegistro"));
	        	textIdAdopMascota.setText(rs.getString("IdAdopMascota"));
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}

}
