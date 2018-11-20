package usuario;

public abstract class Usuario {
	
	private String id;
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;
	
	
	public Usuario(String id, String nome, String email, String celular, String classe) {
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = getStatus();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	private String formatIdCpf() {
		
		
		String[] finalId = this.id.split("");
		String cpf1 = this.id.substring(0, 3);
		String cpf2 = this.id.substring(3, 6);
		String cpf3 = this.id.substring(6, 9);
		String cpf4 = this.id.substring(9, 11);
		
		String finalCpf = cpf1 + "." + cpf2 + "." + cpf3 + "-" + cpf4;
		return finalCpf;
	}
	
	
	@Override
	public String toString() {
		return this.nome + "/" + formatIdCpf() + ", " +  this.email + ", " + this.celular + ", " + "status: " + this.status;
	}
	
	
	
	
	

}
