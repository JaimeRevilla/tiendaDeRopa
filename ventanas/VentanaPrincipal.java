package ventanas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.BD;
import clases.PanelConImagenDeFondo;
import clases.Producto;
import clases.Usuario;


public class VentanaPrincipal extends JFrame {
	//DECLARACION DE LOS ATRIBUTOS
	
	private JPanel contentPane;
	private JPanel panelCentral, panelArriba, panelNorte, panelArribaDrc, panelArribaIzq, panelNorteIzq, panelNorteMedio, panelNorteDrc;
	private JPanel panelP1, panelP2, panelP3, panelP4, panelArribaIzq1, panelArribaIzq2;
	private JButton btnSalir, btnRegistrarme,  btnCarrito, btnBusqueda;
	private JButton btnP1, btnP2, btnP3, btnP4, btnP5;
	public static JButton btnAdmin, btnCerrarSesion, btnCambiarCon,  btnInicioSesion;
	private JFrame ventanaActual;
	public static TreeMap<String, ArrayList<Producto>> tmPedidos = new TreeMap<>(); //MAPA que tiene como clave el nombre del usuario y como valor el pedido con los productos
	public static TreeMap<String, Usuario> tmUsuarios = new TreeMap<>();
	public static DefaultListModel<Usuario> modeloListaUsuario;
	public static JList<Usuario> listaUsuario;
	private JScrollPane scrollLista;
	private JLabel lblTitulo;
	public static JLabel lblNombre;
	private JLabel lblHora, lblFrase;
	public static JProgressBar pb = new JProgressBar();
	public static SpinnerModel modelosp;
	public static JSpinner sp;
	private JTextField txtBusqueda;
	private JComboBox<String> comboBusqueda;
	public static ArrayList<String> listaHistorialBusqueda = new ArrayList<String>();
	
	private PanelConImagenDeFondo panelConFondo, panelP5;
	
