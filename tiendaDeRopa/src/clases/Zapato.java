package clases;

public class Zapato extends Producto {
	private String colorCordones;
	private boolean goretex;
	
	public Zapato(String codigo, String nombre, double precio, int stock, String marca, String color, boolean goretex) {
		super(codigo, nombre, precio, stock, marca);
		this.colorCordones = color;
		this.goretex = goretex;
	}

	public String getColorCordones() {
		return colorCordones;
	}

	public void setColorCordones(String colorCordones) {
		this.colorCordones = colorCordones;
	}

	public boolean isGoretex() {
		return goretex;
	}

	public void setGoretex(boolean goretex) {
		this.goretex = goretex;
	}

	@Override
	public String toString() {
		return "Zapato [colorCordones=" + colorCordones + ", goretex=" + goretex + ", getNombre()=" + getNombre()
				+ ", getPrecio()=" + getPrecio() + ", getStock()=" + getStock() + ", getDescripcion()="
				+ getDescripcion() + ", getMarca()=" + getMarca() + "]";
	}
	
	
	
	
	
}

//prueba desde surface
