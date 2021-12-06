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
import java.io.File;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import clases.Producto;
import clases.TipoCalcetines;
import clases.TipoProductos;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;
	private JPanel panelCentro, panelSur, panelArriba, panelArribaIzq, panelArribaDrc, panelNorte;
	private JButton btnMostarMapa, btnVolver, btnAniadir;
	private JComboBox<String> comboProductos;
	private JLabel lblPrecio, lblStock, lblMarca, lblNombre, lblColor, lblFoto;
	private JTextField txtPrecio, txtStock, txtMarca, txtColor;
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
		
		mnFile = new JMenu("File");
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);
		
		mntmCargarArchivo = new JMenuItem("Cargar Archivo");
		mntmCargarArchivo.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmCargarArchivo);
		
		mnExit = new JMenu("Exit");
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
		
		
		btnMostarMapa = new JButton("USUARIOS");
		btnVolver = new JButton("VOLVER");
		btnAniadir = new JButton("AÑADIR PRODUCTO");
		
		
//		textArea = new JTextArea();
//		panelArribaDrc.add(textArea);
		lblFoto = new JLabel();
		panelArribaDrc.add(lblFoto);
		
		comboProductos = new JComboBox<>();
		for (TipoProductos s: TipoProductos.values()) {
			String n = String.valueOf(s);
			comboProductos.addItem(n);
		
		}
		
		
		lblNombre = new JLabel("NOMBRE: ");
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
		
		
		
		panelArribaIzq.add(lblNombre);
		panelArribaIzq.add(comboProductos);
		panelArribaIzq.add(lblColor);
		panelArribaIzq.add(txtColor);
		panelArribaIzq.add(lblPrecio);
		panelArribaIzq.add(txtPrecio);
		panelArribaIzq.add(lblStock);
		panelArribaIzq.add(txtStock);
		panelArribaIzq.add(lblMarca);
		panelArribaIzq.add(txtMarca);
		
		panelSur.add(btnVolver);
		panelSur.add(btnAniadir);
		panelSur.add(btnMostarMapa);
		
		
		
		
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
		
		//modeloTablaProductos.setColumnIdentifiers(nombres);
		tablaProductos = new JTable(modeloTablaProductos);
		
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
				String rutaFoto = im.getDescription();
				
				//AQUI HABRA QUE VER COMO ATRIBUIRLE EL CODIGO AL PRODUCTO
				//SUPONGO QUE SE HARA CONTANDO LOS PRODUCTOS DE LA BASE DE DATOS
				//OSEA AÑADIENDOLO A LA BASES DE DATOS Y PILLANDO DE HAY EL ID/CODIGO!!!
				Producto p = new Producto(999, color, nombre, precio, stock, marca, rutaFoto);
				String [] fila = {String.valueOf(p.getCodigo()), color, nombre, String.valueOf(precio), String.valueOf(stock), marca, rutaFoto};
				modeloTablaProductos.addRow(fila);
				
				
				
			}
		});
		
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
				new VentanaAdmin2(ventanaActual);
				ventanaActual.setVisible(false);
				
				//NO SE XQ NO ME HABRE LA VENTANA ADMIN2
				
				
				
				
				
				
//				String texto = "";
//				for (String clave: VentanaPrincipal.tmPedidos.keySet()) {
//					texto = texto + clave + "\n" + "\t";
//					ArrayList<Producto> productos = VentanaPrincipal.tmPedidos.get(clave);
//					for(Producto p: productos) {
//						texto = texto + p + "\n";
//					}
//				}
//				textArea.setText(texto);
				
				
				//METODO QUE MOSTRARA EL MAPA DE LO QUE SEA EN UN TEXT AREA!!
				
			}
		});
		
		mntmCerrarAplicacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		
		mntmCargarArchivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("C:\\Users\\34688\\Desktop\\workspace\\tiendaDeRopa\\tiendaDeRopa\\src\\imagenes");
				int sel = fc.showOpenDialog(null);
				if(sel == JFileChooser.APPROVE_OPTION) {
					File ficheroSeleccionado = fc.getSelectedFile();
					ImageIcon im = new ImageIcon(ficheroSeleccionado.getAbsolutePath());
					im.setDescription(ficheroSeleccionado.getAbsolutePath());
					lblFoto.setIcon(im);
				}
				
				
			}
		});
		
	}

}
