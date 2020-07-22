package ao.com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import ao.com.biblioteca.dao.EditoraDAO;
import ao.com.biblioteca.modelo.Editora;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaEditoraBean implements Serializable {

	private static final long serialVersionUID = 1L;

private List<Editora> editorasFiltrados;
	
	private String nome;
	
	@Inject
	EditoraDAO editoraDAO;
	
	@Inject
	private EntityManager manager;

	private List<Editora> editoras = new ArrayList<>();
	
	private Editora editoraSelecionado;
	
	public List<Editora> getEditoras() {
		return editoras;
	}
	
	public void pesquisar() {
		this.editorasFiltrados = this.editoraDAO.filtrados(this.nome);
	}
	
	public void excluir() {
		try {
			editoraDAO.excluir(editoraSelecionado);
			this.editorasFiltrados.remove(editoraSelecionado);
			FacesUtil.addSuccessMessage("Editora " + editoraSelecionado.getNome() + " excluída com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	
	public List<Editora> getEditorasFiltrados() {
		return editorasFiltrados;
	}

	public void setEditorasFiltrados(List<Editora> editorasFiltrados) {
		this.editorasFiltrados = editorasFiltrados;
	}

	public Editora getEditoraSelecionado() {
		return editoraSelecionado;
	}

	public void setEditoraSelecionado(Editora editoraSelecionado) {
		this.editoraSelecionado = editoraSelecionado;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void inicializar() {
		editoras = editoraDAO.buscarTodos();
	}
	
	
}