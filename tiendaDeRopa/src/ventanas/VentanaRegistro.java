package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.BD;
import clases.ExcepcionImplicita;
import clases.Usuario;

public class VentanaRegistro extends JFrame {

	private static Logger log = Logger.getLogger("Log de Usuarios");
	
	private JPanel contentPane;
	private JPanel panelCentral, panelSur;
	private JLabel lblNombre, lblEdad, lblMail, lblCon;
	private JPasswordField txtCon;
	private JTextField txtNombre, txtEdad, txtMail;
	private JButton btnRegistrar, btnVolver;
	private JFrame VentanaActual, VentanaAnterior;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaRegistro frame = new VentanaRegistro();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaRegistro(JFrame va) {

		Connection con = BD.initBD("SweetWear.db");
		BD.crearTabla(con);
		BD.closeBD(con);
		VentanaAnterior = va;
		VentanaActual = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNombre = new JLabel("NOMBRE: ");
		lblEdad = new JLabel("EDAD: ");
		lblMail = new JLabel("CORREO ELECTRÓNICO: ");
		lblCon = new JLabel("CONTRASEÑIA: ");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtCon = new JPasswordField();
		txtCon.setColumns(10);
		
		panelCentral.add(lblNombre);
		panelCentral.add(txtNombre);
		panelCentral.add(lblEdad);
		panelCentral.add(txtEdad);
		panelCentral.add(lblMail);
		panelCentral.add(txtMail);
		panelCentral.add(lblCon);
		panelCentral.add(txtCon);
		
		
		btnRegistrar = new JButton("REGISTRAR");
		btnVolver = new JButton("VOLVER");
		
		panelSur.add(btnVolver);
		panelSur.add(btnRegistrar);
		
		panelSur.setBackground(Color.CYAN);
		panelCentral.setBackground(Color.CYAN);
		
//		btnRegistrar.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String n = txtNombre.getText();
//				int ed = Integer.parseInt(txtEdad.getText());
//				String ermail = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$\")";
//				String m = txtMail.getText();
//				boolean correctoCorreo = Pattern.matches(ermail, m);
//				if (correctoCorreo) {
//					String c = txtCon.getText();
//					//FALTARIA PONER AQUI DE ALGUNA MANERA LO DE LOS PERMISOS
//					Usuario u = new Usuario(n, ed, m, c, false);
//					//AÑADIRLO AL MAPA DE CLAVE USUARIO Y VALOR EL CARRITO ASOCIADO A ESE USUARIO!
//					Connection con = BDUsuario.initBD("SweetWear.db");
//					BDUsuario.insertarUsuario(con, n, ed, m, c, false);
//					BDUsuario.closeBD(con);
//					JOptionPane.showMessageDialog(null, "Persona registrada correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
//					
//					//Logger Usuarios
//					try {
//						Handler handler = new FileHandler("Usuarios.log");
//						handler.setFormatter(new SimpleFormatter());
//						log.addHandler(handler);
//						log.log(Level.INFO, "Se ha añadido un Usuario");
//						
//					} catch (SecurityException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
//					vaciarCampos();
//				}else {
//					JOptionPane.showMessageDialog(null, "El correo electronico no es correcto", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
//					throw new ExcepcionImplicita("ERROR! El correo electronico no es correcto");
//				}
//				
//			}
//		});
		
		
		
		
		
		
		
		btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String n = txtNombre.getText();
				int ed = Integer.parseInt(txtEdad.getText());
				String m = txtMail.getText();
				
				String c = txtCon.getText();
				//FALTARIA PONER AQUI DE ALGUNA MANERA LO DE LOS PERMISOS
				Usuario u = new Usuario(n, ed, m, c, false);
				//AÑADIRLO AL MAPA DE CLAVE USUARIO Y VALOR EL CARRITO ASOCIADO A ESE USUARIO!
				Connection con = BD.initBD("SweetWear.db");
				BD.insertarUsuario(con, n, ed, m, c, false);
				BD.closeBD(con);
				JOptionPane.showMessageDialog(null, "Persona registrada correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
					
				//Logger Usuarios
				try {
					Handler handler = new FileHandler("Usuarios.log");
					handler.setFormatter(new SimpleFormatter());
					log.addHandler(handler);
					log.log(Level.INFO, "Se ha añadido un Usuario");
						
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				vaciarCampos();
				
			}
		});
		
		
		/**
		 * Botón que al clickar cierra la ventana actual y abre la anterior
		 */
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaActual.dispose();
				VentanaAnterior.setVisible(true);
			}
		});
		setVisible(true);		
		
		
		
	}
	private void vaciarCampos() {
		txtNombre.setText("");
		txtEdad.setText("");
		txtMail.setText("");
		txtCon.setText("");
		
	}
}
