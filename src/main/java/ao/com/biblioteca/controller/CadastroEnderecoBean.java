package ao.com.biblioteca.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ao.com.biblioteca.modelo.Endereco;
import ao.com.biblioteca.service.CadastroEnderecoService;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEnderecoBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroEnderecoService cadastroEnderecoService;
	
	private Endereco endereco;
	
	public void salvar() {
		try {
			this.cadastroEnderecoService.salvar(endereco);
			FacesUtil.addSuccessMessage("Endereço salvo com sucesso!");
			
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		this.limpar();
	}
	
	public void limpar() {
		this.endereco = new Endereco();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	
}