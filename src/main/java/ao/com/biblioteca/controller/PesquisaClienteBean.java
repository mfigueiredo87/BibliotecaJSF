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
import ao.com.biblioteca.dao.ClienteDAO;
import ao.com.biblioteca.modelo.Autor;
import ao.com.biblioteca.modelo.Cliente;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;


@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

private List<Cliente> clientesFiltrados;
	
	private String nome;
	
	@Inject
	ClienteDAO clienteDAO;
	
	@Inject
	private EntityManager manager;

	private List<Cliente> clientes = new ArrayList<>();
	
	private Cliente clienteSelecionado;
	
	public List<Cliente> getClientees() {
		return clientes;
	}
	
	public void pesquisar() {
		this.clientesFiltrados = this.clienteDAO.filtrados(this.nome);
	}
	
	public void excluir() {
		try {
			clienteDAO.excluir(clienteSelecionado);
			this.clientesFiltrados.remove(clienteSelecionado);
			FacesUtil.addSuccessMessage("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}



	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void inicializar() {
		clientes = clienteDAO.buscarTodos();
	}
	
	
}