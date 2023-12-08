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
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Agregar_TipoMascota extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNuevoTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar_TipoMascota frame = new Agregar_TipoMascota();
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
	public Agregar_TipoMascota() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 486, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAgregarTipoMascota = new JLabel("Agregar Tipo Mascota");
		lblAgregarTipoMascota.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarTipoMascota.setForeground(new Color(64, 128, 128));
		lblAgregarTipoMascota.setFont(new Font("Lucida Sans", Font.PLAIN, 24));
		lblAgregarTipoMascota.setBackground(Color.WHITE);
		lblAgregarTipoMascota.setBounds(62, 11, 357, 48);
		contentPane.add(lblAgregarTipoMascota);

		JLabel lblNuevoTipo = new JLabel("Nuevo Tipo :");
		lblNuevoTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoTipo.setForeground(new Color(64, 128, 128));
		lblNuevoTipo.setFont(new Font("Lucida Sans", Font.PLAIN, 20));
		lblNuevoTipo.setBackground(Color.WHITE);
		lblNuevoTipo.setBounds(41, 88, 145, 37);
		contentPane.add(lblNuevoTipo);

		JButton btnNewButton = new JButton("  Regresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestorproductos frameGespro = new gestorproductos();
				frameGespro.setVisible(false);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Agregar_TipoMascota.class.getResource("/imagenes/atras.png")));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(41, 153, 179, 45);
		contentPane.add(btnNewButton);

		JButton btnGuardar = new JButton("     Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Tipo = textNuevoTipo.getText();

				consultas consultasObj = new consultas();
				boolean result = consultasObj.agregartipomascota(Tipo);

				if (result) {
					JOptionPane.showMessageDialog(null, "Tipo registrado correctamente.");
					textNuevoTipo.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "Error al registrar Producto.");
				}

			}
		});
		btnGuardar.setIcon(new ImageIcon(Agregar_TipoMascota.class.getResource("/imagenes/user-add.png")));
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Lucida Sans", Font.PLAIN, 19));
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBounds(230, 151, 189, 46);
		contentPane.add(btnGuardar);

		textNuevoTipo = new JTextField();
		textNuevoTipo.setHorizontalAlignment(SwingConstants.CENTER);
		textNuevoTipo.setForeground(Color.BLACK);
		textNuevoTipo.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		textNuevoTipo.setColumns(10);
		textNuevoTipo.setBackground(Color.LIGHT_GRAY);
		textNuevoTipo.setBounds(196, 89, 222, 37);
		contentPane.add(textNuevoTipo);

		setSize(475, 275);
		setLocationRelativeTo(null);
	}
}
