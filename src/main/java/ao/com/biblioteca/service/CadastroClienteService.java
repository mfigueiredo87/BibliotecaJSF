package ao.com.biblioteca.service;

import java.io.Serializable;

import javax.inject.Inject;

import ao.com.biblioteca.dao.ClienteDAO;
import ao.com.biblioteca.modelo.Cliente;
import ao.com.biblioteca.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@Transactional
	public void salvar(Cliente cliente) throws NegocioException {
		
		if (cliente.getNome() == null) {
			throw new NegocioException("O cliente é obrigatório");
		}if (cliente.getEmail() == null) {
			throw new NegocioException("O email é obrigatório");
		}if(cliente.getBilhete() == null) {
			throw new NegocioException("O bilhete é obrigatório");
		}if(cliente.getTelefone() == null) {
			throw new NegocioException("O Telefone é obrigatório");
		}if(cliente.getSexo() == null) {
			throw new NegocioException("O Sexo é obrigatório");
		}if(cliente.getEndereco() == null) {
			throw new NegocioException("O Endereço é obrigatório");
		}if(cliente.getDataNascimento() == null) {
			throw new NegocioException("A data é obrigatória");
		}
		
		this.clienteDAO.salvar(cliente);
	}

}