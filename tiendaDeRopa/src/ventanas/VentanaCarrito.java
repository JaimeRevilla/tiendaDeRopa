package ventanas;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Producto;
import examen.parc202112.BaseDatos;

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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class VentanaCarrito extends JFrame {

	private JPanel contentPane;
	private static JFrame ventanaActual;
	private JFrame ventanaAnterior;
	private JTable uProductos;//JTable de la ventana
	private DefaultTableModel tmPedidos1;
	public static TreeMap<String, ArrayList<Producto>> tmPedidos;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCarrito frame = new VentanaCarrito(ventanaActual);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
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
