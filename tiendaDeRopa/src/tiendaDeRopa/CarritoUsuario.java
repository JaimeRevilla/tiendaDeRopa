package tiendaDeRopa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JButton;

public class CarritoUsuario extends JFrame {

	private JPanel contentPane;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarritoUsuario frame = new CarritoUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CarritoUsuario() {
		setTitle("SWEET WEAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CARRITO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 82, 19);
		contentPane.add(lblNewLabel);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(407, 11, 17, 239);
		contentPane.add(scrollBar);
		
		JButton btnNewButton = new JButton("PAGAR ");
		btnNewButton.setBounds(174, 208, 89, 23);
		contentPane.add(btnNewButton);
	}
	/*
	 * Guardar el pago en un fichero
	 */
	public void guardarPago(){ 	
		try {
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("guardadodeprueba.dat"));
			salida.writeObject(this.carritos.getCarritos());
			salida.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 *Cargar el pago en un fichero
	 */
	public void cargarPago() {	
		try {
			ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("guardadodeprueba.dat"));
			ArrayList<Carrito> entrada1 = (ArrayList<Carrito>)entrada.readObject();
			entrada.close();
			carritos.setCarritos(entrada1);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
