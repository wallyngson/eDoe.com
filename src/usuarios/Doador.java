package usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import itens.Item;

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
	 * Sobscreve o metodo que esta em usuario, adicionando um item passado por parametro ao doador.
	 */
	@Override
	public void adicionaItemDoacao(Integer idItem, Item item) {
		this.itens.put(idItem, item);
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
	public void validaItem(Integer idItem) {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (this.itens.get(idItem) == null)
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
		this.validaItem(idItem);
		
		this.itens.remove(idItem);
	}
	
	/**
	 * Verifica se nao existe nenhum item cadastrado no doador.
	 */
	private void semItensCadastrados() {
		if (this.itens.size() == 0)
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
	}
	
	@Override
	public List<Item> retornaItens() {
		List<Item> itensDoUsuario = new ArrayList<>();
		itensDoUsuario.addAll(this.itens.values());
		
		return itensDoUsuario;
	}
	
	@Override
	public String nomeItem(Integer id) {
		return this.itens.get(id).getNome();
	}
	
}