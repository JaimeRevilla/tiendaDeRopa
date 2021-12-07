package clases;

public class Sudadera extends Producto {
	private TipoSudadera tipoSudadera;

	public Sudadera(int codigo, String color, String nombre, double precio, int stock, String marca, String rutaFoto,
			TipoSudadera tipoSudadera) {
		super(codigo, color, nombre, precio, stock, marca, rutaFoto);
		if(nombre.contains("CON_GORRO")) {
			this.tipoSudadera = tipoSudadera.CON_GORRO;
		}else if(nombre.contains("SIN_GORRO")) {
			this.tipoSudadera = tipoSudadera.SIN_GORRO;
		}else if(nombre.contains("LARGA_GORRO")) {
			this.tipoSudadera = tipoSudadera.LARGA_GORRO;
		}else if(nombre.contains("")) {
			this.tipoSudadera = tipoSudadera.LARGA_SIN_GORRO;
		}
	
	}

	
	public TipoSudadera getTipoSudadera() {
		return tipoSudadera;
	}


	public void setTipoSudadera(TipoSudadera tipoSudadera) {
		this.tipoSudadera = tipoSudadera;
	}


	@Override
	public String toString() {
		return "Sudadera [tipoSudadera=" + tipoSudadera + ", getCodigo()=" + getCodigo() + ", getColor()=" + getColor()
				+ ", getNombre()=" + getNombre() + ", getPrecio()=" + getPrecio() + ", getStock()=" + getStock()
				+ ", getMarca()=" + getMarca() + ", getRutaFoto()=" + getRutaFoto() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
