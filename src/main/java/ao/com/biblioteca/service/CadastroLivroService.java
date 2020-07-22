package ao.com.biblioteca.service;

import java.io.Serializable;

import javax.inject.Inject;

import ao.com.biblioteca.dao.LivroDAO;
import ao.com.biblioteca.modelo.Livro;
import ao.com.biblioteca.util.jpa.Transactional;

public class CadastroLivroService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDAO livroDAO;
	
	@Transactional
	public void salvar(Livro livro) throws NegocioException {
		
		if (livro.getTitulo() == null) {
			throw new NegocioException("O título é obrigatório");
		}if (livro.getPaginas() == null) {
			throw new NegocioException("O número de página é obrigatório");
		}if(livro.getEdicao() == null) {
			throw new NegocioException("A edição é obrigatória");
		}if(livro.getIsbn() == null) {
			throw new NegocioException("O ISBN é obrigatório");
		}if(livro.getEstilo() == null) {
			throw new NegocioException("Indique o estilo/categoria é obrigatória");
		}if(livro.getAutor() == null) {
			throw new NegocioException("O autor é obrigatório.");
		}if(livro.getEditora() == null) {
			throw new NegocioException("A editora é obrigatório.");
		}if(livro.getDataLancamento() == null) {
			throw new NegocioException("Ano de lançamento é obrigatório.");
		}
		
		this.livroDAO.salvar(livro);
	}

}