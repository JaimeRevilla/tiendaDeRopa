package ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


//import tiendaDeRopa.Carrito.GestorCarrito;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class VentanaCarrito extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaCarrito frame = new VentanaCarrito();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	//JTable de la ventana
	private JTable uProductos;
	/*
	 * Creacion de la ventana
	 */
	public VentanaCarrito(JFrame va) {
		//Configuracion general de la ventana
		setTitle("SWEET WEAR");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaAnterior = va;
		ventanaActual = this;
		//Creacion de los componentes y contenedores 
		JPanel uCarrito = new JPanel();
		uProductos = new JTable();
		JButton btnCargarPago = new JButton("Cargar_pago ");
		JButton btnGuardarPago = new JButton("Guardar_Pago ");
		//Asigancion de los botones 
		uCarrito.add(btnCargarPago);//El boton es uCarrito
		uCarrito.add(btnGuardarPago);
		getContentPane().add(new JScrollPane(uCarrito), BorderLayout.CENTER);
		getContentPane().add(uCarrito, BorderLayout.SOUTH);
		
		//Eventos cargar y guardar
		btnCargarPago.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//btncargarPago();
			}
		});
		btnGuardarPago.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//btnguardarPago();
			}
		});
		
		
		
		
		//No entiendo @JONPAR
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
		
	}
}
