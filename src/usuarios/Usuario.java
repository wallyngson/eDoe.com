package usuarios;

import java.util.Arrays;
import java.util.List;

import itens.Item;
import util.Validador;

/**
 * Classe abstrata que representa um Usuario do sistema de doacoes, podendo ser
 * Receptor ou Doador.
 * 
 * @author Dacio Bezerra, Felipe Nunes, Victor Paz e Wallyngson Guedes.
 *
 */
public abstract class Usuario implements Comparable<Usuario> {

	private String[] classes = { "PESSOA_FISICA", "IGREJA", "ORGAO_PUBLICO_MUNICIPAL", "ORGAO_PUBLICO_ESTADUAL",
			"ORGAO_PUBLICO_FEDERAL", "ONG", "ASSOCIACAO", "SOCIEDADE" };

	private String id, nome, email, celular, classe, status;
	private Validador validador = new Validador();

	/**
	 * Construtor da classe Usuario.
	 * 
	 * @param id
	 * @param nome
	 * @param email
	 * @param celular
	 * @param classe
	 * 
	 */
	public Usuario(String id, String nome, String email, String celular, String classe) {
		this.validador.parametrosUsuarioInvalidos(id, nome, email, celular, classe, classes);

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = getStatus();
	}

	public String nomeItem(Integer id) {
		return null;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public String getId() {
		return this.id;
	}


	/**
	 * Esse metodo deve ser sobscrito nos doadores, pois ira cadastrar todos os
	 * itens de cada doador.
	 * 
	 * @param descritor
	 * @param qtd
	 * @param tags
	 */
	public void adicionaItem(Integer idItem, Item item) {}

	/**
	 * Esse metodo deve ser sobscrevido pelo metodo de doador que exibe um item
	 * cadastrado.
	 * 
	 * @param descritor
	 * @return
	 */
	public String exibeItem(Integer idItem) {
		return null;
	}
	
	/**
	 * Metodo que remove itens de um doador, deve ser subscrito por um metodo em
	 * doador.
	 * 
	 * @param descritor
	 */
	public void removeItem(Integer idItem) {}
	
	public void validaItem(Integer idItem) {}
	
	public List<Item> retornaItens() {
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((classe == null) ? 0 : classe.hashCode());
		result = prime * result + Arrays.hashCode(classes);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", " + "status: " + this.status;
	}

	/**
	 * Ordena os Usuarios em ordem decrescente de ID.
	 *
	 */
	@Override
	public int compareTo(Usuario outroUsuario) {
		return this.id.compareTo(outroUsuario.getId());
	}

}
