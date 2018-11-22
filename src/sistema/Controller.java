package sistema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import usuario.Usuario;
import usuario.usuarioDoador;
import usuario.usuarioReceptor;

public class Controller {

	private HashMap<String, Usuario> colecaoUsurarios = new HashMap<>();

	
	public void lerReceptores(String arquivo) throws IOException {
		Scanner sc = new Scanner(new File(arquivo));
		String linha = null;
		while(sc.hasNextLine()) {
			linha = sc.nextLine();
			if(linha.equals("id,nome,E-mail,celular,classe"))
				continue;
			String[] dadosDoReceptor = linha.split(",");
			if(dadosDoReceptor.length != 5)
				throw new IOException("Campos invalidos");
			adicionaReceptor(dadosDoReceptor[0], dadosDoReceptor[1], dadosDoReceptor[2], dadosDoReceptor[3], dadosDoReceptor[4]);
		}
		sc.close();
	}
	
	private String adicionaReceptor(String id, String nome, String email, String celular, String classe) {

		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}

		if (this.colecaoUsurarios.containsKey(id)) {
			atualizaUsuario(id, nome, email, celular);
		}

		usuarioReceptor novoReceptor = new usuarioReceptor(id, nome, email, celular, classe);
		this.colecaoUsurarios.put(id, novoReceptor);
		return id;

	}
	
	
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {

		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}

		if (this.colecaoUsurarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}

		usuarioDoador novoDoador = new usuarioDoador(id, nome, email, celular, classe);
		this.colecaoUsurarios.put(id, novoDoador);
		return id;

	}

	public String pesquisaUsuarioPorId(String id) {

		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}

		if (!this.colecaoUsurarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		String representacao = "";
		if (this.colecaoUsurarios.containsKey(id)) {
			representacao = this.colecaoUsurarios.get(id).toString();
		}

		return representacao;
	}

	public String pesquisaUsuarioPorNome(String nome) {

		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}

		ArrayList<Usuario> listaAuxiliarDeUsuarios = new ArrayList<>();
		listaAuxiliarDeUsuarios.addAll(this.colecaoUsurarios.values());
		String representacao = "";
		boolean justOneUser = true;
		for (Usuario user : listaAuxiliarDeUsuarios) {
			if (user.getNome().equals(nome) && justOneUser == true) {
				justOneUser = false;
				representacao = user.toString();
			} else if (user.getNome().equals(nome)) {
				representacao += " | " + user.toString();
			}

		}

		if (representacao.equals("")) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
		}

		return representacao;
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {

		if (!this.colecaoUsurarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (!(nome == null) && !(nome.trim().equals(""))) {
			this.colecaoUsurarios.get(id).setNome(nome);
		}
		if (!(email == null) && !(email.trim().equals(""))) {
			this.colecaoUsurarios.get(id).setEmail(email);
		}
		if (!(celular == null) && !(celular.trim().equals(""))) {
			this.colecaoUsurarios.get(id).setCelular(celular);
		}

		return this.colecaoUsurarios.get(id).toString();

	}

	public boolean removeUsuario(String id) {

		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}

		if (!this.colecaoUsurarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}

		this.colecaoUsurarios.remove(id);
		return true;
	}

}
