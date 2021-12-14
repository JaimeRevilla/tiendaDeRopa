package clases;

public class Calcetines extends Producto {
	private TipoCalcetines tipoCalcetinas;

	public Calcetines(int codigo, String color, String nombre, double precio, int stock, String marca, String rutaFoto,
			TipoCalcetines tipoCalcetinas) {
		super(codigo, color, nombre, precio, stock, marca, rutaFoto);
		if(nombre.contains("PINKIE")) {
			this.tipoCalcetinas = TipoCalcetines.PINKIE;
		}else if(nombre.contains("TOBILLERO")) {
			this.tipoCalcetinas = TipoCalcetines.TOBILLERO;
		}else if(nombre.contains("ALTO")) {
			this.tipoCalcetinas = TipoCalcetines.ALTO;
		}
		
	}

	public TipoCalcetines getTipoCalcetinas() {
		return tipoCalcetinas;
	}

	public void setTipoCalcetinas(TipoCalcetines tipoCalcetinas) {
		this.tipoCalcetinas = tipoCalcetinas;
	}

	@Override
	public String toString() {
		return "Calcetines [tipoCalcetinas=" + tipoCalcetinas + ", getTipoCalcetinas()=" + getTipoCalcetinas()
				+ ", getCodigo()=" + getCodigo() + ", getColor()=" + getColor() + ", getNombre()=" + getNombre()
				+ ", getPrecio()=" + getPrecio() + ", getStock()=" + getStock() + ", getMarca()=" + getMarca()
				+ ", getRutaFoto()=" + getRutaFoto() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
