package tiendaDeRopa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel panelIzq, panelSur, panelNorte, panelDrc;
	private JButton btnVolverAtras, btnIrParaAdelante, btnPrincipio, btnIniciarSesion, btnCarrito;
	private JLabel lblHombre, lblMujer;
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 150, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		panelIzq = new JPanel();
		contentPane.add(panelIzq, BorderLayout.WEST);
		
		lblMujer = new JLabel("MUJER");
		panelIzq.add(lblMujer);
		lblMujer.setBackground(new Color(240, 240, 240));
		
		lblHombre = new JLabel("HOMBRE");
		panelIzq.add(lblHombre);
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		btnVolverAtras = new JButton("<--");
		panelNorte.add(btnVolverAtras);
		
		btnIrParaAdelante = new JButton("-->");
		panelNorte.add(btnIrParaAdelante);
		
		btnPrincipio = new JButton("SWEET WEAR ");
		panelNorte.add(btnPrincipio);
		
		btnIniciarSesion = new JButton("IniciarSesion");
		panelNorte.add(btnIniciarSesion);
		
		btnCarrito = new JButton("Carrito");
		panelNorte.add(btnCarrito);
		
		panelDrc = new JPanel();
		contentPane.add(panelDrc, BorderLayout.EAST);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		
		JButton btnImagenCentral = new JButton("IMAGEN CENTRAL");
		
		//EVENTOS
		btnImagenCentral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panelCentral.add(btnImagenCentral);
	}

}
