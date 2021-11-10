package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Producto;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentro, panelSur;
	private JButton btnMostarMapa, btnVolver, btnAniadir;
	private JComboBox<Producto> comboProductos;
	private JLabel lblPrecio, lblStock, lblMarca, lblNombre;
	private JTextField txtPrecio, txtStock, txtMarca;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin frame = new VentanaAdmin();
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
	public VentanaAdmin() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2));
		panelCentro.setBackground(Color.CYAN);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		btnMostarMapa = new JButton("Mapa");
		btnVolver = new JButton("VOLVER");
		btnAniadir = new JButton("AÑADIR PRODUCTO");
		
		
		comboProductos = new JComboBox<Producto>();
		
		
		lblNombre = new JLabel("NOMBRE: ");
		lblPrecio = new JLabel("PRECIO: ");
		lblStock = new JLabel("STOCK: ");
		lblMarca = new JLabel("MARCA: ");
		
		txtPrecio = new JTextField();
		txtStock = new JTextField();
		txtMarca = new JTextField();
		txtPrecio.setColumns(10);
		txtStock.setColumns(10);
		txtMarca.setColumns(10);
		
		textArea = new JTextArea();
		
		panelCentro.add(lblNombre);
		panelCentro.add(comboProductos);
		panelCentro.add(lblPrecio);
		panelCentro.add(txtPrecio);
		panelCentro.add(lblStock);
		panelCentro.add(txtStock);
		panelCentro.add(lblMarca);
		panelCentro.add(txtMarca);
		
		panelSur.add(btnVolver);
		panelSur.add(btnAniadir);
		panelSur.add(btnMostarMapa);
		
		//EVENTOS
		btnAniadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//CREAR UNA BASE DE DATOS PARA PRODUCTO
				//METER EL PRODUCTO EN LA BASE DE DATOS
				//METERLOS EN LOS DEMAS SITIOS QUE SEA NECESARIO
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//QUE VUELVA A LA VENTANA DESDE LA QUE HA VENIDO!
			}
		});
		
		
		btnMostarMapa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = "";
				for (String clave: VentanaPrincipal.tmPedidos.keySet()) {
					texto = texto + clave + "\n" + "\t";
					ArrayList<Producto> productos = VentanaPrincipal.tmPedidos.get(clave);
					for(Producto p: productos) {
						texto = texto + p + "\n";
					}
				}
				textArea.setText(texto);
				panelCentro.add(textArea);
				
				//METODO QUE MOSTRARA EL MAPA DE LO QUE SEA EN UN TEXT AREA!!
				
			}
		});
		
		
	}

}
