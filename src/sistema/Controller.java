package sistema;

import java.util.ArrayList;
import java.util.HashMap;

import usuario.Usuario;
import usuario.usuarioDoador;

public class Controller {

	private HashMap<Long, Usuario> colecaoUsurarios = new HashMap<>();

	
	
	public long adicionaDoador(String id, String nome, String email, String celular, String classe) {
		
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		
		if (this.colecaoUsurarios.containsKey(Long.parseLong(id))) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}

		
		usuarioDoador novoDoador = new usuarioDoador(Long.parseLong(id), nome, email, celular, classe);
		this.colecaoUsurarios.put(Long.parseLong(id), novoDoador);
		return Long.parseLong(id);

	}
	
	
	

	
	public String pesquisaUsuarioPorId(long id) {
		
		
		if (Long.toString(id) == null || Long.toString(id).trim().equals("")) {
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
			}else if(user.getNome().equals(nome)) {
				representacao += " | " + user.toString();
			}
			
		}

		if (representacao.equals("")) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
		}

		return representacao;
	}
	
	public boolean removeUsuario(long id) {
		if(!this.colecaoUsurarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		
		this.colecaoUsurarios.remove(id);
		return true;
	}

}
