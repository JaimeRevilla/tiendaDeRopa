package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

	
	private JPanel contentPane;
	private JPanel panelCentral, panelSur;
	private JLabel lblNombre, lblEdad, lblMail, lblCon;
	private JPasswordField txtCon;
	private JTextField txtNombre, txtEdad, txtMail;
	private JButton btnRegistrar, btnVolver;
	private static JFrame ventanaActual;
	public static String ermail = "[A-Za-z]{1,}[0-9]{0,}[A-Za-z]{0,}@[A-Za-z]{1,}.[a-z]{2,3}";

	private JFrame ventanaAnterior;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro(null);
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
	public VentanaRegistro(JFrame va) {

		Connection con = BD.initBD("SweetWear.db");
		ventanaAnterior = va;
		ventanaActual = this;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 600, 450);
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
		VentanaPrincipal.ponerFotoABoton(btnRegistrar, "imagenes\\IconoRegistro.png", 30, 30, 30, 30);
		btnVolver = new JButton("VOLVER");
		VentanaPrincipal.ponerFotoABoton(btnVolver, "imagenes\\IconoSalir.png", 30, 30, 30, 30);
		
		panelSur.add(btnVolver);
		panelSur.add(btnRegistrar);
		
		panelSur.setBackground(Color.CYAN);
		panelCentral.setBackground(Color.CYAN);
		
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
		
		btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String n = txtNombre.getText();
				Connection con = BD.initBD("SweetWear.db");
				boolean existe = false;
				try {
					existe = BD.existeUsuarioConEseNombre(con, n);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (!VentanaPrincipal.tmUsuarios.containsKey(n) && existe == false) {
					int ed = Integer.parseInt(txtEdad.getText());
					//String ermail = "[A-Za-z]{1,20}@[A-Za-z]{1,20}.[a-z]{3}";
					String m = txtMail.getText();
					boolean correctoCorreo = Pattern.matches(ermail, m);
					if (correctoCorreo) {
						String c = txtCon.getText();
						Usuario u = new Usuario(n, ed, m, c, false);
						VentanaPrincipal.tmUsuarios.put(n, u);
						BD.insertarUsuario(con, n, ed, m, c, false);
						BD.closeBD(con);
						JOptionPane.showMessageDialog(null, "Persona registrada correctamente", "REGISTRO CORRECTO", JOptionPane.INFORMATION_MESSAGE);
						//Logger Usuarios
						VentanaPrincipal.log.log(Level.INFO, "Se ha añadido un Usuario");;
						vaciarCampos();
					}else {
						JOptionPane.showMessageDialog(null, "El correo electronico no es correcto", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
						vaciarCampos();
						throw new ExcepcionImplicita("ERROR! El correo electronico no es correcto");
					}
				}else
					JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre!!!", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
					vaciarCampos();

			}
			
		});	
		
		/**
		 * Botón que al clickar cierra la ventana actual y abre la anterior
		 */
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
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
