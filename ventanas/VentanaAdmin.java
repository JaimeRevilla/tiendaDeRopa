package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import clases.BD;
import clases.PanelConImagenDeFondo;
import clases.Pantalon;
import clases.Producto;
import clases.TipoCalcetines;
import clases.TipoCamiseta;
import clases.TipoPantalon;
import clases.TipoProductos;
import clases.TipoSudadera;
import clases.TipoZapato;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;
	public static JPanel panelCentro, panelSur, panelArriba, panelArribaIzq, panelArribaDrc, panelNorte;
	private JButton btnMostarMapa, btnVolver, btnAniadir, btnElegirEsePanel, btnOfertas, btnEstadisticas;
	public static JComboBox<String> comboProductos;
	public static JLabel lblPrecio, lblStock, lblMarca, lblNombre, lblColor, lblFoto;
	public static JTextField txtPrecio, txtStock, txtMarca, txtColor, txtColorCordones, txtGoretex;
	public static JLabel lblTipoCalcetines, lblTipoSudadera, lblTipoCamiseta, lblTipoPantalon, lblColorCordones, lblGoretex, lblTipoZapato;
	public static JComboBox<String> comboTipoCalcetines, comboTipoSudaderas, comboTipoCamisetas, comboTipoPantalones, comboTipoZapatos;
	private JTextArea textArea;
	private JFrame ventanaActual, ventanaAnterior;
	private JTable tablaProductos;
	private DefaultTableModel modeloTablaProductos;
	private JScrollPane scrollTablaProductos;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnExit;
	private JMenuItem mntmCargarArchivo;
	private JMenuItem mntmCerrarAplicacion;
	public static JPanel panelCalcetines, panelSudadera, panelPantalones, panelCamiseta, panelZapato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin frame = new VentanaAdmin(null);
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
	public VentanaAdmin(JFrame va) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1650, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ventanaAnterior = va;
		ventanaActual = this;
		
		panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 1, 0, 0));
		panelCentro.setBackground(Color.CYAN);
		
		panelNorte = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelNorte.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setBackground(Color.CYAN);
		
		menuBar = new JMenuBar();
		panelNorte.add(menuBar);
		
		mnFile = new JMenu("CARGAR FOTO");
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);
		
		mntmCargarArchivo = new JMenuItem("CARGAR FOTO");
		mntmCargarArchivo.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmCargarArchivo);
		
		mnExit = new JMenu("SALIR");
		mnExit.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnExit);
		
		mntmCerrarAplicacion = new JMenuItem("SALIR");
		mntmCerrarAplicacion.setHorizontalAlignment(SwingConstants.LEFT);
		mnExit.add(mntmCerrarAplicacion);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setBackground(Color.CYAN);
		
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
		
		//--------------------------------------------------------
//		panelCalcetines = new JPanel();
//		panelCalcetines.setLayout(new GridLayout(0, 2));
//		panelArriba.add(panelCalcetines);
//		panelCalcetines.setBackground(Color.CYAN);
		
		
		
		//---------------------------------------------------------------
		btnMostarMapa = new JButton("USUARIOS");
		btnVolver = new JButton("VOLVER");
		btnAniadir = new JButton("AÑADIR PRODUCTO");
		btnElegirEsePanel = new JButton("CREAR UN PRODUCTO DE ESE TIPO");
		btnOfertas = new JButton("OFERTAS");
		btnEstadisticas = new JButton("ESTADISTICAS");
		
		
		
