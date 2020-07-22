package ao.com.biblioteca.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ao.com.biblioteca.dao.EnderecoDAO;
import ao.com.biblioteca.modelo.Endereco;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Endereco> enderecosFiltrados;
	
	private String nome;

	@Inject
	EnderecoDAO enderecoDAO;
	
	private List<Endereco> enderecos = new ArrayList<>();
	
	private Endereco enderecoSelecionado;
	
	public List<Endereco> getMedicamentos() {
		return enderecos;
	}
	
	public void pesquisar() {
		this.enderecosFiltrados = this.enderecoDAO.filtrados(this.nome);
	}
	
	public void excluir() {
		try {
			enderecoDAO.excluir(enderecoSelecionado);
			this.enderecosFiltrados.remove(enderecoSelecionado);
			FacesUtil.addSuccessMessage("Endereços " + enderecoSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	
	public List<Endereco> getEnderecosFiltrados() {
		return enderecosFiltrados;
	}

	public void setEnderecosFiltrados(List<Endereco> enderecosFiltrados) {
		this.enderecosFiltrados = enderecosFiltrados;
	}

	public Endereco getenderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setenderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void inicializar() {
		enderecos = enderecoDAO.buscarTodos();
	}
}