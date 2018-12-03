package itens;

import java.util.Arrays;
import util.Validador;

public class Item implements Comparable<Item>{

	private String nome;
	private String[] tags;
	private Integer idItem;
	private int qtdItem;
	
	private Validador validador = new Validador();

	public Item(String nome, int qtd, String tags, Integer idItem) {
		this.validador.quatidadeInvalida(qtd);

		this.nome = nome;
		this.tags = tags.split(",");
		this.qtdItem = qtd;
		this.idItem = idItem;
	}

	public int getIdItem() {
		return this.idItem;
	}
	
	public String descricaoCompleta() {
		return this.nome + " - " + Arrays.toString(tags);
	}
	
	/**
	 * Metodo responsavel por atualizar os valores do item a ser mudado,
	 * 
	 * @param qtd
	 * @param tags
	 * @return
	 */
	public String atualizaItem(int qtd, String tags) {
		if (tags != null && !tags.trim().isEmpty())
			this.tags = tags.split(",");
		if (qtd > 0)
			this.setQtdItem(qtd);
		
		return this.toString();
	}

	
	public String getNome() {
		return this.nome;
	}

	public int getQtdItem() {
		return qtdItem;
	}

	public void setQtdItem(int qtdItem) {
		this.qtdItem = qtdItem;
	}

	@Override
	public String toString() {
		return this.idItem + " - " + this.nome + ", tags: " + Arrays.toString(tags) + ", quantidade: " + this.qtdItem;
	}

	@Override
	public int compareTo(Item other) {
		if (this.qtdItem < other.qtdItem)
			return 1;
		
		if (this.qtdItem > other.qtdItem)
			return -1;
		
		return compareToNome(other);
	}
	
	public int compareToNome(Item i) {
		return this.getNome().compareTo(i.getNome());
	}

}
