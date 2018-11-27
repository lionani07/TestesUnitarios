package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDvidirPorZeroException;

public class CalculadoraTest {
	
	private Calculadora cal;
	
	@Before
	public void setup(){
		cal = new Calculadora();
	}
	
	
	@Test
	public void debeSumarDoisValores(){
		
		//cenario
		int a =5;
		int b = 3;					
		
		//acao
		int resultado = cal.somar(a, b);
		
		//verificacion
		Assert.assertEquals(8, resultado);
		
	}
	
	@Test
	public void debeSustrairDoisValores(){
		
		//cenario
		int a = 10;
		int b = 5;		
		
		//acao
		int resultado = cal.resta(a, b);
		
		//Verifica
		
		Assert.assertEquals(5, resultado);
	}
	
	@Test
	public void debeDividirdoisValores() throws NaoPodeDvidirPorZeroException{
		
		//cenario
		int a = 10;
		int b = 5;
		
				
		//acao
		int resultado = cal.divide(a, b);
		
		//verificacao
		Assert.assertEquals(2, resultado);
		
		
	}
	
	@Test(expected = NaoPodeDvidirPorZeroException.class)
	public void deveLanzarExcepcionDividirPorZero() throws NaoPodeDvidirPorZeroException{
		
		int a = 10;
		int b = 0;
		
		cal.divide(a, b);
				
		
		
		
	}

}
