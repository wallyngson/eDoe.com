package itens;

public class Descritor {
	
	private int qtdItens;
	private String descritor, tags;
	
	public Descritor(String descritor) {
		this.parametroInvalido(descritor);
		
		this.descritor = descritor;
	}
	
	private void parametroInvalido(String descritor) {
		if (descritor == null || descritor.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
	}
	
	public void setQtdItens(int qtd) {
		this.qtdItens = qtd;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public String nomeDescritor() {
		return this.descritor;
	}

	public int getQtdItens() {
		return qtdItens;
	}

	public String getTags() {
		return tags;
	}
	
	
	
	
}
