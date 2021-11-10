package clases;

import java.io.Serializable;

public class Producto{
	
	private static int contador = 0;
	
	private int codigo;
	private String nombre;
	private double precio;
	private int stock;
	private String descripcion;
	private String marca;
	
	public Producto(String codigo, String nombre, double precio, int stock, String marca) {
		this.codigo = contador++; //Guardo el valor del contado en el atributo código y luego al contador le sumo 1
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.marca = marca;
	}
	
	
	

	public static int getContador() {
		return contador;
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



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	
	
	
	

}
