package interfaces;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conex.consultas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Agregar_HistorialMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textPeso;
	private JTextField textTamano;
	private JTextField textEdad;
	private JTextField textEstado;
	private JTextField textAlergias;
	private JTextField textTratamientos;
	private JTextField textCirugia;
	private JComboBox<String> comboBoxIdAdopMascota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar_HistorialMedico frame = new Agregar_HistorialMedico();
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
	public Agregar_HistorialMedico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAgregarHistorialDe = new JLabel("Agregar Historial de la Mascota");
		lblAgregarHistorialDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarHistorialDe.setForeground(new Color(64, 128, 128));
		lblAgregarHistorialDe.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
		lblAgregarHistorialDe.setBackground(Color.WHITE);
		lblAgregarHistorialDe.setBounds(44, 11, 421, 48);
		contentPane.add(lblAgregarHistorialDe);
		
		JLabel lblPeso = new JLabel("Peso (kg)  :");
		lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeso.setForeground(new Color(64, 128, 128));
		lblPeso.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblPeso.setBackground(Color.WHITE);
		lblPeso.setBounds(67, 70, 144, 37);
		contentPane.add(lblPeso);
		
		textPeso = new JTextField();
		textPeso.setHorizontalAlignment(SwingConstants.CENTER);
		textPeso.setForeground(Color.BLACK);
		textPeso.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textPeso.setColumns(10);
		textPeso.setBackground(Color.LIGHT_GRAY);
		textPeso.setBounds(221, 71, 222, 37);
		contentPane.add(textPeso);
		
		JLabel lblTamaocm = new JLabel("Tamaño (cm)  :");
		lblTamaocm.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamaocm.setForeground(new Color(64, 128, 128));
		lblTamaocm.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblTamaocm.setBackground(Color.WHITE);
		lblTamaocm.setBounds(52, 118, 144, 37);
		contentPane.add(lblTamaocm);
		
		JLabel lblEdad = new JLabel(" Edad :");
		lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdad.setForeground(new Color(64, 128, 128));
		lblEdad.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblEdad.setBackground(Color.WHITE);
		lblEdad.setBounds(92, 166, 119, 37);
		contentPane.add(lblEdad);
		
		JLabel lblEstado = new JLabel(" Estado  :");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setForeground(new Color(64, 128, 128));
		lblEstado.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblEstado.setBackground(Color.WHITE);
		lblEstado.setBounds(67, 214, 144, 37);
		contentPane.add(lblEstado);
		
		JLabel lblAlergias = new JLabel(" Alergias  :");
		lblAlergias.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlergias.setForeground(new Color(64, 128, 128));
		lblAlergias.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblAlergias.setBackground(Color.WHITE);
		lblAlergias.setBounds(67, 260, 144, 37);
		contentPane.add(lblAlergias);
		
		textTamano = new JTextField();
		textTamano.setHorizontalAlignment(SwingConstants.CENTER);
		textTamano.setForeground(Color.BLACK);
		textTamano.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textTamano.setColumns(10);
		textTamano.setBackground(Color.LIGHT_GRAY);
		textTamano.setBounds(221, 119, 222, 37);
		contentPane.add(textTamano);
		
		textEdad = new JTextField();
		textEdad.setHorizontalAlignment(SwingConstants.CENTER);
		textEdad.setForeground(Color.BLACK);
		textEdad.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textEdad.setColumns(10);
		textEdad.setBackground(Color.LIGHT_GRAY);
		textEdad.setBounds(221, 166, 222, 37);
		contentPane.add(textEdad);
		
		JLabel lblTratamientos = new JLabel(" Tratamientos  :");
		lblTratamientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTratamientos.setForeground(new Color(64, 128, 128));
		lblTratamientos.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblTratamientos.setBackground(Color.WHITE);
		lblTratamientos.setBounds(26, 308, 175, 37);
		contentPane.add(lblTratamientos);
		
		textEstado = new JTextField();
		textEstado.setHorizontalAlignment(SwingConstants.CENTER);
		textEstado.setForeground(Color.BLACK);
		textEstado.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textEstado.setColumns(10);
		textEstado.setBackground(Color.LIGHT_GRAY);
		textEstado.setBounds(221, 214, 222, 37);
		contentPane.add(textEstado);
		
		textAlergias = new JTextField();
		textAlergias.setHorizontalAlignment(SwingConstants.CENTER);
		textAlergias.setForeground(Color.BLACK);
		textAlergias.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textAlergias.setColumns(10);
		textAlergias.setBackground(Color.LIGHT_GRAY);
		textAlergias.setBounds(221, 260, 222, 37);
		contentPane.add(textAlergias);
		
		JLabel lblCirugia = new JLabel(" Cirugia  :");
		lblCirugia.setHorizontalAlignment(SwingConstants.CENTER);
		lblCirugia.setForeground(new Color(64, 128, 128));
		lblCirugia.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblCirugia.setBackground(Color.WHITE);
		lblCirugia.setBounds(86, 356, 125, 37);
		contentPane.add(lblCirugia);
		
		textTratamientos = new JTextField();
		textTratamientos.setHorizontalAlignment(SwingConstants.CENTER);
		textTratamientos.setForeground(Color.BLACK);
		textTratamientos.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textTratamientos.setColumns(10);
		textTratamientos.setBackground(Color.LIGHT_GRAY);
		textTratamientos.setBounds(221, 308, 222, 37);
		contentPane.add(textTratamientos);
		
		textCirugia = new JTextField();
		textCirugia.setHorizontalAlignment(SwingConstants.CENTER);
		textCirugia.setForeground(Color.BLACK);
		textCirugia.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textCirugia.setColumns(10);
		textCirugia.setBackground(Color.LIGHT_GRAY);
		textCirugia.setBounds(221, 356, 222, 37);
		contentPane.add(textCirugia);
		
		JButton btnRegresar = new JButton("  Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gestorproductos frameGespro = new gestorproductos();
				frameGespro.setVisible(false);
				dispose();
			}
		});
		btnRegresar.setIcon(new ImageIcon(Agregar_HistorialMedico.class.getResource("/imagenes/atras.png")));
		btnRegresar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		btnRegresar.setBackground(Color.WHITE);
		btnRegresar.setBounds(44, 477, 172, 46);
		contentPane.add(btnRegresar);
		
		JButton btnGuardar = new JButton("     Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String PesoKG = textPeso.getText();
				String Tamaño = textTamano.getText();
				String Edad = textEdad.getText();
				String Estado = textEstado.getText();
				String Alergias = textAlergias.getText();
				String Tratamientos = textTratamientos.getText();
				String Cirugia = textCirugia.getText();
				String HistorialMedico = comboBoxIdAdopMascota.getSelectedItem().toString();
				

				consultas consulta = new consultas();
				boolean resultado = consulta.registrarHistorialMedico(PesoKG, Tamaño, Edad, Estado, Alergias, Tratamientos, Cirugia, HistorialMedico);

				if (resultado) {
					JOptionPane.showMessageDialog(null, "Mascota registrada correctamente.");

					// Limpiar los JTextField
					textPeso.setText("");
					textTamano.setText("");
					textEdad.setText("");
					textEstado.setText("");
					textAlergias.setText("");
					textTratamientos.setText("");
					textCirugia.setText("");
					comboBoxIdAdopMascota.setSelectedIndex(0); // Puedes ajustar esto si es necesario
					
				} else {
					JOptionPane.showMessageDialog(null, "Error al registrar la mascota.");
				}
				
				
				
			}
			
			
			
		});
		btnGuardar.setIcon(new ImageIcon(Agregar_HistorialMedico.class.getResource("/imagenes/user-add.png")));
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Lucida Sans", Font.PLAIN, 19));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBounds(264, 476, 189, 46);
		contentPane.add(btnGuardar);
		
		JLabel lblMascota = new JLabel(" Mascota  :");
		lblMascota.setHorizontalAlignment(SwingConstants.CENTER);
		lblMascota.setForeground(new Color(64, 128, 128));
		lblMascota.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblMascota.setBackground(Color.WHITE);
		lblMascota.setBounds(86, 413, 125, 37);
		contentPane.add(lblMascota);
		
		comboBoxIdAdopMascota = new JComboBox<String>();
		comboBoxIdAdopMascota.setBounds(221, 417, 221, 37);
		contentPane.add(comboBoxIdAdopMascota);
		
		consultas consulta = new consultas();
		List<String> AdopMascota = consulta.obtenerAdopMascota();
		for (String Nombre : AdopMascota) {
			comboBoxIdAdopMascota.addItem(Nombre);
		}
		
		
		setSize(531, 611);
		setLocationRelativeTo(null);
	}
}
