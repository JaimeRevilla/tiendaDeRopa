package clases;



public class Usuario{
	
	private String nombre;
	private String dir;
	private String mail;
	private String con;
	private boolean permisos;
	
	public Usuario(String nombre, String dir, String mail, String con, boolean permisos) {
		this.nombre = nombre;
		this.dir = dir;
		this.mail = mail;
		this.con = con;
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

	public String getCon() {
		return con;
	}

	public boolean getPermisos() {
		return permisos;
	}

	
	
	

}
