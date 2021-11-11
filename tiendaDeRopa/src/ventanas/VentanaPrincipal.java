package ventanas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import clases.Producto;
import clases.Usuario;

public class VentanaPrincipal extends JFrame {
	//DECLARACION DE LOS ATRIBUTOS
	
	private JPanel contentPane;
	private JPanel panelCentral;
	private JButton btnInicioSesion, btnSalir, btnPruebaFoto;
	private JFrame ventanaActual;
	public static TreeMap<String, ArrayList<Producto>> tmPedidos; //MAPA que tiene como clave el nombre del usuario y como valor el pedido con los productos
	public static TreeMap<String, Usuario> tmUsuarios;
	private DefaultListModel<Usuario> modeloListaUsuario;
	private JList<Usuario> listaUsuario;
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

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		//PROPIEDADES DE LA VENTANA
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		modeloListaUsuario = new DefaultListModel<>();
		listaUsuario = new JList<Usuario>(modeloListaUsuario);
		
		//CREAMOS LOS PANELES
		panelCentral = new JPanel();
		panelCentral.setBackground(Color.CYAN);
		panelCentral.setLayout(new GridLayout(5,5,3,3));
		//AÑADIMOS LOS PANELES AL PANEL PRINCIPAL DE LA VENTANA
		contentPane.add(panelCentral, BorderLayout.CENTER);
		//CREAMOS LOS COMPONENTES
		btnInicioSesion = new JButton("INICIAR SESION");
		btnSalir = new JButton("SALIR");
		
//		ImageIcon icon = new ImageIcon("tiendaDeRopa\\src\\imagenes\\IconoCarrito.png");
//		Icon i = new ImageIcon(icon.getImage().getScaledInstance(alto, ancho, Image.SCALE_DEFAULT));
		
		
		
		lblHora = new JLabel("");
		
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
		panelCentral.add(btnPruebaFoto);
		//AÑADIMOS LOS COMPONENTES A LOS PANELES
		panelCentral.add(btnInicioSesion);
		panelCentral.add(btnSalir);
		panelCentral.add(lblHora);
		//EVENTOS
		btnInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaInicioSesion(ventanaActual);
				ventanaActual.setVisible(false);
				
			}
		});
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
		});
		
		//HILOS
		
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
						Thread.sleep(500);
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
	
	
	
	
	private void cargarMapaPedidos() {
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
	
	
	private void cargarMapaUsuarios () {
		for(String clave: tmUsuarios.keySet()) {
			Usuario valor = tmUsuarios.get(clave);
			modeloListaUsuario.addElement(valor);
		}

		
	}
	
	
	
	
}




