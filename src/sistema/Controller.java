package sistema;

import java.util.ArrayList;
import java.util.HashMap;

import usuario.Usuario;
import usuario.usuarioDoador;

public class Controller {

	private HashMap<Long, Usuario> colecaoUsurarios = new HashMap<>();

	public Long adicionaDoador(Long id, String nome, String email, String celular, String classe) {

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

		if (this.colecaoUsurarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id);
		}

		usuarioDoador novoDoador = new usuarioDoador(id, nome, email, celular, classe);
		this.colecaoUsurarios.put(id, novoDoador);
		return id;

	}
	
	
	

	public String pesquisaUsuarioPorId(Long id) {
		if (Long.toString(id).trim().equals("") || id == null) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}

		if (!this.colecaoUsurarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id);
		}
		String representacao = "";
		if (this.colecaoUsurarios.containsKey(id)) {
			representacao = this.colecaoUsurarios.get(id).toString();
		}
		
		return representacao;
	}
	
	
	

	public String pesquisarUsuarioPorNome(String nome) {

		if (nome.trim().equals("") || nome == null) {
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
			}else if(user.getNome().equals(nome)) {
				representacao += " | " + user.toString();
			}
			
		}

		if (representacao.equals("")) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome);
		}

		return representacao;
	}

}
