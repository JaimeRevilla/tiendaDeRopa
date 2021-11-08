package tiendaDeRopa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Label;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class CuentaUsuario extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CuentaUsuario frame = new CuentaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CuentaUsuario() {
		setTitle("SWEET WEAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CUENTA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 73, 30);
		contentPane.add(lblNewLabel);
		
		Label label = new Label("NOMBRE");
		label.setBounds(10, 70, 62, 22);
		contentPane.add(label);
		
		Label label_1 = new Label("DIRECCION");
		label_1.setBounds(10, 114, 62, 22);
		contentPane.add(label_1);
		
		Label label_2 = new Label("EMAIL");
		label_2.setBounds(10, 164, 62, 22);
		contentPane.add(label_2);
		
		Label label_3 = new Label("CONTRASE\u00D1A");
		label_3.setBounds(10, 213, 73, 22);
		contentPane.add(label_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(94, 70, 122, 20);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(94, 114, 122, 20);
		contentPane.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(94, 164, 122, 20);
		contentPane.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(94, 215, 122, 20);
		contentPane.add(textPane_3);
	}
}
