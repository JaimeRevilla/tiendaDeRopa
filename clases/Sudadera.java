package clases;

public class Sudadera extends Producto {
	private TipoSudadera tipoSudadera;

	public Sudadera(int codigo, String color, String nombre, double precio, int stock, String marca, String rutaFoto,
			TipoSudadera tipoSudadera) {
		super(codigo, color, nombre, precio, stock, marca, rutaFoto);
		if(nombre.contains("CON_GORRO")) {
			this.tipoSudadera = TipoSudadera.CON_GORRO;
		}else if(nombre.contains("SIN_GORRO")) {
			this.tipoSudadera = TipoSudadera.SIN_GORRO;
		}else if(nombre.contains("CON_CREMALLERA")) {
			this.tipoSudadera = TipoSudadera.CON_CREMALLERA;
		}else if (nombre.contains("SIN_CREMALLERA")) {
			this.tipoSudadera = TipoSudadera.SIN_CREMALLERA;
		}
	
	}

	
	public TipoSudadera getTipoSudadera() {
		return tipoSudadera;
	}


	public void setTipoSudadera(TipoSudadera tipoSudadera) {
		this.tipoSudadera = tipoSudadera;
	}

	public String toString() {
		return getNombre() + " " + getPrecio() + "euros" + " " + getStock() + " uds";
	}
}
