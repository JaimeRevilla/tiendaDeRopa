package tiendaDeRopa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import examenResuelto.parc201811.examenResuelto.parc201811.BD;
import examenResuelto.parc201811.examenResuelto.parc201811.CentroEd;
import examenResuelto.parc201811.examenResuelto.parc201811.Contador;
import examenResuelto.parc201811.examenResuelto.parc201811.Datos;
import examenResuelto.parc201811.examenResuelto.parc201811.Tabla;
import examenResuelto.parc201811.examenResuelto.parc201811.VentanaDatos;
import examenResuelto.parc201811.examenResuelto.parc201811.VentanaDatos.MentoraCentro;
import tiendaDeRopa.Carrito.GestorCarrito;

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
	//JTable de la ventana
	private JTable uProductos;
	/*
	 * Creacion de la ventana
	 */
	public CarritoUsuario() {
		//Configuracion general de la ventana
		setTitle("SWEET WEAR");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				btncargarPago();
			}
		});
		btnGuardarPago.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnguardarPago();
			}
		});
		//NUEVO
		//Cierra y centra la  ventana en el escritorio
		/*setLocationRelativeTo(null);
		//Asigna una tabla de datos a la JTable principal de la ventana
		public void setTable(Tabla, tabla) {
			uProductos.setModel(tabla.getTableModel);
		}
		
		//Boton de guardarPago
		private void btnguardaPago() {
			Connection carr = BD.initBD( "centrosEd" );
			Statement stat = BD.usarCrearTablasBD( conn );
			if (stat==null) return; // Error
			for (CentroEd centro : Datos.centros.values()) {
				if (centro.getContSesiones().get()>0) { // S�lo se actualizan los centros con sesiones
					// Tabla centros
					boolean existe = BD.centroSelect( stat, centro.getCodigo() );
					if (existe) {
						BD.centroUpdate( stat, centro );
					} else {
						BD.centroInsert( stat, centro );
					}
					// Tabla sesiones
					for (int numSes=0; numSes<6; numSes++) {
						int numEsts = BD.sesionSelect( stat, centro.getCodigo(), numSes+1 );
						if (numEsts==-1) {
							BD.sesionInsert( stat, centro.getCodigo(), numSes+1, centro.getEstudPorSesion()[numSes] );
						} else {
							BD.sesionUpdate( stat, centro.getCodigo(), numSes+1, centro.getEstudPorSesion()[numSes] );
						}
					}
				}
			}
		}

		// Click bot�n de b�squeda de mentora
		private void clickBuscarMentora() {
			String aBuscar = JOptionPane.showInputDialog( this, "Introduce email", "B�squeda de mentora", JOptionPane.QUESTION_MESSAGE );
			if (aBuscar==null || aBuscar.isEmpty()) return;
			ArrayList<MentoraCentro> listaMentorasCentros = new ArrayList<>();
			try {
				Tabla mentoras = Tabla.processCSV( VentanaDatos.class.getResource( "Mentoring2018.csv" ).toURI().toURL() );
				int columnaCentro = mentoras.getColumnWithHeader( "COD", true );
				int columnaEmail = mentoras.getColumnWithHeader( "email", false );
				for (int fila=0; fila<mentoras.size(); fila++) {
					try {
						String codCentro = mentoras.get( fila, columnaCentro );
						String email = mentoras.get( fila,  columnaEmail );
						if (!email.isEmpty() && !codCentro.isEmpty()) {
							MentoraCentro mc = new MentoraCentro( email, codCentro );
							int yaEsta = listaMentorasCentros.indexOf( mc );
							if (yaEsta==-1)
								listaMentorasCentros.add( mc );  // Nueva mentora-centro: meterla en la lista
							else
								listaMentorasCentros.get( yaEsta ).getContSesiones().inc();  // Ya estaba: incrementar contador de sesiones
						}
					} catch (Exception e) {}
				}
				listaMentorasCentros.sort( new Comparator<MentoraCentro>() {  // Ordena la lista por emails y luego por centros
					@Override
					public int compare(MentoraCentro o1, MentoraCentro o2) {
						return (o1.email+o1.codCentro).compareTo( o2.email+o2.codCentro );
					}
				});
				for (MentoraCentro mc : listaMentorasCentros) System.out.println( mc );  // Visualiza las mentoras en consola (a efectos de entender la estructura en el examen)
				// T4 - B�squeda recursiva
				contLlamadas = 0;
				int posi = buscarMentoraRec( listaMentorasCentros, aBuscar, 0, listaMentorasCentros.size()-1 );
				String mens = "";
				while (posi>=0 && posi<listaMentorasCentros.size() && listaMentorasCentros.get(posi).getEmail().equals( aBuscar )) {
					mens = mens + listaMentorasCentros.get(posi).getCodCentro() + " - " + listaMentorasCentros.get(posi).getContSesiones() + "\n";
					posi++;
				}
				if (mens.isEmpty()) mens = "No encontrada";
				JOptionPane.showMessageDialog( this, mens, "Sesiones de mentora " + aBuscar, JOptionPane.INFORMATION_MESSAGE );
				System.out.println( "N�mero de llamadas recursivas: " + contLlamadas + " (tama�o de la lista = " + listaMentorasCentros.size() + ")" );
			} catch (Exception e) { e.printStackTrace(); }
		}
		
			private static int contLlamadas = 0;
			// T4 - M�todo(s) para recursividad
			private int buscarMentoraRec( ArrayList<MentoraCentro> listaMC, String emailABuscar, int desde, int hasta ) {
				contLlamadas++;
				if (desde>=hasta) {  // Caso base: encontrado elemento o no
					if (desde==hasta && listaMC.get(desde).getEmail().equals(emailABuscar)) return desde;  // B�squeda exitosa
					return -1;  // B�squeda no exitosa
				} else {
					int medio = (desde + hasta) / 2;
					MentoraCentro mc = listaMC.get( medio );
					if (mc.getEmail().compareTo(emailABuscar)>=0) {  // Email del medio >= email a buscar
						return buscarMentoraRec( listaMC, emailABuscar, desde, medio );
					} else {  // Email del medio < email a buscar
						return buscarMentoraRec( listaMC, emailABuscar, medio+1, hasta );
					}
				}
			}
		*/
			/** Clase interna de gesti�n de mentora-centro con contador de sesiones de esa mentora en ese centro */
			/*public static class MentoraCentro {
				public String email;
				public String codCentro;
				public Contador contSesiones;
				/** Crea un nuevo objeto mentora-centro con contador de sesiones a uno
				 * @param email	Email de la mentora
				 * @param codCentro	C�digo del centro
				 */
				/*public MentoraCentro(String email, String codCentro) {
					super();
					this.email = email;
					this.codCentro = codCentro;
					this.contSesiones = new Contador(1);
				}
				public Contador getContSesiones() { return contSesiones; }
				public String getEmail() { return email; }
				public String getCodCentro() { return codCentro; }
				@Override
				public boolean equals(Object obj) {
					if (!(obj instanceof MentoraCentro)) return false;
					MentoraCentro mc = (MentoraCentro) obj;
					return mc.codCentro.equals(codCentro) && mc.email.equals(email);
				}
				@Override
				public String toString() {
					return "{" + email + "-" + codCentro + " : " + contSesiones + "}";
				}
			}

		// Click bot�n de carga de feedback
		private void clickCargaFeedback() {
			// C�lculo de datos en funci�n del seguimiento del mentoring
			seguimientoSesiones();
		}
		*/
		
		
		
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
