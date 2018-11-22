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
	 * Metodo que serve para modificar o tipo de numeracao lancada no toString do
	 * usuario. dependendo da classe do usuario, o metodo fornece um cpf ou cnpj
	 * para o toString
	 * 
	 * @return retorna a String que representa o id do usuario ja no formato final.
	 */
	private String tipoRetornoId() {
		if (this.classe.equals("PESSOA_FISICA")) {
			return formatIdCpf();
		}
		return formatCnpj();
	}

	private String formatIdCpf() {

		String cpf1 = this.id.substring(0, 3);
		String cpf2 = this.id.substring(3, 6);
		String cpf3 = this.id.substring(6, 9);
		String cpf4 = this.id.substring(9, 11);

		String finalCpf = cpf1 + "." + cpf2 + "." + cpf3 + "-" + cpf4;
		return finalCpf;
	}

	private String formatCnpj() {

		String cnpj1 = this.id.substring(0, 2);
		String cnpj2 = this.id.substring(2, 5);
		String cnpj3 = this.id.substring(5, 8);
		String cnpj4 = this.id.substring(8, 12);
		String cnpj5 = this.id.substring(12, 14);

		String finalCnpj = cnpj1 + "." + cnpj2 + "." + cnpj3 + "/" + cnpj4 + "-" + cnpj5;
		return finalCnpj;
	}

	/**
	 * toString de um usuario: "nomeUser/numeroId, email@emai.com, 9999-1231,
	 * status: doador"
	 */
	@Override
	public String toString() {
		return this.nome + "/" + tipoRetornoId() + ", " + this.email + ", " + this.celular + ", " + "status: "
				+ this.status;
	}

}
