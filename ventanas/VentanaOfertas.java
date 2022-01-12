package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;

import clases.BD;


public class VentanaOfertas extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	private JButton btnVolver, btnEmpezarOferta, btnFechaInicio, btnFechaFin;
	private JPanel panelCentral, panelSur, panelCentralArriba, panelCentralAbajo;
	private JLabel lblFecha1, lblFecha2, lblProducto, lblPorcentaje;
	private JTextField txtProducto, txtPorcentaje;
	private SimpleDateFormat sdf;
	private JCalendar calendar;
	private Date fI, fF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaOfertas frame = new VentanaOfertas(null);
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
	public VentanaOfertas(JFrame va) {
		//PROPIEDADES DE LA VENTANA
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setBounds(500, 150, 600, 450);
		//setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ventanaAnterior = va;
		ventanaActual = this;
		
		
		
		
		//CREACION DE LOS PANELES
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(2, 1));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setBackground(new Color(122,217,196));
		
		panelCentralArriba = new JPanel();
		panelCentral.add(panelCentralArriba);
		panelCentralArriba.setBackground(new Color(122,217,196));
		
		panelCentralAbajo = new JPanel();
		panelCentralAbajo.setLayout(new GridLayout(3,2));
		panelCentral.add(panelCentralAbajo);
		panelCentralAbajo.setBackground(new Color(122,217,196));
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		//SimpleDayFormtat y JCalendar
		sdf = new SimpleDateFormat("yyyy-MM-dd");
				
		calendar = new JCalendar();
		panelCentralArriba.add(calendar);
		
		calendar.setTodayButtonVisible(true);
		calendar.setTodayButtonText("Fecha Actual");
		calendar.setWeekOfYearVisible(false);
		calendar.setMaxDayCharacters(2);
		
		Date fechaMin = new Date(System.currentTimeMillis());
		String fecha = "2022-12-12";
		Date fechaMax;
		try {
			fechaMax = sdf.parse(fecha);
			calendar.setSelectableDateRange(fechaMin, fechaMax);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Date fechaNeutra = new Date(System.currentTimeMillis());
		calendar.setDate(fechaNeutra);
		
	
		
		//CREACION DE LOS COMPONENTES
		btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		VentanaPrincipal.ponerFotoABoton(btnVolver, "imagenes\\IconoSalir.png", 30, 30, 30, 30);
		
		btnFechaInicio = new JButton("FECHA INICIO");
		panelSur.add(btnFechaInicio);
		VentanaPrincipal.ponerFotoABoton(btnFechaInicio, "imagenes\\IconoFechaInicio.png", 30, 30, 30, 30);

		
		btnFechaFin = new JButton("FECHA FIN");
		panelSur.add(btnFechaFin);
		VentanaPrincipal.ponerFotoABoton(btnFechaFin, "imagenes\\IconoFechaFin.png", 30, 30, 30, 30);
		btnFechaFin.setVisible(false);

		
		btnEmpezarOferta = new JButton("APLICAR OFERTA");
		panelSur.add(btnEmpezarOferta);
		VentanaPrincipal.ponerFotoABoton(btnEmpezarOferta, "imagenes\\IconoOferta.png", 30, 30, 30, 30);
		btnEmpezarOferta.setVisible(false);
		
		
		lblFecha1 = new JLabel("FECHA INICIAL DE LAS REBAJAS: ");
		lblFecha2 = new JLabel("FECHA FIN DE LAS REBAJAS: ");
		lblProducto = new JLabel("NOMBRE DEL PRODUCTO: ");
		lblPorcentaje = new JLabel("PORCENTAJE DE OFERTA (SOBRE 1): ");
		
		txtProducto = new JTextField(50);
		txtPorcentaje = new JTextField();
		
		panelCentralAbajo.add(lblFecha1);
		panelCentralAbajo.add(lblFecha2);
		panelCentralAbajo.add(lblProducto);
		panelCentralAbajo.add(txtProducto);
		panelCentralAbajo.add(lblPorcentaje);
		panelCentralAbajo.add(txtPorcentaje);
		
		//-----------------------------------------------------------------------------------------------------------
		ventanaActual.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal.guardarMapaUsuariosEnFicheroDeTexto();
				VentanaPrincipal.guardarMapaPedidosEnFicheroDeTexto();
				VentanaPrincipal.guardarListaHistorialBusqueda();
				VentanaPrincipal.guardarMapaSatisfaccion();
				VentanaPrincipal.log.log(Level.INFO, "Los ficheros de informaci�n han sido actualizados correctamente");
			
			}
		});
		
		//---------------------------------------------------------------------------------------------------------------
		
		//EVENTOS
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
				
			}
		});
		
		btnFechaInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date fechaSeleccionada = calendar.getDate();
				fI = fechaSeleccionada;
				String fechaInicio = sdf.format(fechaSeleccionada);
				lblFecha1.setText("FECHA INICIAL DE LAS REBAJAS: " + fechaInicio);
				String fin = lblFecha2.getText();
				if (!fin.equals("FECHA INICIAL DE LAS REBAJAS: ")) {
					btnFechaFin.setVisible(true);
					btnFechaInicio.setVisible(false);
				}
					
					
				
				
			}
		});
		
		btnFechaFin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date fechaSeleccionada = calendar.getDate();
				String fechaFin = sdf.format(fechaSeleccionada);
				fF = fechaSeleccionada;
				String inicio = lblFecha1.getText();
				String fin = lblFecha2.getText();
				long fechIni = fI.getTime();
				long fechFin = fF.getTime();
				if (fechIni >= fechFin) {
					JOptionPane.showMessageDialog(null, "Selecciona una fecha mayor a la inicial", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					lblFecha2.setText("FECHA FINAL DE LAS REBAJAS: " + fechaFin);
					if (!inicio.equals("FECHA INICIAL DE LAS REBAJAS: ") && !fin.equals("FECHA FINAL DE LAS REBAJAS: ") ) {
						btnEmpezarOferta.setVisible(true);
						btnFechaFin.setVisible(false);
					}
				}
			}
		});
		
		btnEmpezarOferta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String producto = txtProducto.getText();
				String porc = txtPorcentaje.getText();
				
				if (!producto.equals("") && !porc.equals("")) {
					Connection con = BD.initBD("SweetWear.db");
					String product = txtProducto.getText();
					boolean res = false;
					try {
						res = BD.existeProductoMismoNombre(con, product);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (res == true) {
						Date fA = new Date(System.currentTimeMillis());
						if(fA.getTime()>= fI.getTime() && fA.getTime()<= fF.getTime()) {	
							try {
								BD.ponerProductoEnOferta(con, producto, Double.parseDouble(porc));
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							BD.closeBD(con);
							VentanaPrincipal.log.log(Level.INFO, "Se ha aplicado la oferta correctamente");
							JOptionPane.showMessageDialog(null, "OFERTA APLICADA CORRECTAMENTE!!!", "OFERTA", JOptionPane.NO_OPTION);
						}	
					}else
						JOptionPane.showMessageDialog(null, "El producto no existe!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else 
					JOptionPane.showMessageDialog(null, "Nombra un producto y su descuento correspondiente", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

}