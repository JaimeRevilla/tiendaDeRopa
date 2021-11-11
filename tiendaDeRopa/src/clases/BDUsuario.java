package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDUsuario {

	
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
	public static void insertarUsuario(Connection con, String nom, int edad, String mail, String c, boolean permisos) {
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
				if (rs.getString("con").equals(c)) {
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
	 * Metodo para crear la tabla usuario en caso de que todavia no exista
	 * @param con Conexion con la BBDD
	 */
	public static void crearTabla(Connection con) {
		String sent = "CREATE TABLE IF NOT EXISTS usuario (nom String, int edad, mail String, c String , permisos boolean)";
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
	
	
	
	
	
	
	
}
