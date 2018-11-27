package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDvidirPorZeroException;

public class Calculadora {

	public int somar(int a, int b) {
		return a + b;
	}

	public int resta(int a, int b) {		
		return a - b;
	}

	public int divide(int a, int b) throws NaoPodeDvidirPorZeroException {
		if(b==0){
			throw new NaoPodeDvidirPorZeroException();
		}
		return a/b;
	}

}
