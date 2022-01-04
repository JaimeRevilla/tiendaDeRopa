package clases;

/**
 * @author 34688
 *
 */
public class Zapato extends Producto {
	private String colorCordones;
	private boolean goretex;
	private TipoZapato tipoZapato;

	
	
	public Zapato(int codigo, String nombre, double precio, int stock, String marca, String color, String rutaFoto, /*boolean goretex,*/
			TipoZapato tipoZapato) {
		super(codigo, nombre, color, precio, stock, marca, rutaFoto);
		this.colorCordones = color;
		this.goretex = goretex;
		if(nombre.contains("BOTAS")) {
			this.tipoZapato = TipoZapato.BOTAS;
		}else if(nombre.contains("DEPORTIVAS")) {
			this.tipoZapato = TipoZapato.DEPORTIVAS;
		}else if(nombre.contains("FORMALES")) {
			this.tipoZapato = TipoZapato.FORMALES;
		}else if(nombre.contains("TACONES")) {
			this.tipoZapato = TipoZapato.TACONES;
		}
	
	}
	
	

	public TipoZapato getTipoZapato() {
		return tipoZapato;
	}



	public void setTipoZapato(TipoZapato tipoZapato) {
		this.tipoZapato = tipoZapato;
	}



	/*public String getColorCordones() {
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
	}*/



	/*@Override
	public String toString() {
		return "Sudadera [tipoSudadera=" + tipoSudadera + ", getCodigo()=" + getCodigo() + ", getColor()=" + getColor()
				+ ", getNombre()=" + getNombre() + ", getPrecio()=" + getPrecio() + ", getStock()=" + getStock()
				+ ", getMarca()=" + getMarca() + ", getRutaFoto()=" + getRutaFoto() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}*/
	
	public String toString() {
		return getNombre() + " " + getPrecio() + "euros" + " " + getStock() + " uds";
	}
}


