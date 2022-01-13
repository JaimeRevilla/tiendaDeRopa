package clases;

public class Camiseta extends Producto {
	private TipoCamiseta tipoCamiseta;

	public Camiseta(int codigo, String color, String nombre, double precio, int stock, String marca, String rutaFoto,
			TipoCamiseta tipoCamiseta) {
		super(codigo, color, nombre, precio, stock, marca, rutaFoto);
		if(nombre.contains("POLO")) {
			this.tipoCamiseta = TipoCamiseta.POLO;
		}else if(nombre.contains("CAMISETA")) {
			this.tipoCamiseta = TipoCamiseta.CAMISETA;
		}else if(nombre.contains("CAMISA")) {
			this.tipoCamiseta = TipoCamiseta.CAMISA;
		}
		
	}
	
	
	
	public TipoCamiseta getTipoCamiseta() {
		return tipoCamiseta;
	}



	public void setTipoCamiseta(TipoCamiseta tipoCamiseta) {
		this.tipoCamiseta = tipoCamiseta;
	}
	
	public String toString() {
		return getNombre() + " " + getPrecio() + "euros" + " " + getStock() + " uds";
	}
	
}
