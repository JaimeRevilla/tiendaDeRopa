package clases;



public class Usuario implements Comparable<Usuario> {
	
	private String nombre;
	private String mail;
	private int edad;
	private String con;
	private boolean permisos;
	
	public Usuario(String nombre, int edad, String mail, String con, boolean permisos) {
		this.nombre = nombre;
		this.edad = edad;
		this.mail = mail;
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

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", mail=" + mail + ", edad=" + edad + ", con=" + con + ", permisos="
				+ permisos + "]";
	}

	
	public int compareTo(Usuario o) {
		//ORDENAR DESCENDENTEMENTE
		return o.nombre.compareTo(this.nombre);
	}
	

}
