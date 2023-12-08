package interfaces;

import java.awt.EventQueue;

import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conex.consultas;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Agregar_Mascota extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textRaza;
	private JTextField textDisponivilidad;
	private JTextField textPedigree;
	private JComboBox<String> comboBoxTipoMascota;
	private JTextField textGenero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar_Mascota frame = new Agregar_Mascota();
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
	public Agregar_Mascota() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("  Regresar");
		btnNewButton.setIcon(new ImageIcon(Agregar_Mascota.class.getResource("/imagenes/atras.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorproductos frameGespro = new gestorproductos();
				frameGespro.setVisible(false);
				dispose();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(62, 374, 172, 46);
		contentPane.add(btnNewButton);

		JLabel lblAgregarNuevaMascota = new JLabel("Agregar Nueva Mascota");
		lblAgregarNuevaMascota.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarNuevaMascota.setForeground(new Color(64, 128, 128));
		lblAgregarNuevaMascota.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
		lblAgregarNuevaMascota.setBackground(Color.WHITE);
		lblAgregarNuevaMascota.setBounds(94, 11, 357, 48);
		contentPane.add(lblAgregarNuevaMascota);

		JButton btnGuardar = new JButton("     Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textNombre.getText();
				String raza = textRaza.getText();
				String disponibilidad = textDisponivilidad.getText();
				String pedigree = textPedigree.getText();
				String tipoMascota = comboBoxTipoMascota.getSelectedItem().toString();
				String genero = textGenero.getText();

				consultas consulta = new consultas();
				boolean resultado = consulta.registrarNuevaMascota(nombre, raza, disponibilidad, pedigree, tipoMascota, genero);

				if (resultado) {
					JOptionPane.showMessageDialog(null, "Mascota registrada correctamente.");

					// Limpiar los JTextField
					textNombre.setText("");
					textRaza.setText("");
					textDisponivilidad.setText("");
					textPedigree.setText("");
					comboBoxTipoMascota.setSelectedIndex(0); // Puedes ajustar esto si es necesario
					textGenero.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Error al registrar la mascota.");
				}

			}
		});
		btnGuardar.setIcon(new ImageIcon(Agregar_Mascota.class.getResource("/imagenes/user-add.png")));
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Lucida Sans", Font.PLAIN, 19));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBounds(262, 373, 189, 46);
		contentPane.add(btnGuardar);

		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(64, 128, 128));
		lblNombre.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(76, 70, 144, 37);
		contentPane.add(lblNombre);

		JLabel lblRaza = new JLabel("Raza :");
		lblRaza.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaza.setForeground(new Color(64, 128, 128));
		lblRaza.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblRaza.setBackground(Color.WHITE);
		lblRaza.setBounds(76, 118, 144, 37);
		contentPane.add(lblRaza);

		JLabel lblDisponivilidad = new JLabel("Disponibilidad :");
		lblDisponivilidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisponivilidad.setForeground(new Color(64, 128, 128));
		lblDisponivilidad.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblDisponivilidad.setBackground(Color.WHITE);
		lblDisponivilidad.setBounds(62, 166, 172, 37);
		contentPane.add(lblDisponivilidad);

		JLabel lblTipoMascota = new JLabel("Tipo Mascota :");
		lblTipoMascota.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoMascota.setForeground(new Color(64, 128, 128));
		lblTipoMascota.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblTipoMascota.setBackground(Color.WHITE);
		lblTipoMascota.setBounds(76, 312, 144, 37);
		contentPane.add(lblTipoMascota);

		textNombre = new JTextField();
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setForeground(Color.BLACK);
		textNombre.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textNombre.setColumns(10);
		textNombre.setBackground(Color.LIGHT_GRAY);
		textNombre.setBounds(229, 70, 222, 37);
		contentPane.add(textNombre);

		textRaza = new JTextField();
		textRaza.setHorizontalAlignment(SwingConstants.CENTER);
		textRaza.setForeground(Color.BLACK);
		textRaza.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textRaza.setColumns(10);
		textRaza.setBackground(Color.LIGHT_GRAY);
		textRaza.setBounds(230, 118, 222, 37);
		contentPane.add(textRaza);

		textDisponivilidad = new JTextField();
		textDisponivilidad.setHorizontalAlignment(SwingConstants.CENTER);
		textDisponivilidad.setForeground(Color.BLACK);
		textDisponivilidad.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textDisponivilidad.setColumns(10);
		textDisponivilidad.setBackground(Color.LIGHT_GRAY);
		textDisponivilidad.setBounds(229, 166, 222, 37);
		contentPane.add(textDisponivilidad);

		textPedigree = new JTextField();
		textPedigree.setHorizontalAlignment(SwingConstants.CENTER);
		textPedigree.setForeground(Color.BLACK);
		textPedigree.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textPedigree.setColumns(10);
		textPedigree.setBackground(Color.LIGHT_GRAY);
		textPedigree.setBounds(229, 214, 222, 37);
		contentPane.add(textPedigree);

		JLabel lblPedegri = new JLabel("Pedigree :");
		lblPedegri.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedegri.setForeground(new Color(64, 128, 128));
		lblPedegri.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblPedegri.setBackground(Color.WHITE);
		lblPedegri.setBounds(76, 214, 144, 37);
		contentPane.add(lblPedegri);

		comboBoxTipoMascota = new JComboBox<>();
		comboBoxTipoMascota.setBounds(230, 316, 221, 37);
		contentPane.add(comboBoxTipoMascota);
		
		JLabel lblGenero = new JLabel("Genero :");
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setForeground(new Color(64, 128, 128));
		lblGenero.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblGenero.setBackground(Color.WHITE);
		lblGenero.setBounds(76, 262, 144, 37);
		contentPane.add(lblGenero);
		
		textGenero = new JTextField();
		textGenero.setHorizontalAlignment(SwingConstants.CENTER);
		textGenero.setForeground(Color.BLACK);
		textGenero.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textGenero.setColumns(10);
		textGenero.setBackground(Color.LIGHT_GRAY);
		textGenero.setBounds(230, 262, 222, 37);
		contentPane.add(textGenero);

		consultas consulta = new consultas();
		List<String> tiposMascota = consulta.obtenerTiposMascota();
		for (String tipo : tiposMascota) {
			comboBoxTipoMascota.addItem(tipo);
		}

		setSize(529, 494);
		setLocationRelativeTo(null);
	}
}
