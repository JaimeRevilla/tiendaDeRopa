package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import clases.BD;
import clases.PanelConImagenDeFondo;
import clases.Usuario;

public class VentanaCambiarContrasenia extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	private JButton btnvolver, btnCambiarCon;
	private JPanel panelSur, panelCentral;
	private JLabel lblConActual, lblConNueva, lblConNueva2;
	private JPasswordField txtConActual, txtConNueva, txtConNueva2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCambiarContrasenia frame = new VentanaCambiarContrasenia(null);
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
	public VentanaCambiarContrasenia(JFrame va ) {
		
		ventanaAnterior = va;
		ventanaActual = this;
		
		//PROPIEDADES DE LA VENTANA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setBounds(100, 100, 450, 300);
		//setSize(1650, 1080);
		//setBounds(500, 150, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//CREACION DE LOS PANELES
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setBackground(new Color(122,217,196));
		
		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(3,2));
		panelCentral.setBackground(new Color(122,217,196));
		
		//CREACION DE LOS COMPONENTES
		btnvolver = new JButton("VOLVER");
		VentanaPrincipal.ponerFotoABoton(btnvolver, "imagenes\\IconoSalir.png", 30, 30, 30, 30);
		
		btnCambiarCon = new JButton("CAMBIAR CONTRASENIA");
		VentanaPrincipal.ponerFotoABoton(btnCambiarCon, "imagenes\\IconoCambiarContrase�a.png", 30, 30, 30, 30);
		
		lblConActual = new JLabel("CONTRASE�A ACTUAL: ");
		lblConNueva = new JLabel("CONTRASE�A NUEVA: ");
		lblConNueva2 = new JLabel("REPETIR CONTRASE�A");

		
		txtConActual = new JPasswordField();
		txtConNueva = new JPasswordField();
		txtConNueva2 = new JPasswordField();
		
		//A�ADIMOS LOS COMPONENTES A LOS PANELES
		panelCentral.add(lblConActual);
		panelCentral.add(txtConActual);
		panelCentral.add(lblConNueva);
		panelCentral.add(txtConNueva);
		panelCentral.add(lblConNueva2);
		panelCentral.add(txtConNueva2);
		
		panelSur.add(btnvolver);
		panelSur.add(btnCambiarCon);
		
		
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
		btnvolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
				
			}
		});
		
		btnCambiarCon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String vieja = txtConActual.getText();
				String nueva = txtConNueva.getText();
				String nuevaRepetida = txtConNueva2.getText();
				
				Connection con = BD.initBD("SweetWear.db");
				String c = BD.obtenerConUsuario(con, VentanaInicioSesion.n);
				if (c.equals(vieja)) {
					if(nueva.equals(vieja)) {
						JOptionPane.showMessageDialog(null, "LA CONTRASE�A NUEVA ES LA MISMA QUE LA VIEJA", "ERROR", JOptionPane.ERROR_MESSAGE);
					}else if (nueva.equals(nuevaRepetida)) {
						BD.modificarConUsuario(con, VentanaInicioSesion.n, nueva);
						Usuario u = BD.obtenerUsuario(con, VentanaInicioSesion.n);
						u.setCon(nueva);
						VentanaPrincipal.tmUsuarios.get(VentanaInicioSesion.n).setCon(nueva);
						VentanaPrincipal.log.log(Level.INFO, VentanaInicioSesion.n + " ha modificiado su contrasenia");
						JOptionPane.showMessageDialog(null, "CONTRASE�A ACTUALIZADA CORRECTAMENTE");
					}else{
						JOptionPane.showMessageDialog(null, "LA CONTRASE�AS NO COINCIDEN", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "LA CONTRASE�A NO ES CORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				BD.closeBD(con);
				vaciarCampos();
		
			}
		});
		
//------------------------------------------------------------------------------------------------------------------
		
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
				
				
		
	}
	
//---------------------------------------------------------------------------------------------------------------------	
	
	
			
	
	
	private void vaciarCampos() {
		txtConActual.setText("");
		txtConNueva.setText("");
		txtConNueva2.setText("");
	}

}