//		textArea = new JTextArea();
//		panelArribaDrc.add(textArea);
		lblFoto = new JLabel();
		panelArribaDrc.add(lblFoto);
		
		//----------------------------------------------------------------------
		//CARGAR TODOS LOS COMBOS
		comboProductos = new JComboBox<>();
		for (TipoProductos s: TipoProductos.values()) {
			String n = String.valueOf(s);
			comboProductos.addItem(n);
		}
		
		comboTipoCalcetines = new JComboBox<>();
		for (TipoCalcetines s : TipoCalcetines.values()) {
			String n = String.valueOf(s);
			comboTipoCalcetines.addItem(n);
		}
		
		comboTipoSudaderas = new JComboBox<>();
		for (TipoSudadera s : TipoSudadera.values()) {
			String n = String.valueOf(s);
			comboTipoSudaderas.addItem(n);
		}
		
		comboTipoCamisetas = new JComboBox<>();
		for (TipoCamiseta s : TipoCamiseta.values()) {
			String n = String.valueOf(s);
			comboTipoCamisetas.addItem(n);
		}

		comboTipoPantalones = new JComboBox<>();
		for (TipoPantalon s : TipoPantalon.values()) {
			String n = String.valueOf(s);
			comboTipoPantalones.addItem(n);
		}
		
		comboTipoZapatos = new JComboBox<>();
		for (TipoZapato s : TipoZapato.values()) {
			String n = String.valueOf(s);
			comboTipoZapatos.addItem(n);
		}
		
		
		//SEGURAMENTE ESTO HABRA QUE METERLO EN ALGUN HILO SUPONGO
		
		lblNombre = new JLabel("NOMBRE");
		panelArribaIzq.add(lblNombre);
		panelArribaIzq.add(comboProductos);
		
		
		panelSur.add(btnVolver);
		panelSur.add(btnAniadir);
		panelSur.add(btnMostarMapa);
		panelSur.add(btnElegirEsePanel);
		panelSur.add(btnOfertas);
		panelSur.add(btnEstadisticas);
		
		
		
		/**
		 * CREACION DE LA TABLA
		 */
		
		//CUANDO HABRA LA VENTANA TENDRAS QUE CARGARSE LOS PRODUCTOS QUE HAY EN LA BASE DE DATOS!!!
		//String nombres[] = {"ID","COLOR","TIPO PRODUCTO","PRECIO", "STOCK", "MARCA", "RUTA FOTO"};
		
		modeloTablaProductos = new DefaultTableModel();
		Vector<String> cabeceras = new Vector<String>( Arrays.asList( "ID","COLOR","TIPO PRODUCTO","PRECIO", "STOCK", "MARCA", "RUTA FOTO") );
		modeloTablaProductos = new DefaultTableModel(  
			new Vector<Vector<Object>>(),  
			cabeceras  
		) {
			public boolean isCellEditable(int row, int column) {
				if(column==0)
					return false;
				return true;
			}
			
		};
		
		Connection con = BD.initBD("SweetWear.db");
		ArrayList<Producto> al = BD.getTienda(con);
		BD.closeBD(con);
		
//		try {
//			cod = BD.contarProductosTienda(con);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		//NO FUNCIONA EL CODIGO DEL PRODUCTO --> SALE UNO QUE NO TIENE QUE SALIR
		for (Producto p: al)
			modeloTablaProductos.addRow( new Object[] { p.getCodigo()-1, p.getColor(), p.getNombre(), p.getPrecio(), p.getStock(), p.getMarca(), p.getRutaFoto() } );
			
			
		
		
		tablaProductos = new JTable(modeloTablaProductos);
		
		tablaProductos.addMouseListener(new MouseAdapter() {
			
			
			
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount()== 3) {
					JOptionPane.showMessageDialog(null, "Para borrar un producto, pulse el raton sobre el producto a borrar con ALT pulsado (Solamente borre los nuevos productos)", "ADVERTENCIA!!!", JOptionPane.NO_OPTION);
					if (e.isAltDown()) {
						int fil = tablaProductos.getSelectedRow();
						if (fil != -1) {
							int cod = (int) modeloTablaProductos.getValueAt(fil, 0);
							Connection con = BD.initBD("SweetWear.db");
							BD.eliminarProductoTienda(con, cod);
							modeloTablaProductos.removeRow(fil);
						}
					}
				}
			}
	
		});
		
		tablaProductos.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
