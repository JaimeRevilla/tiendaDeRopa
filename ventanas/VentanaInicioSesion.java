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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		ventanaAnterior = va;
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setBackground(new Color(122,217,196));
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(0, 2));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(new Color(122,217,196));
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setBackground(new Color(122,217,196));
		
		lblNombre = new JLabel("INTRODUCE TU NOMBRE DE USUARIO: ");
		lblCon = new JLabel("INTRODUCE UNA CONTRASEÑIA: ");
		
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
		VentanaPrincipal.ponerFotoABoton(btnVolverAtras,"imagenes\\IconoSalir.png", 30, 30, 30, 30);
		
		btnIniciarSesion = new JButton("INICIAR SESION");
		panelSur.add(btnIniciarSesion);
		VentanaPrincipal.ponerFotoABoton(btnIniciarSesion, "imagenes\\IconoiniciarSesion.png", 30, 30, 30, 30);
		
		panelCentral.setBackground(new Color(122,217,196));
		panelSur.setBackground(new Color(122,217,196));
		
		ventanaActual.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal.guardarMapaUsuariosEnFicheroDeTexto();
				VentanaPrincipal.guardarMapaPedidosEnFicheroDeTexto();
				VentanaPrincipal.guardarListaHistorialBusqueda();
				VentanaPrincipal.guardarMapaSatisfaccion();
				VentanaPrincipal.log.log(Level.INFO, "Los ficheros de informacion han sido actualizados correctamente");
			
			}
		});
		
		
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
				String c = textCon.getText();
				
				Connection con = BD.initBD("SweetWear.db"); 
				int resul = BD.obtenerUsuario(con, n, c);
				if (resul == 0) {
					JOptionPane.showMessageDialog(null, "PRIMERO TIENES QUE REGISTRARTE!!", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
					new VentanaRegistro(ventanaActual);
					ventanaActual.setVisible(false);
				} else if (resul == 1){
					JOptionPane.showMessageDialog(null, "LA CONTRASEÑIA EN INCORRECTA!!", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
				} else if (resul == 2){
					JOptionPane.showMessageDialog(null, "BIENVENIDO "+ n+ "!!");
					VentanaPrincipal.lblNombre.setText("BIENVENIDO " + n);
					VentanaPrincipal.log.log(Level.INFO, "Se ha iniciado sesion como" + " " + n);
					
					if (!VentanaPrincipal.tmPedidos.containsKey(n))
						VentanaPrincipal.tmPedidos.put(n, new ArrayList<>());
					else 
						VentanaPrincipal.tmPedidos.get(n);
					
					VentanaPrincipal.btnCerrarSesion.setVisible(true);
					if (!VentanaPrincipal.hmSatisfaccion.containsKey(n)) {
						VentanaPrincipal.pb.setVisible(true);
						VentanaPrincipal.sp.setVisible(true);
						VentanaPrincipal.lblValoracion.setVisible(true);
					}
					VentanaPrincipal.btnCambiarCon.setVisible(true);
					VentanaPrincipal.btnInicioSesion.setVisible(false);
					boolean admin = BD.obtenerAdmin(con, n);
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
