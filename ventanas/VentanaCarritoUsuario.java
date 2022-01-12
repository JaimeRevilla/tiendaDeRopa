package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import clases.BD;
import clases.Pantalon;
import clases.Producto;
import clases.Usuario;

public class VentanaCarritoUsuario extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	private JPanel panelSur, panelNorte, panelCentral, panelCentralArriba, panelCentralAbajo;
	private JButton btnVolver, btnImprimirRecivo, btnBorrar;
	public static JLabel lblCarrito;
	public static DefaultListModel<Producto> modeloListaPedido;
	public static JList<Producto> listaPedido;
	private JScrollPane scrollListaPedido;
	private JLabel lblPrecio, lblFoto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCarritoUsuario frame = new VentanaCarritoUsuario(null);
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
	public VentanaCarritoUsuario(JFrame va) {
		Connection con = BD.initBD("SweetWear.db");
		//AQUI IRA CODIGO
		BD.closeBD(con);
		
		//PROPIEDADES DE LA VENTANA
		ventanaAnterior = va;
		ventanaActual = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			
		//CREACI�N DE LOS PANELES
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setBackground(new Color(122,217,196));
		
		btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		VentanaPrincipal.ponerFotoABoton(btnVolver, "imagenes\\IconoSalir.png", 30, 30, 30, 30);
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setBackground(new Color(122,217,196));
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(2,1));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(new Color(122,217,196));
		
		panelCentralArriba = new JPanel();
		panelCentral.add(panelCentralArriba);
		panelCentralArriba.setBackground(new Color(122,217,196));
		
		panelCentralAbajo = new JPanel();
		panelCentral.add(panelCentralAbajo);
		panelCentralAbajo.setBackground(new Color(122,217,196));
		
		//CREACION DE LOS COMPONENTES
		lblCarrito = new JLabel();
		lblPrecio = new JLabel();
		lblFoto = new JLabel();
		
		btnImprimirRecivo = new JButton("IMPRIMIR RECIVO");
		panelSur.add(btnImprimirRecivo);
		VentanaPrincipal.ponerFotoABoton(btnImprimirRecivo, "imagenes\\IconoImprimirRecivo.png", 30, 30, 30, 30);
		
		
		btnBorrar = new JButton("BORRAR PRODUCTO");
		panelSur.add(btnBorrar);
		VentanaPrincipal.ponerFotoABoton(btnBorrar, "imagenes\\IconoBorrarProducto.png", 30, 30, 30, 30);
		
		modeloListaPedido = new DefaultListModel<>();
		listaPedido = new JList<>(modeloListaPedido);
		scrollListaPedido = new JScrollPane(listaPedido);
		
		//METER LOS COMPONENTES EN LOS PANELES
		panelNorte.add(lblCarrito);
		panelCentralArriba.add(scrollListaPedido);
		panelCentralAbajo.add(lblPrecio);
		
		
		for (Producto p:VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n)){
				modeloListaPedido.addElement(p);
		}

		if (VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).size() == 0){
			lblPrecio.setText("NO HAY PRODUCTOS EN EL CARRITO");
		}else {
			double pre = 0;
			for (Producto p: VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n)) {
				pre = pre + (p.getPrecio() * p.getStock());
				lblPrecio.setText("PRECIO TOTAL: "+ pre + " euros");
			 }
		}	
		
		//EVENTOS DE VENTANA
		ventanaActual.addWindowListener(new WindowAdapter() {
					
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal.guardarMapaPedidosEnFicheroDeTexto();
				VentanaPrincipal.guardarMapaUsuariosEnFicheroDeTexto();
				VentanaPrincipal.guardarListaHistorialBusqueda();
				VentanaPrincipal.guardarMapaSatisfaccion();
				VentanaPrincipal.log.log(Level.INFO, "Los ficheros de informaci�n han sido actualizados correctamente");
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
		
		btnImprimirRecivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).size() != 0) {
					VentanaCarritoUsuario.RealizarFactura();
					VentanaPrincipal.log.log(Level.INFO, VentanaInicioSesion.n + " ha realizado una compra");

					modeloListaPedido.removeAllElements();
					
					Iterator<Producto> it = VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).iterator();
					while(it.hasNext()) {
						Producto p = it.next();
						it.remove();
						VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).remove(p);
						Connection con = BD.initBD("SweetWear.db");
						BD.eliminarProductoCliente(con, p.getCodigo());
						BD.closeBD(con);
					}
					listaPedido.repaint();
					lblFoto.setIcon(null);
					lblPrecio.setText("NO HAY PRODUCTOS EN EL CARRITO");
					JOptionPane.showMessageDialog(null, "COMPRA REALIZADA CORRECTAMENTE!! GRACIAS POR CONFIAR EN NOSOTROS!! ", "GRACIAS!!!", JOptionPane.NO_OPTION);
				}else 
					JOptionPane.showMessageDialog(null, "Su carrito esta vacio! Realize algun pedido para poder facturar la compra!", "VACIO", JOptionPane.ERROR_MESSAGE);

			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int sto = 0;
				int pos = listaPedido.getSelectedIndex();
				if (pos != -1) {
					modeloListaPedido.removeElementAt(pos);	
					Producto p = VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).get(pos);
					Connection con = BD.initBD("SweetWear.db");
					try {
						BD.eliminarProductoCliente(con, p.getCodigo());
						BD.sumarUnidadesAProducto(con, p.getNombre(), p.getStock());
						sto = BD.obtenerStockProducto(con, p.getNombre());
						double pre = BD.obtenerPrecioProducto(con,p.getNombre());
						
						VentanaPantalones.lblChandal = new JLabel();
						VentanaPantalones.lblVaquero = new JLabel();
						VentanaPantalones.lblCampana = new JLabel();
						VentanaCalcetines.lblPinkie = new JLabel();
						VentanaCalcetines.lblTobillero = new JLabel();
						VentanaCalcetines.lblAlto = new JLabel();
						VentanaCamiseta.lblPolo = new JLabel();
						VentanaCamiseta.lblCamiseta = new JLabel();
						VentanaCamiseta.lblCamisa = new JLabel();
						VentanaSudadera.lblConGorro = new JLabel();
						VentanaSudadera.lblSinGorro = new JLabel();
						VentanaSudadera.lblConCremallera = new JLabel();
						VentanaSudadera.lblSinCremallera = new JLabel();
						VentanaZapato.lblBotas = new JLabel();
						VentanaZapato.lblDeportivas = new JLabel();
						VentanaZapato.lblFormales = new JLabel();
						VentanaZapato.lblTacones = new JLabel();
						
						if (p.getNombre().equals("Chandal")) {
							VentanaPantalones.lblChandal.setText("PANTALONES CHANDAL" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Vaquero")){
							VentanaPantalones.lblVaquero.setText("PANTALONES VAQUEROS" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Campana")) {
							VentanaPantalones.lblCampana.setText("PANTALONES CAMPANA" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Pinkie")) {
							VentanaCalcetines.lblPinkie.setText("CALCETINES PINKIE" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Tobillero")) {
							VentanaCalcetines.lblTobillero.setText("CALCETINES TOBILLEROS" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Alto")) {
							VentanaCalcetines.lblAlto.setText("CALCETINES ALTOS" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Polo")) {
							VentanaCamiseta.lblPolo.setText("POLO" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Camisa")) {
							VentanaCamiseta.lblCamisa.setText("CAMISA" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Camiseta")) {
							VentanaCamiseta.lblCamiseta.setText("CAMISETA" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("ConCremallera")) {
							VentanaSudadera.lblConCremallera.setText("SUDADERA CON CREMALLERA" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("SinCremallera")) {
							VentanaSudadera.lblSinCremallera.setText("SUDADERA SIN CREMALLERA" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("ConGorro")) {
							VentanaSudadera.lblConGorro.setText("SUDADERA CON GORRO" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("SinGorro")) {
							VentanaSudadera.lblSinGorro.setText("SUDADERA SIN GORRO" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Bota")) {
							VentanaZapato.lblBotas.setText("BOTAS" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Deportiva")) {
							VentanaZapato.lblDeportivas.setText("DEPORTIVAS" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Tacon")) {
							VentanaZapato.lblTacones.setText("TACONES" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}else if (p.getNombre().equals("Formal")) {
							VentanaZapato.lblFormales.setText("FORMALES" + ": " + "Cantidades restantes: " + sto + " unidades   " + "Precio: " + pre + " euros");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (modeloListaPedido.size() != 0) {
						VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).remove(p);
						double pre = 0;
						for (Producto pro: VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n)) {
							pre = pre + (pro.getPrecio() * pro.getStock());
							lblPrecio.setText("PRECIO TOTAL: "+ pre + "euros");
						}
					} else if (modeloListaPedido.size() == 0) {
						VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).remove(p);
						panelCentralArriba.removeAll();
						panelCentralArriba.add(scrollListaPedido);
						lblFoto.setIcon(null);
						lblPrecio.setText("NO HAY PRODUCTOS EN EL CARRITO");
					}
				}else 
					JOptionPane.showMessageDialog(null, "NO HAS SELECCIONADO NINGUN PRODUCTO", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		
		
		//EVENTOS DE VENTANA
				ventanaActual.addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						VentanaPrincipal.guardarMapaUsuariosEnFicheroDeTexto();
						VentanaPrincipal.guardarMapaPedidosEnFicheroDeTexto();
						VentanaPrincipal.guardarListaHistorialBusqueda();
						VentanaPrincipal.guardarMapaSatisfaccion();
						VentanaPrincipal.log.log(Level.INFO, "Los ficheros de informaci�n han sido actualizados correctamente");
					
					}
				});
				
		
		//HILO (FOTO DEL PRODUCTO)
		
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					int pos1 = listaPedido.getSelectedIndex();
					if (pos1 != -1) {
						panelCentralArriba.removeAll();
						Producto p = null;
						if (VentanaPrincipal.tmPedidos.containsKey(VentanaInicioSesion.n))
							p = VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).get(pos1);
						panelCentralArriba.setLayout(new GridLayout(1,2));
						panelCentralArriba.add(scrollListaPedido);
						panelCentralArriba.add(lblFoto);
						if (p != null) {
							ImageIcon im = new ImageIcon(p.getRutaFoto());
							ImageIcon imagenConDimensiones = new ImageIcon(im.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
							im.setDescription(p.getRutaFoto());
							lblFoto.setIcon(imagenConDimensiones);
							lblFoto.setSize(300, 300);
						}
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if (modeloListaPedido.size() != 0) {
						panelCentralArriba.removeAll();
						Producto p1 = null;
						if (VentanaPrincipal.tmPedidos.containsKey(VentanaInicioSesion.n))
							p1 = VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).get(0);
						panelCentralArriba.setLayout(new GridLayout(1,2));
						panelCentralArriba.add(scrollListaPedido);
						panelCentralArriba.add(lblFoto);
						if (p1 != null) {
							ImageIcon im2 = new ImageIcon(p1.getRutaFoto());
							im2.setDescription(p1.getRutaFoto());
							ImageIcon imagenConDimensiones2 = new ImageIcon(im2.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
							lblFoto.setIcon(imagenConDimensiones2);
							lblFoto.setSize(300, 300);
						}
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if (modeloListaPedido.size() == 0){
						panelCentralArriba.removeAll();
						panelCentralArriba.add(scrollListaPedido);
						lblFoto.setIcon(null);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
		};
		Thread t1 = new Thread(r1);
		t1.start();
		
		
	}
	
	public static void RealizarFactura() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("FACTURA DE " + VentanaInicioSesion.n + ".txt");
			pw.println("FACTURA DE " + VentanaInicioSesion.n);
			double sumaTotal = 0;
			double suma1 = 0;
			int i = 1;
			ArrayList<Producto> al = VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n);
			for (Producto p: al) {
				suma1 = (p.getPrecio() * p.getStock());
				sumaTotal = sumaTotal + (p.getPrecio() * p.getStock());
				pw.println(i + "- " + p.getNombre() + " " + p.getColor() + " " + p.getPrecio()+ " euros  " + p.getStock()+ " " + p.getMarca()+ " " + p.getRutaFoto() + "      " + "Precio Del Mismo Articulo teniendo en cuenta las unidades compradas: " + suma1 + " euros");
				i++;
			}
			pw.println("Precio total de la compra: " + sumaTotal + " euros");
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			if (pw!= null) {
				pw.flush();
				pw.close();
			}
		}
	}

}
