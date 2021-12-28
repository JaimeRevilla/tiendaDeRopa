package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	public static Date fechaIni, fechaFin;


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
//		setBounds(500, 150, 600, 450);
		setSize(1650, 1080);
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
		
		panelCentralArriba = new JPanel();
		panelCentral.add(panelCentralArriba);
		
		
		panelCentralAbajo = new JPanel();
		panelCentralAbajo.setLayout(new GridLayout(3,2));
		panelCentral.add(panelCentralAbajo);
		
		
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
		
		btnFechaInicio = new JButton("FECHA INICIO");
		panelSur.add(btnFechaInicio);
		
		btnFechaFin = new JButton("FECHA FIN");
		panelSur.add(btnFechaFin);
		btnFechaFin.setVisible(false);

		
		btnEmpezarOferta = new JButton("APLICAR OFERTA");
		panelSur.add(btnEmpezarOferta);
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
		
		//INICIALIZAR LAS VARIABLE fechaIni y fechaFin;
		VentanaOfertas.fechaIni = null;
		VentanaOfertas.fechaFin = null;
		
		
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
				VentanaOfertas.fechaIni = fechaSeleccionada;
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
				VentanaOfertas.fechaFin = fechaSeleccionada;
				String inicio = lblFecha1.getText();
				String fin = lblFecha2.getText();
				long fechIni = VentanaOfertas.fechaIni.getTime();
				long fechFin = VentanaOfertas.fechaFin.getTime();
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
				
				System.out.println("Producto: " + producto);
				System.out.println("Porc: " + porc);
				
				
				
				if (!producto.equals("") && !porc.equals("")) {
					Runnable r1 = new Runnable() {
						
						@Override
						public void run() {
							
							boolean fin = false;
							while (fin == false) {
								long milis = System.currentTimeMillis();
								Date d = new Date(milis);
								String f = sdf.format(d);
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Date ini = VentanaOfertas.fechaIni;
								String iniString = sdf.format(ini);
								
								String anio = iniString.substring(0, 4);
								
								String mes = iniString.substring(5,7);
								
								String dia = iniString.substring(8, iniString.length());
								
	//							String producto = txtProducto.getText();
	//							String porc = txtPorcentaje.getText();
	//							
	//							System.out.println("Producto: " + producto);
	//							System.out.println("Porc: " + porc);
	//							
								
								
								if (d.getYear() == Integer.parseInt(mes) && d.getMonth() == Integer.parseInt(mes) && d.getDay() == Integer.parseInt(dia)) {
									Connection con = BD.initBD("SweetWear.db");
									boolean resul = false;
									try {
										resul = BD.existeProductoMismoNombre(con, producto);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if (resul) {
										try {
											BD.ponerProductoEnOferta(con, producto, Double.parseDouble(porc));
											BD.closeBD(con);
											fin = true;
										} catch (NumberFormatException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
										
								}
								
							}
							
							fin = false;
							while (fin == false) {
								long milis = System.currentTimeMillis();
								Date d = new Date(milis);
								String f = sdf.format(d);
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Date fi = VentanaOfertas.fechaFin;
								String iniString = sdf.format(fi);
								
								String anio = iniString.substring(0, 4);
								
								String mes = iniString.substring(5,7);
								
								String dia = iniString.substring(8, iniString.length());
								
								String producto = txtProducto.getText();
								String porc = txtPorcentaje.getText();
								
								
								if (d.getYear() == Integer.parseInt(mes) && d.getMonth() == Integer.parseInt(mes) && d.getDay() == Integer.parseInt(dia)) {
									Connection con = BD.initBD("SweetWear.db");
									boolean resul = false;
									try {
										resul = BD.existeProductoMismoNombre(con, producto);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if (resul) {
										try {
											BD.finOferta(con, producto, Double.parseDouble(porc));
											BD.closeBD(con);
											fin = true;
										} catch (NumberFormatException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
								
						}
							
					}
					};
					Thread t1 = new Thread(r1);
					t1.start();
				}else 
					JOptionPane.showMessageDialog(null, "Nombra un producto y su descuento correspondiente", "ERROR", JOptionPane.ERROR_MESSAGE);

				
			}
		});
	}

}
