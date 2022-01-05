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
import clases.Camiseta;
import clases.Pantalon;
import clases.Producto;
import clases.TipoCamiseta;
import clases.TipoPantalon;

public class VentanaCamiseta extends JFrame {

	private JPanel contentPane;
	public static JPanel panelCentral, panelP1, panelP11, panelP12, panelP2, panelP21, panelP22, panelP3, panelP31, panelP32, panelNorte;
	private JFrame ventanaAnterior, ventanaActual;
	private JPanel panelSur;
	public static JButton btnVolver, btnPolo, btnCamisa, btnCamiseta;
	static public JLabel lblPolo, lblCamisa, lblCamiseta;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCamiseta frame = new VentanaCamiseta(null);
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
	public VentanaCamiseta(JFrame va) {
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
		
		btnPolo = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnPolo, "imagenes\\IconoCamisetaPolo.png",  120, 120, 120, 120);
		panelP11.add(btnPolo);
		
		lblPolo = new JLabel("POLO");
		panelP12.add(lblPolo);
		
		Connection con = BD.initBD("SweetWear.db");
		int sto = BD.obtenerStockProducto(con, "Polo");
		double pre1 = BD.obtenerPrecioProducto(con,"Polo");
		if (sto == 0) 
			lblPolo.setText("POLO" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblPolo.setText("POLO" + ": " + "Unidades restantes: " + sto + " unidades    " + "Precio: " + pre1 + " euros");
		
		//----------------------------------------------------------------------------
		panelP2 = new JPanel();
		panelP2.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP2);
		
		panelP21 = new JPanel();
		panelP2.add(panelP21);
		
		panelP22 = new JPanel();
		panelP2.add(panelP22);
		
		btnCamisa = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnCamisa, "imagenes\\IconoCamisetaCamisa.png",  120, 120, 120, 120);
		panelP21.add(btnCamisa);
		
		lblCamisa = new JLabel("CAMISA");
		panelP22.add(lblCamisa);
		
		
		int sto1 = BD.obtenerStockProducto(con, "Camisa");
		double pre2 = BD.obtenerPrecioProducto(con,"Camisa");
		if (sto1 == 0) 
			lblCamisa.setText("CAMISA" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblCamisa.setText("CAMISA" + ": " + "Unidades restantes: " + sto1 + " unidades   " + "Precio: " + pre2 + " euros");
		
		//--------------------------------------------------------------------------
		panelP3 = new JPanel();
		panelP3.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP3);
		
		panelP31 = new JPanel();
		panelP3.add(panelP31);
		
		panelP32 = new JPanel();
		panelP3.add(panelP32);
		
		btnCamiseta = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnCamiseta, "imagenes\\IconoCamisetaNormal.png",  120, 120, 120, 120);
		panelP31.add(btnCamiseta);
		
		lblCamiseta = new JLabel("CAMISETA");
		panelP32.add(lblCamiseta);
		
		int sto2 = BD.obtenerStockProducto(con, "Camiseta");
		double pre3 = BD.obtenerPrecioProducto(con,"Camiseta");
		if (sto2 == 0)
			lblCamiseta.setText("CAMISETA: " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblCamiseta.setText("CAMISETA: " + "Unidades restantes: " + sto2 + " unidades    " + "Precio: " + pre3 + " euros");
		
		
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
		
		
		btnPolo.addActionListener(new ActionListener() {
			
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
							int stock = BD.obtenerStockProducto(con, "Polo");
							if (stock >= canti) {
								if (stock == 0) 
									lblPolo.setText("POLO" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblPolo.setText("POLO" + ": " + "Unidades restantes: " + (stock-canti) + " unidades     " + "Precio: " + pre1 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "Polo");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Camiseta(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoCamiseta.POLO)); //AQUI AÑADIR EL PRODUCTO
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
//								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Camiseta", "", "Polo", "", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Polo", canti);
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
		
		
		btnCamisa.addActionListener(new ActionListener() {
			
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
							int stock = BD.obtenerStockProducto(con, "Camisa");
							if (stock >= canti) {
								if (stock == 0) 
									lblCamisa.setText("CAMISA" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblCamisa.setText("CAMISA" + ": " + "Unidades restantes: " + (stock-canti) + " unidades     " + "Precio: " + pre2 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "Camisa");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Camiseta(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoCamiseta.CAMISA)); //AQUI AÑADIR EL PRODUCTO
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Camiseta", "", "Camisa", "", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Camisa", canti);
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
		
		
		btnCamiseta.addActionListener(new ActionListener() {
			
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
							int stock = BD.obtenerStockProducto(con, "Camiseta");
							if (stock >= canti) {
								if (stock == 0) 
									lblCamiseta.setText("CAMISETA" + ": " + "NO HAY UNIDADES EN STOCK");
								else
									lblCamiseta.setText("CAMISETA" + ": " + "Unidades restantes: " + (stock-canti) + " unidades     " + "Precio: " + pre3 + " euros");
								Producto p = BD.obtenerProductoTienda(con, "Camiseta");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Camiseta(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoCamiseta.CAMISETA)); //AQUI AÑADIR EL PRODUCTO
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Camiseta", "", "Camiseta", "", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Camiseta", canti);
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
