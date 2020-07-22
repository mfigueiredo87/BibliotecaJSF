package ao.com.biblioteca.service;

import java.io.Serializable;

import javax.inject.Inject;

import ao.com.biblioteca.dao.RequisicaoDAO;
import ao.com.biblioteca.modelo.Requisicao;
import ao.com.biblioteca.util.jpa.Transactional;

public class CadastroRequisitadoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RequisicaoDAO requisicaoDAO;
	
	@Transactional
	public void salvar(Requisicao requisicao) throws NegocioException {
		
		if (requisicao.getLivro() == null) {
			throw new NegocioException("O livro é obrigatório");
		}if (requisicao.getCliente() == null) {
			throw new NegocioException("O cliente/beneficiário é obrigatório");
		}if(requisicao.getDataDevolucao() == null) {
			throw new NegocioException("Indique a data de devolução");
	
		}if(requisicao.getEstado() == null) {
			throw new NegocioException("O estado é obrigatório");
		}
		
		this.requisicaoDAO.salvar(requisicao);
	}

}