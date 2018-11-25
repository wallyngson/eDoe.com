package Sistema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import Usuarios.Usuario;
import Usuarios.Doador;
import Usuarios.Receptor;

/**
 * Classe que reprensenta um sistema de doacao, onde existem doadores e
 * receptores, sendo possiveis cadastrar usuarios e doar itens.
 * 
 * @author Dacio Bezerra, Felipe Nunes, Victor Paz e Wallyngson Guedes.
 *
 */
public class Controller {

	private HashMap<String, Usuario> usuarios;
	private Scanner sc;

	public Controller() {
		this.usuarios = new HashMap<>();
	}

	/**
	 * Recebe o arquivo CSV e o le.
	 * @param arquivo
	 * @throws IOException
	 */
	public void lerReceptores(String arquivo) throws IOException {
		this.sc = new Scanner(new File(arquivo));
		this.sc.hasNextLine();
		String linha = null;
		
		this.lerReceptor(sc, linha);
		
	}
	
	/**
	 * Percorre todas as linhas do CSV e adiciona os receptores aos usuarios.
	 * 
	 * @param sc
	 * @param linha
	 */
	private void lerReceptor(Scanner sc, String linha) {
		while(sc.hasNext()) {
			linha = sc.nextLine();
			if (linha.equals("id,nome,E-mail,celular,classe") || linha.equals("id,nome,e-mail,celular,classe"))
				continue;
			
			String[] dados = linha.split(",");
			this.adicionaReceptor(dados[0], dados[1], dados[2], dados[3], dados[4]);
		}
	}
	
	
	/**
	 * Metodo que adiciona um usuario recepetor. Neste caso todos os cadastros para
	 * esse tipo de usuario estao sendo feitos metodo lerReceptores().
	 * 
	 * @param id
	 * @param nome
	 * @param email
	 * @param celular
	 * @param classe
	 * 
	 * @return Id do usuario;
	 */

	private String adicionaReceptor(String id, String nome, String email, String celular, String classe) {
		this.idInvalido(id);

		if (this.usuarios.containsKey(id)) {
			this.atualizaUsuario(id, nome, email, celular);
		}
		
		Usuario receptor = new Receptor(id, nome, email, celular, classe);
		this.usuarios.put(id, receptor);
		return id;

	}

	
	/**
	 * Metodo que adiciona um usuario doador. estes so podem realizar enviar itens.
	 * 
	 * @param id
	 * @param nome
	 * @param email
	 * @param celular
	 * @param classe
	 * 
	 * @return Id do doador;
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		this.idInvalido(id);
		this.validaUsuario(id);

		Usuario doador = new Doador(id, nome, email, celular, classe);
		this.usuarios.put(id, doador);
		return id;

	}

	/**
	 * Metodo que procura o usuario atraves da String id, que eh a chave no mapa de
	 * usuarios. caso o mapa contrenha a chave, o metodo retorna o toString do
	 * usuario correspondente.
	 * 
	 * @param id String que reprensenta o numero de documento do usuario
	 * @return retorna o toString do usuario, e so deve retornar um usuario, ja que
	 *         nao pode existir dois usuarios com o mesmo id.
	 */

	public String pesquisaUsuarioPorId(String id) {
		this.idInvalido(id);
		this.usuarioInexistente(id);

		String representacao = "";
		if (this.usuarios.containsKey(id)) {
			representacao = this.usuarios.get(id).toString();
		}

		return representacao;
	}
	
	/**
	 * Verifica se o usuario existe no sistema.
	 * 
	 * @param id
	 */
	private void validaUsuario(String id) {
		if (this.usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
	}

	/**
	 * Verifica se o usuario nao existe no sistema.
	 * 
	 * @param id
	 */
	private void usuarioInexistente(String id) {
		if (!this.usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
	}
	
	/**
	 * Metodo que procura o usuario atraves da String nome, os valores do mapa de
	 * usuarios sao copiados para um arrayList para podermos iterar, assim
	 * comparamos o nome passado no metodo com o nome dos usurios caso encontre, ele
	 * retorna o toString desse usuario. E caso exista mais de um usuario com este
	 * nome, ele separa os toString deles com " | " e ordena de forma decrescente no
	 * que diz respeito ao id.
	 * 
	 * @param nome 
	 * 
	 * @return retorna o toString do usuario, e so deve retornar um usuario, ja que
	 *         nao pode existir dois usuarios com o mesmo id.
	 */

	public String pesquisaUsuarioPorNome(String nome) {
		this.nomeInvalido(nome);
		
		ArrayList<Usuario> listaAuxiliarDeUsuarios = new ArrayList<>();
		listaAuxiliarDeUsuarios.addAll(this.usuarios.values());
		String representacao = "";
		boolean justOneUser = true;
		Collections.sort(listaAuxiliarDeUsuarios, Collections.reverseOrder());
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

	/**
	 * Metodo que recebe uma String id e atraves dela tenta achar o usuario
	 * correspondente no mapa, uma vez encontrado eh passado para o metodo um nome,
	 * email e/ ou celular. que atualizara o atributo correspondente ao usuario com
	 * id passado no meotodo
	 * 
	 * @param id      
	 * @param nome    
	 * @param email   
	 * @param celular 
	 * 
	 * @return retornar o toString do usuario que esta sendo atualizado com as
	 *         atualizacoes ja implementadas
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		this.idInvalido(id);
		this.usuarioInexistente(id);
		
		if (!(nome == null) && !(nome.trim().equals(""))) {
			this.usuarios.get(id).setNome(nome);
		}
		if (!(email == null) && !(email.trim().equals(""))) {
			this.usuarios.get(id).setEmail(email);
		}
		if (!(celular == null) && !(celular.trim().equals(""))) {
			this.usuarios.get(id).setCelular(celular);
		}

		return this.usuarios.get(id).toString();

	}

	/**
	 * Atraves da String id passada procura-se o usuario nas chaves do mapa de
	 * usuarios, caso exista, ele eh removido do mapa caso nao exista, uma erro eh
	 * lancado e caso a String id seja nula ou vazia um erro tambem eh lancado
	 * 
	 * @param id 
	 * 
	 * @return true se a remocao acontecer com sucesso.
	 */
	public boolean removeUsuario(String id) {
		this.idInvalido(id);
		this.usuarioInexistente(id);

		this.usuarios.remove(id);
		return true;
	}

	/**
	 * Verifica se o Id passador por parametro eh invalido.
	 * 
	 * @param id
	 */
	private void idInvalido(String id) {
		if (id == null || id.trim().isEmpty())
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
	}
	
	/**
	 * Verifica se o nome eh invalido.
	 * 
	 * @param nome
	 */
	private void nomeInvalido(String nome) {
		if (nome == null || nome.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
	}

}
