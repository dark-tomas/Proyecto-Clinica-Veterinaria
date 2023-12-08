package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gestorproductosUsuarioComun extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSistemaVeterinaria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestorproductos frame = new gestorproductos();
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
	public gestorproductosUsuarioComun() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 915, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setBounds(0, 0, 1264, 681);
		contentPane.add(panel);
		panel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(406, 96, 466, 22);
		panel.add(menuBar);

		JMenu mnNewCliente = new JMenu("Cliente");
		mnNewCliente.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewCliente);

		JButton btnNewCliente = new JButton("Nuevo Cliente");
		btnNewCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nuevo_Cliente frameNewCliente = new Nuevo_Cliente();
				frameNewCliente.setVisible(true);
			}
		});
		mnNewCliente.add(btnNewCliente);

		JButton btnListaClientes = new JButton("Mostrar Clientes");
		btnListaClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mostrar_Clientes frameListaClientes = new Mostrar_Clientes();
				frameListaClientes.setVisible(true);

			}
		});
		mnNewCliente.add(btnListaClientes);

		JMenu mnNewVenta = new JMenu("Facturar");
		mnNewVenta.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewVenta);

		JButton btnVenta = new JButton("Venta");
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventas frameVenta = new ventas();
				frameVenta.setVisible(true);
			}
		});
		mnNewVenta.add(btnVenta);

		JMenu mnNewProductos = new JMenu("Productos");
		mnNewProductos.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewProductos);

		JButton btnAddProducto = new JButton("Agregar Producto");
		btnAddProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Agregar_Producto frameAddProducto = new Agregar_Producto();
				frameAddProducto.setVisible(true);
			
			}
		});
		mnNewProductos.add(btnAddProducto);

		JButton btnCatalogoDeProductos = new JButton("Mostrar Catalogo de Productos");
		btnCatalogoDeProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mostrara_Productos frameCatalogoDeProductos = new Mostrara_Productos();
				frameCatalogoDeProductos.setVisible(true);
			}
		});
		mnNewProductos.add(btnCatalogoDeProductos);

		JMenu mnNewAdopcion = new JMenu("Adopcion Mascotas");
		mnNewAdopcion.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewAdopcion);

		JButton btnNewAddMascota = new JButton("Agregar Mascota");
		btnNewAddMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar_Mascota frameNewAddMascota = new Agregar_Mascota();
				frameNewAddMascota.setVisible(true);
				
			}
		});
		mnNewAdopcion.add(btnNewAddMascota);

		JButton btnMostrarMascotas = new JButton("Mostrara Mascotas");
		btnMostrarMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mostrar_Mascotas frameMostrarMascotas = new Mostrar_Mascotas();
				frameMostrarMascotas.setVisible(true);
			}
		});
		mnNewAdopcion.add(btnMostrarMascotas);

		JMenu mnNewMenu_5 = new JMenu("Historial");
		mnNewMenu_5.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewMenu_5);

		JButton btnMostrarHistorialMed = new JButton("Mostrar Historial Medico");
		btnMostrarHistorialMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mostrar_HistorialMedico frameMostrarHistorialMed = new Mostrar_HistorialMedico();
				frameMostrarHistorialMed.setVisible(true);
			}
		});
		mnNewMenu_5.add(btnMostrarHistorialMed);

		JButton btnCerrar = new JButton("Cerrar Sesion");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				login frameLogin = new login();
				frameLogin.setVisible(true);
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerrar.setBounds(875, 31, 142, 23);
		panel.add(btnCerrar);

		txtSistemaVeterinaria = new JTextField();
		txtSistemaVeterinaria.setEditable(false);
		txtSistemaVeterinaria.setBackground(new Color(255, 255, 255));
		txtSistemaVeterinaria.setFont(new Font("Lucida Sans", Font.BOLD, 20));
		txtSistemaVeterinaria.setHorizontalAlignment(SwingConstants.CENTER);
		txtSistemaVeterinaria.setText("Sistema Veterinaria");
		txtSistemaVeterinaria.setBounds(473, 23, 331, 36);
		panel.add(txtSistemaVeterinaria);
		txtSistemaVeterinaria.setColumns(10);

		JLabel lblNewFondo = new JLabel("");
		lblNewFondo.setIcon(new ImageIcon(gestorproductosUsuarioComun.class.getResource("/imagenes/Fondaso.png")));
		lblNewFondo.setBounds(0, 0, 1264, 681);
		panel.add(lblNewFondo);

		setSize(1280, 720);
		setLocationRelativeTo(null);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
