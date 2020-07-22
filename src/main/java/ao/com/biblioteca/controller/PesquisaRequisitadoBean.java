package ao.com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;



import ao.com.biblioteca.dao.ClienteDAO;
import ao.com.biblioteca.dao.LivroDAO;
import ao.com.biblioteca.dao.RequisicaoDAO;
import ao.com.biblioteca.modelo.Requisicao;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaRequisitadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

    private List<Requisicao> requisicaoFiltrados;
	
	private String estado;
	

	@Inject
	RequisicaoDAO requisicaoDAO;
	@Inject
	ClienteDAO clienteDAO;
	@Inject
	LivroDAO livrooDAO;
	
	@Inject
	private EntityManager manager;

	private List<Requisicao> requisicoes = new ArrayList<>();
	
	private Requisicao requisicaoSelecionado;
	
 	public List<Requisicao> getRequisicaoFiltrados() {
		return requisicaoFiltrados;
	}


	public void setRequisicaoFiltrados(List<Requisicao> requisicaoFiltrados) {
		this.requisicaoFiltrados = requisicaoFiltrados;
	}


	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}


	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}


	public Requisicao getRequisicaoSelecionado() {
		return requisicaoSelecionado;
	}


	public void setRequisicaoSelecionado(Requisicao requisicaoSelecionado) {
		this.requisicaoSelecionado = requisicaoSelecionado;
	}


	public void pesquisar() {
		this.requisicaoFiltrados = this.requisicaoDAO.filtrados(this.estado);
	}
	
	public void requisitados() {
		this.requisicaoFiltrados = this.requisicaoDAO.buscarRequisitados();
	}
	
	public void devolvidos() {
		this.requisicaoFiltrados = this.requisicaoDAO.buscarDevolvidos();
	}
	
	public void excluir() {
		try {
			requisicaoDAO.excluir(requisicaoSelecionado);
			this.requisicaoFiltrados.remove(requisicaoSelecionado);
			FacesUtil.addSuccessMessage("Requisicao " + requisicaoSelecionado.getEstado() + " excluída com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}



	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	@PostConstruct
	public void inicializar() {
		requisicoes = requisicaoDAO.buscarTodos();
	}
	
}