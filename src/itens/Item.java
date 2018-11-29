package itens;

import java.util.Arrays;

public class Item implements Comparable<Item>{

	private String nome;
	private String[] tags;
	private int qtdItem;
	private Integer idItem;

	public Item(String nome, int qtd, String tags, Integer idItem) {
		this.parametrosInvalidos(tags, qtd);

		this.nome = nome;
		this.tags = tags.split(",");
		this.qtdItem = qtd;
		this.idItem = idItem;
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
	}
	
	public void setQtdItens(int qtd) {
		this.qtdItem = qtd;
	}
	
	public int getIdItem() {
		return this.idItem;
	}
	
	public void setTags(String tags) {
		this.tags = tags.split(",");
	}
	
	public String descricaoCompleta() {
		return this.nome + " - " + Arrays.toString(tags);
	}
	
	public String getNome() {
		return this.nome;
	}

	@Override
	public String toString() {
		return this.idItem + " - " + this.nome + ", tags: " + Arrays.toString(tags) + ", quantidade: " + this.qtdItem;
	}

	@Override
	public int compareTo(Item arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
