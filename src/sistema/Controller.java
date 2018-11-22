package sistema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import usuario.Usuario;
import usuario.usuarioDoador;
import usuario.usuarioReceptor;

/**
 * Classe controller. contem todos os metodo que manipulam as acoes relacionadas
 * a usuarios.
 * 
 * @author victorpfb
 *
 */
public class Controller {

	private HashMap<String, Usuario> colecaoUsurarios = new HashMap<>();

	/**
	 * Metodo para a leitura dos arquivos .csv, que carregam uma serie de usuarios
	 * receptores.
	 * 
	 * @param arquivo
	 *            string que indica o caminho para o arquivo .csv
	 * @throws IOException
	 */
	public void lerReceptores(String arquivo) throws IOException {
		Scanner sc = new Scanner(new File(arquivo));
		String linha = null;
		while (sc.hasNextLine()) {
			linha = sc.nextLine();
			if (linha.equals("id,nome,E-mail,celular,classe"))
				continue;
			String[] dadosDoReceptor = linha.split(",");
			if (dadosDoReceptor.length != 5)
				throw new IOException("Campos invalidos");
			adicionaReceptor(dadosDoReceptor[0], dadosDoReceptor[1], dadosDoReceptor[2], dadosDoReceptor[3],
					dadosDoReceptor[4]);
		}
		sc.close();
	}

	/**
	 * Metodo que adiciona um usuario recepetor. Neste caso todos os cadastros para
	 * esse tipo de usuario estao sendo feitos metodo lerReceptores().
	 * 
	 * @param id
	 *            String que representa a numeracao de um documento do usuario a ser
	 *            cadastrado
	 * @param nome
	 *            String que representa o nome do usuario
	 * @param email
	 *            String que representa o email do usuario
	 * @param celular
	 *            String que representa o numero de telefone celular do usuario
	 * @param classe
	 *            String que representa qual classe do usuario (Ex:PESSOA_FISICA,
	 *            IGREJA, etc) com a intencao de determinar, tambem, se ele tera cpf
	 *            ou cnpj
	 * @return o metodo retorna a String id do usuario cadastrado em caso de
	 *         sucesso.
	 */

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

	/**
	 * Metodo que adiciona um usuario doador. estes so podem realizar enviar itens.
	 * 
	 * @param id
	 *            String que representa a numeracao de um documento do usuario a ser
	 *            cadastrado
	 * @param nome
	 *            String que representa o nome do usuario
	 * @param email
	 *            String que representa o email do usuario
	 * @param celular
	 *            String que representa o numero de telefone celular do usuario
	 * @param classe
	 *            String que representa qual classe do usuario (Ex:PESSOA_FISICA,
	 *            IGREJA, etc) com a intencao de determinar, tambem, se ele tera cpf
	 *            ou cnpj
	 * @return o metodo retorna a String id do usuario cadastrado em caso de
	 *         sucesso.
	 */
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

	/**
	 * Metodo que procura o usuario atraves da String id, que eh a chave no mapa de
	 * usuarios. caso o mapa contrenha a chave, o metodo retorna o toString do
	 * usuario correspondente.
	 * 
	 * @param id
	 *            String que reprensenta o numero de documento do usuario
	 * @return retorna o toString do usuario, e so deve retornar um usuario, ja que
	 *         nao pode existir dois usuarios com o mesmo id.
	 */

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

	/**
	 * Metodo que procura o usuario atraves da String nome, os valores do mapa de
	 * usuarios sao copiados para um arrayList para podermos iterar, assim
	 * comparamos o nome passado no metodo com o nome dos usurios caso encontre, ele
	 * retorna o toString desse usuario. E caso exista mais de um usuario com este
	 * nome, ele separa os toString deles com " | " e ordena de forma decrescente no
	 * que diz respeito ao id
	 * 
	 * @param nome
	 *            String que reprensenta o nome de documento do usuario
	 * @return retorna o toString do usuario, e so deve retornar um usuario, ja que
	 *         nao pode existir dois usuarios com o mesmo id.
	 */

	public String pesquisaUsuarioPorNome(String nome) {

		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}

		ArrayList<Usuario> listaAuxiliarDeUsuarios = new ArrayList<>();
		listaAuxiliarDeUsuarios.addAll(this.colecaoUsurarios.values());
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
	 *            String que identifica a numeracao do documento do usuario
	 * @param nome
	 *            String que representa o nome do usuario
	 * @param email
	 *            String que representa o email do usuario
	 * @param celular
	 *            String que representa o telefone celular do usuario
	 * @return retornar o toString do usuario que esta sendo atualizado com as
	 *         atualizacoes ja implementadas
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {

		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}

		if (!this.colecaoUsurarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
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
