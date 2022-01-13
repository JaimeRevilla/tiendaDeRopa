package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ventanaAnterior = va;
		ventanaActual = this;
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setBackground(new Color(122,217,196));
		
		btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		VentanaPrincipal.ponerFotoABoton(btnVolver, "imagenes\\IconoSalir.png", 30, 30, 30, 30);

		
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(0, 2));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(new Color(122,217,196));	
		
		panelP1 = new JPanel();
		panelP1.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP1);
		panelP1.setBackground(new Color(122,217,196));
		
		panelP11 = new JPanel();
		panelP1.add(panelP11);
		panelP11.setBackground(new Color(122,217,196));
		
		panelP12 = new JPanel();
		panelP1.add(panelP12);
		panelP12.setBackground(new Color(122,217,196));
		
		btnConCremallera = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnConCremallera, "imagenes\\IconoSudaderaConCremallera.png",  120, 120, 120, 120);
		panelP11.add(btnConCremallera);
		
		lblConCremallera = new JLabel("SUDADERA CON CREMALLERA");
		panelP12.add(lblConCremallera);
		
		Connection con = BD.initBD("SweetWear.db");
		int sto = BD.obtenerStockProducto(con, "ConCremallera");
		double pre1 = BD.obtenerPrecioProducto(con,"ConCremallera");
		if (sto == 0) 
			lblConCremallera.setText("SUDADERA CON CREMALLERA" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblConCremallera.setText("SUDADERA CON CREMALLERA" + ": " + "Unidades restantes: " + sto + " unidades     " + "Precio: " + pre1 + " euros");
		
		panelP2 = new JPanel();
		panelP2.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP2);
		panelP2.setBackground(new Color(122,217,196));
		
		panelP21 = new JPanel();
		panelP2.add(panelP21);
		panelP21.setBackground(new Color(122,217,196));
		
		panelP22 = new JPanel();
		panelP2.add(panelP22);
		panelP22.setBackground(new Color(122,217,196));
		
		btnSinCremallera = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnSinCremallera, "imagenes\\IconoSudaderaSinCremallera.png",  120, 120, 120, 120);
		panelP21.add(btnSinCremallera);
		
		lblSinCremallera = new JLabel("SUDADERA SIN CREMALLERA");
		panelP22.add(lblSinCremallera);
		
		
		int sto1 = BD.obtenerStockProducto(con, "SinCremallera");
		double pre2 = BD.obtenerPrecioProducto(con,"SinCremallera");
		if (sto1 == 0) 
			lblSinCremallera.setText("SUDADERA SIN CREMALLERA" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblSinCremallera.setText("SUDADERA SIN CREMALLERA" + ": " + "Unidades restantes: " + sto1 + " unidades     " + "Precio: " + pre2 + " euros");
		
		panelP3 = new JPanel();
		panelP3.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP3);
		panelP3.setBackground(new Color(122,217,196));
		
		panelP31 = new JPanel();
		panelP3.add(panelP31);
		panelP31.setBackground(new Color(122,217,196));
		
		panelP32 = new JPanel();
		panelP3.add(panelP32);
		panelP32.setBackground(new Color(122,217,196));
		
		btnConGorro = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnConGorro, "imagenes\\IconoSudaderaConGorro.png",  120, 120, 120, 120);
		panelP31.add(btnConGorro);
		
		lblConGorro = new JLabel("SUDADERA CON GORRO");
		panelP32.add(lblConGorro);
		
		int sto2 = BD.obtenerStockProducto(con, "ConGorro");
		double pre3 = BD.obtenerPrecioProducto(con,"ConGorro");

		if (sto2 == 0)
			lblConGorro.setText("SUDADERA CON GORRO: " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblConGorro.setText("SUDADERA CON GORRO: " + "Unidades restantes: " + sto2 + " unidades      " + "Precio: " + pre3 + " euros");
		
		
		//------------------------------------------------------------------------------------------------------------------
		panelP4 = new JPanel();
		panelP4.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP4);
		panelP4.setBackground(new Color(122,217,196));
		
		panelP41 = new JPanel();
		panelP4.add(panelP41);
		panelP41.setBackground(new Color(122,217,196));
		
		panelP42 = new JPanel();
		panelP4.add(panelP42);
		panelP42.setBackground(new Color(122,217,196));
		
		btnSinGorro = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnSinGorro, "imagenes\\IconoSudaderaSinGorro.png",  120, 120, 120, 120);
		panelP41.add(btnSinGorro);
		
		lblSinGorro = new JLabel("SUDADERA SIN GORRO");
		panelP42.add(lblSinGorro);
		
		int sto3 = BD.obtenerStockProducto(con, "SinGorro");
		double pre4 = BD.obtenerPrecioProducto(con,"SinGorro");
		if (sto3 == 0)
			lblSinGorro.setText("SUDADERA SIN GORRO: " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblSinGorro.setText("SUDADERA SIN GORRO: " + "Cantidades restantes: " + sto3 + " unidades    " + "Precio: " + pre4 + " euros");
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
				VentanaPrincipal.log.log(Level.INFO, "Los ficheros de informacion han sido actualizados correctamente");
			
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
						String cant = JOptionPane.showInputDialog("Cuantas unidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "ConCremallera");
							if (stock >= canti) {
								if (stock == 0) 
									lblConCremallera.setText("SUDADERA CON CREMALLERA" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblConCremallera.setText("SUDADERA CON CREMALLERA" + ": " + "Unidades restantes: " + (stock-canti) + " unidades   " + "Precio: " + pre1 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "ConCremallera");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Sudadera(p.getCodigo(), p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoSudadera.CON_CREMALLERA)); 
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Sudadera", "", "", "", "ConCremallera", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "ConCremallera", canti);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "PRODUCTO AÑIADIDO CORRECTAMENTE!!");
								BD.closeBD(con);
							}else
								JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes unidades en stock", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "Error en cantidad, introduce un numero mayor que cero", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesion primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
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
						String cant = JOptionPane.showInputDialog("Cuantas unidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "SinCremallera");
							if (stock >= canti) {
								if (stock == 0) 
									lblSinCremallera.setText("SUDADERA SIN CREMALLERA:" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblSinCremallera.setText("SUDADERA SIN CREMALLERA" + ": " + "Unidades restantes: " + (stock-canti) + " unidades   " + "Precio: " + pre2 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "SinCremallera");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Sudadera(p.getCodigo(), p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoSudadera.SIN_CREMALLERA)); 
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Sudadera", "", "", "", "SinCremallera", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "SinCremallera", canti);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "PRODUCTO AÑIADIDO CORRECTAMENTE!!");
								BD.closeBD(con);
							}else
								JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes unidades en stock", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "Error en cantidad, introduce un numero mayor que cero", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesion primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
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
						String cant = JOptionPane.showInputDialog("Cuantas unidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "ConGorro");
							if (stock >= canti) {
								if (stock == 0) 
									lblConGorro.setText("SUDADERA CON GORRO:" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblConGorro.setText("SUDADERA CON GORRO:" + ": " + "Unidades restantes: " + (stock-canti) + " unidades   " + "Precio: " + pre3 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "ConGorro");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Sudadera(p.getCodigo(), p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoSudadera.CON_GORRO)); 
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Sudadera", "", "", "", "ConGorro", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "ConGorro", canti);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "PRODUCTO AÑIADIDO CORRECTAMENTE!!");
								BD.closeBD(con);
							}else
								JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes unidades en stock", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "Error en cantidad, introduce un numero mayor que cero", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesion primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
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
						String cant = JOptionPane.showInputDialog("Cuantas unidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "SinGorro");
							if (stock >= canti) {
								if (stock == 0) 
									lblSinGorro.setText("SUDADERA SIN GORRO" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblSinGorro.setText("SUDADERA SIN GORRO" + ": " + "Unidades restantes: " + (stock-canti) + " unidades   " + "Precio: " + pre4 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "SinGorro");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Sudadera(p.getCodigo(), p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoSudadera.SIN_GORRO)); 
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Sudadera", "", "", "", "SinGorro", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "SinGorro", canti);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "PRODUCTO AÑIADIDO CORRECTAMENTE!!");
								BD.closeBD(con);
							}else
								JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes unidades en stock", "ERROR", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "Error en cantidad, introduce un numero mayor que cero", "ERROR", JOptionPane.ERROR_MESSAGE);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesion primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		});
		
	}
	

}