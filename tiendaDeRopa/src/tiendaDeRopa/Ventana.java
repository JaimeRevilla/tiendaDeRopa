package tiendaDeRopa;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Ventana extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	public Ventana() {
		setTitle("SWEET WEAR");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BIENVENIDO A SWEET WEAR");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 464, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 63, 81, 32);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CONTRASE\u00D1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 106, 106, 32);
		getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(126, 71, 348, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(126, 114, 348, 20);
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("ACCEDER");
		btnNewButton.setBounds(202, 177, 89, 23);
		getContentPane().add(btnNewButton);
	}
}
