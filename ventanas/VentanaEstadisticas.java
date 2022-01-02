package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.PanelConImagenDeFondo;
import clases.Producto;

public class VentanaEstadisticas extends JFrame {

//	private PanelConImagenDeFondo contentPane;
	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	private JPanel panelSur, panelCentralArriba, panelCentral, panelCentralAbajo, panelNorte;
	private JButton btnVolver, btnObtenerEstadisticas;
	private String fondo = "/imagenes/tienda-de-ropa.jpg";
	private JLabel lblTitulo1, lblPrecioMedioPedidos, lblCliente, lblSatisfaccion, lblPrecioMedioPedidoCliente, lblSatisfaccionCliente;
	private JComboBox<String> comboClientes;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEstadisticas frame = new VentanaEstadisticas(null);
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
	public VentanaEstadisticas(JFrame va) {
		//PROPIEDADES DE LA VENTANA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 600, 450);
//		contentPane = new PanelConImagenDeFondo(getSize());
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
	
//		contentPane = new PanelConImagenDeFondo(getSize());
//		contentPane.setImage(fondo);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ventanaAnterior = va;
		ventanaActual = this;
		
		//CREAMOS LOS PANELES
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(2, 1));
		
		panelCentralArriba = new JPanel();
		panelCentral.add(panelCentralArriba);
		panelCentralArriba.setLayout(new GridLayout(0, 2));
		
		panelCentralAbajo = new JPanel();
		panelCentral.add(panelCentralAbajo);
		panelCentralAbajo.setLayout(new GridLayout(0, 2));
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
	
		
		//CREAMOS LOS COMPONENTES
		btnVolver = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnVolver, "imagenes\\IconoSalir.png", 30, 30, 30, 30);
		panelSur.add(btnVolver);
		
		btnObtenerEstadisticas = new JButton("OBTENER ESTADISTICAS");
		panelSur.add(btnObtenerEstadisticas);
		
		lblTitulo1 = new JLabel("ESTADISTICAS GENERALES Y PERSONALES");
		panelNorte.add(lblTitulo1);
		
		lblPrecioMedioPedidos = new JLabel("PRECIO MEDIO DE LOS PEDIDOS: ");
		double avg = dineroTotalDeLosProductosQueSeHanVendido() / cuantosProductosSeHanVendido();
		String texto = "PRECIO MEDIO DE LOS PEDIDOS: " + avg + " euros";
		lblPrecioMedioPedidos.setText(texto);
		panelCentralArriba.add(lblPrecioMedioPedidos);
		
		lblSatisfaccion = new JLabel();
		int avg2 = sumaDeValoraciones() / cantidadDeValoraciones();
		String texto2 = "VALORACION MEDIA: " + avg2;
		lblSatisfaccion.setText(texto2);
		panelCentralArriba.add(lblSatisfaccion);
		
		
		lblCliente = new JLabel("ESCOGE UN CLIENTE:");
		panelCentralAbajo.add(lblCliente);
		
		comboClientes = new JComboBox<>();
		for (String s: VentanaPrincipal.tmUsuarios.keySet()) {
			comboClientes.addItem(s);
		}
		panelCentralAbajo.add(comboClientes);
		
		
		
		lblPrecioMedioPedidoCliente = new JLabel();
		lblSatisfaccionCliente = new JLabel();
		
		//-----------------------------------------------------------------------------------------------------------
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
		
		btnObtenerEstadisticas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboClientes.getSelectedIndex();
				if (index != -1) {
					panelCentralAbajo.add(lblPrecioMedioPedidoCliente);
					panelCentralAbajo.add(lblSatisfaccionCliente);
					
					String selec = comboClientes.getItemAt(index);
					double medio = dineroTotalDeLosProductosQueSeHaVendidoElCliente(selec) / cuantosProductosSeHanVendidoDelCliente(selec);
					String lblMedio = "";
					if (VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).size() == 0) {
						lblMedio = selec + " NO TIENE NINGUN PEDIDO REALIZADO";
					}else
						lblMedio = "PRECIO MEDIO DE " + selec + ": " + medio + " euros";
					lblPrecioMedioPedidoCliente.setText(lblMedio);
					
					
					int valor = sumaDeValoracionesCliente(selec);
					String lblValoracion = "";
					if (!VentanaPrincipal.hmSatisfaccion.containsKey(selec)) {
						lblValoracion = selec + " No ha votado" ;
					}else 
						lblValoracion = "SATISFACCION DE " + selec + ": " + valor;
					lblSatisfaccionCliente.setText(lblValoracion);
					
					
							
				}
				
			}
		});


	}
	
	//------------------------------------------------------------------------------------------------
	
	
	//-----------------------------------------------------------------------------------------
	
	public static int cuantosProductosSeHanVendido() {
		int cont = 0;
		for(String s: VentanaPrincipal.tmPedidos.keySet()) {
			ArrayList<Producto> al = VentanaPrincipal.tmPedidos.get(s);
			cont = cont + al.size();
		}
		return cont;
	}
	
	public static double dineroTotalDeLosProductosQueSeHanVendido() {
		double cont = 0;
		for(String s: VentanaPrincipal.tmPedidos.keySet()) {
			ArrayList<Producto> al = VentanaPrincipal.tmPedidos.get(s);
			for (Producto p: al) {
				cont = cont + (p.getPrecio() * p.getStock());
			}
		}
		return cont;
	}
	
	public static int cantidadDeValoraciones () {
		int cont = 0;
		cont = VentanaPrincipal.hmSatisfaccion.keySet().size();
		return cont;
	}
	
	public static int sumaDeValoraciones () {
		int suma = 0;
		for (String n: VentanaPrincipal.hmSatisfaccion.keySet()) {
			suma = suma + VentanaPrincipal.hmSatisfaccion.get(n);
		}
		return suma;
	}
	
	//----------------------------------------------------------------------------------------------------------------
	public static int cuantosProductosSeHanVendidoDelCliente(String nombre) {
		int cont = 0;
		
		ArrayList<Producto> al = VentanaPrincipal.tmPedidos.get(nombre);
		if (al != null) {
			cont = cont + al.size();
		}
		return cont;
	}
	
	public static double dineroTotalDeLosProductosQueSeHaVendidoElCliente(String nombre) {
		double cont = 0;
		ArrayList<Producto> al = VentanaPrincipal.tmPedidos.get(nombre);
		if (al != null) {
			for (Producto p: al) {
				cont = cont + (p.getPrecio() * p.getStock());
			}
		}
		return cont;
	}
	
	
	
	public static int sumaDeValoracionesCliente (String nombre) {
		int va = 0;
		if (VentanaPrincipal.hmSatisfaccion.get(nombre) != null)
			va = VentanaPrincipal.hmSatisfaccion.get(nombre);
		return va;
		
	}
	

}
