package itens;

import java.util.Arrays;

import util.Validador;

/**
 * Classe que representa um item que pode ser doado e adicionado na lista de itens doaveis do doador
 * @author Dacio Bezerra, Felipe Nunes, Victor Paz e Wallyngson Guedes.
 *
 */
public class ItemDoavel implements Item {
	private String nome;
	private String[] tags;
	private Integer idItem;
	private int qtdItem;
	private String representacaoUsuario;
	private int pontuacao;
	
	private Validador validador = new Validador();
	
	/**
	 * Construtor da classe ItemDoavel
	 * @param nome
	 * @param qtd
	 * @param tags
	 * @param idItem
	 * @param representacaoUsuario
	 */
	public ItemDoavel(String nome, int qtd, String tags, Integer idItem, String representacaoUsuario) {
		this.validador.quatidadeInvalida(qtd);

		this.nome = nome;
		this.tags = tags.split(",");
		this.qtdItem = qtd;
		this.idItem = idItem;
		this.representacaoUsuario = representacaoUsuario;
	}
	
	/**
	 * Retorna o id do item atual
	 */
	public int getIdItem() {
		return this.idItem;
	}
	
	/**
	 * Retorna a descricao completa do item
	 */
	public String descricaoCompleta() {
		return this.nome + " - " + Arrays.toString(tags);
	}
	
	/**
	 * Metodo que retorna um array de Strings com as tags
	 */
	public String[] getTags() {
		return this.tags;
	}


	/**
	 * Metodo que retorna a pontuacao
	 * @return um inteiro representando a pontuacao
	 */
	@Override
	public int getPontuacao() {
		return this.pontuacao;
	}

	
	/**
	 * Metodo que insere a pontuacao e soma a pontuacao atual do item
	 * @param pontuacao
	 */
	public void setPontuacao(int pontuacao) {
		this.pontuacao += pontuacao;
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

//	@Override
	public String toStringParaRealizarDoacao() {
		return null;
	}

	@Override
	public String toString() {
		return this.idItem + " - " + this.nome + ", tags: " + Arrays.toString(tags) + ", quantidade: " + this.qtdItem;
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
	
	/**
	 * Metodo que retorna a representacao do usuario que possui esse item
	 */
	public String getUsuarioVinculado() {
		return this.representacaoUsuario;
	}
}