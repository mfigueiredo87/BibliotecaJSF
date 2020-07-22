package ao.com.biblioteca.service;

import java.io.Serializable;

import javax.inject.Inject;

import ao.com.biblioteca.dao.AutorDAO;
import ao.com.biblioteca.modelo.Autor;
import ao.com.biblioteca.util.jpa.Transactional;

public class CadastroAutorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AutorDAO autorDAO;
	
	@Transactional
	public void salvar(Autor autor) throws NegocioException {
		
		if (autor.getNome() == null) {
			throw new NegocioException("O nome é obrigatório");
		}if (autor.getEmail() == null) {
			throw new NegocioException("O email é obrigatório");
		}if(autor.getTelefone() == null) {
			throw new NegocioException("O Telefone é obrigatório");
		}if(autor.getNacionalidade() == null) {
			throw new NegocioException("A nacionalidade é obrigatória0");
		}if(autor.getEndereco() == null) {
			throw new NegocioException("O Endereço é obrigatório");
		}
		
		this.autorDAO.salvar(autor);
	}

}