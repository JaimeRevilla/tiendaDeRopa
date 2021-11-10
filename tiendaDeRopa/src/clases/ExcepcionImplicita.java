package clases;

public class ExcepcionImplicita extends RuntimeException {
	
	private String mensaje;
	
	public ExcepcionImplicita(String m) {
		mensaje = m;
	}
	
	
	/**
	 * METODO QUE SIRVE PARA PODER MOSTRAR EL MENSAJE QUE LE PASEMOS POR PARAMETRO
	 */
	public String toString() {
		return mensaje;
	}
	
	
	
	
}
