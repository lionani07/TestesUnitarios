package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void test(){
		Assert.assertTrue(true);
		
		Assert.assertFalse(false);
		
		Assert.assertEquals(2, 2);
		
		Assert.assertEquals(1.5, 1.5, 0.1);
		
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		int i =5;
		Integer i2 = 5;
		
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals(Integer.valueOf(i), i2);
		
		Assert.assertEquals("bola", "bola");
		
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		
		//Utiliza el metodo equal del objeto
		Assert.assertEquals(u1, u2);
		
		Assert.assertNotEquals("no", "si");
		
		
	}

}
