package ao.com.biblioteca.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ao.com.biblioteca.dao.ClienteDAO;
import ao.com.biblioteca.dao.LivroDAO;
import ao.com.biblioteca.modelo.Cliente;
import ao.com.biblioteca.modelo.Estado;
import ao.com.biblioteca.modelo.Livro;
import ao.com.biblioteca.modelo.Requisicao;
import ao.com.biblioteca.modelo.Sexo;
import ao.com.biblioteca.service.CadastroRequisicaoService;
import ao.com.biblioteca.service.NegocioException;
import ao.com.biblioteca.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroRequisicaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Requisicao requisicao;
	
	private List<Livro> livros;
	private List<Cliente> clientes;
	private List<Estado> estados;
	
	@Inject
	private CadastroRequisicaoService cadastroRequisicaoService;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@Inject
	private LivroDAO livroDAO;
	
	public void salvar() {
		try {
			this.cadastroRequisicaoService.salvar(requisicao);
			FacesUtil.addSuccessMessage("Requisição salva com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage("Erro desconhecido. Contactar o administrador");
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.livros = this.livroDAO.buscarTodos();
		this.clientes = this.clienteDAO.buscarTodos();
		this.estados = Arrays.asList(Estado.values());
	}
	
	public void limpar() {
		this.requisicao = new Requisicao();
		
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	
	
}