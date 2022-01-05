package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import clases.BD;
import clases.Producto;
import clases.Usuario;

public class VentanaAdmin2 extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaAnterior, ventanaActual;
	private JButton btnVolver, btnTextArea;
	private JPanel panelSur, panelCentro;
	private JTextArea textAreaUsuarios, textAreaPedidos, textAreaTienda, textAreaEstadisticas;
	private JScrollPane scrollUsuarios, scrollPedidos, scrollTienda, scrollEstadisticas;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin2 frame = new VentanaAdmin2(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaAdmin2(JFrame va) {
		Connection con = BD.initBD("SweetWear.db");
		VentanaPrincipal.tmUsuarios = BD.obtenerMapaUsuarios(con);
		BD.closeBD(con);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ventanaAnterior = va;
		ventanaActual = this;
		
		panelSur = new JPanel();
		panelSur.setBackground(Color.CYAN);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		VentanaPrincipal.ponerFotoABoton(btnVolver, "imagenes\\IconoSalir.png", 30, 30, 30, 30);

		
		btnTextArea = new JButton("MOSTRAR DATOS REGISTRADOS");
		panelSur.add(btnTextArea);
		VentanaPrincipal.ponerFotoABoton(btnTextArea, "imagenes\\IconoDatos.png", 30, 30, 30, 30);

		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 2));
	
		textAreaUsuarios = new JTextArea();
		textAreaUsuarios.setText("USUARIOS");
		scrollUsuarios = new JScrollPane(textAreaUsuarios);
		panelCentro.add(scrollUsuarios);
		
		textAreaPedidos = new JTextArea();
		textAreaPedidos.setText("PEDIDOS");
		scrollPedidos = new JScrollPane(textAreaPedidos);
		panelCentro.add(scrollPedidos);
		
		textAreaTienda = new JTextArea();
		textAreaTienda.setText("TIENDA");
		scrollTienda = new JScrollPane(textAreaTienda);
		panelCentro.add(scrollTienda);
		
		textAreaEstadisticas = new JTextArea();
		textAreaEstadisticas.setText("VALORACIÓN");
		scrollEstadisticas = new JScrollPane(textAreaEstadisticas);
		panelCentro.add(scrollEstadisticas);
		
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
		
		
		
		
		//EVENTOS
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
				
			}
		});
		
		btnTextArea.addActionListener(new ActionListener() {
			//ARREGLAR ESTO !!!!!
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String texto = "USUARIOS:";
				for (String clave: VentanaPrincipal.tmUsuarios.keySet()) {
					texto = texto  + "\n" + clave + "\t"+ VentanaPrincipal.tmUsuarios.get(clave) + "\n";
				}
				textAreaUsuarios.setText(texto);
				
				String texto2 = "PEDIDOS:";
				for (String clave: VentanaPrincipal.tmPedidos.keySet()) {
					texto2 = texto2 + "\n" + clave;
					for (Producto p :VentanaPrincipal.tmPedidos.get(clave)) {
						texto2 = texto2 + "\t" + p + "\n";
					}
				}
				textAreaPedidos.setText(texto2);
	
				String texto3 = "TIENDA:";
				int i = 1;
				Connection con = BD.initBD("SweetWear.db");
				ArrayList<Producto> al = BD.getTienda(con);
				for (Producto p : al) {
					texto3 = texto3  + "\n" + i + "- " + p + "\n";
					i++;
				}
				textAreaTienda.setText(texto3);
				
				String texto4 = "ESTADISTICAS:";
				mostrarMapaEstadisticas(VentanaPrincipal.hmSatisfaccion, VentanaPrincipal.hmSatisfaccion.keySet().iterator(), texto4);
				
				
				JOptionPane.showMessageDialog(null, "DATOS OBTENIDOS CORRECTAMENTE!!!", "EXITO!!!", JOptionPane.NO_OPTION);
			}
		});		
		
		
	}
	
	
	/**
	 * METODO PARA RECORRER UN MAPA RECURSIVAMENTE Y MOSTRARLO EN UN TEXT AREA
	 * @param hm El mapa a recorrer
	 * @param it El iterador para poder acceder a la siguiente posicion del mapa
	 * @param texto texto donde se recopila la informacion del mapa y luego se muestra en el text area
	 */
	private void mostrarMapaEstadisticas(HashMap<String, Integer> hm , Iterator<String> it, String texto) {
		if (it.hasNext()) {	
			String clave = it.next();
			Integer valor = hm.get(clave);
			texto  = texto  + "\n" + clave + "\t"+ valor + "\n"; 	
			textAreaEstadisticas.setText(texto);
			mostrarMapaEstadisticas(hm, it, texto);	
		}
		
	}
	
	/**
	 * METODO PARA PODER VOLVER A LA VENTANA DESDE LA QUE SE ACCEDIO
	 */
	private void volver() {
		ventanaActual.dispose();
		ventanaAnterior.setVisible(true);
	}
	
	


}