	static Logger log = Logger.getLogger("Log de Usuarios");
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		
		//BASE DE DATOS
		Connection con = BD.initBD("SweetWear.db");
		BD.crearTablaUsuario(con);
		BD.crearTablaProductosTienda(con);
		BD.crearTablaProductosCliente(con);
		VentanaPrincipal.tmUsuarios = BD.obtenerMapaUsuarios(con);
		BD.closeBD(con);
		
		
		//CREACION DEL LOGGER
		Handler handler;
		try {
			handler = new FileHandler("Usuarios.log");
			handler.setFormatter(new SimpleFormatter());
			log.addHandler(handler);
		} catch (SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//PROPIEDADES DE LA VENTANA
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(500, 500, 600, 450);
		setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//FONDO DE LA VENTANA
//		panelConFondo = new PanelConImagenDeFondo(getSize());
//		panelConFondo.setImage("/imagenes/IconoZapatillas.png");
//		setContentPane(panelConFondo);
		
		
		//CREAMOS LOS PANELES
		panelCentral = new JPanel();
		panelCentral.setBackground(Color.CYAN);
		panelCentral.setLayout(new GridLayout(0,1,0,0));
		
		panelArriba = new JPanel();
		panelArriba.setLayout(new GridLayout(2,0,0,0));
		panelCentral.add(panelArriba);
		
		panelArribaIzq = new JPanel();
		panelArribaIzq.setLayout(new GridLayout(2, 1));
		panelArriba.add(panelArribaIzq);
		panelArribaIzq.setBackground(Color.CYAN);
		
		panelArribaIzq1 = new JPanel();
		panelArribaIzq.add(panelArribaIzq1);
		panelArribaIzq1.setBackground(Color.CYAN);
		
		panelArribaIzq2 = new JPanel();
		panelArribaIzq.add(panelArribaIzq2);
		panelArribaIzq2.setBackground(Color.CYAN);
		
	   
		panelArribaDrc = new JPanel();
		panelArriba.add(panelArribaDrc);
		panelArribaDrc.setLayout(new GridLayout(2, 3));
		panelArribaDrc.setBackground(Color.CYAN);
		
		panelNorte = new JPanel();
		panelNorte.setBackground(Color.CYAN);
		panelNorte.setLayout(new GridLayout(0,3,0,0));
		
		panelNorteIzq = new JPanel();
		panelNorteIzq.setBackground(Color.CYAN);
		panelNorte.add(panelNorteIzq);
		
		
		
		panelNorteMedio = new JPanel();
		panelNorteMedio.setBackground(Color.CYAN);
		panelNorte.add(panelNorteMedio);
		
		panelNorteDrc = new JPanel();
		panelNorteDrc.setBackground(Color.CYAN);
		panelNorte.add(panelNorteDrc);
	
		//-------------------------------------------
		panelP1 = new JPanel();
		panelArribaDrc.add(panelP1);
		panelP1.setBackground(Color.CYAN);
		
		panelP2 = new JPanel();
		panelArribaDrc.add(panelP2);
		panelP2.setBackground(Color.CYAN);
		
		panelP3 = new JPanel();
		panelArribaDrc.add(panelP3);
		panelP3.setBackground(Color.CYAN);
		
		panelP4 = new JPanel();
		panelArribaDrc.add(panelP4);
		panelP4.setBackground(Color.CYAN);
		
		panelP5 = new PanelConImagenDeFondo(getSize());
		//panelP5.setImage("/imagenes/IconoZapatillas.png");
		panelArribaDrc.add(panelP5);
		panelP5.setBackground(Color.CYAN);
		
		
		
		btnP1 = new JButton();
		ponerFotoABoton(btnP1, "tiendaDeRopa\\src\\imagenes\\IconoZapatillas.png", 120, 120, 120, 120);
		panelP1.add(btnP1);
		
		btnP2 = new JButton();
		ponerFotoABoton(btnP2, "tiendaDeRopa\\src\\imagenes\\IconoCalcetines.png", 120, 120, 120, 120);
		panelP2.add(btnP2);
		
		btnP3 = new JButton();
		ponerFotoABoton(btnP3, "tiendaDeRopa\\src\\imagenes\\IconoSudadera.png", 120, 120, 120, 120);
		panelP3.add(btnP3);
		
		btnP4 = new JButton();
		ponerFotoABoton(btnP4, "tiendaDeRopa\\src\\imagenes\\IconoPantalones.png", 120, 120, 120, 120);
		panelP4.add(btnP4);
		
		btnP5 = new JButton();
		ponerFotoABoton(btnP5, "tiendaDeRopa\\src\\imagenes\\IconoCamiseta.png", 120, 120, 120, 120);
		panelP5.add(btnP5);
		
		
		
		
		
		//A�ADIMOS LOS PANELES AL PANEL PRINCIPAL DE LA VENTANA
		contentPane.add(panelCentral, BorderLayout.CENTER);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		//CREAMOS LOS COMPONENTES
		btnInicioSesion = new JButton();
		ponerFotoABoton(btnInicioSesion, "tiendaDeRopa\\src\\imagenes\\IconoIniciarSesion.png", 30, 30, 30, 30);
		
		btnSalir = new JButton();
		ponerFotoABoton(btnSalir, "tiendaDeRopa\\src\\imagenes\\IconoSalir.png", 30, 30, 30, 30);
		
		btnRegistrarme = new JButton();
		ponerFotoABoton(btnRegistrarme, "tiendaDeRopa\\src\\imagenes\\IconoRegistro.jpg", 30, 30, 30, 30);
		
		
		btnAdmin = new JButton();
		ponerFotoABoton(btnAdmin, "tiendaDeRopa\\src\\imagenes\\IconoAdmin.png", 30, 30, 30, 30);
		btnAdmin.setVisible(false);
		
		btnCerrarSesion = new JButton();
		ponerFotoABoton(btnCerrarSesion, "tiendaDeRopa\\src\\imagenes\\IconoCerrarSesion.png", 30, 30, 30, 30);
		
		btnBusqueda = new JButton();
		ponerFotoABoton(btnBusqueda, "tiendaDeRopa\\src\\imagenes\\IconoBusqueda.jpg", 30, 30, 30, 30);
		//NO SE COMO PONER EL BOTON A LA DERECHA DEL TODOO
		btnBusqueda.setAlignmentX(JButton.RIGHT_ALIGNMENT);
		
		
		lblNombre = new JLabel("");
		lblTitulo = new JLabel("SWEET WEAR");
		lblTitulo.setForeground(Color.BLACK);
		comboBusqueda = new JComboBox<String>();

		//lblFrase = new JLabel("DI QUI�N ERES SIN HABLAR");
		lblFrase = new JLabel("AQUI VA UNA FRASE (SLOGAN) ");
		Font font = new Font("Agency FB", Font.ITALIC, 50);
		lblFrase.setFont(font);
		
		
		FlowLayout flowLayout = (FlowLayout) panelNorteIzq.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		FlowLayout flowLayout2 = (FlowLayout) panelNorteDrc.getLayout();
		flowLayout2.setAlignment(FlowLayout.RIGHT);
		
		if (lblNombre.getText() == "") {
			btnCerrarSesion.setVisible(false);
		}
		
		panelNorteMedio.add(lblTitulo);
		
		lblHora = new JLabel("");
		txtBusqueda = new JTextField(10);
	
			
		btnCarrito = new JButton();
		ponerFotoABoton(btnCarrito, "tiendaDeRopa\\src\\imagenes\\IconoCarrito.png", 30, 30, 30, 30);
		
		btnCambiarCon = new JButton();
		ponerFotoABoton(btnCambiarCon, "tiendaDeRopa\\src\\imagenes\\IconoCambiarContrase�a.png", 30, 30, 30, 30);
		btnCambiarCon.setVisible(false);
		
		modelosp = new SpinnerNumberModel(50, 0, 100, 1);
		sp = new JSpinner(modelosp);
		sp.setVisible(false);
		
		pb = new JProgressBar(0, 100);
		pb.setValue(50);
		pb.setVisible(false);
		
		
		
	
		
		//A�ADIMOS LOS COMPONENTES A LOS PANELES

		panelArribaIzq1.add(btnCarrito);
		panelArribaIzq1.add(btnInicioSesion);
		panelArribaIzq1.add(btnSalir);
		panelArribaIzq1.add(btnCerrarSesion);
		panelArribaIzq1.add(btnCambiarCon);
		

		panelNorteDrc.add(lblHora);
		panelArribaIzq1.add(btnRegistrarme);
		panelNorteIzq.add(lblNombre);
		panelArribaIzq1.add(btnAdmin);
		panelArribaIzq1.add(pb);
		panelArribaIzq1.add(sp);
		
		panelArribaIzq1.add(btnBusqueda);
		panelArribaIzq1.add(txtBusqueda);
		panelArribaIzq1.add(comboBusqueda);
		
		panelArribaIzq2.add(lblFrase);
		
		setLocationRelativeTo( null );
		
		 
		//EVENTOS
		
		sp.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int valor = (int) sp.getValue();
				pb.setValue(valor);
			}
		});
		btnInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {;
				new VentanaInicioSesion(ventanaActual);
				ventanaActual.setVisible(false);
			}
		});
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//guardarMapaUsuariosEnFicheroDeTexto();		
				System.exit(0);
						
					}
		});
		
		//EVENTO DELBOTON REGISTRAR
		btnRegistrarme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistro(ventanaActual);
				ventanaActual.setVisible(false);
			}
		});
		
		
		btnCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = lblNombre.getText();
				if (texto != "") {
					VentanaCarritoUsuario v1 = new VentanaCarritoUsuario(ventanaActual);
					ventanaActual.setVisible(false);
					v1.setVisible(true);
					VentanaCarritoUsuario.lblCarrito.setText("CARRITO DE LA COMPRA DE " + VentanaInicioSesion.n);
				} else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesi�n primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin v1 = new VentanaAdmin(ventanaActual);
				ventanaActual.setVisible(false);
				v1.setVisible(true);
				
			}
		});
		
		btnCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			lblNombre.setText("");
			VentanaInicioSesion.n = "";
			btnAdmin.setVisible(false);
			btnCambiarCon.setVisible(false);
			pb.setVisible(false);
			sp.setVisible(false);
			btnCerrarSesion.setVisible(false);
			btnInicioSesion.setVisible(true);
			repaint();
				
			}
		});
		
		
		btnP4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPantalones v1 = new VentanaPantalones(ventanaActual);
				ventanaActual.setVisible(false);
				v1.setVisible(true);
				
			}
		});
		
		btnCambiarCon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCambiarContrasenia v1 = new VentanaCambiarContrasenia(ventanaActual);
				ventanaActual.setVisible(false);
				v1.setVisible(true);
				
			}
		});
		
		txtBusqueda.addMouseListener(new MouseAdapter() {
	
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Escriba el producto o tipo del producto en minuscula y en singular porfavor!!!", "ADVERTENCIA!!!", JOptionPane.NO_OPTION);
				JOptionPane.showMessageDialog(null, "Si quiere consultar algo buscado recientemente, seleccionelo en el desplegable, pulse enter y pulse el boton de busqueda!!! ", "ADVERTENCIA!!!", JOptionPane.NO_OPTION);
				if(listaHistorialBusqueda.size() != 0) {
					comboBusqueda.removeAllItems();
					for (String n: listaHistorialBusqueda) {
						comboBusqueda.addItem(n);
						comboBusqueda.getItemAt(-1);
					}
				}
			}
			
		});
		
		
		comboBusqueda.addKeyListener(new KeyAdapter() {
			
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					int pos = comboBusqueda.getSelectedIndex();
					if (pos != -1) {
						String n = comboBusqueda.getItemAt(pos);
						txtBusqueda.setText(n);
					}
				}
				
			}
		});
		
		
		btnBusqueda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				String busq = txtBusqueda.getText();
				if(!busq.equals("")) {
					if (busq.equals("pantalon") || busq.equals("vaquero") || busq.equals("chandal") || busq.equals("campana")) {
						if (!listaHistorialBusqueda.contains(busq)) {
							listaHistorialBusqueda.add(busq);
						}
						txtBusqueda.setText("");
						VentanaPantalones v1 = new VentanaPantalones(ventanaActual);
						ventanaActual.setVisible(false);
						v1.setVisible(true);
						
					} else {
						JOptionPane.showMessageDialog(null, "NO EXISTE ESE ARTICULO", "ERROR", JOptionPane.ERROR_MESSAGE);
						txtBusqueda.setText("");
					}	

					//AQUI IRAN OTROS ELSE IF CUANDO ESTEN HECHAS LAS DEMAS VENTANAS
				}else 
					JOptionPane.showMessageDialog(null, "INDICA EL ARTICULO A ENCONTRAR", "ERROR", JOptionPane.ERROR_MESSAGE);
				
				comboBusqueda.removeAllItems();
				for (String n: listaHistorialBusqueda) {
					comboBusqueda.addItem(n);
				}
				
			}
			
		});
		
		
		//--------------------------------------------------------------------------------------------------------------
		
		//HILOS
		
		/*
		 * ESTE HILO NOS MUESTRA LA HORA ACTUAL EL CUAL SE ACTUALIZA CADA SEGUNDO PARA QUE SEA POSIBLE ACTUALIZARSE.
		 */
		
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				while (true) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH_mm_ss");
					long milis = System.currentTimeMillis();
					Date fecha = new Date(milis);
					String f = sdf.format(fecha);
					lblHora.setText(f);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lblHora.setText("");
				}
				
				
			}
		};
		Thread t1 = new Thread(r1);
		t1.start();
		
	
		
		//----------------------------------------------------------------------------------------------------------------
		
		//EVENTOS DE VENTANA
		ventanaActual.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				cargarMapaUsuariosDeFicheroDeTexto();
				cargarMapaPedidosDeFicheroDeTexto();
				cargarListaHistorialBusqueda();
			}
				
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				guardarMapaUsuariosEnFicheroDeTexto();
				guardarMapaPedidosEnFicheroDeTexto();
				guardarListaHistorialBusqueda();
			}
			
			
			
		});
		
		
	
	//------------------------------------------------------------------------------------------------------------------------------
		
	//EVENTO PROGRESS BAR
		
	pb.addMouseListener(new MouseAdapter() {
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			int resp = JOptionPane.showConfirmDialog(null,"�ESTA SEGURO DE SU OPCI�N?");
			if(resp == JOptionPane.OK_OPTION) {
				VentanaPrincipal.pb.setVisible(false);
				VentanaPrincipal.sp.setVisible(false);
				JOptionPane.showMessageDialog(null, "GRACIAS POR COLABORAR!!!", "GRACIAS!!!", JOptionPane.NO_OPTION);
				//AQUI IRA ALGO PARA EL TEMA DE LAS ESTADISTICAS
			}
			
		}
	});
		

		
	//--------------------------------------------------------------------------------------------------------------------------------	
		
		
	
	}
	/**
	 * Metodo por el cual los pedidos que se hubiesen hecho en el carrito son borrados
	 */
	
	private void borrarPedidos() {
		Object [] claves = tmPedidos.keySet().toArray();
		for (Object c : claves) {
			tmPedidos.remove(c);
		}
	}
	
	
	
	
	/**
	 * Metodo que carga el mapaPedidos en un TextArea
	 */
	private void cargarMapaPedidosEnTextArea() {
		/*String texto = "INFORMACION DEL PEDIDO";
		for(String clave: tmPedidos.keySet()) {
			texto = texto + clave + "\n";
			HashSet<String> valor = tmPedidos.get(clave);
			for(String pedi: valor) {
				texto = texto + "\t\t" + pedi + "\n";
			}
		}*/
		
		//hacer que el tmPedidos se pueda enlazar con el HashSet<>
	}
	
	
	/**
	 * Metodo que carga el mapaUsuarios en una JList
	 */
	private void cargarMapaUsuariosEnLista () {
		for(String clave: tmUsuarios.keySet()) {
			Usuario valor = tmUsuarios.get(clave);
			modeloListaUsuario.addElement(valor);
		}

		
	}
	
	//PRIMERO LLAMAR A ESTE METODO Y UNA VEZ EL MAPA ESTE LLENO LLAMAR A cargarMapaUsuariosEnLista PARA QUE SE CARGUE EL MODELO DE LA LISTA
	/**
	 * Metodo que carga de un fichero de texto los el mapaUsuarios.
	 */
	public static void cargarMapaUsuariosDeFicheroDeTexto () {
		BufferedReader br = null;
		//System.out.println("ENTRO A CARGAR");
		try {
			br = new BufferedReader(new FileReader("USUARIOS.txt"));
			String linea = br.readLine();
			while (linea != null) {
				String [] datosUsuario = linea.split(" ");
				String nombre = datosUsuario[0];
				String mail = datosUsuario[1];
				int edad = Integer.parseInt(datosUsuario[2]);
				String cont = datosUsuario[3];
//				boolean permisos = Boolean.parseBoolean(datosUsuario[4]);
				Connection con = BD.initBD("SweetWear.db");
				boolean permisos = BD.obtenerAdmin(con, nombre);
				BD.closeBD(con);
				Usuario u = new Usuario(nombre, edad, mail, cont, permisos);
				tmUsuarios.put(nombre, u);
				linea = br.readLine();
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (br!= null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
//		for(String n: tmUsuarios.keySet()) {
//			System.out.println(n);
//			System.out.println("\t"+tmUsuarios.get(n));
//		}
//		
		
		
	}
	
	
	/**
	 * METODO QUE GUARDA EL mapaUsuarios EN UN FICHERO DE TEXTO
	 */
	public static void guardarMapaUsuariosEnFicheroDeTexto () {
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter("USUARIOS.txt");
			for (String nombre: VentanaPrincipal.tmUsuarios.keySet()) {
				Usuario u = VentanaPrincipal.tmUsuarios.get(nombre);
				pw.print(u.getNombre()+ " " + u.getMail() + " " + u.getEdad() + " " + u.getCon() + " " + u.getPermisos() + "\n"); //TODO Cambiar formato al guardar
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pw!= null) {
				pw.flush();
				pw.close();
			}
		}
		
		
		
	}
	
	/**
	 * METODO QUE CARGA EN UN FICHERO DE TEXTO LOS PEDIDOS DE CADA USUARIO
	 */
	public static void cargarMapaPedidosDeFicheroDeTexto () {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("PRODUCTOS.txt"));
			String linea = br.readLine();
			while (linea != null) {
				String [] datosProducto = linea.split(" ");
				String nombreCliente = datosProducto[0];
				int codigo = Integer.parseInt(datosProducto[1]);
				String color = datosProducto[2];
				String nombre = datosProducto[3];
				double precio = Double.parseDouble(datosProducto[4]);
				int stock = Integer.parseInt(datosProducto[5]);
				String marca = datosProducto[6];
				String rutaFoto = datosProducto[7];
				Producto p = new Producto(codigo, color, nombre, precio, stock, marca, rutaFoto);
				if (!VentanaPrincipal.tmPedidos.containsKey(nombreCliente)){
					VentanaPrincipal.tmPedidos.put(nombreCliente, new ArrayList<Producto>());
					VentanaPrincipal.tmPedidos.get(nombreCliente).add(p);
				}else {
					VentanaPrincipal.tmPedidos.get(nombreCliente).add(p);
				}
				linea = br.readLine();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (br!= null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		for(String n: tmPedidos.keySet()) {
//			System.out.println(n);
//			for(Producto p: tmPedidos.get(n))
//				System.out.println("\t" +p);
//		}
		
	}	
	
	public static void guardarMapaPedidosEnFicheroDeTexto () {
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter("PRODUCTOS.txt");
			for (String nombre: VentanaPrincipal.tmPedidos.keySet()) {
				ArrayList<Producto> u = VentanaPrincipal.tmPedidos.get(nombre);
				//pw.println(nombre + "\n" + "\t");
				for (Producto p: u) {
					pw.println(nombre + " " + p.getCodigo() + " " + p.getColor() + " " + p.getNombre()+ " " + p.getPrecio()+ " " + p.getStock()+ " " + p.getMarca()+ " " + p.getRutaFoto());
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pw!= null) {
				pw.flush();
				pw.close();
			}
		}
		
		
		
	}
	
	public static void guardarListaHistorialBusqueda() {
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter("HISTORIAL.txt");
			for (String n: listaHistorialBusqueda) {
				pw.println(n);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pw!= null) {
				pw.flush();
				pw.close();
			}
		}
	}
	
	public static void cargarListaHistorialBusqueda () {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("HISTORIAL.txt"));
			String linea = br.readLine();
			while (linea != null) {
				String [] datosProducto = linea.split(" ");
				String n = datosProducto[0];
				VentanaPrincipal.listaHistorialBusqueda.add(n);
				linea = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (br!= null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

		
	/**
	 * METODO QUE PONE UNA IM�GEN A UN BOTON CON LAS MEDIDAS PERSONALIZADAS
	 * @param btn --> EL BOTON AL QUE SE LE VA A PONER UNA IM�GEN
	 * @param rutaFoto --> RUTA DE LA IMAGEN PARA PODER ACCEDER A ELLA
	 * @param x --> VALOR DE LA COORDENADA X DE LA IM�GEN
	 * @param y --> VALOR DE LA COORDENADA Y DE LA IM�GEN
	 * @param width --> ANCHURA DE LA IM�GEN
	 * @param height --> ALTURA DE LA IM�GEN
	 */
	public static void ponerFotoABoton (JButton btn, String rutaFoto, int x, int y, int width, int height) {
		btn.setBounds(x, y, width, height);
		int ancho = btn.getWidth();
		int alto = btn.getHeight();
		ImageIcon icon = new ImageIcon(rutaFoto);
		Icon i = new ImageIcon(icon.getImage().getScaledInstance(alto, ancho, Image.SCALE_DEFAULT));
		btn.setIcon(i);
		btn.setBackground(Color.GREEN);
	}
	
	
	
    
	
}




