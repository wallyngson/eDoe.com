package Itens;

public class Item {

	private String nome, tags;
	private int qtdItem;

	public Item(String nome, int qtd, String tags) {
		this.parametrosInvalidos(tags, qtd);

		this.nome = nome;
		this.tags = tags;
		this.qtdItem = qtd;
	}

	/**
	 * Verifica se algum dos parametros passados sao nulos ou invalidos.
	 * 
	 * @param tags
	 * @param qtd
	 */
	private void parametrosInvalidos(String tags, int qtd) {
		if (qtd <= 0)
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		if (tags == null || tags.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: tags nao podem ser vazias ou nulas");
	}
	
	public void setQtdItens(int qtd) {
		this.qtdItem = qtd;
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		return nome + ", tags: [" + tags + "], quantidade: " + qtdItem;
	}

}
