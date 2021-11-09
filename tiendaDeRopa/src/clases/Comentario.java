package clases;

import java.io.Serializable;

public class Comentario implements Serializable{
	
	String codigoComentario;
	String fecha;
	String hora;
	String codigoProducto;
	String Email;
	String nombre;
	String comentario;
	
	public Comentario(String codigoComentario, String fecha, String hora, String codigoProducto, String Email, String nombre, String comentario) {
		this.codigoComentario = codigoComentario;
		this.fecha = fecha;
		this.hora = hora;
		this.codigoProducto = codigoProducto;
		this.Email = Email;
		this.nombre = nombre;
		this.comentario = comentario;
		
		
	}

	//getters y setters
	public String getCodigoComentario() {
		return codigoComentario;
	}

	public void setCodigoComentario(String codigoComentario) {
		this.codigoComentario = codigoComentario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComenterio() {
		return comentario;
	}

	public void setComenterio(String comenterio) {
		this.comentario = comentario;
	}
	
	

}
