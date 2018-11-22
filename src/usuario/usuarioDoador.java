package usuario;

/**
 * classe que define um usuario doador. usuario que so pode doar itens.
 * 
 * @author victorpfb
 *
 */
public class usuarioDoador extends Usuario {

	/**
	 * Construtor da classe usuarioDoador.
	 * 
	 * @param id
	 *            String que representa a numeracao do documento do usuario
	 * @param nome
	 *            String que representa o nome do usuario
	 * @param email
	 *            String que representa o email do usuario
	 * @param celular
	 *            String que representa o celular do usuario
	 * @param classe
	 *            String que representa a classe do usuario (Ex: PESSOA_FISICA,
	 *            ORGAO_PUBLIC_MUNICIPAL, etc)
	 */
	public usuarioDoador(String id, String nome, String email, String celular, String classe) {

		super(id, nome, email, celular, classe);
		super.setStatus("doador");

		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
		if (email == null || email.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
		if (celular == null || celular.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
		if (classe == null || classe.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		}
		if (!(classe.toUpperCase().equals("PESSOA_FISICA") || classe.toUpperCase().equals("IGREJA")
				|| classe.toUpperCase().equals("ORGAO_PUBLICO_MUNICIPAL")
				|| classe.toUpperCase().equals("ORGAO_PUBLICO_ESTADUAL")
				|| classe.toUpperCase().equals("ORGAO_PUBLICO_FEDERAL") || classe.toUpperCase().equals("ONG")
				|| classe.toUpperCase().equals("ASSOCIACAO") || classe.toUpperCase().equals("SOCIEDADE"))) {

			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");

		}

	}

	/**
	 * toString do usuarioDoador, que eh o mesmo da classe abstrata superior
	 * Usuario.
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
