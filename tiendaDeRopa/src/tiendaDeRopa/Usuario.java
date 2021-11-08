package tiendaDeRopa;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	private String nombre;
	private String dir;
	private String mail;
	private String pass;
	private char permisos;
	
	public void Usuario(String nombre, String dir, String mail, String pass, char permisos) {
		this.nombre = nombre;
		this.dir = dir;
		this.mail = mail;
		this.pass = pass;
		this.permisos = permisos;
		
	}

	public String getNombre() {
		return nombre;
	}

	public String getDir() {
		return dir;
	}

	public String getMail() {
		return mail;
	}

	public String getPass() {
		return pass;
	}

	public char getPermisos() {
		return permisos;
	}


	
	
	

}
