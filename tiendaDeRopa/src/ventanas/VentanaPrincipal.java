package ventanas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.BD;
import clases.Producto;
import clases.Usuario;


public class VentanaPrincipal extends JFrame {
	//DECLARACION DE LOS ATRIBUTOS
	
	private JPanel contentPane;
	private JPanel panelCentral, panelArriba, panelNorte, panelArribaDrc, panelArribaIzq;
	private JButton btnInicioSesion, btnSalir, btnPruebaFoto, btnRegistrarme;
	private JFrame ventanaActual;
	public static TreeMap<String, ArrayList<Producto>> tmPedidos; //MAPA que tiene como clave el nombre del usuario y como valor el pedido con los productos
	public static TreeMap<String, Usuario> tmUsuarios;
	public static DefaultListModel<Usuario> modeloListaUsuario;
	public static JList<Usuario> listaUsuario;
	private JScrollPane scrollLista;
	private JLabel lblTitulo;
	public static JLabel lblNombre;
	private JTable tablaPrin;
	private DefaultTableModel modeloTablaPrin;
	private JScrollPane scrollTabla;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnExit;
	private JMenu mnProductos;
	private JMenuItem mntmCargarArchivo;
	private JMenuItem mntmCerrarAplicacion;
	private JMenuItem mntmCalcetines;
	private JLabel lblHora;
	

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
		//PROPIEDADES DE LA VENTANA
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		//CREAMOS LOS PANELES
		panelCentral = new JPanel();
		panelCentral.setBackground(Color.CYAN);
		panelCentral.setLayout(new GridLayout(0,1,0,0));
		
		panelArriba = new JPanel();
		panelArriba.setLayout(new GridLayout(0,2,0,0));
		panelCentral.add(panelArriba);
		
		panelArribaIzq = new JPanel();
		panelArriba.add(panelArribaIzq);
		panelArribaIzq.setBackground(Color.CYAN);
		
		panelArribaDrc = new JPanel();
		panelArriba.add(panelArribaDrc);
		panelArribaDrc.setBackground(Color.CYAN);
		
		panelNorte = new JPanel();
		panelNorte.setBackground(Color.CYAN);
		
		
		
		
		//AÑADIMOS LOS PANELES AL PANEL PRINCIPAL DE LA VENTANA
		contentPane.add(panelCentral, BorderLayout.CENTER);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		//CREAMOS LOS COMPONENTES
		btnInicioSesion = new JButton("INICIAR SESION");
		btnSalir = new JButton("SALIR");
		btnRegistrarme = new JButton("REGISTRARME");
		
		modeloListaUsuario = new DefaultListModel<>();
		listaUsuario = new JList<Usuario>(modeloListaUsuario);
		scrollLista = new JScrollPane(listaUsuario);
		
		modeloTablaPrin = new DefaultTableModel();
		Vector<String> cabeceras = new Vector<String>( Arrays.asList() );
		modeloTablaPrin = new DefaultTableModel(  
				new Vector<Vector<Object>>(),  
				cabeceras  
			) {
				public boolean isCellEditable(int row, int column) {
					if(column==0)
						return false;
					return true;
				}
			};
			
		tablaPrin = new JTable(modeloTablaPrin);
		
