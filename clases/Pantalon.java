package clases;

public class Pantalon extends Producto {
	private TipoPantalon tipoPantalon;

	public Pantalon(int codigo, String color, String nombre, double precio, int stock, String marca, String rutaFoto,
			TipoPantalon tipoPantalon) {
		super(codigo, color, nombre, precio, stock, marca, rutaFoto);
		if(nombre.contains("VAQUEROS")) {
			this.tipoPantalon = TipoPantalon.VAQUEROS;
		}else if(nombre.contains("CHANDAL")) {
			this.tipoPantalon = TipoPantalon.CHANDAL;
		}else if(nombre.contains("CAMPANA")) {
			this.tipoPantalon = TipoPantalon.CAMPANA;
		}
	
	}

	
	public TipoPantalon getTipoPantalon() {
		return tipoPantalon;
	}


	public void setTipoPantalon(TipoPantalon tipoPantalon) {
		this.tipoPantalon = tipoPantalon;
	}
	
	public String toString() {
		return getNombre() + " " + getPrecio() + "euros" + " " + getStock() + " uds";
	}
	
}