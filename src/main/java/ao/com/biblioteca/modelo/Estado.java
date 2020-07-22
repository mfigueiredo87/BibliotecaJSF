package ao.com.biblioteca.modelo;

public enum Estado {

	REQUISITADO("Requisitado"),
	DEVOLVIDO("Devolvido");
		
	private String descricao;
	
	Estado(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
