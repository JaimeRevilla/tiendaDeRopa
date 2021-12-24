package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;



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
	
	public static void crearTablaProductosTienda(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS tienda (codigo int PRIMARY KEY AUTOINCREMENT, color String, nombre String, precio double, stock int, marca String, rutaFoto String,  tipoProducto String, tipoCalcetines String, tipoCamiseta String, tipoPantalon String, tipoSudadera String, colorCordones String, goretex boolean, tipoZapato String)";
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
	
	public static ArrayList<Producto> getTienda(Connection con) {
		ArrayList<Producto> al = new ArrayList<>();
		String sent = "select * from tienda;";
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
	
	
	
	public static int contarProductosTienda(Connection con) throws SQLException {
		String sent = "select count(*) from tienda";
		Statement stmt = null;
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sent);
		int resul = rs.getInt(1);
		rs.close();
		return resul;
	}
	
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
	
	public static void restarUnidadesAProducto(Connection con, String nom, int unidades) throws SQLException {
		String sent = "update tienda set stock = stock - "+unidades+ " where nombre = '"+nom+"'";
		Statement stmt = null;
		stmt = con.createStatement();
		stmt.executeUpdate(sent);
	}
	
	public static void sumarUnidadesAProducto(Connection con, String nom, int unidades) throws SQLException {
		String sent = "update tienda set stock = stock + "+unidades+ " where nombre = '"+nom+"'";
		Statement stmt = null;
		stmt = con.createStatement();
		stmt.executeUpdate(sent);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	

	
	
	public static void crearTablaCalcetines(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS calcetines (codigo int, color String, nombre String, precio double, stock int, marca String, rutaFoto String, tipoCalcetines String)";
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
	
	
	//------------------------------------------------------------------------------------------------------------------------------
	
	
	
	public static void crearTablaCamiseta(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS camiseta (codigo int, color String, nombre String, precio double, stock int, marca String, rutaFoto String, tipoCamiseta String)";
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
	
	//------------------------------------------------------------------------------------------------------------------
	
	
	public static void crearTablaPantalon(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS pantalon (codigo int, color String, nombre String, precio double, stock int, marca String, rutaFoto String, tipoPantalon String)";
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
	
	//-----------------------------------------------------------------------------------------------------------------
	
	public static void crearTablaSudadera(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS sudadera (codigo int, color String, nombre String, precio double, stock int, marca String, rutaFoto String, tipoSudadera String)";
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
	
//----------------------------------------------------------------------------------------------------------------
	
	public static void crearTablaZapato(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS zapato (codigo int, color String, nombre String, precio double, stock int, marca String, rutaFoto String, colorCordones String, goretex boolean, tipoZapato String)";
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
	
	//eliminarProducto
	//InsetarProducto
	//ObtenerProducto
	//Los que se ocurran
}
