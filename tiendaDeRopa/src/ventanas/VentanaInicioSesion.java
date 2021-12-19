package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.BD;

public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaAnterior, ventanaActual;
	private JButton btnVolverAtras, btnIniciarSesion;
	private JPanel panelNorte, panelCentral, panelSur;
	private JLabel lblNombre, lblCon;
	private JTextField textNombre;
	private JPasswordField textCon;
	public static String n;
	
	
	public VentanaInicioSesion(JFrame va) {
//		Connection con = BD.initBD("SweetWear.db");
//		BD.crearTablaUsuario(con);
//		BD.crearTablaCalcetines(con);
//		BD.crearTablaCamiseta(con);
//		BD.crearTablaPantalon(con);
//		BD.crearTablaSudadera(con);
//		BD.crearTablaZapato(con);
//		VentanaPrincipal.tmUsuarios = BD.obtenerMapaUsuarios(con);
//		BD.closeBD(con);
		ventanaAnterior = va;
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(0, 2));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		lblNombre = new JLabel("INTRODUCE TU NOMBRE DE USUARIO: ");
		lblCon = new JLabel("INTRODUCE UNA CONTRASEÑA: ");
		
		textNombre = new JTextField();
		textCon = new JPasswordField();
		
		panelCentral.add(lblNombre);
		panelCentral.add(textNombre);
		panelCentral.add(lblCon);
		panelCentral.add(textCon);
		
		textNombre.setColumns(10);
		textCon.setColumns(10);
		
		btnVolverAtras = new JButton("VOLVER");
		panelSur.add(btnVolverAtras);
		
		btnIniciarSesion = new JButton("INICIAR SESION");
		panelSur.add(btnIniciarSesion);
		
		panelCentral.setBackground(Color.CYAN);
		panelSur.setBackground(Color.CYAN);
		
		
		
		btnVolverAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
				volver();
			}
		});
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				n = textNombre.getText();
				//String n = textNombre.getText();
				String c = textCon.getText();
				
				Connection con = BD.initBD("SweetWear.db"); 
				int resul = BD.obtenerUsuario(con, n, c);
				if (resul == 0) {
					JOptionPane.showMessageDialog(null, "¡¡PRIMERO TIENES QUE REGISTRARTE!!");
					new VentanaRegistro(ventanaActual);
					ventanaActual.setVisible(false);
				} else if (resul == 1){
					JOptionPane.showMessageDialog(null, "¡¡LA CONTRASEÑA EN INCORRECTA!!");
				} else if (resul == 2){
					JOptionPane.showMessageDialog(null, "¡¡BIENVENIDO "+ n+ "!!");
					VentanaPrincipal.lblNombre.setText("BIENVENIDO " + n);
					
					if (!VentanaPrincipal.tmPedidos.containsKey(n))
						VentanaPrincipal.tmPedidos.put(n, new ArrayList<>());
					else 
						VentanaPrincipal.tmPedidos.get(n);
					
					VentanaPrincipal.btnCerrarSesion.setVisible(true);
					VentanaPrincipal.pb.setVisible(true);
					VentanaPrincipal.btnCambiarCon.setVisible(true);
					VentanaPrincipal.btnInicioSesion.setVisible(false);
					boolean admin = BD.obtenerAdmin(con, n);
					System.out.println(n);
					System.out.println(admin);
					if (admin == true) {
						VentanaPrincipal.btnAdmin.setVisible(true);
					}
					volver();
				}
				BD.closeBD(con);
				vaciarCampos();
			}
		});
		
		
		
		setVisible(true);
		
	}

	private void vaciarCampos() {
		textNombre.setText("");
		textCon.setText("");
	}
	
	private void volver() {
		ventanaActual.dispose();
		ventanaAnterior.setVisible(true);
	}
	
}
