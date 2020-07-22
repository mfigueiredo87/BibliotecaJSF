package ao.com.biblioteca.service;

import java.io.Serializable;

import javax.inject.Inject;

import ao.com.biblioteca.dao.EditoraDAO;
import ao.com.biblioteca.modelo.Editora;
import ao.com.biblioteca.util.jpa.Transactional;

public class CadastroEditoraService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EditoraDAO editoraDAO;
	
	@Transactional
	public void salvar(Editora editora) throws NegocioException {
		
		if (editora.getNome() == null) {
			throw new NegocioException("O Nome é obrigatório");
		}if (editora.getEmail() == null) {
			throw new NegocioException("O email é obrigatório");
		
		}if(editora.getTelefone() == null) {
			throw new NegocioException("O Telefone é obrigatório");
		
		}if(editora.getEndereco() == null) {
			throw new NegocioException("O Endereço é obrigatório");
		}
		
		this.editoraDAO.salvar(editora);
	}

}