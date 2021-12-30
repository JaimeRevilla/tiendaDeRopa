package clases;

public class Calcetines extends Producto {
	private TipoCalcetines tipoCalcetines;

	public Calcetines(int codigo, String color, String nombre, double precio, int stock, String marca, String rutaFoto,
			TipoCalcetines tipoCalcetinas) {
		super(codigo, color, nombre, precio, stock, marca, rutaFoto);
		if(nombre.contains("PINKIE")) {
			this.tipoCalcetines = TipoCalcetines.PINKIE;
		}else if(nombre.contains("TOBILLERO")) {
			this.tipoCalcetines = TipoCalcetines.TOBILLERO;
		}else if(nombre.contains("ALTO")) {
			this.tipoCalcetines = TipoCalcetines.ALTO;
		}
		
	}

	public TipoCalcetines getTipoCalcetinas() {
		return tipoCalcetines;
	}

	public void setTipoCalcetinas(TipoCalcetines tipoCalcetinas) {
		this.tipoCalcetines = tipoCalcetinas;
	}

	@Override
	public String toString() {
		return "Calcetines [tipoCalcetinas=" + tipoCalcetines + ", getTipoCalcetinas()=" + getTipoCalcetinas()
				+ ", getCodigo()=" + getCodigo() + ", getColor()=" + getColor() + ", getNombre()=" + getNombre()
				+ ", getPrecio()=" + getPrecio() + ", getStock()=" + getStock() + ", getMarca()=" + getMarca()
				+ ", getRutaFoto()=" + getRutaFoto() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