		//ESTO NO FUNCIONA!!!
		tablaPrin.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JButton btnCalcetines = new JButton("CALCETINES");
				JButton btnCamisetas = new JButton("CAMISETAS");
				
				
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(column == 0 && row == 0) {
					value = btnCalcetines;
					return  (JButton) value;
				}
				if (column == 1 && row == 1) {
					value = btnCamisetas;
					return (Component) value;
				}
				return c;
			}
		});
		
		JScrollPane scrollTabla = new JScrollPane(tablaPrin);
		panelArribaDrc.add(scrollTabla);
		lblNombre = new JLabel("");
		lblTitulo = new JLabel("SWEET WEAR");
		lblTitulo.setForeground(Color.BLACK);
		
		FlowLayout flowLayout = (FlowLayout) panelNorte.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		menuBar = new JMenuBar();
		panelNorte.add(menuBar);
		
		mnFile = new JMenu("File");
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);
		
		mntmCargarArchivo = new JMenuItem("Cargar Archivo");
		mntmCargarArchivo.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmCargarArchivo);
		
		mnExit = new JMenu("Exit");
		mnExit.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnExit);
		
		mntmCerrarAplicacion = new JMenuItem("SALIR");
		mntmCerrarAplicacion.setHorizontalAlignment(SwingConstants.LEFT);
		mnExit.add(mntmCerrarAplicacion);
		
		mnProductos = new JMenu("PRODUCTOS");
		mnProductos.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnProductos);
		
		mntmCalcetines = new JMenuItem("CALCETINES");
		mntmCalcetines.setHorizontalAlignment(SwingConstants.LEFT);
		mnProductos.add(mntmCalcetines);
		
		panelNorte.add(lblTitulo);
		
		lblHora = new JLabel("");
		panelNorte.add(lblHora);
		
		
		btnPruebaFoto = new JButton();
		btnPruebaFoto.setBounds(80, 80, 80, 80);
		int ancho = btnPruebaFoto.getWidth();
		int alto = btnPruebaFoto.getHeight();
		ImageIcon icon = new ImageIcon("tiendaDeRopa\\src\\imagenes\\IconoCarrito.png");
		Icon i = new ImageIcon(icon.getImage().getScaledInstance(alto, ancho, Image.SCALE_DEFAULT));
		btnPruebaFoto.setIcon(i);
		
		//btnPruebaFoto = new JButton(new ImageIcon("tiendaDeRopa\\src\\imagenes\\IconoCarrito.png"));
		btnPruebaFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelArribaIzq.add(btnPruebaFoto);
		//AÑADIMOS LOS COMPONENTES A LOS PANELES
		panelArribaIzq.add(btnInicioSesion);
		panelArribaIzq.add(btnSalir);
		panelArribaIzq.add(scrollLista);
		panelArribaIzq.add(btnRegistrarme);
		panelArribaIzq.add(lblNombre);
		
		setLocationRelativeTo( null );
		
		 
		//EVENTOS
		btnInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaInicioSesion I = new VentanaInicioSesion();
				I.setVisible(true);
				VentanaPrincipal P = new VentanaPrincipal();
				P.setVisible(false);
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
				VentanaRegistro R = new VentanaRegistro();
				R.setVisible(true);
				VentanaPrincipal P = new VentanaPrincipal();
				P.setVisible(false);
			}
		});
		
		
		btnPruebaFoto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//NO FUNCIONA!!
				new VentanaCarrito(ventanaActual);
				ventanaActual.setVisible(false);
				
			}
		});
		
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
		
		/*Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					panelArribaIzq.setBackground(Color.PINK);
					panelArribaDrc.setBackground(Color.BLUE);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panelArribaIzq.setBackground(Color.BLUE);
					panelArribaDrc.setBackground(Color.PINK);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};
		Thread t2 = new Thread(r2);
		t2.start();*/
		
		ventanaActual.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				cargarMapaUsuariosDeFicheroDeTexto();
			}
				
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				//guardarMapaUsuariosEnFicheroDeTexto();
			}
			
			
			
		});
		
		
		
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
		
		try {
			br = new BufferedReader(new FileReader("USUARIOS.txt"));
			String linea = br.readLine();
			while (linea != null) {
				String [] datosUsuario = linea.split(" ");
				String nombre = datosUsuario[0];
				int edad = Integer.parseInt(datosUsuario[1]);
				String mail = datosUsuario[2];
				String con = datosUsuario[3];
				boolean permisos = Boolean.parseBoolean(datosUsuario[4]);
				Usuario u = new Usuario(nombre, edad, mail, con, permisos);
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
		
		
		
	}
	
	
	/**
	 * Metodo que guarda el mapaUsuarios en un fichero de Texto
	 */
	private void guardarMapaUsuariosEnFicheroDeTexto () {
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter("USUARIOS.txt");
			for (String nombre: VentanaPrincipal.tmUsuarios.keySet()) {
				pw.println(nombre);
				Usuario u = VentanaPrincipal.tmUsuarios.get(nombre);
				pw.print(" " + u);
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
	
	private void cargarMapaPedidosDeFicheroDeTexto () {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("USUARIOS.txt"));
			String linea = br.readLine();
			while (linea != null) {
				String [] datosUsuario = linea.split(" ");
				String nombre = datosUsuario[0];
				int edad = Integer.parseInt(datosUsuario[1]);
				String mail = datosUsuario[2];
				String con = datosUsuario[3];
				boolean permisos = Boolean.parseBoolean(datosUsuario[4]);
				Usuario u = new Usuario(nombre, edad, mail, con, permisos);
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
		
		
		
	}	
		
	
	
}





