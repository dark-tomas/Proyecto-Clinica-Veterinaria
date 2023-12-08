package interfaces;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
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
import java.awt.Dialog.ModalExclusionType;

public class gestorproductos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSistemaVeterinaria;
	private List<JFrame> ventanasAbiertas;

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

	public gestorproductos() {

		ventanasAbiertas = new ArrayList<>();

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 915, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setBounds(0, 0, 1904, 1041);
		contentPane.add(panel);
		panel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(323, 82, 655, 22);
		panel.add(menuBar);

		JMenu mnNewUsuarios = new JMenu("Usuarios");
		mnNewUsuarios.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewUsuarios);

		JButton btnNewUsuario = new JButton("Nuevo Usuario");
		btnNewUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Nuevo_Usuario frameNewUsuario = new Nuevo_Usuario();
		        agregarVentanaAbierta(frameNewUsuario);  // Agrega la ventana a la lista
		        frameNewUsuario.setVisible(true);
		    }
		});
		
		mnNewUsuarios.add(btnNewUsuario);

		JButton btnAdminUsuarios = new JButton("Administrar Usuarios");
		btnAdminUsuarios.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Administrar_Usuarios frameAdminUsuarios = new Administrar_Usuarios();
		        agregarVentanaAbierta(frameAdminUsuarios);  // Agrega la ventana a la lista
		        frameAdminUsuarios.setVisible(true);
		    }
		});
		mnNewUsuarios.add(btnAdminUsuarios);

		JMenu mnNewCliente = new JMenu("Cliente");
		mnNewCliente.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewCliente);

		JButton btnNewCliente = new JButton("Nuevo Cliente");
		btnNewCliente.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Nuevo_Cliente frameNuevo_Cliente = new Nuevo_Cliente();
		        agregarVentanaAbierta(frameNuevo_Cliente);  // Agrega la ventana a la lista
		        frameNuevo_Cliente.setVisible(true);
		    }
		});
		mnNewCliente.add(btnNewCliente);

		JButton btnAdminClientes = new JButton("Administrar Clientes");
		btnAdminClientes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Administrar_Clientes frameAdminClientes = new Administrar_Clientes();
		        agregarVentanaAbierta(frameAdminClientes);  // Agrega la ventana a la lista
		        frameAdminClientes.setVisible(true);
		    }
		});
		mnNewCliente.add(btnAdminClientes);

		JMenu mnNewFactura = new JMenu("Facturar");
		mnNewFactura.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewFactura);

		JButton btnVenta = new JButton("Venta");
		btnVenta.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        ventas frameVenta = new ventas();
		        agregarVentanaAbierta(frameVenta);  // Agrega la ventana a la lista
		        frameVenta.setVisible(true);
		    }
		});

		mnNewFactura.add(btnVenta);

		JMenu mnNewProductos = new JMenu("Productos");
		mnNewProductos.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewProductos);

		JButton btnAddProducto = new JButton("Agregar Producto");
		btnAddProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar_Producto frameAddProducto = new Agregar_Producto();
				agregarVentanaAbierta(frameAddProducto);
				frameAddProducto.setVisible(true);
			}
		});
		mnNewProductos.add(btnAddProducto);

		JButton btnAdminProducto = new JButton("Administracion Productos");
		btnAdminProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrar_Productos frameAdminProducto = new Administrar_Productos();
				agregarVentanaAbierta(frameAdminProducto);
				frameAdminProducto.setVisible(true);
			}
		});
		mnNewProductos.add(btnAdminProducto);

		JMenu mnNewProveedores = new JMenu("Proveedores");
		mnNewProveedores.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewProveedores);

		JButton btnAddProveedor = new JButton("Agregar Proveedor");
		btnAddProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar_Proveedor frameAddProveedor = new Agregar_Proveedor();
				agregarVentanaAbierta(frameAddProveedor);
				frameAddProveedor.setVisible(true);
			}
		});
		mnNewProveedores.add(btnAddProveedor);

		JButton btnAdminProveedor = new JButton("Administrar Proveedores");
		btnAdminProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrar_Proveedor frameAdminProveedor = new Administrar_Proveedor();
				agregarVentanaAbierta(frameAdminProveedor);
				frameAdminProveedor.setVisible(true);
			}
		});
		mnNewProveedores.add(btnAdminProveedor);

		JMenu mnNewAdopcion = new JMenu("Adopcion Mascotas");
		mnNewAdopcion.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewAdopcion);

		JButton btnAddMascota = new JButton("Agregar Mascota");
		btnAddMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar_Mascota frameAddMascota = new Agregar_Mascota();
				agregarVentanaAbierta(frameAddMascota);
				frameAddMascota.setVisible(true);
			}
		});
		mnNewAdopcion.add(btnAddMascota);

		JButton btnAdminMascotas = new JButton("Administrar Mascotas");
		btnAdminMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrar_Mascotas frameAdminMascotas = new Administrar_Mascotas();
				agregarVentanaAbierta(frameAdminMascotas);
				frameAdminMascotas.setVisible(true);
			}
		});

		JButton btnTipoMascota = new JButton("Nuevo Tipo de Mascota");
		btnTipoMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar_TipoMascota frameTipoMascotal = new Agregar_TipoMascota();
				agregarVentanaAbierta(frameTipoMascotal);
				frameTipoMascotal.setVisible(true);
			}
		});
		mnNewAdopcion.add(btnTipoMascota);
		mnNewAdopcion.add(btnAdminMascotas);

		JMenu mnNewMenu_5 = new JMenu("Historial");
		mnNewMenu_5.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		menuBar.add(mnNewMenu_5);

		JButton btnAdminHistorial = new JButton("Administrar historial Medico");
		btnAdminHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrar_HistorialMedico frameAdminHistorial = new Administrar_HistorialMedico();
				agregarVentanaAbierta(frameAdminHistorial);
				frameAdminHistorial.setVisible(true);
			}
		});

		JButton btnNewAddHistorialMedico = new JButton("Agregar Historial Medico");
		btnNewAddHistorialMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar_HistorialMedico frameAdminHistorial = new Agregar_HistorialMedico();
				agregarVentanaAbierta(frameAdminHistorial);
				frameAdminHistorial.setVisible(true);
			}
			
		});
		mnNewMenu_5.add(btnNewAddHistorialMedico);
		mnNewMenu_5.add(btnAdminHistorial);

		JButton btnCerrar = new JButton("Cerrar Sesion");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarVentanasAbiertas(); // Cierra todas las ventanas
				dispose();
				login frameLogin = new login();
				agregarVentanaAbierta(frameLogin); // Agrega la nueva ventana a la lista
				frameLogin.setVisible(true);
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerrar.setBounds(836, 31, 142, 23);
		panel.add(btnCerrar);

		txtSistemaVeterinaria = new JTextField();
		txtSistemaVeterinaria.setEditable(false);
		txtSistemaVeterinaria.setBackground(new Color(255, 255, 255));
		txtSistemaVeterinaria.setFont(new Font("Lucida Sans", Font.BOLD, 20));
		txtSistemaVeterinaria.setHorizontalAlignment(SwingConstants.CENTER);
		txtSistemaVeterinaria.setText("Sistema Veterinaria");
		txtSistemaVeterinaria.setBounds(474, 23, 331, 36);
		panel.add(txtSistemaVeterinaria);
		txtSistemaVeterinaria.setColumns(10);

		JLabel lblNewFondo = new JLabel("");
		lblNewFondo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewFondo.setIcon(new ImageIcon(gestorproductos.class.getResource("/imagenes/Fondaso.png")));
		lblNewFondo.setBounds(0, 0, 1262, 683);
		panel.add(lblNewFondo);

		setSize(1280, 720);
		setLocationRelativeTo(null);

	}

	private void agregarVentanaAbierta(JFrame ventana) {
		ventanasAbiertas.add(ventana);
	}

	private void cerrarVentanasAbiertas() {
		for (JFrame ventana : ventanasAbiertas) {
			ventana.dispose();
		}
		ventanasAbiertas.clear();
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