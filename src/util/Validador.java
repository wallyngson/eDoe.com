package util;

import java.io.Serializable;
import java.util.Map;

import itens.Descritor;
import itens.Item;
import usuarios.Usuario;

/**
 * Classe validadora de estados e parametros
 * @author Dacio Bezerra, Felipe Nunes, Victor Paz e Wallyngson Guedes.
 *
 */
public class Validador implements Serializable{
	
	
	// USUARIOS
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Verifica se o Id passador por parametro eh invalido.
	 * 
	 * @param id
	 */
	public void idInvalido(String id) {
		if (id == null || id.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
	}


	/**
	 * Verifica se o usuario existe no sistema.
	 * 
	 * @param id
	 */
	public void validaUsuario(String id, Map<String, Usuario> usuarios) {
		if (usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
	}
	

	/**
	 * Verifica se o usuario nao existe no sistema.
	 * 
	 * @param id
	 */
	public void usuarioInexistente(String id, Map<String, Usuario> usuarios) {
		if (!usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
	}
	
	/**
	 * Verifica se o nome eh invalido.
	 * 
	 * @param nome
	 */
	public void nomeInvalido(String nome) {
		if (nome == null || nome.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
	}


	public void descritorInvalido(String descritor) {
		if (descritor == null || descritor.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
	}
	
	
	// DESCRITORES
	
	
	/**
	 * Verifica se o descritor ja esta cadastrado.
	 * 
	 * @param descritor
	 */
	public void descritorCadastrado(String descritor, Map<String, Descritor> descritores) {
		if (descritores.containsKey(this.formataString(descritor)))
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descritor.toLowerCase() + ".");
	}
	
	/**
	 * Tira todos os espacos e deixa tudo minusculo.
	 * 
	 * @param string
	 * @return
	 */
	private String formataString(String string) {
		return string.replace(" ", "").toLowerCase();
	}
	
	
	// ITENS
	
	/**
	 * Verifica se o item nao esta cadastrado no doador.
	 * 
	 * @param idItem
	 */
	public void validaItem(Integer idItem, Map<Integer, Item> itens) {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (itens.get(idItem) == null)
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
	}
	
	public void quatidadeInvalida(int qtd) {
		if (qtd <= 0)
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
	}
	
	
	/**
	 * Verifica se nao existe nenhum item cadastrado no doador.
	 */
	public void semItensCadastrados(Map<Integer, Item> itens) {
		if (itens.size() == 0)
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
	}
	
	// USUARIOS
	
	
	/**
	 * Metodo que verifica se todos os parametros sao validos.
	 * 
	 * @param id
	 * @param nome
	 * @param email
	 * @param celular
	 * @param classe
	 */
	public void parametrosUsuarioInvalidos(String id, String nome, String email, String celular, String classe, String[] classes) {
		if (nome == null || nome.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		if (email == null || email.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		if (celular == null || celular.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		if (this.validaClasse(classe, classes) == false)
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
	}
	
	/**
	 * Valida se a classe do usuario eh valida.
	 * 
	 * @param classe
	 * @return
	 */
	private Boolean validaClasse(String classe, String[] classes) {
		if (classe == null || classe.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");

		for (int i = 0; i < classes.length; i++) {
			if (classe.equals(classes[i]))
				return true;
		}
		return false;
		
	}
	
	/**
	 * Verifica se a string pesquisa está nula ou vazia,  lançando exceção em caso afirmativo.
	 * @param descritor
	 */
	public void validaPesquisa(String descritor) { 
		if(descritor == null || descritor.trim().isEmpty()) 
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
	}

}
