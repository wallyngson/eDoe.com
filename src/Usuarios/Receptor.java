package Usuarios;

/**
 * Classe que representa um Receptor de Doacoes.
 * 
 * @author Dacio Bezerra, Felipe Nunes, Victor Paz e Wallyngson Guedes.
 *
 */
public class Receptor extends Usuario {

	/**
	 * Construtor do Receptor.
	 * 
	 * @param id
	 * @param nome
	 * @param email
	 * @param celular
	 * @param classe
	 * 
	 */

	public Receptor(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		super.setStatus("receptor");
	}

}
