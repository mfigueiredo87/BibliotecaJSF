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
import ao.com.biblioteca.dao.EditoraDAO;
import ao.com.biblioteca.dao.LivroDAO;
import ao.com.biblioteca.modelo.Autor;
import ao.com.biblioteca.modelo.Editora;
import ao.com.biblioteca.modelo.Livro;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaLivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Livro> livrosFiltrados;
	private String titulo;

	@Inject
	LivroDAO livroDAO;
	@Inject
	AutorDAO autorDAO;
	@Inject
	EditoraDAO editoraDAO;
	@Inject
	private EntityManager manager;

	private List<Livro> livros = new ArrayList<>();

	private Livro livroSelecionado;

	public List<Livro> getLivros() {
		return livros;
	}

	public void pesquisar() {
		this.livrosFiltrados = this.livroDAO.filtrados(this.titulo);
	}

	public void excluir() {
		try {
			livroDAO.excluir(livroSelecionado);
			this.livrosFiltrados.remove(livroSelecionado);
			FacesUtil.addSuccessMessage("Livro " + livroSelecionado.getTitulo() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<Livro> getLivrosFiltrados() {
		return livrosFiltrados;
	}

	public void setLivrosFiltrados(List<Livro> livrosFiltrados) {
		this.livrosFiltrados = livrosFiltrados;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		livros = livroDAO.buscarTodos();
	}

}