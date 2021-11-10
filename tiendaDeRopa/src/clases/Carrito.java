package clases;


import java.util.ArrayList;

public class Carrito {
	private String codigo;
	private String nombreUsuario;
	private double precio;
	private String fecha;
	private ArrayList<Producto> productos;
	
	public Carrito(String codigo, String nombreUsuario, double precio, String fecha) {
		this.codigo = codigo;
		this.nombreUsuario = nombreUsuario;
		this.precio = precio;
		this.fecha = fecha;
		this.productos = new ArrayList<Producto>();
		
	}
	
	//Getters y Setters
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String usuario) {
		this.nombreUsuario = usuario;
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
	
	
//	public class GestorCarrito{
//		
//		// Parte no static
//		private ArrayList<Carrito> carritos;
//		
//		/** Crea un nuevo gestor de carritos partiendo de una lista de carritos existente
//		 * 
//		 */
//		public GestorCarrito(ArrayList<Carrito> carritos) {
//			super();
//			this.carritos = carritos;
//		}
//
//		/** Crea un nuevo gestor de carritos con una serie de datos de ejemplo (3 camisetas y 2 pantalones)
//		 */
//		public GestorCarrito() {
//			super();
//			this.carritos = new ArrayList<Carrito>();
//			getCarritos();
//		}
//		
//		/** Devuelve la lista de carritos del gestor
//		 * 
//		 */
//		public ArrayList<Carrito> getCarritos() {
//			return carritos;
//		}
//
//		/** Modifica la lista de carritos del gestor
//		 * 
//		 */
//		public void setCarritos(ArrayList<Carrito> Carritos) {
//			this.carritos = carritos;
//		}
//
//		@Override
//		public String toString() {
//			return "GestorCarrito [carritos=" + carritos + "]";
//		}
//				
//	}
}
