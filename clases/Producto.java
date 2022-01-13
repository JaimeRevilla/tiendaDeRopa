package clases;

import java.io.Serializable;

public class Producto{
	
	
	private int codigo;
	private String color;
	private String nombre;
	private double precio;
	private int stock;
	private String marca;
	private String rutaFoto;
	
	public Producto(int codigo, String color, String nombre, double precio, int stock, String marca, String rutaFoto) {
		this.codigo = codigo; 
		this.color = color;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.marca = marca;
		this.rutaFoto = rutaFoto;
		
	}	


	public int getCodigo() {
		return codigo;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}
	
	public String toString() {
		return getNombre() + " " + getPrecio() + "euros" + " " + getStock() + " uds";
	}
	
	

}
