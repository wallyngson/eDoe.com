package usuario;

/**
 * classe abstrata Usuario que define um usuario generico
 * 
 * @author victorpfb
 *
 */
public abstract class Usuario implements Comparable<Usuario> {

	private String id;
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;

	/**
	 * Construtor da classe usuario. Recebe um id, nome, email, celular, e classe
	 * 
	 * @param id
	 *            String que representa a numeracao do documento do usuario
	 * @param nome
	 *            String que representa o nome do usuario
	 * @param email
	 *            String que representa o email do usuario
	 * @param celular
	 *            String que representa o numero de telefone do usuario
	 * @param classe
	 *            String que representa a que classe pertence o usuario (Ex:
	 *            PESSOA_FISICA, IGREJA, ETC); o intuito aqui eh determinar se o
	 *            usuario tera cpf ou cnpj
	 */
	public Usuario(String id, String nome, String email, String celular, String classe) {

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = getStatus();
	}

	/**
	 * Setter que altera o atributo nome atraves de uma String nome que recebe
	 * 
	 * @param nome
	 *            String que sera o novo nome de usuario
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Setter que altera o atributo email atraves de uma String email que recebe
	 * 
	 * @param email
	 *            String que sera o novo email de usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Setter que altera o atributo celular atraves de uma String celular que recebe
	 * 
	 * @param celular
	 *            String que sera o novo celular de usuario
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return Getter que retorna o atributo nome * String que sera o novo nome de
	 *         usuario
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Setter que altera o atributo status atraves de uma String status que recebe
	 * 
	 * @param status
	 *            String que sera o novo celular de usuario
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Getter que retorna o atributo Status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @return Getter que retorna a String que presenta o atributo Id do usuario
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * metodo equals de usuario, que determina que dois usuarios sao iguais se
	 * tiverem o mesmo Id
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario outroUsuario = (Usuario) obj;
			return this.id.equals(outroUsuario.getId());
		}
		return false;
	}

	/**
	 * compareTo de usuario. Ordena os usuarios em uma possivel listagem por ordem
	 * decrescente de seus Id's.
	 */
	@Override
	public int compareTo(Usuario outroUsuario) {
		return this.id.compareTo(outroUsuario.getId());

	}

	/**
	 * toString de um usuario: "nomeUser/numeroId, email@emai.com, 9999-1231,
	 * status: doador"
	 */
	@Override
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", " + "status: " + this.status;
	}

}
