package interfaces;

import conex.conexion;

import conex.consultas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Administrar_Usuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTable jtable_usuarios = new JTable();
	private JTextField txt_Nombre;
	private JTextField txt_Apellido;
	private JTextField txt_Telf;
	private JTextField txt_Contra;
	private JTextField txt_DNI;
	private JTextField txt_Cargo;
	private int IdUsuarios;
	public static JScrollPane scrollPane = new JScrollPane();

	private conexion conexion;
	private JTextField textField;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrar_Usuarios frame = new Administrar_Usuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Administrar_Usuarios() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(30, 70, 850, 320);
		contentPane.add(scrollPane_1);

		Administrar_Usuarios.jtable_usuarios.setModel(new DefaultTableModel());
		jtable_usuarios.setEnabled(true);
		// jtable_usuarios.setEditingRow(false);
		scrollPane_1.setViewportView(Administrar_Usuarios.jtable_usuarios);

		JLabel lblAdministrarUsuarios = new JLabel("Administrador de Usuarios");
		lblAdministrarUsuarios.setBounds(332, 11, 420, 48);
		lblAdministrarUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrarUsuarios.setForeground(new Color(64, 128, 128));
		lblAdministrarUsuarios.setFont(new Font("Lucida Sans", Font.BOLD, 30));
		lblAdministrarUsuarios.setBackground(Color.WHITE);
		contentPane.add(lblAdministrarUsuarios);

		JButton btnActualizar = new JButton("Actualizar Usuario");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarUsuario();
				
			}

			private void actualizarUsuario() {
        // Get values from JTextFields
        String nombre = txt_Nombre.getText();
        String apellido = txt_Apellido.getText();
        String contrasena = txt_Contra.getText();
        String dni = txt_DNI.getText();
        String telefono = txt_Telf.getText();
        String cargo = txt_Cargo.getText();
        String tipoUsuario = textField.getText();

        // Check if all required fields are filled
        if (nombre.isEmpty() || apellido.isEmpty() || contrasena.isEmpty() || dni.isEmpty()
                || telefono.isEmpty() || cargo.isEmpty() ||  tipoUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the user in the database
        consultas consulta = new consultas();
        boolean resultado = consulta.actualizarUsuario(IdUsuarios, nombre, apellido, contrasena, dni, telefono, cargo, 
                tipoUsuario);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            // Refresh the table after updating
            DefaultTableModel model = (DefaultTableModel) jtable_usuarios.getModel();
            model.setRowCount(0);
            cargarTablas();
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
		});
		btnActualizar.setBounds(910, 100, 140, 86);
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnActualizar);

		JButton btnEliminar = new JButton("Eliminar Usuario");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarUsuario();
			}
			
			private void eliminarUsuario() {
		        int selectedRow = jtable_usuarios.getSelectedRow();

		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario a eliminar", "Advertencia",
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        DefaultTableModel model = (DefaultTableModel) jtable_usuarios.getModel();
		        int idUsuario = (int) model.getValueAt(selectedRow, 0); // Assuming the first column is IdUsuarios

		        consultas consulta = new consultas();
		        boolean resultado = consulta.eliminarUsuario(idUsuario);

		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente", "Éxito",
		                    JOptionPane.INFORMATION_MESSAGE);
		            // Remove the selected row from the table
		            model.removeRow(selectedRow);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    }

			
		});
		btnEliminar.setBounds(910, 217, 140, 95);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnEliminar);

		JPanel panel = new JPanel();
		panel.setBounds(30, 415, 1030, 120);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(25, 20, 80, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel);

		txt_Nombre = new JTextField();
		txt_Nombre.setBounds(115, 18, 170, 30);
		txt_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(txt_Nombre);
		txt_Nombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setBounds(25, 68, 80, 23);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1);

		txt_Apellido = new JTextField();
		txt_Apellido.setBounds(115, 66, 170, 30);
		txt_Apellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Apellido.setColumns(10);
		panel.add(txt_Apellido);

		txt_Telf = new JTextField();
		txt_Telf.setBounds(403, 66, 170, 30);
		txt_Telf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Telf.setColumns(10);
		panel.add(txt_Telf);

		JLabel lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setBounds(303, 20, 120, 20);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_2);

		txt_Contra = new JTextField();
		txt_Contra.setBounds(423, 18, 150, 30);
		txt_Contra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Contra.setColumns(10);
		panel.add(txt_Contra);

		JLabel lblNewLabel_1_1 = new JLabel("Teléfono:");
		lblNewLabel_1_1.setBounds(303, 68, 90, 20);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_3 = new JLabel("DNI:");
		lblNewLabel_3.setBounds(591, 20, 80, 20);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_3);

		txt_DNI = new JTextField();
		txt_DNI.setBounds(648, 18, 170, 30);
		txt_DNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_DNI.setColumns(10);
		panel.add(txt_DNI);

		txt_Cargo = new JTextField();
		txt_Cargo.setBounds(659, 66, 170, 30);
		txt_Cargo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Cargo.setColumns(10);
		panel.add(txt_Cargo);

		JLabel lblNewLabel_1_2 = new JLabel("Cargo:");
		lblNewLabel_1_2.setBounds(591, 68, 80, 22);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_3_1 = new JLabel("Tipo Usuario:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(856, 20, 125, 20);
		panel.add(lblNewLabel_3_1);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		textField.setBounds(856, 51, 150, 30);
		panel.add(textField);
		textField.setColumns(10);

		/*
		 * consultas consulta = new consultas(); List<String> tipos_usuario =
		 * consulta.obtenerTiposUsuarios(); for (String id_tipo_usuario : tipos_usuario)
		 * { ComBox_Usuario.addItem(id_tipo_usuario); }
		 */

		setSize(1100, 600);
		setLocationRelativeTo(null);
		
		
		conexion = new conexion();

		this.cargarTablas();
	}

	private void cargarTablas() {
	    Connection con = conexion.obtenerConexion();
	    DefaultTableModel model;

	    if (Administrar_Usuarios.jtable_usuarios.getModel().getColumnCount() == 0) {
	        // Initialize the model only if it's not already set
	        model = new DefaultTableModel();
	        Administrar_Usuarios.jtable_usuarios.setModel(model);

	        model.addColumn("IdUsuarios");
	        model.addColumn("Nombre");
	        model.addColumn("Apellido");
	        model.addColumn("Contraseña");
	        model.addColumn("DNI");
	        model.addColumn("Telefono");
	        model.addColumn("Cargo");
	        model.addColumn("Tipo Usuario");
	    } else {
	        // Clear the existing rows
	        model = (DefaultTableModel) Administrar_Usuarios.jtable_usuarios.getModel();
	        model.setRowCount(0);
	    }

	    String sql = "SELECT IdUsuarios, Nombre, Apellido, Contraseña, DNI, Telefono, Cargo, id_tipo_usuario FROM Usuarios;";
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

	    jtable_usuarios.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int fila_point = jtable_usuarios.rowAtPoint(e.getPoint());
	            int columna_point = 0;

	            if (fila_point > -1 && columna_point < model.getColumnCount()) {
	                IdUsuarios = (int) model.getValueAt(fila_point, columna_point);
	                EnviarDatosUserSeleccionado(IdUsuarios);
	            }
	        }
	    });
	}

	private void EnviarDatosUserSeleccionado(int IdUsuarios) {
	    try {
	        Connection con = conexion.obtenerConexion();
	        PreparedStatement pst = con.prepareStatement("SELECT * FROM Usuarios WHERE IdUsuarios = ?");
	        pst.setInt(1, IdUsuarios);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            txt_Nombre.setText(rs.getString("Nombre"));
	            txt_Apellido.setText(rs.getString("Apellido"));
	            txt_Contra.setText(rs.getString("Contraseña"));
	            txt_DNI.setText(rs.getString("DNI"));
	            txt_Telf.setText(rs.getString("Telefono"));
	            txt_Cargo.setText(rs.getString("Cargo"));
	            textField.setText(rs.getString("id_tipo_usuario"));
	        }
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al seleccionar: " + e);
	    }
	}
}

