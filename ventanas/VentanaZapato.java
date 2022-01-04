package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.BD;
import clases.Pantalon;
import clases.Producto;
import clases.Sudadera;
import clases.TipoPantalon;
import clases.TipoSudadera;
import clases.TipoZapato;
import clases.Zapato;

public class VentanaZapato extends JFrame {

	private JPanel contentPane;
	public static JPanel panelCentral, panelP1, panelP11, panelP12, panelP2, panelP21, panelP22, panelP3, panelP31, panelP32, panelP4, panelP41, panelP42, panelNorte;
	private JFrame ventanaAnterior, ventanaActual;
	private JPanel panelSur;
	public static JButton btnVolver, btnBotas, btnDeportivas, btnTacones, btnFormales;
	static public JLabel lblBotas, lblDeportivas, lblTacones, lblFormales;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaZapato frame = new VentanaZapato(null);
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
	public VentanaZapato(JFrame va) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ventanaAnterior = va;
		ventanaActual = this;
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(0, 2));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		//--------------------------------------------------------------------------------------------------
	
		
		panelP1 = new JPanel();
		panelP1.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP1);
		
		panelP11 = new JPanel();
		panelP1.add(panelP11);
		
		panelP12 = new JPanel();
		panelP1.add(panelP12);
		
		btnBotas = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnBotas, "imagenes\\IconoZapatillasBotas.png",  120, 120, 120, 120);
		panelP11.add(btnBotas);
		
		lblBotas = new JLabel("BOTAS");
		panelP12.add(lblBotas);
		
		Connection con = BD.initBD("SweetWear.db");
		int sto = BD.obtenerStockProducto(con, "Botas");
		if (sto == 0) 
			lblBotas.setText("BOTAS" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblBotas.setText("BOTAS" + ": " + "Cantidades restantes: " + sto + " unidades");
		
		//----------------------------------------------------------------------------
		panelP2 = new JPanel();
		panelP2.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP2);
		
		panelP21 = new JPanel();
		panelP2.add(panelP21);
		
		panelP22 = new JPanel();
		panelP2.add(panelP22);
		
		btnDeportivas = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnDeportivas, "imagenes\\IconoZapatillasDeportivas.png",  120, 120, 120, 120);
		panelP21.add(btnDeportivas);
		
		lblDeportivas = new JLabel("DEPORTIVAS");
		panelP22.add(lblDeportivas);
		
		
		int sto1 = BD.obtenerStockProducto(con, "Deportivas");
		if (sto1 == 0) 
			lblDeportivas.setText("DEPORTIVAS" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblDeportivas.setText("DEPORTIVAS" + ": " + "Cantidades restantes: " + sto1 + " unidades");
		
		//--------------------------------------------------------------------------
		panelP3 = new JPanel();
		panelP3.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP3);
		
		panelP31 = new JPanel();
		panelP3.add(panelP31);
		
		panelP32 = new JPanel();
		panelP3.add(panelP32);
		
		btnTacones = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnTacones, "imagenes\\IconoZapatillasTacones.png",  120, 120, 120, 120);
		panelP31.add(btnTacones);
		
		lblTacones = new JLabel("TACONES");
		panelP32.add(lblTacones);
		
		int sto2 = BD.obtenerStockProducto(con, "Tacones");
		if (sto2 == 0)
			lblTacones.setText("TACONES: " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblTacones.setText("TACONES: " + "Cantidades restantes: " + sto2 + " unidades");
		
		
		//------------------------------------------------------------------------------------------------------------------
		panelP4 = new JPanel();
		panelP4.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP4);
		
		panelP41 = new JPanel();
		panelP4.add(panelP41);
		
		panelP42 = new JPanel();
		panelP4.add(panelP42);
		
		btnFormales = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnFormales, "imagenes\\IconoZapatillasFormales.png",  120, 120, 120, 120);
		panelP41.add(btnFormales);
		
		lblFormales = new JLabel("FORMALES");
		panelP42.add(lblFormales);
		
		int sto3 = BD.obtenerStockProducto(con, "Formales");
		if (sto3 == 0)
			lblFormales.setText("FORMALES: " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblFormales.setText("FORMALES: " + "Cantidades restantes: " + sto3 + " unidades");
		//------------------------------------------------------------------------------------------------------------------
		
		//EVENTOS DE VENTANA
		
		ventanaActual.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal.guardarMapaUsuariosEnFicheroDeTexto();
				VentanaPrincipal.guardarMapaPedidosEnFicheroDeTexto();
				VentanaPrincipal.guardarListaHistorialBusqueda();
				VentanaPrincipal.guardarMapaSatisfaccion();
			
			}
		});
		
		//---------------------------------------------------------------------------------------------------------------
		
		 
		
		//EVENTOS
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
			}
		});
		
		
		btnBotas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = VentanaPrincipal.lblNombre.getText();
				if (texto != "") {
					int resp = JOptionPane.showConfirmDialog(null,"¿Quieres aniadir este producto a tu cesta?");
					if(resp == JOptionPane.OK_OPTION) {
						//AQUI SE AÑADIRA AL CARRITO ESTE PRODUCTO
						String cant = JOptionPane.showInputDialog("Cuantas cantidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "Botas");
							if (stock >= canti) {
								if (stock == 0) 
									lblBotas.setText("BOTAS" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblBotas.setText("BOTAS" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Botas");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Zapato(0, p.getNombre(), p.getPrecio(), canti, p.getMarca(), p.getColor(), p.getRutaFoto(), /*p.isGoretex(),*/ TipoZapato.BOTAS)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
//								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Botas", "", "", "", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Botas", canti);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "¡¡PRODUCTO AÑADIDO CORRECTAMENTE!!");
								BD.closeBD(con);
							}else
								JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes unidades en stock", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "Error en cantidad, introduce un nÃºmero mayor que cero", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesión primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		
		btnDeportivas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = VentanaPrincipal.lblNombre.getText();
				if (texto != "") {
					int resp = JOptionPane.showConfirmDialog(null,"¿Quieres aniadir este producto a tu cesta?");
					if(resp == JOptionPane.OK_OPTION) {
						//AQUI SE AÑADIRA AL CARRITO ESTE PRODUCTO
						String cant = JOptionPane.showInputDialog("Cuantas cantidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "Deportivas");
							if (stock >= canti) {
								if (stock == 0) 
									lblDeportivas.setText("DEPORTIVAS:" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblDeportivas.setText("DEPORTIVAS" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Deportivas");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Zapato(0, p.getNombre(), p.getPrecio(), canti, p.getMarca(), p.getColor(), p.getRutaFoto(), /*p.isGoretex(),*/ TipoZapato.DEPORTIVAS)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Deportivas", "", "", "", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Deportivas", canti);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "¡¡PRODUCTO AÑADIDO CORRECTAMENTE!!");
								BD.closeBD(con);
							}else
								JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes unidades en stock", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "Error en cantidad, introduce un nÃºmero mayor que cero", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesión primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		btnTacones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = VentanaPrincipal.lblNombre.getText();
				if (texto != "") {
					int resp = JOptionPane.showConfirmDialog(null,"¿Quieres aniadir este producto a tu cesta?");
					if(resp == JOptionPane.OK_OPTION) {
						//AQUI SE AÑADIRA AL CARRITO ESTE PRODUCTO
						String cant = JOptionPane.showInputDialog("Cuantas cantidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "Tacones");
							if (stock >= canti) {
								if (stock == 0) 
									lblTacones.setText("TACONES:" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblTacones.setText("TACONES:" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Tacones");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Zapato(0, p.getNombre(), p.getPrecio(), canti, p.getMarca(), p.getColor(), p.getRutaFoto(), /*p.isGoretex(),*/ TipoZapato.TACONES)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Tacones", "", "", "", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Tacones", canti);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "¡¡PRODUCTO AÑADIDO CORRECTAMENTE!!");
								BD.closeBD(con);
							}else
								JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes unidades en stock", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "Error en cantidad, introduce un numero mayor que cero", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesión primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnFormales.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = VentanaPrincipal.lblNombre.getText();
				if (texto != "") {
					int resp = JOptionPane.showConfirmDialog(null,"¿Quieres aniadir este producto a tu cesta?");
					if(resp == JOptionPane.OK_OPTION) {
						//AQUI SE AÑADIRA AL CARRITO ESTE PRODUCTO
						String cant = JOptionPane.showInputDialog("Cuantas cantidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "Formales");
							if (stock >= canti) {
								if (stock == 0) 
									lblTacones.setText("FORMALES" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblTacones.setText("FORMALES" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Formales");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Zapato(0, p.getNombre(), p.getPrecio(), canti, p.getMarca(), p.getColor(), p.getRutaFoto(), /*p.isGoretex(),*/ TipoZapato.FORMALES)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Formales", "", "", "", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Formales", canti);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "¡¡PRODUCTO AÑADIDO CORRECTAMENTE!!");
								BD.closeBD(con);
							}else
								JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes unidades en stock", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "Error en cantidad, introduce un numero mayor que cero", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesión primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		});
		
	}
	

}