package interfaces;

import conex.consultas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField JTextUsuario;
	private JPasswordField JpasswordPass;
	private consultas consultas = new consultas();

	/**
	 * Launch the application.
	 */
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 418, 604);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblIngreso = new JLabel("Ingreso");
		lblIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreso.setForeground(new Color(64, 128, 128));
		lblIngreso.setBackground(new Color(255, 255, 255));
		lblIngreso.setFont(new Font("Dialog", Font.BOLD, 30));
		lblIngreso.setBounds(124, 11, 187, 55);
		panel.add(lblIngreso);

		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraseña.setForeground(new Color(64, 128, 128));
		lblContraseña.setFont(new Font("Dialog", Font.BOLD, 24));
		lblContraseña.setBackground(Color.WHITE);
		lblContraseña.setBounds(124, 392, 176, 32);
		panel.add(lblContraseña);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(new Color(64, 128, 128));
		lblUsuario.setFont(new Font("Dialog", Font.BOLD, 24));
		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setBounds(154, 306, 120, 32);
		panel.add(lblUsuario);

		JTextUsuario = new JTextField();
		JTextUsuario.setBackground(new Color(192, 192, 192));
		JTextUsuario.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		JTextUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		JTextUsuario.setForeground(new Color(0, 0, 0));
		JTextUsuario.setBounds(102, 349, 283, 32);
		panel.add(JTextUsuario);
		JTextUsuario.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/imagenes/USUARIO.png")));
		lblNewLabel.setBounds(53, 338, 39, 55);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(login.class.getResource("/imagenes/lock.png")));
		lblNewLabel_1.setBounds(53, 426, 39, 41);
		panel.add(lblNewLabel_1);

		JButton JbtnAcceder = new JButton("Iniciar Sesion");
		JbtnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String usuario = JTextUsuario.getText();
				String clave = new String(JpasswordPass.getPassword());
				String tipoUsuario = consultas.autenticacion(usuario, clave);

				if (tipoUsuario.equals("Administrador")) {
					gestorproductos frameGestor = new gestorproductos();
					frameGestor.setVisible(true);
					dispose();
					JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso para el Administrador!");
				} else if (tipoUsuario.equals("UsuarioComun")) {
					gestorproductosUsuarioComun frameGestor = new gestorproductosUsuarioComun();
					frameGestor.setVisible(true);
					dispose();
					JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso para Usuario Común!");
				} else {

					JOptionPane.showMessageDialog(null, "Error: Usuario o contraseña incorrectos");
				}

			}

		});
		JbtnAcceder.setBackground(new Color(192, 192, 192));
		JbtnAcceder.setFont(new Font("Dialog", Font.BOLD, 18));
		JbtnAcceder.setForeground(new Color(64, 128, 128));
		JbtnAcceder.setBounds(124, 503, 193, 64);
		panel.add(JbtnAcceder);

		JpasswordPass = new JPasswordField();
		JpasswordPass.setHorizontalAlignment(SwingConstants.CENTER);
		JpasswordPass.setBackground(new Color(192, 192, 192));
		JpasswordPass.setForeground(new Color(0, 0, 0));
		JpasswordPass.setBounds(102, 435, 283, 32);
		panel.add(JpasswordPass);

		JLabel lblNewImg = new JLabel("");
		lblNewImg.setIcon(new ImageIcon(login.class.getResource("/imagenes/Logo_Vet.jpeg")));
		lblNewImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewImg.setBounds(94, 77, 250, 228);
		panel.add(lblNewImg);

		setSize(443, 643);
		setLocationRelativeTo(null);

	}
}
