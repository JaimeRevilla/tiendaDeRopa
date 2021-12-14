package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	 * @param c Contrase�a del usuario
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
	 * Metodo que recive el nombre y contrase�a de un usuario y comprueba si esta registrado en la BBDD
	 * @param con Conexcion con la BBDD
	 * @param nom Nombre del usuario
	 * @param c Contrase�a del usuario
	 * @return 0 --> El usuario no esta todav�a registrado
	 * 		   1 --> El usuario esta registrado pero la contrase�a introducida es incorrecta
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
	
	
	
	//------------------------------------------------------------------------------------------------------------------------
	//METODOS DE LA BD RELACIONADOS CON LOS PRODUCTOS
	

	
	
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
		String sent = "CREATE TABLE IF NOT EXISTS sudadera (codigo int, color String, nombre String, precio double, stock int, marca String, rutaFoto String, tipoCamiseta String)";
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
