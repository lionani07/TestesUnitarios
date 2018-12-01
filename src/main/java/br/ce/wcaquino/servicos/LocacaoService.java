package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException{
		
		
		if(usuario==null){
			throw new LocadoraException("Usuario vazio");
		}
		if(filmes==null || filmes.isEmpty()){
			throw new LocadoraException("Lista de Filmes vazios");
		}
		for (Filme filme2 : filmes) {
			if(filme2.getEstoque()==0){
				throw new FilmeSemEstoqueException();
			}
		}		
		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		Double valorTotal = 0d;
		for (int i=0; i<filmes.size(); i++) {
			Filme filme = filmes.get(i);
			Double valor = filme.getPrecoLocacao();
			
			switch (i) {
			case 2: valor *=0.75; break;
			case 3: valor *=0.50; break;
			case 4: valor *=0.25; break;
			case 5: valor = 0d; break;
			}
			valorTotal += valor;
		}
		locacao.setValor(valorTotal);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
			dataEntrega = adicionarDias(dataEntrega, 1);
		}
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	
}