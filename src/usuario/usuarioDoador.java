package usuario;

public class usuarioDoador extends Usuario {

	private String status;
	
	public usuarioDoador(Long id, String nome, String email, String celular, String classe) {
		 
		super(id, nome, email, celular, classe);
		super.setStatus("doador");
		
		
		if (Long.toString(id) == null || Long.toString(id).equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
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
