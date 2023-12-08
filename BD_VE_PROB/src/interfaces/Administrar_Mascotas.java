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

public class Administrar_Mascotas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_AdopMascota = new JTable();
	private JTextField textNombre;
	private JTextField textRaza;
	private JTextField textDisponibilidad;
	private JTextField textPedigree;
	private JTextField textFechaIngreso;
	private JTextField textFechaAdopcion;
	private JTextField textTipoUsuario;
	private int IdAdopMascota;
	public static JScrollPane scrollPane = new JScrollPane();
	
	private conexion conexion;
	private JTextField textField;
	private JTextField textGenero;
	
	
	
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
	public Administrar_Mascotas() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdministrarMascotas = new JLabel("Administrar Mascotas");
		lblAdministrarMascotas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarMascotas.setForeground(new Color(64, 128, 128));
		lblAdministrarMascotas.setFont(new Font("Lucida Sans", Font.PLAIN, 30));
		lblAdministrarMascotas.setBackground(Color.WHITE);
		lblAdministrarMascotas.setBounds(270, 0, 475, 48);
		contentPane.add(lblAdministrarMascotas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(46, 55, 858, 328);
		contentPane.add(scrollPane_1);
		
		Administrar_Mascotas.jtable_AdopMascota.setModel(new DefaultTableModel());
		jtable_AdopMascota.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_Mascotas.jtable_AdopMascota);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(46, 394, 983, 139);
		contentPane.add(panel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNombre.setBounds(42, 20, 80, 20);
		panel.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNombre.setColumns(10);
		textNombre.setBounds(168, 16, 170, 30);
		panel.add(textNombre);
		
		JLabel lblRaza = new JLabel("Raza :");
		lblRaza.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRaza.setBounds(52, 60, 83, 23);
		panel.add(lblRaza);
		
		textRaza = new JTextField();
		textRaza.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textRaza.setColumns(10);
		textRaza.setBounds(168, 57, 170, 30);
		panel.add(textRaza);
		
		textPedigree = new JTextField();
		textPedigree.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPedigree.setColumns(10);
		textPedigree.setBounds(507, 16, 196, 30);
		panel.add(textPedigree);
		
		JLabel lblDisponibilidad = new JLabel("Disponibilidad :");
		lblDisponibilidad.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDisponibilidad.setBounds(21, 108, 138, 20);
		panel.add(lblDisponibilidad);
		
		textDisponibilidad = new JTextField();
		textDisponibilidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDisponibilidad.setColumns(10);
		textDisponibilidad.setBounds(168, 104, 170, 30);
		panel.add(textDisponibilidad);
		
		JLabel lblPedigree = new JLabel("Pedigree:");
		lblPedigree.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPedigree.setBounds(407, 20, 90, 20);
		panel.add(lblPedigree);
		
		JLabel lblFechaIngreso = new JLabel("Fecha  Ingreso :");
		lblFechaIngreso.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFechaIngreso.setBounds(348, 67, 170, 20);
		panel.add(lblFechaIngreso);
		
		textFechaIngreso = new JTextField();
		textFechaIngreso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFechaIngreso.setColumns(10);
		textFechaIngreso.setBounds(507, 63, 196, 30);
		panel.add(textFechaIngreso);
		
		textFechaAdopcion = new JTextField();
		textFechaAdopcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFechaAdopcion.setColumns(10);
		textFechaAdopcion.setBounds(507, 104, 196, 30);
		panel.add(textFechaAdopcion);
		
		JLabel lblFechaAdopcion = new JLabel("Fecha Adopcion:");
		lblFechaAdopcion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFechaAdopcion.setBounds(348, 107, 170, 22);
		panel.add(lblFechaAdopcion);
		
		JLabel lblTipoMascota = new JLabel("Tipo Usuario:");
		lblTipoMascota.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTipoMascota.setBounds(787, 11, 125, 20);
		panel.add(lblTipoMascota);
		
		textTipoUsuario = new JTextField();
		textTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textTipoUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		textTipoUsuario.setColumns(10);
		textTipoUsuario.setBounds(751, 32, 191, 30);
		panel.add(textTipoUsuario);
		
		JLabel lblGenero = new JLabel("Genero :");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGenero.setBounds(801, 67, 90, 20);
		panel.add(lblGenero);
		
		textGenero = new JTextField();
		textGenero.setHorizontalAlignment(SwingConstants.CENTER);
		textGenero.setFont(new Font("Tahoma", Font.BOLD, 13));
		textGenero.setColumns(10);
		textGenero.setBounds(751, 98, 191, 26);
		panel.add(textGenero);
		
		JButton btnActualizar = new JButton("Actualizar Mascota");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarMascota();
				
			}
			
			private void actualizarMascota() {
				
		        String Nombre = textNombre.getText();
		        String Raza = textRaza.getText();
		        String Disponibilidad = textDisponibilidad.getText();
		        String Pedigree = textPedigree.getText();
		        String FechaIngreso = textFechaIngreso.getText();
		        String FechaAdopcion = textFechaAdopcion.getText();
		        String id_TipoMascota = textTipoUsuario.getText();
		        String genero = textGenero.getText();

		        
		        if (Nombre.isEmpty() || Raza.isEmpty() || Disponibilidad.isEmpty() || Pedigree.isEmpty()
		                || FechaIngreso.isEmpty() || FechaAdopcion.isEmpty() ||  id_TipoMascota.isEmpty() || genero.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        
		        consultas consulta = new consultas();
		        boolean resultado = consulta.actualizarMascota(IdAdopMascota, Nombre, Raza, Disponibilidad, Pedigree, FechaIngreso, FechaAdopcion, 
		        		id_TipoMascota, genero);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Mascota actualizado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            
		            DefaultTableModel model = (DefaultTableModel) jtable_AdopMascota.getModel();
		            model.setRowCount(0);
		            cargarTablas();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al actualizar la Mascota", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizar.setBounds(914, 85, 140, 89);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar Mascota");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarMascota();
				
			}
			
			private void eliminarMascota() {
		        int selectedRow = jtable_AdopMascota.getSelectedRow();

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, seleccione una Mascota a eliminar", "Advertencia",
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        DefaultTableModel model = (DefaultTableModel) jtable_AdopMascota.getModel();
		        int IdAdopMascota = (int) model.getValueAt(selectedRow, 0); 

		        consultas consulta = new consultas();
		        boolean resultado = consulta.eliminarMascota(IdAdopMascota);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Mascota eliminado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            
		            model.removeRow(selectedRow);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(914, 190, 140, 89);
		contentPane.add(btnEliminar);

		setSize(1100, 600);
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
	        	textNombre.setText(rs.getString("Nombre"));
	        	textRaza.setText(rs.getString("Raza"));
	        	textDisponibilidad.setText(rs.getString("Disponibilidad"));
	        	textPedigree.setText(rs.getString("Pedigree"));
	        	textFechaIngreso.setText(rs.getString("FechaIngreso"));
	        	textFechaAdopcion.setText(rs.getString("FechaAdopcion"));
	        	textTipoUsuario.setText(rs.getString("id_TipoMascota"));
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}
	

}
