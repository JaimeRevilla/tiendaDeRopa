package clases;



public class Usuario{
	
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

	
	
	

}
