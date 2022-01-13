package clases;

import java.util.regex.Pattern;

import ventanas.VentanaRegistro;

public class Usuario implements Comparable<Usuario> {
	
	private String nombre;
	private String mail;
	private int edad;
	private String con;
	private boolean permisos;
	
	public Usuario(String nombre, int edad, String mail, String con, boolean permisos) {
		this.nombre = nombre;
		this.edad = edad;
		boolean correcto = Pattern.matches(VentanaRegistro.ermail, mail);
		if (correcto) {
			this.mail = mail;
		}else {
			this.mail = nombre + "@"+ "sweetWear.com";
		}
		this.con = con;
		this.permisos = permisos;
		
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCon(String con) {
		this.con = con;
	}


	public void setPermisos(boolean permisos) {
		this.permisos = permisos;
	}


	public String getNombre() {
		return nombre;
	}

	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCon() {
		return con;
	}

	public boolean getPermisos() {
		return permisos;
	}
	
	public String toString() {
		return "Nombre: " + nombre + "   Edad: " + edad + "   Correo: " + mail + "   Contrasenia: " + con + "   Permisos: " + permisos;
	}
	
	public int compareTo(Usuario o) {
		//ORDENAR DESCENDENTEMENTE
		return o.nombre.compareTo(this.nombre);
	}
	

}
