package pruebasUnitarias;

import static org.junit.Assert.*;

import org.junit.Test;

import clases.Usuario;

public class UsuarioTest {

	@Test
	public void test() {
		Usuario u1 = new Usuario("Jon", 33, "jonJon123@jonJon.com", "Jon", false);
		assertEquals("jonJon123@jonJon.com", u1.getMail());
		
		Usuario u2 = new Usuario("Mikel", 33, "Mikel", "Mikel", false);
		assertEquals("Mikel@sweetWear.com", u2.getMail());
	}

}
