package usuario;

public abstract class Usuario implements Comparable<Usuario> {

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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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

	public String getId() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario outroUsuario = (Usuario) obj;
			return this.id.equals(outroUsuario.getId());
		}
		return false;
	}

	@Override
	public int compareTo(Usuario outroUsuario) {
		return this.id.compareTo(outroUsuario.getId());

	}

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

	@Override
	public String toString() {
		return this.nome + "/" + tipoRetornoId() + ", " + this.email + ", " + this.celular + ", " + "status: "
				+ this.status;
	}

}
