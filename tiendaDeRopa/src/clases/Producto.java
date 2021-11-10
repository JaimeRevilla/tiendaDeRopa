package clases;

import java.io.Serializable;

public class Producto implements Serializable{
	
	private static int contador = 0;
	
	private int codigo;
	private String nombre;
	private double precio;
	private int stock;
	private String descripcion;
	
	public void producto(String codigo, String nombre, double precio, int stock) {
		this.codigo = contador++; //Guardo el valor del contado en el atributo código y luego al contador le sumo 1
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	
	}
	
	public void producto(String codigo, String nombre, double precio, int stock, String descripcion, String detalles) {
		this.codigo = contador++; 
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.descripcion = descripcion;
	
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	
	

}
