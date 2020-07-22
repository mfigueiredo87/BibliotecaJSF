package ao.com.biblioteca.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ao.com.biblioteca.dao.EnderecoDAO;
import ao.com.biblioteca.modelo.Cliente;
import ao.com.biblioteca.modelo.Endereco;
import ao.com.biblioteca.modelo.Sexo;
import ao.com.biblioteca.service.CadastroClienteService;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private List<Sexo> sexos;
	
	private List<Endereco> enderecos;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	@Inject
	private EnderecoDAO enderecoDAO;
	
	public void salvar() {
		try {
			this.cadastroClienteService.salvar(cliente);
			FacesUtil.addSuccessMessage("Cliente salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage("Erro desconhecido. Contactar o administrador" +e);
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.enderecos = this.enderecoDAO.buscarTodos();
		this.sexos = Arrays.asList(Sexo.values());
	}
	
	public void limpar() {
		this.cliente = new Cliente();
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	
	public List<Sexo> getSexos() {
		return sexos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	
}