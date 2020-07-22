package ao.com.biblioteca.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ao.com.biblioteca.dao.EnderecoDAO;
import ao.com.biblioteca.modelo.Editora;
import ao.com.biblioteca.modelo.Endereco;
import ao.com.biblioteca.service.CadastroEditoraService;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEditoraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Editora editora;
	
	private List<Endereco> enderecos;
	
	@Inject
	private CadastroEditoraService cadastroEditoraService;
	
	@Inject
	private EnderecoDAO enderecoDAO;
	
	public void salvar() {
		try {
			this.cadastroEditoraService.salvar(editora);
			FacesUtil.addSuccessMessage("Editora salva com sucesso!");
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
		this.editora = new Editora();
		
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	
}