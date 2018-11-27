package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	private LocacaoService service;
	
	

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup(){		
		service = new LocacaoService();
		
	}
	
	@Test
	public void testLocacao() throws Exception {

		// Cenario
		List<Filme> filmes = new ArrayList<Filme>();
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 2.5);
		Filme filme2 =  new Filme("Filme 1", 2, 2.5);
		filmes.add(filme);
		filmes.add(filme2);
		
		

		// acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		// validacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(
				DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
				is(true));
		error.checkThat(
				DataUtils.isMesmaData(locacao.getDataRetorno(),
						DataUtils.obterDataComDiferencaDias(1)), is(true));
		
		

	}

	// Forma Elegante
	@Test(expected = FilmeSemEstoqueException.class)
	public void test_locacao_FilmeSemEstoque() throws Exception {

		// Cenario
				List<Filme> filmes = new ArrayList<Filme>();
				LocacaoService service = new LocacaoService();
				Usuario usuario = new Usuario("Usuario 1");
				Filme filme = new Filme("Filme 1", 0, 5.0);
				Filme filme2 =  new Filme("Filme 1", 0, 5.0);
				filmes.add(filme);
				filmes.add(filme2);
				
				

				// acao
				Locacao locacao = service.alugarFilme(usuario, filmes);
		
		
	}
	
	//Forma Robusta
	@Test
	public void TestLocacaoUsuarioVazio() throws FilmeSemEstoqueException{
		
		List<Filme> filmes = new ArrayList<Filme>();
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);
		Filme filme2 =  new Filme("Filme 1", 2, 5.0);
		filmes.add(filme);
		filmes.add(filme2);
		//acao
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
		
		
		
		
	}
	
	//Forma Nova
	@Test
	public void testeLocacao_ValidaFilme() throws FilmeSemEstoqueException, LocadoraException{

		//Cenario		
		Usuario usuario = new Usuario("User");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Lista de Filmes vazios");
		//acao
		service.alugarFilme(usuario, null);
		
		
	}

	

}
