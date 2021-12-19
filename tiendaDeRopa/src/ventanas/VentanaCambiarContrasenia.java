package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import clases.BD;

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
		//setBounds(100, 100, 450, 300);
		setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//CREACION DE LOS PANELES
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(3,2));
		
		
		//CREACION DE LOS COMPONENTES
		btnvolver = new JButton();
		VentanaPrincipal.ponerFotoABoton(btnvolver, "tiendaDeRopa\\src\\imagenes\\IconoSalir.png", 30, 30, 30, 30);
		
		btnCambiarCon = new JButton("CAMBIAR CONTRASENIA");
		
		lblConActual = new JLabel("CONTRASEÑA ACTUAL: ");
		lblConNueva = new JLabel("CONTRASEÑA NUEVA: ");
		lblConNueva2 = new JLabel("REPETIR CONTRASEÑA");
		
		txtConActual = new JPasswordField();
		txtConNueva = new JPasswordField();
		txtConNueva2 = new JPasswordField();
		
		//AÑADIMOS LOS COMPONENTES A LOS PANELES
		panelCentral.add(lblConActual);
		panelCentral.add(txtConActual);
		panelCentral.add(lblConNueva);
		panelCentral.add(txtConNueva);
		panelCentral.add(lblConNueva2);
		panelCentral.add(txtConNueva2);
		
		panelSur.add(btnvolver);
		panelSur.add(btnCambiarCon);
		
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
						JOptionPane.showMessageDialog(null, "LA CONTRASEÑA NUEVA ES LA MISMA QUE LA VIEJA", "ERROR", JOptionPane.ERROR_MESSAGE);
					}else if (nueva.equals(nuevaRepetida)) {
						BD.modificarConUsuario(con, VentanaInicioSesion.n, nueva);
						JOptionPane.showMessageDialog(null, "CONTRASEÑA ACTUALIZADA CORRECTAMENTE");
					}else{
						JOptionPane.showMessageDialog(null, "LA CONTRASEÑAS NO COINCIDEN", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "LA CONTRASEÑA NO ES CORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				BD.closeBD(con);
				vaciarCampos();
		
			}
		});
		
		
	}
	private void vaciarCampos() {
		txtConActual.setText("");
		txtConNueva.setText("");
		txtConNueva2.setText("");
	}

}
