package tiendaDeRopa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Carrito implements Serializable{
	private String codigo;
	private String usuario;
	private Map <String, Integer> articulos = new HashMap <String, Integer>();
	private double precio;
	private String fecha;
	private String hora;
	private String formaPago;
	public String getCodigo() {
		return codigo;
	}
	
	
	//getters y setters
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Map<String, Integer> getArticulos() {
		return articulos;
	}
	public void setArticulos(Map<String, Integer> articulos) {
		this.articulos = articulos;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	public class GestorCarrito{
		
		// Parte no static
		private ArrayList<Carrito> carritos;
		
		/** Crea un nuevo gestor de carritos partiendo de una lista de carritos existente
		 * 
		 */
		public GestorCarrito(ArrayList<Carrito> carritos) {
			super();
			this.carritos = carritos;
		}

		/** Crea un nuevo gestor de carritos con una serie de datos de ejemplo (3 camisetas y 2 pantalones)
		 */
		public GestorCarrito() {
			super();
			this.carritos = new ArrayList<Carrito>();
			getCarritos();
		}
		
		/** Devuelve la lista de carritos del gestor
		 * 
		 */
		public ArrayList<Carrito> getCarritos() {
			return carritos;
		}

		/** Modifica la lista de carritos del gestor
		 * 
		 */
		public void setCarritos(ArrayList<Carrito> Carritos) {
			this.carritos = carritos;
		}

		@Override
		public String toString() {
			return "GestorCarrito [carritos=" + carritos + "]";
		}
				
	}
}
