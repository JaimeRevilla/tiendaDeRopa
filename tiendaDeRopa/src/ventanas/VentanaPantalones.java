package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Pantalon;
import clases.TipoPantalon;

public class VentanaPantalones extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentral, panelP1, panelP11, panelP12, panelP2, panelP21, panelP22, panelP3, panelP31, panelP32;
	private JFrame ventanaAnterior, ventanaActual;
	private JPanel panelSur;
	private JButton btnVolver, btnChandal, btnVaqueros, btnCampana;
	private JLabel lblChandal, lblVaquero, lblCampana;

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
		VentanaPrincipal.ponerFotoABoton(btnChandal, "tiendaDeRopa\\src\\imagenes\\IconoPantalonChandal.png",  120, 120, 120, 120);
		panelP11.add(btnChandal);
		
		lblChandal = new JLabel("PANTALONES CHANDAL");
		panelP12.add(lblChandal);
		
		//----------------------------------------------------------------------------
		panelP2 = new JPanel();
		panelP2.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP2);
		
		panelP21 = new JPanel();
		panelP2.add(panelP21);
		
		panelP22 = new JPanel();
		panelP2.add(panelP22);
		
		btnVaqueros = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnVaqueros, "tiendaDeRopa\\src\\imagenes\\IconoPantalonVaquero.jpg",  120, 120, 120, 120);
		panelP21.add(btnVaqueros);
		
		lblVaquero = new JLabel("PANTALONES VAQUEROS");
		panelP22.add(lblVaquero);
		
		//--------------------------------------------------------------------------
		panelP3 = new JPanel();
		panelP3.setLayout(new GridLayout(2, 1));
		panelCentral.add(panelP3);
		
		panelP31 = new JPanel();
		panelP3.add(panelP31);
		
		panelP32 = new JPanel();
		panelP3.add(panelP32);
		
		btnCampana = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnCampana, "tiendaDeRopa\\src\\imagenes\\IconoPantalonCampana.png",  120, 120, 120, 120);
		panelP31.add(btnCampana);
		
		lblCampana = new JLabel("PANTALONES CAMPANA");
		panelP32.add(lblCampana);
		
		
		
		
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
						System.out.println(canti);
						//IDEA!!: IGUAL CREAR EN LA BD LOS OBJETOS DE LAS IMAGENES Y COGER LOS 
						//ATRIBUTOS PARA CREAR EL OBJETO DE LA BASE DE DATOS
						VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Pantalon(0, "rojo", "vaquero", 20, canti, "nike","laRuta", TipoPantalon.CHANDAL)); //AQUI AÑADIR EL PRODUCTO
						System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
						System.out.println("Hola");
						JOptionPane.showMessageDialog(null, "¡¡PRODUCTO AÑADIDO CORRECTAMENTE!!");
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesión primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}

}
