package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	//DECLARACION DE LAS VARIABLES
	private JPanel contentPane;
	private JPanel  panelCentral, panelCentralNorte, panelCentralSur, panelDrc;
	private JButton btnVolverAtras, btnIrParaAdelante, btnPrincipio, btnIniciarSesion, btnCarrito;
	private JLabel imagenCentral;
	private JFrame ventanaActual;
	 

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
//		Connection con = BDUsuario.initBD("SweetWear.db");
//		BDUsuario.crearTabla(con);
//		BDUsuario.closeBD(con);
		ventanaActual = this;
		
		
		
		//PROPIEDADES DE LAS VENTANAS
		setTitle("SWEET WEAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		
		//CREACION DE LOS PANELES
		
		btnCarrito = new JButton(new ImageIcon("tiendaDeRopa\\src\\imagenes\\IconoCarrito.png"));
	
		panelCentral = new JPanel();
		panelCentralNorte = new JPanel(new GridLayout(1, 3));
		panelCentralSur = new JPanel(new GridLayout(3, 3));
		panelDrc = new JPanel();
		
		
		//CREACION DE LOS COMPONENTES
		imagenCentral = new JLabel(new ImageIcon("tiendaDeRopa\\src\\imagenes\\tienda-de-ropa.jpg"));
		
		//AÑADIR LOS COMPONENTES A LOS CONTENEDORES
		panelCentralSur.add(imagenCentral);
		panelCentral.add(panelCentralNorte);
		panelCentral.add(panelCentralSur);
		
		panelCentralNorte.add(btnIniciarSesion);
		panelCentralNorte.add(btnCarrito);
		
		
		//AÑADIR LOS PANELES A LA VENTANA
		contentPane.add(panelCentral, BorderLayout.CENTER);
		//contentPane.add(panelDrc, BorderLayout.EAST);
		

		
		
		
		//EVENTOS
		
	}

}
