package tiendaDeRopa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Carrito implements Serializable{
	private String codigo;
	private String usuario;
	private Map<String, Integer> articulos = new HashMap <String, Integer>();
	private double precio;
	private String fecha;
	private String hora;
	private String formaPago;
	public String getCodigo() {
		return codigo;
	}
	
	
	
	//getters y setters
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Map<String, Integer> getArticulos() {
		return articulos;
	}
	public void setArticulos(Map<String, Integer> articulos) {
		this.articulos = articulos;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
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
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	

	
	
	

}
