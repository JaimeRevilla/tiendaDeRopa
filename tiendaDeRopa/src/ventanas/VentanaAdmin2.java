package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import clases.BD;
import clases.Usuario;

public class VentanaAdmin2 extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaAnterior, ventanaActual;
	private JButton btnVolver, btnTextArea;
	private JPanel panelSur, panelCentro, panelArriba, panelArribaDrc, panelArribaIzq;
	private JTextArea textAreaUsuarios;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin2 frame = new VentanaAdmin2(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaAdmin2(JFrame va) {
		Connection con = BD.initBD("SweetWear.db");
		VentanaPrincipal.tmUsuarios = BD.obtenerMapaUsuarios(con);
		BD.closeBD(con);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ventanaAnterior = va;
		ventanaActual = this;
		
		panelSur = new JPanel();
		panelSur.setBackground(Color.CYAN);
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		
		btnTextArea = new JButton("MOSTRAR USUARIOS");
		panelSur.add(btnTextArea);
		
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 1, 0, 0));
		panelCentro.setBackground(Color.CYAN);
		
		panelArriba = new JPanel();
		panelArriba.setLayout(new GridLayout(0, 2, 0, 0));
		panelCentro.add(panelArriba);
		
		panelArribaIzq = new JPanel();
		panelArribaIzq.setLayout(new GridLayout(0, 2));
		panelArriba.add(panelArribaIzq);
		panelArribaIzq.setBackground(Color.CYAN);
		
		panelArribaDrc = new JPanel();
		panelArribaDrc.setLayout(new GridLayout(2, 1));
		panelArriba.add(panelArribaDrc);
		panelArribaDrc.setBackground(Color.CYAN);
		
		textAreaUsuarios = new JTextArea();
		textAreaUsuarios.setText("");
		panelArribaDrc.add(textAreaUsuarios);
		
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				VentanaPrincipal.cargarMapaUsuariosDeFicheroDeTexto();
			}
				
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				//guardarMapaUsuariosEnFicheroDeTexto();
			}
			
			
			
		});
		
		
		
		
		//EVENTOS
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
				
			}
		});
		
		btnTextArea.addActionListener(new ActionListener() {
			//ARREGLAR ESTO !!!!!
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = "";
				for (String clave: VentanaPrincipal.tmUsuarios.keySet()) {
					texto = texto + clave + "\t"+ VentanaPrincipal.tmUsuarios.get(clave) + "\n";
				}
				textAreaUsuarios.setText(texto);
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	private void volver() {
		ventanaActual.dispose();
		ventanaAnterior.setVisible(true);
	}


}
