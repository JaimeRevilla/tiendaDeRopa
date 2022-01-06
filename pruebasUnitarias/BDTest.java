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
		Connection con = BD.initBD("SweetWear.db");
	}
	
	@After
	public void tearDown() {
		BD.closeBD(con);
	}
	
	@Before
	public void before() {
		Connection con = BD.initBD("SweetWear.db");
	}
	
	public void after() {
		BD.closeBD(con);
	}

	@Test
	//ESTE TEST NO SE PORQUE NO FUNCIONA
	public void testBorrarTodosLosProductos() {
		BD.borrarTodosLosProductos(con);
		ArrayList<Producto> al = BD.getTienda(con);
		assertTrue(al.size() == 0);
	}
	
	@Test
	//ESTE TEST NO FUNCIONA
	public void testInsertarProducto () {
		ArrayList<Producto> al1 = BD.getTienda(con);
		BD.insertarProductoTienda(con, 99, "ROJO", "CHAMARRA", 80, 400, "TERNUA", "inexistente", "Sudadera", null, null, null, null, null, false, null);
		ArrayList<Producto> al2 = BD.getTienda(con);
		assertTrue(al2.size() == al1.size()+1);

	}
	
	@Test
	//ESTE TEST NO FUNCIONA
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
