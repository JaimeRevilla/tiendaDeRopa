package pruebasUnitarias;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.BD;
import clases.Producto;

public class BDTest {
	private Connection con;
	
	
	@Before
	public void setUp() {
		 con = BD.initBD("SweetWear.db");
	}
	
	@After
	public void tearDown() {
		BD.closeBD(con);
	}
	
	

	@Test
	public void testBorrarTodosLosProductos() {
		BD.borrarTodosLosProductos(con);
		try {
			ArrayList<Producto> al = BD.getTienda(con);
			assertTrue(al.size() == 0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void testInsertarProducto () {
		try {
			ArrayList<Producto> al1 = BD.getTienda(con);
			BD.insertarProductoTienda(con, 99, "ROJO", "CHAMARRA", 80, 400, "TERNUA", "inexistente", "Sudadera", null, null, null, null, null, false, null);
			ArrayList<Producto> al2 = BD.getTienda(con);
			assertTrue(al2.size() == al1.size()+1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}
	
	@Test
	public void testEliminarProducto () {
		try {
			int contAntesDeEliminar = BD.contarProductosTienda(con);
			if (contAntesDeEliminar > 0) {
				ArrayList<Producto> al = BD.getTienda(con);
				if (BD.existeUsuarioConEseNombre(con, al.get(0).getNombre())) {
					BD.eliminarProductoTienda(con, al.get(0).getCodigo());
					int contDespuesDeEliminar = BD.contarProductosTienda(con);
					assertTrue(contAntesDeEliminar == contDespuesDeEliminar + 1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
