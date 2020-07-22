package ao.com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;


import ao.com.biblioteca.dao.AutorDAO;
import ao.com.biblioteca.modelo.Autor;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaAutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

private List<Autor> autoresFiltrados;
	
	private String nome;
	
	@Inject
	AutorDAO autorDAO;
	
	@Inject
	private EntityManager manager;

	private List<Autor> autores = new ArrayList<>();
	
	private Autor autorSelecionado;
	
	public List<Autor> getAutores() {
		return autores;
	}
	
	public void pesquisar() {
		this.autoresFiltrados = this.autorDAO.filtrados(this.nome);
	}
	
	public void excluir() {
		try {
			autorDAO.excluir(autorSelecionado);
			this.autoresFiltrados.remove(autorSelecionado);
			FacesUtil.addSuccessMessage("Autor " + autorSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<Autor> getAutoresFiltrados() {
		return autoresFiltrados;
	}

	public void setAutoresFiltrados(List<Autor> autoresFiltrados) {
		this.autoresFiltrados = autoresFiltrados;
	}

	public Autor getAutorSelecionado() {
		return autorSelecionado;
	}

	public void setAutorSelecionado(Autor autorSelecionado) {
		this.autorSelecionado = autorSelecionado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void inicializar() {
		autores = autorDAO.buscarTodos();
	}
	
	
}