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

public class VentanaSudadera extends JFrame {

	private JPanel contentPane;
	public static JPanel panelCentral, panelP1, panelP11, panelP12, panelP2, panelP21, panelP22, panelP3, panelP31, panelP32, panelP4, panelP41, panelP42, panelNorte;
	private JFrame ventanaAnterior, ventanaActual;
	private JPanel panelSur;
	public static JButton btnVolver, btnConCremallera, btnSinCremallera, btnConGorro, btnSinGorro;
	static public JLabel lblConCremallera, lblSinCremallera, lblConGorro, lblSinGorro;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSudadera frame = new VentanaSudadera(null);
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
	public VentanaSudadera(JFrame va) {
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
		
		btnConCremallera = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnConCremallera, "imagenes\\IconoSudaderaConCremallera.png",  120, 120, 120, 120);
		panelP11.add(btnConCremallera);
		
		lblConCremallera = new JLabel("SUDADERA CON CREMALLERA");
		panelP12.add(lblConCremallera);
		
		Connection con = BD.initBD("SweetWear.db");
		int sto = BD.obtenerStockProducto(con, "Con cremallera");
		if (sto == 0) 
			lblConCremallera.setText("SUDADERA CON CREMALLERA" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblConCremallera.setText("SUDADERA CON CREMALLERA" + ": " + "Cantidades restantes: " + sto + " unidades");
		
		//----------------------------------------------------------------------------
		panelP2 = new JPanel();
		panelP2.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP2);
		
		panelP21 = new JPanel();
		panelP2.add(panelP21);
		
		panelP22 = new JPanel();
		panelP2.add(panelP22);
		
		btnSinCremallera = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnSinCremallera, "imagenes\\IconoSudaderaSinCremallera.png",  120, 120, 120, 120);
		panelP21.add(btnSinCremallera);
		
		lblSinCremallera = new JLabel("SUDADERA SIN CREMALLERA");
		panelP22.add(lblSinCremallera);
		
		
		int sto1 = BD.obtenerStockProducto(con, "Sin cremallera");
		if (sto1 == 0) 
			lblSinCremallera.setText("SUDADERA SIN CREMALLERA" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblSinCremallera.setText("SUDADERA SIN CREMALLERA" + ": " + "Cantidades restantes: " + sto1 + " unidades");
		
		//--------------------------------------------------------------------------
		panelP3 = new JPanel();
		panelP3.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP3);
		
		panelP31 = new JPanel();
		panelP3.add(panelP31);
		
		panelP32 = new JPanel();
		panelP3.add(panelP32);
		
		btnConGorro = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnConGorro, "imagenes\\IconoSudaderaConGorro.png",  120, 120, 120, 120);
		panelP31.add(btnConGorro);
		
		lblConGorro = new JLabel("SUDADERA CON GORRO");
		panelP32.add(lblConGorro);
		
		int sto2 = BD.obtenerStockProducto(con, "Con gorro");
		if (sto2 == 0)
			lblConGorro.setText("SUDADERA CON GORRO: " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblConGorro.setText("SUDADERA CON GORRO: " + "Cantidades restantes: " + sto2 + " unidades");
		
		
		//------------------------------------------------------------------------------------------------------------------
		panelP4 = new JPanel();
		panelP4.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP4);
		
		panelP41 = new JPanel();
		panelP4.add(panelP41);
		
		panelP42 = new JPanel();
		panelP4.add(panelP42);
		
		btnSinGorro = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnSinGorro, "imagenes\\IconoSudaderaSinGorro.png",  120, 120, 120, 120);
		panelP41.add(btnSinGorro);
		
		lblSinGorro = new JLabel("SUDADERA SIN GORRO");
		panelP42.add(lblSinGorro);
		
		int sto3 = BD.obtenerStockProducto(con, "Sin gorro");
		if (sto3 == 0)
			lblSinGorro.setText("SUDADERA SIN GORRO: " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblSinGorro.setText("SUDADERA SIN GORRO: " + "Cantidades restantes: " + sto3 + " unidades");
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
		
		
		btnConCremallera.addActionListener(new ActionListener() {
			
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
							int stock = BD.obtenerStockProducto(con, "Con cremallera");
							if (stock >= canti) {
								if (stock == 0) 
									lblConCremallera.setText("SUDADERA CON CREMALLERA" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblConCremallera.setText("SUDADERA CON CREMALLERA" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Con cremallera");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Sudadera(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoSudadera.CON_CREMALLERA)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
//								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Sudadera", "", "", "Con cremallera", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Con cremallera", canti);
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
		
		
		btnSinCremallera.addActionListener(new ActionListener() {
			
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
							int stock = BD.obtenerStockProducto(con, "Sin cremallera");
							if (stock >= canti) {
								if (stock == 0) 
									lblSinCremallera.setText("SUDADERA SIN CREMALLERA:" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblSinCremallera.setText("SUDADERA SIN CREMALLERA" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Sin cremallera");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Sudadera(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoSudadera.SIN_CREMALLERA)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Sudadera", "", "", "Sin cremallera", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Sin cremallera", canti);
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
		
		
		btnConGorro.addActionListener(new ActionListener() {
			
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
							int stock = BD.obtenerStockProducto(con, "Con gorro");
							if (stock >= canti) {
								if (stock == 0) 
									lblConGorro.setText("SUDADERA CON GORRO:" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblConGorro.setText("SUDADERA CON GORRO:" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Con gorro");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Sudadera(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoSudadera.CON_GORRO)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Sudadera", "", "", "Con gorro", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Con gorro", canti);
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
		
		btnSinGorro.addActionListener(new ActionListener() {
			
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
							int stock = BD.obtenerStockProducto(con, "Con gorro");
							if (stock >= canti) {
								if (stock == 0) 
									lblConGorro.setText("SUDADERA CON GORRO" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblConGorro.setText("SUDADERA CON GORRO" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Con gorro");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Sudadera(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoSudadera.SIN_GORRO)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Sudadera", "", "", "Sin gorro", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Sin gorro", canti);
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