//				int fil = e.getFirstRow();
//				//int codigo = (int) modeloTablaProductos.getValueAt(fil, 0);
//				String cod = String.valueOf(modeloTablaProductos.getValueAt(fil, 0));
//				int codigo = Integer.parseInt(cod);
//						
//				
//				String color =  (String) modeloTablaProductos.getValueAt(fil, 1);
//				String nombre =  (String) modeloTablaProductos.getValueAt(fil, 2);
//				double precio = (double) modeloTablaProductos.getValueAt(fil, 3);
//				int stock = (int) modeloTablaProductos.getValueAt(fil, 4);
//				String marca = (String) modeloTablaProductos.getValueAt(fil, 5);
//				String rutaFoto = (String) modeloTablaProductos.getValueAt(fil, 6);
//				
//				Connection con = BD.initBD("SweetWear.db");
//				
//				BD.modificarProductoTienda(con, codigo,  color, nombre, precio, stock, marca, rutaFoto);
//				BD.closeBD(con);
//				
			}
		});
		
		tablaProductos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(column == 0) {
					c.setBackground(Color.LIGHT_GRAY);
				}else {
					c.setBackground(Color.WHITE);
				}
				return c;
			}
		
			
			
		});
		
		
		
		JScrollPane scrollTablaProductos = new JScrollPane(tablaProductos);
		panelArribaDrc.add(scrollTablaProductos);
		
		
				
			
				
		//EVENTOS
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
			}
		});
		setVisible(true);
				
				//QUE VUELVA A LA VENTANA DESDE LA QUE HA VENIDO!
			
		
		
		btnMostarMapa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin2 v1 = new VentanaAdmin2(ventanaActual);
				ventanaActual.setVisible(false);
				v1.setVisible(true);
			}
		});
		
		btnOfertas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaOfertas v1 = new VentanaOfertas(ventanaActual);
				ventanaActual.setVisible(false);
				v1.setVisible(true);
				
				
			}
		});
		
		btnEstadisticas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaEstadisticas v1 = new VentanaEstadisticas(ventanaActual);
				ventanaActual.setVisible(false);
				v1.setVisible(true);
				
			}
		});
		
		mntmCerrarAplicacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JOptionPane.showMessageDialog(null, "PARA BORRAR UN PRODUCTO PULSE EL RATÓN SOBRE EL CODIGO DE LOS PRODUCTO EN LA TABLA 3 VECES", "ADVERTENCIA!!!", JOptionPane.NO_OPTION);

		
		mntmCargarArchivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("imagenes");
				int sel = fc.showOpenDialog(null);
				if(sel == JFileChooser.APPROVE_OPTION) {
					File ficheroSeleccionado = fc.getSelectedFile();
					ImageIcon im = new ImageIcon(ficheroSeleccionado.getAbsolutePath());
					im.setDescription(ficheroSeleccionado.getAbsolutePath());
					lblFoto.setIcon(im);
				}
				
				
			}
		});
		
		btnElegirEsePanel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = comboProductos.getSelectedIndex();
					if (pos != -1) {
						String selec = comboProductos.getItemAt(pos);
						if (selec == "CALCETINES") {
							VentanaAdmin.mostrarPanel(panelArribaIzq);
							lblTipoCalcetines = new JLabel("TIPO CALCETINES: ");
							panelArribaIzq.add(lblTipoCalcetines);
							panelArribaIzq.add(comboTipoCalcetines);
						
						}else if (selec == "SUDADERA") {
							VentanaAdmin.mostrarPanel(panelArribaIzq);
							lblTipoSudadera = new JLabel("TIPO SUDADERA: ");
							panelArribaIzq.add(lblTipoSudadera);
							panelArribaIzq.add(comboTipoSudaderas);
						
						}else if (selec == "CAMISETA") {
							VentanaAdmin.mostrarPanel(panelArribaIzq);
							lblTipoCamiseta = new JLabel("TIPO CAMISETA: ");
							panelArribaIzq.add(lblTipoCamiseta);
							panelArribaIzq.add(comboTipoCamisetas);
						}else if (selec == "PANTALON") {
							VentanaAdmin.mostrarPanel(panelArribaIzq);
							lblTipoPantalon = new JLabel("TIPO PANTALON: ");
							panelArribaIzq.add(lblTipoPantalon);
							panelArribaIzq.add(comboTipoPantalones);
						}else if (selec == "ZAPATOS") {
							//ME DA PROBLEMAS Y NO SE PORQUE
							VentanaAdmin.mostrarPanel(panelArribaIzq);
							lblColorCordones = new JLabel("COLOR DE LOS CORDONES: ");
							txtColorCordones = new JTextField();
							lblGoretex = new JLabel("GORETEX (true o false): ");
							txtGoretex = new JTextField();
							lblTipoZapato = new JLabel("TIPO ZAPATO: ");
							panelArribaIzq.add(lblColorCordones);
							panelArribaIzq.add(txtColorCordones);
							panelArribaIzq.add(lblGoretex);
							panelArribaIzq.add(txtGoretex);
							panelArribaIzq.add(lblTipoZapato);
							panelArribaIzq.add(comboTipoZapatos);
						}
					}			
			}
		});
		
		
		
		//EVENTOS
			btnAniadir.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					
						
					//CREAR UNA BASE DE DATOS PARA PRODUCTO
					//METER EL PRODUCTO EN LA BASE DE DATOS
					//METERLOS EN LOS DEMAS SITIOS QUE SEA NECESARIO (LA TABLA Y LA JLIST)
					int pos = comboProductos.getSelectedIndex();
					String nombre = comboProductos.getItemAt(pos);
					String color = txtColor.getText();
					double precio = Double.parseDouble(txtPrecio.getText());
					int stock = Integer.parseInt(txtStock.getText());
					String marca = txtMarca.getText();
					ImageIcon im = (ImageIcon)lblFoto.getIcon();
					
					if (im != null) {	
						System.out.println("1");
						String rutaFoto = im.getDescription();
						String rutaAdecuada = rutaFoto.substring(32, rutaFoto.length()); 
						
						
						String nombreTipoCalcetines = "";
						String nombreTipoPantalon = "";
						String nombreTipoSudaderas = "";
						String nombreTipoCamiseta = "";
						String nombreTipoZapato = "";
						String colorCordones = "";
						boolean goretex = false;
						
						if (nombre.equals("CALCETINES")) {
							int posTipoCalcetines = comboTipoCalcetines.getSelectedIndex();
							nombreTipoCalcetines = comboTipoCalcetines.getItemAt(posTipoCalcetines);
						} else if (nombre.equals("PANTALON")) {
							int posTipoPantalon = comboTipoPantalones.getSelectedIndex();
							nombreTipoPantalon = comboTipoPantalones.getItemAt(posTipoPantalon);
						} else if (nombre.equals("SUDADERA")) {
							int posTipoSudaderas = comboTipoSudaderas.getSelectedIndex();
							nombreTipoSudaderas = comboTipoSudaderas.getItemAt(posTipoSudaderas);
						} else if (nombre.equals("CAMISETA")) {
							int posTipoCamiseta = comboTipoCamisetas.getSelectedIndex();
							nombreTipoCamiseta = comboTipoCamisetas.getItemAt(posTipoCamiseta);
						}else if (nombre.equals("ZAPATOS")) {
							int posTipoZapato = comboTipoZapatos.getSelectedIndex();
							nombreTipoZapato = comboTipoZapatos.getItemAt(posTipoZapato);
							
							colorCordones = txtColorCordones.getText();
							goretex = Boolean.parseBoolean(txtGoretex.getText()); 	 
						}
						System.out.println("2");

						Connection con = BD.initBD("SweetWear.db");
						int cod = 0;
						try {
							cod = BD.contarProductosTienda(con);
							System.out.println(cod);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						cod = cod + 1;
						
						//AQUI HABRA QUE VER COMO ATRIBUIRLE EL CODIGO AL PRODUCTO
						//SUPONGO QUE SE HARA CONTANDO LOS PRODUCTOS DE LA BASE DE DATOS
						//OSEA AÑADIENDOLO A LA BASES DE DATOS Y PILLANDO DE HAY EL ID/CODIGO!!!
						Producto p = new Producto(cod, color, nombre, precio, stock, marca, rutaFoto);
						String [] fila = {String.valueOf(cod), color, nombre, String.valueOf(precio), String.valueOf(stock), marca, rutaAdecuada};
						modeloTablaProductos.addRow(fila);
						
							
					
						//HABRA QUE PONERLE DE CODIGO 1 MAS QUE EL CODIGO DEL ULTIMO PRODUCTO QUE HAY EN LA TABLA DE LA BASE DE DATOS!!
						if (nombre.equals("CALCETINES")) {
							BD.insertarProductoTienda(con, cod, color, nombre+ " " + nombreTipoCalcetines, precio, stock, marca, rutaAdecuada, nombre , nombreTipoCalcetines, "", "", "", "", false, "");
						} else if (nombre.equals("PANTALON")) {
							BD.insertarProductoTienda(con, cod, color, nombre+ " " + nombreTipoPantalon, precio, stock, marca, rutaAdecuada, nombre , "", "", nombreTipoPantalon, "", "", false, "");
							//METER UN BOTON DEL PRODUCTO EN LA VENTANA CORRESPONDIENTE!!	
						}else if (nombre.equals("CAMISETA")) {
							BD.insertarProductoTienda(con, cod, color, nombre+ " " + nombreTipoCamiseta, precio, stock, marca, rutaAdecuada, nombre , "", nombreTipoCamiseta, "", "", "", false, "");
						}else if (nombre.equals("SUDADERA")) {
							BD.insertarProductoTienda(con, cod, color, nombre+ " " + nombreTipoSudaderas, precio, stock, marca, rutaAdecuada, nombre , "", "", "", nombreTipoSudaderas, "", false, "");
						}else if (nombre.equals("ZAPATOS")) {
							BD.insertarProductoTienda(con, cod, color, nombre+ " " + nombreTipoZapato, precio, stock, marca, rutaAdecuada, nombre , "", "", "", "", colorCordones, goretex, nombreTipoZapato);
						}
						BD.closeBD(con);
						
						//METER UN BOTON DEL PRODUCTO EN LA VENTANA CORRESPONDIENTE!!
						
						
						
						JOptionPane.showMessageDialog(null, "¡¡PRODUCTO AÑADIDO CORRECTAMENTE!!");
					}else {
						JOptionPane.showMessageDialog(null, "Tienes que Seleccionar una imagen", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}	
			});
		
		
	}
	
	public static void mostrarPanel(JPanel panel) {
		panel.removeAll();
		lblNombre = new JLabel("NOMBRE: ");
		comboProductos = new JComboBox<>();
		for (TipoProductos s: TipoProductos.values()) {
			String n = String.valueOf(s);
			comboProductos.addItem(n);
		}
		lblColor = new JLabel("COLOR");
		lblPrecio = new JLabel("PRECIO: ");
		lblStock = new JLabel("STOCK: ");
		lblMarca = new JLabel("MARCA: ");
		
		txtPrecio = new JTextField();
		txtStock = new JTextField();
		txtMarca = new JTextField();
		txtColor = new JTextField();
		txtPrecio.setColumns(10);
		txtStock.setColumns(10);
		txtMarca.setColumns(10);
		txtColor.setColumns(10);
		
		
		
		panel.add(lblNombre);
		panel.add(comboProductos);
		panel.add(lblColor);
		panel.add(txtColor);
		panel.add(lblPrecio);
		panel.add(txtPrecio);
		panel.add(lblStock);
		panel.add(txtStock);
		panel.add(lblMarca);
		panel.add(txtMarca);
		
		panel.updateUI();
		
	}
	
	public static void actionListenerDeLosPantalonesDeLosProdsDeLaTienda (JLabel lblNuevoProd, String nombreProd, String tipoProd) {
		String texto = VentanaPrincipal.lblNombre.getText();
		if (texto != "") {
			int resp = JOptionPane.showConfirmDialog(null,"¿Quieres aniadir este producto a tu cesta?");
			if(resp == JOptionPane.OK_OPTION) {
				//AQUI SE AÑADIRA AL CARRITO ESTE PRODUCTO
				String cant = JOptionPane.showInputDialog("Cuantas cantidades quieres");
				int canti = Integer.parseInt(cant);
				if (canti > 0) {
					Connection con = BD.initBD("SweetWear.db");
					int stock = BD.obtenerStockProducto(con, nombreProd);
					if (stock >= canti) {
						if (stock == 0) 
							 lblNuevoProd.setText(nombreProd + tipoProd + ": " + "NO HAY CANTIDADES EN STOCK");
						else
							lblNuevoProd.setText(nombreProd + tipoProd  + ": " + "Cantidades restantes: " + (stock-canti) + " unidades");
						Producto p = BD.obtenerProductoTienda(con, nombreProd);
						VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n).add(new Pantalon(0, p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), TipoPantalon.CHANDAL)); //AQUI AÑADIR EL PRODUCTO
						System.out.println(VentanaPrincipal.tmPedidos.get(VentanaInicioSesion.n));
						int num = 0;
						try {
							num = BD.contarProductos(con);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						num = num + 1;
						BD.insertarProductoCliente(con, num ,VentanaInicioSesion.n , p.getColor(), p.getNombre(), p.getPrecio(), canti, p.getMarca(),p.getRutaFoto(), "Pantalon", "", "", tipoProd, "", "", false, "");
						try {
							BD.restarUnidadesAProducto(con, nombreProd, canti);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "¡¡PRODUCTO AÑADIDO CORRECTAMENTE!!");
						BD.closeBD(con);
					}else
						JOptionPane.showMessageDialog(null, "Lo sentimos, no hay suficientes unidades en stock", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "Error en cantidad, introduce un nÃºmero mayor que cero", "ERROR", JOptionPane.ERROR_MESSAGE);
			}	
		}else {
			JOptionPane.showMessageDialog(null, "Tienes que iniciar Sesión primero", "ACCESO DENEGADO", JOptionPane.ERROR_MESSAGE);
		}
	}
}
	


