package sistema;

import java.util.ArrayList;
import java.util.HashMap;

import usuario.Usuario;
import usuario.usuarioDoador;

public class Controller {

	private HashMap<Long, Usuario> colecaoUsurarios = new HashMap<>();

	
	
	public Long adicionaDoador(Long id, String nome, String email, String celular, String classe) {
		
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
	
	public boolean removeUsuario(Long id) {
		if(!this.colecaoUsurarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		
		this.colecaoUsurarios.remove(id);
		return true;
	}

}
