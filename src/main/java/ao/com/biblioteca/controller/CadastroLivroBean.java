package ao.com.biblioteca.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ao.com.biblioteca.dao.AutorDAO;
import ao.com.biblioteca.dao.EditoraDAO;
import ao.com.biblioteca.modelo.Autor;
import ao.com.biblioteca.modelo.Editora;
import ao.com.biblioteca.modelo.Livro;
import ao.com.biblioteca.service.CadastroLivroService;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroLivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro;
	
	private List<Autor> autores;
	private List<Editora> editoras;
	@Inject
	private CadastroLivroService cadastroLivroService;
	
	@Inject
	private AutorDAO autorDAO;
	
	@Inject
	private EditoraDAO editoraDAO;
	
	public void salvar() {
		try {
			this.cadastroLivroService.salvar(livro);
			FacesUtil.addSuccessMessage("Livro salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage("Erro desconhecido. Contactar o administrador" +e);
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.editoras = this.editoraDAO.buscarTodos();
		this.autores = this.autorDAO.buscarTodos();
	}
	
	public void limpar() {
		this.livro = new Livro();
		
	}
	

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List<Editora> editoras) {
		this.editoras = editoras;
	}

}