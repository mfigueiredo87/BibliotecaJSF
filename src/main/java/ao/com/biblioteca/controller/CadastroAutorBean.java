package ao.com.biblioteca.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ao.com.biblioteca.dao.EnderecoDAO;
import ao.com.biblioteca.modelo.Autor;
import ao.com.biblioteca.modelo.Endereco;
import ao.com.biblioteca.service.CadastroAutorService;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor;
	
	private List<Endereco> enderecos;
	
	@Inject
	private CadastroAutorService cadastroAutorService;
	
	@Inject
	private EnderecoDAO enderecoDAO;
	
	public void salvar() {
		try {
			this.cadastroAutorService.salvar(autor);
			FacesUtil.addSuccessMessage("Autor salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage("Erro desconhecido. Contactar o administrador");
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.enderecos = this.enderecoDAO.buscarTodos();
	}
	
	public void limpar() {
		this.autor = new Autor();
		
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	
}