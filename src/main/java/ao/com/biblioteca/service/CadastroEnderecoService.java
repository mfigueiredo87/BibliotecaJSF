package ao.com.biblioteca.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import ao.com.biblioteca.dao.EnderecoDAO;
import ao.com.biblioteca.modelo.Endereco;
import ao.com.biblioteca.util.jpa.Transactional;

public class CadastroEnderecoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnderecoDAO enderecoDAO;
	
	@Transactional
	public void salvar(Endereco end) throws NegocioException {
		
		if (StringUtils.isBlank(end.getNome())) {
			throw new NegocioException("O nome de Endereço é obrigatório");
		}
		
		this.enderecoDAO.salvar(end);
	}
	
}
