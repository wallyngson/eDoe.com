package usuario;

public class usuarioDoador extends Usuario {

	private String status;
	
	public usuarioDoador(long id, String nome, String email, String celular, String classe) {
		 
		super(id, nome, email, celular, classe);
		super.setStatus("doador");
		
		
		if (Long.toString(id) == null || Long.toString(id).equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		
		if (nome.trim().equals("") || nome == null) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
		if (email.trim().equals("") || email == null) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
		if (celular.trim().equals("") || celular == null) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
		if (classe.trim().equals("") || classe == null) {
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
	
	public String getStatus() {
		return this.status; 
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	

}
