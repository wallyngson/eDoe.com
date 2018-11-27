package usuarios;

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
		this.parametrosInvalidos(id, nome, email, celular, classe);

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = getStatus();
	}

	/**
	 * Metodo que verifica se todos os parametros sao validos.
	 * 
	 * @param id
	 * @param nome
	 * @param email
	 * @param celular
	 * @param classe
	 */
	private void parametrosInvalidos(String id, String nome, String email, String celular, String classe) {
		if (nome == null || nome.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		if (email == null || email.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		if (celular == null || celular.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		this.validaClasse(classe);
	}

	/**
	 * Valida se a classe do usuario eh valida.
	 * 
	 * @param classe
	 * @return
	 */
	private Boolean validaClasse(String classe) {
		if (classe == null || classe.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");

		for (int i = 0; i < classes.length; i++) {
			if (classe.equals(classes[i]))
				return true;
		}

		throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
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

	public String getClasse() {
		return this.classe;
	}

	/**
	 * Esse metodo deve ser sobscrito nos doadores, pois ira cadastrar todos os
	 * itens de cada doador.
	 * 
	 * @param descritor
	 * @param qtd
	 * @param tags
	 */
	public Integer adicionaItemDoacao(String descritor, int qtd, String tags) {
		return null;
	}

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
	
	/**
	 * Metodo que deve retornar o item atualizado, sobscrito pelo metodo do doador.
	 * 
	 * @param idItem
	 * @param qtd
	 * @param tags
	 * @return
	 */
	public String atualizaItem(Integer idItem, int qtd, String tags) {
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario outroUsuario = (Usuario) obj;
			return this.id.equals(outroUsuario.getId());
		}
		return false;
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
