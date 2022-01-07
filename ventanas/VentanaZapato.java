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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setSize(1650, 1080);
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
		VentanaPrincipal.ponerFotoABoton(btnVolver, "imagenes\\IconoSalir.png", 30, 30, 30, 30);
		
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
		int sto = BD.obtenerStockProducto(con, "Bota");
		double pre1 = BD.obtenerPrecioProducto(con,"Bota");
		if (sto == 0) 
			lblBotas.setText("BOTAS" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblBotas.setText("BOTAS" + ": " + "Unidades restantes: " + sto + " unidades    " + "Precio: " + pre1 + " euros");
		
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
		
		
		int sto1 = BD.obtenerStockProducto(con, "Deportiva");
		double pre2 = BD.obtenerPrecioProducto(con,"Deportiva");
		if (sto1 == 0) 
			lblDeportivas.setText("DEPORTIVAS" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblDeportivas.setText("DEPORTIVAS" + ": " + "Unidades restantes: " + sto1 + " unidades    " + "Precio: " + pre2 + " euros");
		
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
		
		int sto2 = BD.obtenerStockProducto(con, "Tacon");
		double pre3 = BD.obtenerPrecioProducto(con,"Tacon");
		if (sto2 == 0)
			lblTacones.setText("TACONES: " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblTacones.setText("TACONES: " + "Unidades restantes: " + sto2 + " unidades    " + "Precio: " + pre3 + " euros");
		
		
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
		
		int sto3 = BD.obtenerStockProducto(con, "Formal");
		double pre4 = BD.obtenerPrecioProducto(con,"Formal");
		if (sto3 == 0)
			lblFormales.setText("FORMALES: " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblFormales.setText("FORMALES: " + "Unidades restantes: " + sto3 + " unidades    " + "Precio: " + pre4 + " euros");
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
				VentanaPrincipal.log.log(Level.INFO, "Los ficheros de información han sido actualizados correctamente");
			
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
						String cant = JOptionPane.showInputDialog("Cuantas unidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "Bota");
							if (stock >= canti) {
								if (stock == 0) 
									lblBotas.setText("BOTAS" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblBotas.setText("BOTAS" + ": " + "Unidades restantes: " + (stock-canti) + " unidades     " + "Precio: " + pre1 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "Bota");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Zapato(p.getCodigo(), p.getColor(), p.getPrecio(), canti, p.getMarca(), p.getNombre(), p.getRutaFoto(), "Negro", true, TipoZapato.BOTAS)); //AQUI AÑADIR EL PRODUCTO
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Zapato", "", "", "", "", "Negro", true, "Bota");
								try {
									BD.restarUnidadesAProducto(con, "Bota", canti);
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
						String cant = JOptionPane.showInputDialog("Cuantas unidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "Deportiva");
							if (stock >= canti) {
								if (stock == 0) 
									lblDeportivas.setText("DEPORTIVAS:" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblDeportivas.setText("DEPORTIVAS" + ": " + "Unidades restantes: " + (stock-canti) + " unidades     " + "Precio: " + pre2 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "Deportiva");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Zapato(p.getCodigo(), p.getColor(), p.getPrecio(), canti, p.getMarca(), p.getNombre(), p.getRutaFoto(), "Negro",  false, TipoZapato.DEPORTIVAS)); //AQUI AÑADIR EL PRODUCTO
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Zapato", "", "", "", "", "Negro", false, "Deportiva");
								try {
									BD.restarUnidadesAProducto(con, "Deportiva", canti);
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
						String cant = JOptionPane.showInputDialog("Cuantas unidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "Tacon");
							if (stock >= canti) {
								if (stock == 0) 
									lblTacones.setText("TACONES:" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblTacones.setText("TACONES:" + ": " + "Unidades restantes: " + (stock-canti) + " unidades     " + "Precio: " + pre3 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "Tacon");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Zapato(p.getCodigo(), p.getColor(), p.getPrecio(), canti, p.getMarca(), p.getNombre(), p.getRutaFoto(), "Rojo" ,false, TipoZapato.TACONES)); //AQUI AÑADIR EL PRODUCTO
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Zapato", "", "", "", "", "Rojo", false, "Tacon");
								try {
									BD.restarUnidadesAProducto(con, "Tacon", canti);
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
						String cant = JOptionPane.showInputDialog("Cuantas Unidades quieres");
						int canti = Integer.parseInt(cant);
						if (canti > 0) {
							Connection con = BD.initBD("SweetWear.db");
							int stock = BD.obtenerStockProducto(con, "Formal");
							if (stock >= canti) {
								if (stock == 0) 
									lblFormales.setText("FORMALES" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblFormales.setText("FORMALES" + ": " + "Unidades restantes: " + (stock-canti) + " unidades     " + "Precio: " + pre4 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "Formal");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Zapato(0, p.getColor(), p.getPrecio(), canti, p.getMarca(), p.getNombre(), p.getRutaFoto(), "Marron",  false,  TipoZapato.FORMALES)); //AQUI AÑADIR EL PRODUCTO
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Zapato", "", "", "", "", "Marron", false, "Formal");
								try {
									BD.restarUnidadesAProducto(con, "Formal", canti);
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