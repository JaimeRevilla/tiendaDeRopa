package clases;

import java.io.Serializable;

public class Producto{
	
	private static int contador = 0;
	
	private int codigo;
	private String color;
	private String nombre;
	private double precio;
	private int stock;
	private String marca;
	private String rutaFoto;
	
	public Producto(int codigo, String color, String nombre, double precio, int stock, String marca, String rutaFoto) {
		this.codigo = contador++; //Guardo el valor del contado en el atributo código y luego al contador le sumo 1
		this.color = color;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.marca = marca;
		this.rutaFoto = rutaFoto;
	}
	
	//CREO QUE RUTA FOTO ES ALGO QUE NO NECESITAMOS DE MOMENTO, CREO QUE ES ALGO MAS ESTETICO QUE IMPORTANTE (NO DIGO QUE SEA MALA IDEA)
	

	public static int getContador() {
		return contador;
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

}
