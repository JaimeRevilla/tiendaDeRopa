package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;



/**
 * @author 34688
 *
 */
/**
 * @author 34688
 *
 */
/**
 * @author 34688
 *
 */
public class BD {

	
	/**
	 * Metodo que crea la conexion con la base de datos 
	 * @param nombreBD El nombre de la base de datos
	 * @return Un objeto Conexion
	 */
	
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+ nombreBD);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	/**
	 * Metodo que cierra la conexion con la BBDD
	 * @param con Objeto Connection
	 */
	
	public static void closeBD(Connection con) {
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * Metodo que inserta los datos de un usuario en la BBDD
	 * @param con Conexion con la BBDD
	 * @param nom Nombre del usuario
	 * @param mail Correo del usuario
	 * @param c Contraseña del usuario
	 * @param permisos Booleano que indica si el usuario tiene derecho a los permisos de administrador
	 */
	public static void insertarUsuario(Connection con, String nom, int edad, String mail, String c , boolean permisos) {
		String sent = "INSERT INTO usuario VALUES ('"+nom+"', '"+edad+"', '"+mail+"', '"+c+"', '"+permisos+"')";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sent);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	/**
	 * Metodo que borra un usuario de la BBDD
	 * @param con Conexion con la BBDD
	 * @param nom Nombre del usuario que se quiere borrar
	 */
	public static void eleminarUsuario(Connection con, String nom) {
		String sent = "DELETE FROM usuario WHERE nom ='"+nom+"'";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sent);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	/**
	 * Metodo que recive el nombre y contraseña de un usuario y comprueba si esta registrado en la BBDD
	 * @param con Conexcion con la BBDD
	 * @param nom Nombre del usuario
	 * @param c Contraseña del usuario
	 * @return 0 --> El usuario no esta todavía registrado
	 * 		   1 --> El usuario esta registrado pero la contraseña introducida es incorrecta
	 *         2 --> El usuario esta registrado y todos los datos introducidos son correctos
	 */
	public static int obtenerUsuario(Connection con, String nom, String c) {
		String sent = "SELECT c FROM usuario WHERE nom = '"+nom+"'";
		Statement stmt = null;
		int res = 0;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sent);
			if (rs.next()) {
				if (rs.getString("c").equals(c)) {
					res = 2;
				} else {
					res = 1;
				}
			}
			rs.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}return res;	
	}
	
	/**
	 * METODO PARA OBTENER LOS PERMISOS DE UN USUARIO
	 * @param con Conexion con la BBDD
	 * @param nom Nombre del Usuario
	 * @return Devuelve true o fasle
	 */
	public static boolean obtenerAdmin(Connection con, String nom) {
		String sent = "SELECT * FROM usuario WHERE nom = '"+nom+"'";
		Statement stmt = null;
		boolean res = false;
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sent);
			if (rs.next()) {
				String r = rs.getString("permisos");
				res = Boolean.parseBoolean(r);
			}
			rs.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}return res;	
	}
	
	/**
	 * Metodo para crear la tabla usuario en caso de que todavia no exista
	 * @param con Conexion con la BBDD
	 */
	public static void crearTablaUsuario(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS usuario (nom String, edad int, mail String, c String , permisos boolean)";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sent);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	/**
	 * METODO PARA COMPROBAR SI EXISTE ALGUN USUARIO CON ESE NOMBRE
	 * @param con --> Conexion con la BBDD
	 * @param n --> Nombre del usuario
	 * @return --> true en caso de que exista y false en caso de que no
	 * @throws SQLException --> Propaga la exepcion
	 */
	public static boolean existeUsuarioConEseNombre(Connection con, String n) throws SQLException {
		String sent = "select * from usuario where nom='"+n+"'";
		Statement stmt = null;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sent);
		boolean existe = false;
		if(rs.next())
			existe = true;
		rs.close();
		return existe;
	}
	
	/**
	 * METODO PARA CARGA EL MAPA DE USUARIOS MEDIANTE LA BD
	 * @param con --> Conexion con la BD
	 * @return --> El mapa cargado
	 */
	public static TreeMap<String, Usuario> obtenerMapaUsuarios(Connection con) {
		TreeMap<String, Usuario> tmUsuarios = new TreeMap<>();
		
		String sent = "SELECT * FROM usuario";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sent);
			while(rs.next()) {
				String nombre = rs.getString("nom");
				int edad = rs.getInt("edad");
				String mail = rs.getString("mail");
				String c = rs.getString("c");
				boolean permisos = rs.getBoolean("permisos");
				Usuario u = new Usuario(nombre, edad, mail, c, permisos);
				tmUsuarios.put(nombre, u);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmUsuarios;
	}
	
	/**
	 * METODO PARA OBTENER LA CONTRASENIA DEL USUARIO QUE TIENE EL NOMBRE DADO POR PÁRAMETRO
	 * @param con --> Conexion con la BD
	 * @param nom --> Nombre del usuario
	 * @return --> Contrasenia del usuario
	 */
	public static String obtenerConUsuario(Connection con, String nom) {
		String sent = "SELECT * FROM usuario WHERE nom = '"+nom+"'";
		Statement stmt = null;
		String c = "";
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sent);
			if (rs.next()) {
				 c = rs.getString("c");
			}
			rs.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}return c;
	}
	
	/**
	 * METODO PARA MODIFICAR LA CONTRASENIA DEL USUARIO
	 * @param con --> Conexion con la BD
	 * @param nom --> Nombre del Usuario
	 * @param c --> Contrasenia del usuario
	 */
	public static void modificarConUsuario(Connection con, String nom, String c){
		String sent = "update usuario set c='"+c+"' where nom = '"+nom+"'";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Metodo que te devuelve el usuario con el nombre que se le pasa por parámetro
	 * @param con Conexion con la BBDD
	 * @param nom Nombre del usuario
	 * @return Devuelve el usuario
	 */
	public static Usuario obtenerUsuario(Connection con, String nom) {
		String sent = "SELECT * FROM usuario WHERE nom = '"+nom+"'";
		Statement stmt = null;
		Usuario u = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sent);
			if (rs.next()) {
				String nombre = rs.getString("nom");
				int edad = rs.getInt("edad");
				String mail = rs.getString("mail");
				String c = rs.getString("c");
				boolean permisos = rs.getBoolean("permisos");
				u = new Usuario(nombre, edad, mail, c, permisos);
			}
			rs.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}return u;	
	}
	//------------------------------------------------------------------------------------------------------------------------
	//METODOS DE LA BD RELACIONADOS CON LOS PRODUCTOS
	/**
	 * METODO PARA CREAR LA TABLA DE pedido EN LA BD EN EL CASO DE QUE NO ESTE CREADA
	 * @param con --> Conexion con la BD
	 */
	public static void crearTablaProductosCliente(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS pedido (codigo int , cliente String, color String, nombre String, precio double, cantidad int, marca String, rutaFoto String,  tipoProducto String, tipoCalcetines String, tipoCamiseta String, tipoPantalon String, tipoSudadera String, colorCordones String, goretex boolean, tipoZapato String)";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sent);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	
	
	/**
	 * Metodo para saber cuantos productos hay en la tabla de pedidos
	 * @param con Conexion con la BD
	 * @return el numero de pedidos que hay
	 * @throws SQLException Para no tener que poner try and catch 
	 */
	public static int contarProductos(Connection con) throws SQLException {
		String sent = "select count(*) from pedido";
		Statement stmt = null;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sent);
		int resul = rs.getInt(1);
		rs.close();
		return resul;
	}
	
	/**
	 * METODO PARA INSERTAR UN PRODUCTO EN LA TABLA DE pedidos
	 * @param con
	 * @param codigo
	 * @param cliente
	 * @param color
	 * @param nombre
	 * @param precio
	 * @param cantidad
	 * @param marca
	 * @param rutaFoto
	 * @param tipoProducto
	 * @param tipoCalcetines
	 * @param tipoCamiseta
	 * @param tipoPantalon
	 * @param tipoSudadera
	 * @param colorCordones
	 * @param goretex
	 * @param tipoZapato
	 */
	public static void insertarProductoCliente(Connection con, int codigo , String cliente, String color , String nombre , double precio ,int cantidad ,String marca ,String rutaFoto ,String  tipoProducto ,String tipoCalcetines ,String tipoCamiseta ,String tipoPantalon ,String tipoSudadera ,String colorCordones ,boolean goretex ,String tipoZapato ) {
		String sent = "INSERT INTO pedido VALUES ('"+codigo+"', '"+cliente+"', '"+color+"', '"+nombre+"', '"+precio+"', '"+cantidad+"', '"+marca+"', '"+rutaFoto+"', '"+tipoProducto+"', '"+tipoCalcetines+"', '"+tipoCamiseta+"', '"+tipoPantalon+"', '"+tipoSudadera+"', '"+colorCordones+"', '"+goretex+"', '"+tipoZapato+"')";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sent);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * METODO PARA ELIMINAR DE LA TABLA pedido EL PRODUCTO EL CUAL TIENE EL CODIGO DADO POR PARAMETRO
	 * @param con --> Conexión con la BD
	 * @param codigo --> Codigo del producto a eliminar
	 */
	public static void eliminarProductoCliente(Connection con, int codigo) {
		String sent = "DELETE FROM pedido WHERE codigo ='"+codigo+"'";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * METODO PARA CREAR LA TABLA tienda EN CASO DE QUE NO ESTE CREADA
	 * @param con --> Conexion con la BD
	 */
	public static void crearTablaProductosTienda(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS tienda (codigo int PRIMARY KEY AUTOINCREMENT , color String, nombre String, precio double, stock int, marca String, rutaFoto String,  tipoProducto String, tipoCalcetines String, tipoCamiseta String, tipoPantalon String, tipoSudadera String, colorCordones String, goretex boolean, tipoZapato String)";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sent);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * METODO PARA ELIMINAR DE LA TABLA tienda UN PRODUCTO CUYO CODIGO SEA EL RECIBIDO POR PARAMETRO
	 * @param con --> Conexion con la BD
	 * @param codigo --> Codigo del producto a eliminar
	 */
	public static void eliminarProductoTienda(Connection con, int codigo) {
		String sent = "DELETE FROM tienda WHERE codigo ='"+codigo+"'";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * METODO PARA CARGAR UNA LISTA DE PRODUCTOS CON LOS PRODUCTOS QUE HAY EN LA TABLA tienda DE LA BD
	 * @param con --> Conexión con la BD
	 * @return --> La lista cargada de productos
	 */
	public static ArrayList<Producto> getTienda(Connection con) {
		ArrayList<Producto> al = new ArrayList<>();
		String sent = "SELECT * FROM tienda;";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( sent );
			while( rs.next() ) { 
				int codigo = rs.getInt("codigo");
				String color = rs.getString("color");
				String nombre = rs.getString("nombre");
				double precio = rs.getDouble("precio");
				int stock = rs.getInt("stock");
				String marca = rs.getString("marca");
				String rutaFoto = rs.getString("rutaFoto");
				Producto p = new Producto(codigo, color, nombre, precio, stock, marca, rutaFoto);
				al.add(p);
				
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}
	
	/**
	 * METODO PARA SABER SI EXISTE UN PRODUCTO CON EL NOMBRE DADO POR PÁRAMETRO
	 * @param con --> Conexion con la BD
	 * @param n --> Nombre del producto
	 * @return --> true si el producto existe y false en caso contrario
	 * @throws SQLException --> Propaga la excepcion
	 */
	public static boolean existeProductoMismoNombre(Connection con, String n) throws SQLException {
		String sent = "select * from tienda where nombre='"+n+"'";
		Statement stmt = null;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sent);
		boolean existe = false;
		if(rs.next())
			existe = true;
		rs.close();
		return existe;
	}
	
	
	
	/**
	 * METODO PARA APLICAR UNA OFERTA AL PRODUCTO QUE TIENE EL NOMBRE DADO POR PÁRAMETRO
	 * @param con --> Conexión con la BD
	 * @param nombre --> Nombre del producto
	 * @param porcentaje --> Porcentaje por el cual va a ser multiplicado el precio del producto
	 * @throws SQLException --> Propaga la excepción
	 */
	public static void ponerProductoEnOferta(Connection con, String nombre, double porcentaje) throws SQLException {
		String sent = "update tienda set precio = precio * "+porcentaje+" where nombre='"+nombre+"'";
		Statement stmt = null;
		stmt = con.createStatement();
		stmt.executeUpdate(sent);
	}
	
	/**
	 * METODO PARA FINALIZAR LA OFERTA APLICADA AL PRODUCTO QUE TIENE EL NOMBRE DADO POR PÁRAMETRO
	 * @param con --> Conexión con la BD
	 * @param nombre --> Nombre del producto
	 * @param porcentaje --> Porcentaje por el cual va a ser dividido el precio del producto
	 * @throws SQLException --> Propaga la excepción
	 */
	public static void finOferta(Connection con, String nombre, double porcentaje) throws SQLException {
		String sent = "update tienda set precio = precio / "+porcentaje+" where nombre='"+nombre+"'";
		Statement stmt = null;
		stmt = con.createStatement();
		stmt.executeUpdate(sent);
	}
	
	/**
	 * METODO PARA MODIFICAR UN PRODUCTO DE UNA TIENDA EL CUAL SU CÓDIGO ES EL DADO POR PÁRAMETRO
	 * @param con
	 * @param codigo
	 * @param color
	 * @param nombre
	 * @param precio
	 * @param stock
	 * @param marca
	 * @param rutaFoto
	 */
	public static void modificarProductoTienda(Connection con, int codigo, String color, String nombre, double precio, int stock, String marca, String rutaFoto) {
		String sent = "update tienda set codigo="+codigo+", color='"+color+"',nombre='"+nombre+"',precio="+precio+",stock="+stock+",marca='"+marca+"',rutaFoto='"+rutaFoto+"' where codigo="+codigo;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * METODO PARA CONTAR LA CANTIDAD DE PRODUCTOS QUE HAY EN LA TABLA DE tienda
	 * @param con --> Conexion con la BD
	 * @return --> Devuelve la cantidad de productos
	 * @throws SQLException --> Propaga la excepción
	 */
	public static int contarProductosTienda(Connection con) throws SQLException {
		String sent = "select count(*) from tienda";
		Statement stmt = null;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sent);
		int resul = rs.getInt(1);
		rs.close();
		return resul;
	}
	
	/**
	 * METODO PARA OBTENER UN PRODUCTO DE LA tienda EL CUAL SU NOMBRE ES EL DADO POR PÁRAMETRO
	 * @param con --> Conexión con la BD
	 * @param nom --> Nombre del producto
	 * @return --> El producto con el nombre dado por párametro
	 */
	public static Producto obtenerProductoTienda(Connection con, String nom) {
		String sent = "SELECT * FROM tienda WHERE nombre = '"+nom+"'";
		Statement stmt = null;
		int stock = 0;
		Producto p = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sent);
			if (rs.next()) {
				int codigo = rs.getInt("codigo");
				String color = rs.getString("color");
				String nombre = rs.getString("nombre");
				double precio = rs.getDouble("precio");
				stock = rs.getInt("stock");
				String marca = rs.getString("marca");
				String rutaFoto = rs.getString("rutaFoto");
				String tipoProducto = rs.getString("tipoProducto");
				String tipoCalcetines = rs.getString("tipoCalcetines");
				String tipoCamiseta = rs.getString("tipoCamiseta");
				String tipoPantalon = rs.getString("tipoPantalon");
				String tipoSudadera = rs.getString("tipoSudadera");
				String colorCordones = rs.getString("colorCordones");
				boolean goretex = rs.getBoolean("goretex");
				String tipoZapato = rs.getString("tipoZapato");
				p = new Producto(codigo, color, nombre, precio, stock, marca, rutaFoto);
					
			}
			rs.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}return p;	
	}
	
	
	/**
	 * METODO PARA OBTENER EL STOCK RESTANTE DE UN PRODUCTO DE LA tienda CUYO NOMBRE ES EL DADO POR PÁRAMETRO
	 * @param con --> Conexión con la BD
	 * @param nom --> Nombre del producto
	 * @return --> El stock restante (Unidades restantes)
	 */
	public static int obtenerStockProducto(Connection con, String nom) {
		String sent = "SELECT * FROM tienda WHERE nombre = '"+nom+"'";
		Statement stmt = null;
		int stock = 0;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sent);
			if (rs.next()) {
				stock = rs.getInt("stock");
			}
			rs.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}return stock;	
	}
	
	/**
	 * METODO PARA OBTENER EL PRECIO DE UN PRODUCTO CUYO NOMBRE EL ES DADO POR PÁRAMETRO
	 * @param con --> Conexión con la BD
	 * @param nom --> Nombre del producto 
	 * @return --> El precio del producto
	 */
	public static double obtenerPrecioProducto(Connection con, String nom) {
		String sent = "SELECT * FROM tienda WHERE nombre = '"+nom+"'";
		Statement stmt = null;
		double precio = 0;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sent);
			if (rs.next()) {
				precio = rs.getDouble("precio");
			}
			rs.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}return precio;	
	}
	
	/**
	 * METODO PARA INSERTAR UN PRODUCTO EN LA TABLA DE tienda DE LA BD
	 * @param con
	 * @param codigo
	 * @param color
	 * @param nombre
	 * @param precio
	 * @param stock
	 * @param marca
	 * @param rutaFoto
	 * @param tipoProducto
	 * @param tipoCalcetines
	 * @param tipoCamiseta
	 * @param tipoPantalon
	 * @param tipoSudadera
	 * @param colorCordones
	 * @param goretex
	 * @param tipoZapato
	 */
	public static void insertarProductoTienda(Connection con, int codigo  , String color , String nombre  ,  double precio , int stock , String marca , String rutaFoto  , String tipoProducto ,  String tipoCalcetines ,String tipoCamiseta ,String tipoPantalon ,String tipoSudadera ,String colorCordones ,boolean goretex ,String tipoZapato ){
		String sent = "INSERT INTO tienda VALUES ('"+codigo+"', '"+color+"', '"+nombre+"', '"+precio+"', '"+stock+"', '"+marca+"', '"+rutaFoto+"', '"+tipoProducto+"', '"+tipoCalcetines+"', '"+tipoCamiseta+"', '"+tipoPantalon+"', '"+tipoSudadera+"', '"+colorCordones+"', '"+goretex+"', '"+tipoZapato+"')";
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sent);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * METODO PARA RESTAR LAS UNIDADES DADAS POR PÁRAMETRO AL PRODUCTO DE tienda CUYO NOMBRE ES EL DADO POR PÁRAMETRO
	 * @param con --> Conexión con la BD
	 * @param nom --> Nombre del Producto
	 * @param unidades --> Unidades a restar
	 * @throws SQLException --> Propaga la excepción
	 */
	public static void restarUnidadesAProducto(Connection con, String nom, int unidades) throws SQLException {
		String sent = "update tienda set stock = stock - "+unidades+ " where nombre = '"+nom+"'";
		Statement stmt = null;
		stmt = con.createStatement();
		stmt.executeUpdate(sent);
	}
	
	/**
	 * METODO PARA SUMAR LAS UNIDADES DADAS POR PÁRAMETRO AL PRODUCTO DE tienda CUYO NOMBRE ES EL DADO POR PÁRAMETRO
	 * @param con --> Conexión con la BD
	 * @param nom --> Nombre del Producto
	 * @param unidades --> Unidades a sumar
	 * @throws SQLException --> Propaga la excepción
	 */
	public static void sumarUnidadesAProducto(Connection con, String nom, int unidades) throws SQLException {
		String sent = "update tienda set stock = stock + "+unidades+ " where nombre = '"+nom+"'";
		Statement stmt = null;
		stmt = con.createStatement();
		stmt.executeUpdate(sent);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	
}
