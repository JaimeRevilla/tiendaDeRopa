package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
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
	private JLabel lblPrecio;

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
		setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		//CREACIÓN DE LOS PANELES
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(2,1));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		panelCentralArriba = new JPanel();
		panelCentral.add(panelCentralArriba);
		
		panelCentralAbajo = new JPanel();
		panelCentral.add(panelCentralAbajo);
		
		//CREACION DE LOS COMPONENTES
		lblCarrito = new JLabel();
		lblPrecio = new JLabel();
		
		btnImprimirRecivo = new JButton("IMPRIMIR RECIVO");
		panelSur.add(btnImprimirRecivo);
		
		btnBorrar = new JButton("BORRAR PRODUCTO");
		panelSur.add(btnBorrar);
		
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
				lblPrecio.setText("PRECIO TOTAL: "+ pre + "euros");
			 }
		}	
		
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
				//LO QUE VA A HACER ESTE METODO ES COGER Y PONER EL ARRAYLIST EN UN FICHERO DE TEXTO LLAMADO FACTURA
				
			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = listaPedido.getSelectedIndex();
				if (pos != -1) {
					modeloListaPedido.removeElementAt(pos);	
					Producto p = VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).get(pos);
					VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).remove(p);
					double pre = 0;
					for (Producto pro: VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n)) {
						pre = pre + (pro.getPrecio() * pro.getStock());
						lblPrecio.setText("PRECIO TOTAL: "+ pre + "euros");
					}
					//lblPrecio.setText("PRECIO TOTAL: "+ (pre - p.getPrecio()) + "euros");
					
				}else 
					JOptionPane.showMessageDialog(null, "NO HAS SELECCIONADO NINGUN PRODUCTO", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		
		
		//EVENTOS DE VENTANA
				ventanaActual.addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowOpened(WindowEvent e) {
						//VentanaPrincipal.cargarMapaPedidosDeFicheroDeTexto();
					}
						
					
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						VentanaPrincipal.guardarMapaPedidosEnFicheroDeTexto();
					}
					
					
					
				});
				
		
		//HILO
		
//		Runnable r1 = new Runnable() {
//			
//			@Override
//			public void run() {
//				while (true) {
//					if (VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).size() == 0){
//						lblPrecio.setText("NO HAY PRODUCTOS EN EL CARRITO");
//					}else {
//						double pre = 0;
//						for (Producto p: VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n)) {
//							pre = pre + p.getPrecio();
//							lblPrecio.setText("PRECIO TOTAL: "+ pre + "euros");
//						 }
//					}
//				}
//				
//			}
//		};
//		Thread t1 = new Thread(r1);
//		t1.start();
		
		
	}

}
