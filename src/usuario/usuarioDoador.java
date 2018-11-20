package usuario;

public class usuarioDoador extends Usuario {

	private String status;
	
	public usuarioDoador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		super.setStatus("doador");
	}
	
	public String getStatus() {
		return this.status; 
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	

}
