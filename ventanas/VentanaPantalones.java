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
import clases.TipoPantalon;

public class VentanaPantalones extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentral, panelP1, panelP11, panelP12, panelP2, panelP21, panelP22, panelP3, panelP31, panelP32, panelNorte;
	private JFrame ventanaAnterior, ventanaActual;
	private JPanel panelSur;
	private JButton btnVolver, btnChandal, btnVaqueros, btnCampana;
	static public JLabel lblChandal, lblVaquero, lblCampana;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPantalones frame = new VentanaPantalones(null);
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
	public VentanaPantalones(JFrame va) {
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
		panelCentral.setLayout(new GridLayout(2, 2));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		//--------------------------------------------------------------------------------------------------
	
		
		panelP1 = new JPanel();
		panelP1.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP1);
		
		panelP11 = new JPanel();
		panelP1.add(panelP11);
		
		panelP12 = new JPanel();
		panelP1.add(panelP12);
		
		btnChandal = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnChandal, "imagenes\\IconoPantalonChandal.png",  120, 120, 120, 120);
		panelP11.add(btnChandal);
		
		lblChandal = new JLabel("PANTALONES CHANDAL");
		panelP12.add(lblChandal);
		
		Connection con = BD.initBD("SweetWear.db");
		int sto = BD.obtenerStockProducto(con, "Chandal");
		if (sto == 0) 
			lblChandal.setText("PANTALONES CHANDAL" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblChandal.setText("PANTALONES CHANDAL" + ": " + "Cantidades restantes: " + sto + " unidades");
		
		//----------------------------------------------------------------------------
		panelP2 = new JPanel();
		panelP2.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP2);
		
		panelP21 = new JPanel();
		panelP2.add(panelP21);
		
		panelP22 = new JPanel();
		panelP2.add(panelP22);
		
		btnVaqueros = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnVaqueros, "imagenes\\IconoPantalonVaquero.jpg",  120, 120, 120, 120);
		panelP21.add(btnVaqueros);
		
		lblVaquero = new JLabel("PANTALONES VAQUEROS");
		panelP22.add(lblVaquero);
		
		
		int sto1 = BD.obtenerStockProducto(con, "Vaquero");
		if (sto1 == 0) 
			lblVaquero.setText("PANTALONES VAQUEROS" + ": " + "NO HAY NINGUNA UNIDAD EN STOCK");
		else
			lblVaquero.setText("PANTALONES VAQUEROS" + ": " + "Cantidades restantes: " + sto1 + " unidades");
		
		//--------------------------------------------------------------------------
		panelP3 = new JPanel();
		panelP3.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP3);
		
		panelP31 = new JPanel();
		panelP3.add(panelP31);
		
		panelP32 = new JPanel();
		panelP3.add(panelP32);
		
		btnCampana = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnCampana, "imagenes\\IconoPantalonCampana.png",  120, 120, 120, 120);
		panelP31.add(btnCampana);
		
		lblCampana = new JLabel("PANTALONES CAMPANA");
		panelP32.add(lblCampana);
		
		//EVENTOS DE VENTANA
		ventanaActual.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal.guardarMapaPedidosEnFicheroDeTexto();
				VentanaPrincipal.guardarMapaUsuariosEnFicheroDeTexto();
			}
		});
		
		 
		
		//EVENTOS
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
			}
		});
		
		
		btnChandal.addActionListener(new ActionListener() {
			
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
							int stock = BD.obtenerStockProducto(con, "Chandal");
							if (stock >= canti) {
								if (stock == 0) 
									lblChandal.setText("PANTALONES CHANDAL" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblChandal.setText("PANTALONES CHANDAL" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Chandal");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Pantalon(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoPantalon.CHANDAL)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Pantalon", "", "", "Chandal", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Chandal", canti);
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
		
		
		btnVaqueros.addActionListener(new ActionListener() {
			
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
							int stock = BD.obtenerStockProducto(con, "Vaquero");
							if (stock >= canti) {
								if (stock == 0) 
									lblVaquero.setText("PANTALONES VAQUEROS" + ": " + "NO HAY CANTIDADES EN STOCK");
								else
									lblVaquero.setText("PANTALONES VAQUEROS" + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
								Producto p = BD.obtenerProductoTienda(con, "Vaquero");
								VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Pantalon(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoPantalon.VAQUEROS)); //AQUI AÑADIR EL PRODUCTO
								System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
								int num = 0;
								try {
									num = BD.contarProductos(con);
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								num = num + 1;
								BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Pantalon", "", "", "Vaquero", "", "", false, "");
								try {
									BD.restarUnidadesAProducto(con, "Vaquero", canti);
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
	}
	

}
