package itens;

import java.util.Arrays;

import util.Validador;

/**
 * Classe que representa um item necessario, apto a fazer parte da lista de itens necessarios aos receptores
 * @author  Dacio Bezerra, Felipe Nunes, Victor Paz e Wallyngson Guedes.
 *
 */
public class ItemNecessario implements Item {
	private String nome;
	private String[] tags;
	private Integer idItem;
	private int qtdItem;
	private String representacaoUsuario;
	private int potuacao;
	private static final long serialVersionUID = 2L;
	
	private Validador validador = new Validador();
	
	/**
	 * Construtor da classe ItemNecessario
	 * @param nome
	 * @param qtd
	 * @param tags
	 * @param idItem
	 * @param representacaoUsuario
	 */
	public ItemNecessario(String nome, int qtd, String tags, Integer idItem, String representacaoUsuario) {
		this.validador.quatidadeInvalida(qtd);

		this.nome = nome;
		this.tags = tags.split(",");
		this.qtdItem = qtd;
		this.idItem = idItem;
		this.representacaoUsuario = representacaoUsuario;
	}
	
	/**
	 * Metodo que retorna a representacao do usuario que possui esse item
	 */
	public String getUsuarioVinculado() {
		return representacaoUsuario;
	}
	
	/**
	 * Retorna o id do item atual
	 */
	public int getIdItem() {
		return this.idItem;
	}
	
	/**
	 * Metodo que retorna um array de Strings com as tags
	 */
	public String[] getTags() {
		return this.tags;
	}

	@Override
	public int getPontuacao() {
		return this.potuacao;
	}

	/**
	 * Retorna a descricao completa do item
	 */
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

	/**
	 * Metodo que retorna  o nome do item
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Metodo que retorna a quantidade do item
	 */
	public int getQtdItem() {
		return qtdItem;
	}
	
	/**
	 * Metodo que insere a quantidade desse item
	 */
	public void setQtdItem(int qtdItem) {
		this.qtdItem = qtdItem;
	}

	@Override
	public String toString() {
		return this.idItem + " - " + this.nome.toLowerCase() + ", tags: " + Arrays.toString(tags) + ", quantidade: " + this.qtdItem;
	}
	
	@Override
	public int compareTo(Item other) {
		if (this.qtdItem < other.getQtdItem())
			return 1;
		
		if (this.qtdItem > other.getQtdItem())
			return -1;
		
		return compareToNome(other);
	}

	@Override
	public int compareToNome(Item i) {
		return this.getNome().compareTo(i.getNome());
	}
	
	public String toStringParaRealizarDoacao() {
		return this.nome.toLowerCase() + ", quantidade: " + this.qtdItem;
	}
}
