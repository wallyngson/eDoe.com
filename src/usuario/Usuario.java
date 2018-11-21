package usuario;

public abstract class Usuario {
	
	private long id;
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;
	
	
	public Usuario(long id, String nome, String email, String celular, String classe) {
		
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
	
	
	private String tipoRetornoId() {
		if(this.classe.equals("PESSOA_FISICA")) {
			return formatIdCpf();
		}
		return formatCnpj();
	}
	
	
	
	private String formatIdCpf() {
		
		
		String[] finalId = Long.toString(this.id).split("");
		String cpf1 = Long.toString(this.id).substring(0, 3);
		String cpf2 = Long.toString(this.id).substring(3, 6);
		String cpf3 = Long.toString(this.id).substring(6, 9);
		String cpf4 = Long.toString(this.id).substring(9, 11);
		
		String finalCpf = cpf1 + "." + cpf2 + "." + cpf3 + "-" + cpf4;
		return finalCpf;
	}
	
	private String formatCnpj() {
		
		String[] finalId = Long.toString(this.id).split("");
		String cnpj1 = Long.toString(this.id).substring(0, 2);
		String cnpj2 = Long.toString(this.id).substring(2, 5);
		String cnpj3 = Long.toString(this.id).substring(5, 8);
		String cnpj4 = Long.toString(this.id).substring(9, 12);
		String cnpj5 = Long.toString(this.id).substring(12, 14);
		
		String finalCnpj = cnpj1 + "." + cnpj2 + "." + cnpj3 + "/" + cnpj4 + "-" + cnpj5;
		return finalCnpj;
	}
	
	
	@Override
	public String toString() {
		return this.nome + "/" + tipoRetornoId() + ", " +  this.email + ", " + this.celular + ", " + "status: " + this.status;
	}
	
	
	
	
	

}
