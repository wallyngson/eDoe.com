package usuario;

/**
 * classe que define um usuario receptor
 * 
 * @author victorpfb
 *
 */
public class usuarioReceptor extends Usuario {

	public usuarioReceptor(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		super.setStatus("receptor");
	}

}
