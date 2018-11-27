package Usuarios;

import java.util.HashMap;
import java.util.Map;

import Itens.Item;

/**
 * Classe que representa um doador.
 * 
 * @author Dacio Bezerra, Felipe Nunes, Victor Paz e Wallyngson Guedes.
 *
 */
public class Doador extends Usuario {
	
	private Map<Integer, Item> itens;

	/**
	 * Construtor do Doador.
	 * 
	 * @param id
	 * @param nome
	 * @param email
	 * @param celular
	 * @param classe
	 * 
	 */
	public Doador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		super.setStatus("doador");
		
		this.itens = new HashMap<>();
	}

	/**
	 * Sobscreve o metodo que esta em usuario, adicionando um item ao doador.
	 */
	@Override
	public Integer adicionaItemDoacao(String descritor, int qtd, String tags) {
		Integer idItem = this.itens.size() + 1;
		this.itens.put(idItem, new Item(descritor, qtd, tags));
		
		return idItem;
		
	}
	
	/**
	 * Exibe um item cadastrado no doador.
	 */
	@Override
	public String exibeItem(Integer idItem) {
		this.validaItem(idItem);
		
		return this.itens.get(idItem).toString();
	}
	
	/**
	 * Verifica se o item nao esta cadastrado no doador.
	 * 
	 * @param idItem
	 */
	private void validaItem(Integer idItem) {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (this.itens.get(idItem) != null)
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
	}

	/**
	 * Remove um item passado por parametro do usuario doador.
	 * 
	 * @param descritor
	 */
	@Override
	public void removeItem(Integer idItem) {
		this.semItensCadastrados();
		
		this.itens.remove(idItem);
	}

	/**
	 * Verifica se nao existe nenhum item cadastrado no doador.
	 */
	private void semItensCadastrados() {
		if (this.itens.size() == 0)
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
	}
	
	/**
	 * Atualiza os valores de um item de um doador.
	 */
	@Override
	public String atualizaItem(Integer idItem, int qtd, String tags) {
		this.validaItem(idItem);
		this.atualiza(idItem, qtd, tags);
		
		return this.itens.get(idItem).toString();
	}

	/**
	 * Verifica qual valor do item deve alterar e o altera.
	 * 
	 * @param idItem
	 * @param qtd
	 * @param tags
	 */
	private void atualiza(Integer idItem, int qtd, String tags) {
		if (qtd <= 0 && (tags == null || tags.trim().isEmpty()))
			throw new IllegalArgumentException("Nao foi possivel atualizar o item, valores invalidos");
		if (qtd <= 0)
			this.itens.get(idItem).setTags(tags);
		if (tags == null || tags.trim().isEmpty())
			this.itens.get(idItem).setQtdItens(qtd);
		
	}
	
	
}