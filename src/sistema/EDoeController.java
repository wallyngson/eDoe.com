package sistema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import itens.Item;
import itens.ItemDoavel;
import itens.ItemNecessario;
import itens.Descritor;
import usuarios.Doador;
import usuarios.Receptor;
import usuarios.Usuario;
import util.ComparadorId;
import util.ComparadorNome;
import util.Validador;

/**
 * Classe que reprensenta um sistema de doacao, onde existem doadores e
 * receptores, sendo possiveis cadastrar usuarios e doar itens.
 * 
 * @author Dacio Bezerra, Felipe Nunes, Victor Paz e Wallyngson Guedes.
 *
 */
public class EDoeController {

	private Map<String, Usuario> usuarios;
	private Map<String, Descritor> descritores;
	private Map<Integer, Item> itens;
	private Scanner sc;
	private Integer idItem = 1;
	
	private Validador validador = new Validador();
	
	public EDoeController() {
		this.usuarios = new HashMap<>();
		this.descritores = new HashMap<>();
		this.itens = new HashMap<>();
	}
	
	
	// USUARIO RECEPTOR
	

	/**
	 * Recebe o arquivo CSV e o le.
	 * 
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
		while (sc.hasNext()) {
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
		this.validador.idInvalido(id);

		if (this.usuarios.containsKey(id)) {
			this.atualizaUsuario(id, nome, email, celular);
		}

		Usuario receptor = new Receptor(id, nome, email, celular, classe);
		this.usuarios.put(id, receptor);
		return id;

	}
	
	
	// USUARIO DOADOR

	
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
		this.validador.idInvalido(id);
		this.validador.validaUsuario(id, usuarios);

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
		this.validador.idInvalido(id);
		this.validador.usuarioInexistente(id, usuarios);

		String representacao = "";
		if (this.usuarios.containsKey(id)) {
			representacao = this.usuarios.get(id).toString();
		}

		return representacao;
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
		this.validador.nomeInvalido(nome);

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
		this.validador.idInvalido(id);
		this.validador.usuarioInexistente(id, usuarios);

		if (!(nome == null) && !(nome.trim().isEmpty()))
			this.usuarios.get(id).setNome(nome);
		if (!(email == null) && !(email.trim().isEmpty()))
			this.usuarios.get(id).setEmail(email);
		if (!(celular == null) && !(celular.trim().isEmpty()))
			this.usuarios.get(id).setCelular(celular);

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
		this.validador.idInvalido(id);
		this.validador.usuarioInexistente(id, usuarios);

		this.usuarios.remove(id);
		return true;
	}

	
	// DESCRITORES
	
	
	/**
	 * Adiciona descritor ao sistema.
	 * 
	 * @param descritor
	 */
	public void adicionaDescritor(String descritor) {
		this.validador.descritorInvalido(descritor);
		this.validador.descritorCadastrado(descritor, descritores);

		String descritorFormatado = this.formataString(descritor);
		this.descritores.put(descritorFormatado, new Descritor(descritor.toLowerCase()));
	}


	
	// ITEM DOACAO

	
	/**
	 * Verifica se o item a ser cadastro existe nos descritores e apos cadastrar
	 * esse item a um doador.
	 *
	 * @param id
	 * @param descricaoItem
	 * @param qtd
	 * @param tag
	 * @return
	 */
	public Integer adicionaItemParaDoacao(String id, String descritor, int qtd, String tags) {
		this.validador.idInvalido(id);
		this.validador.usuarioInexistente(id, usuarios);
		this.validador.descritorInvalido(descritor);
		this.validaDescritor(descritor);
		
		if (this.itemDoavelCadastrado(descritor, tags) != null)
			return this.adicionaItem(this.itemDoavelCadastrado(descritor, tags), id, descritor, qtd, tags, "doacao");
		
		return adicionaItem(idItem, id, descritor, qtd, tags, "doacao");
	}
	/**
	 * Verifica se o item a ser cadastrado existe nos descritores e apos isso cadastra esse item a um receptor.
	 * @param id
	 * @param descritor
	 * @param qtd
	 * @param tags
	 * @return retorna o metodo privado adicionaItem
	 */
	public Integer adicionaItemNecessario(String id, String descritor, int qtd, String tags) {
		this.validador.idInvalido(id);
		this.validador.usuarioInexistente(id, usuarios);
		this.validador.descritorInvalido(descritor);
		this.validaDescritor(descritor);
		
		if (this.itemNecessarioCadastrado(descritor, tags) != null)
			return this.adicionaItem(this.itemNecessarioCadastrado(descritor, tags), id, descritor, qtd, tags, "necessario");
		
		return adicionaItem(idItem, id, descritor, qtd, tags, "necessario");
	}

	/**
	 * Adiciona um item ao Mapa de itens.
	 * 
	 * @param idItem
	 * @param id
	 * @param descritor
	 * @param qtd
	 * @param tags
	 * @return
	 */
	private Integer adicionaItem(Integer idUnico, String id, String descritor, int qtd, String tags, String tipo) {
		String representacaoUsuario = this.usuarios.get(id).getId();
		if(tipo.equals("doacao")) {
			this.itens.put(idUnico, (Item) new ItemDoavel(descritor, qtd, tags, idUnico, representacaoUsuario));
			this.usuarios.get(id).adicionaItem(idUnico, this.itens.get(idUnico));
		}else {
			this.itens.put(idUnico, (Item) new ItemNecessario(descritor, qtd, tags, idUnico,representacaoUsuario));
			this.usuarios.get(id).adicionaItem(idUnico, this.itens.get(idUnico));
		}
		this.adicionaQtdDescritor(descritor, qtd);
		this.idItem += 1;
			
		return idUnico;
	}
	
	/**
	 * Verifica se o item ja esta cadastrado se ja estiver retorna seu id.
	 * 
	 * @param descritor
	 * @param tag
	 * @return
	 */
	private Integer itemDoavelCadastrado(String descritor, String tag) {
		List<Item> listaItens = new ArrayList<>();
		listaItens.addAll(this.itens.values());
		
		for (Item item : listaItens) {
			if (item.descricaoCompleta().equals(this.formataItem(descritor, tag)) && (item instanceof ItemDoavel))
				return item.getIdItem();
		}
		
		return null;
	}
	/**
	 * Verifica se o item ja esta cadastrado, se ja estiver, retorna seu id
	 * @param descritor
	 * @param tag
	 * @return retorna o id se o item existir, se não, retorna null
	 */
	private Integer itemNecessarioCadastrado(String descritor, String tag) {
		List<Item> listaItens = new ArrayList<>();
		listaItens.addAll(this.itens.values());
		
		for (Item item : listaItens) {
			if (item.descricaoCompleta().equals(this.formataItem(descritor, tag)) && (item instanceof ItemNecessario))
				return item.getIdItem();
		}
		
		return null;
	}
	
	/**
	 * Formata a String recebida para comparacao com o item cadastrado.
	 * 
	 * @param descritor
	 * @param tag
	 * @return
	 */
	private String formataItem(String descritor, String tag) {
		String[] tags = tag.split(",");
		
		return descritor + " - " + Arrays.toString(tags);
	}
	
	/**
	 * Adiciona a quantidade de itens no sistema ao seu descritor.
	 * 
	 * @param descritor
	 * @param qtd
	 */
	private void adicionaQtdDescritor(String descritor, int qtd) {
		String descritorFormatado = this.formataString(descritor);
		this.descritores.get(descritorFormatado).setQtdItens(qtd);
	}

	/**
	 * Verifica se o descritor existe, se nao existir adiciona o descritor ao mapa
	 * de descritores.
	 * 
	 * @param descritor
	 */
	private void validaDescritor(String descritor) {
		String descritorFormatado = this.formataString(descritor);

		if (!this.descritores.containsKey(descritorFormatado))
			this.descritores.put(descritorFormatado, new Descritor(descritor.toLowerCase()));
	}

	/**
	 * Exibe um item de um determinado doador.
	 * 
	 * @param descritor
	 * @param id
	 * @return
	 */
	public String exibeItem(Integer idItem, String id) {
		this.validador.usuarioInexistente(id, usuarios);

		return this.usuarios.get(id).exibeItem(idItem);
	}

	/**
	 * Remove um item de um determinado doador.
	 * 
	 * @param descritor
	 * @param id
	 */
	public void removeItem(Integer idItem, String id) {
		this.validador.idInvalido(id);
		this.validador.usuarioInexistente(id, usuarios);

		this.itens.get(idItem);
		this.usuarios.get(id).removeItem(idItem);
		adicionaQtdDescritor(itens.get(idItem).getNome(), 0);
		this.itens.remove(idItem);
		
	}
	/**
	 * Atualiza a quantidade de itens e as suas tags de um determinado item, e
	 * atualiza o quantidade de itens gerais no seu descritor.
	 * 
	 * @param idItem
	 * @param id
	 * @param qtd
	 * @param tags
	 * @return
	 */
	public String atualizaItem(Integer idItem, String id, int qtd, String tags) {
		this.validador.idInvalido(id);
		this.validador.usuarioInexistente(id, usuarios);
		this.usuarios.get(id).validaItem(idItem);

		String item = this.itens.get(idItem).atualizaItem(qtd, tags);
		String descritor = this.itens.get(idItem).getNome();
		
		if (qtd > 0)
			this.adicionaQtdDescritor(descritor, qtd);

		return item;
	}
	
	/**
	 * Imprime os descritores com os seus respectivos nomes e quantidades.
	 * 
	 * @return
	 */
	public String listaDescritorDeItensParaDoacao() {
		List<Descritor> listaDescritores = new ArrayList<>();
		listaDescritores.addAll(this.descritores.values());

		Collections.sort(listaDescritores);
		String lista = "";
		for (Descritor descritor : listaDescritores) {
			lista += descritor.toString() + " | ";
		}

		return lista.substring(0, lista.length() - 3);
	}

	/**
	 * Metodo que lista os itens de forma ordenada a partir de sua quantidade, e quando ocorrer empate, a partir do seu nome.
	 * 
	 * @return
	 */
	
	public String listaItensParaDoacao() { 
		List<Item> listaQtd = new ArrayList<>();
		
		for(Item i : itens.values()) {
			if(i instanceof ItemDoavel) {
				listaQtd.add(i);
			}
		}
		Collections.sort(listaQtd); 
		String listaFinal = "";
		
		for (Item item: listaQtd) { 
			listaFinal += item.toString() + ", doador: " + usuarios.get(item.getUsuarioVinculado()).getNome() + "/" + usuarios.get(item.getUsuarioVinculado()).getId() +  " | "; 
			} 
		return listaFinal.substring(0, listaFinal.length() - 3);
		
	}
	/**
	 * Metodo que lista todos os itens necessarios do nosso mapa de itens e os ordena pelo id.
	 * @return listagem de itens necessarios separados por " | "
	 */
	public String listaItensNecessarios() { 
		List<Item> listaQtd = new ArrayList<>();
		for(Item i : itens.values()) {
			if(i instanceof ItemNecessario) {
				listaQtd.add(i);
			}
		}
		Collections.sort(listaQtd, new ComparadorId()); 
		String listaFinal = "";
		
		for (Item item: listaQtd) { 
			listaFinal += item.toString() + ", Receptor: " + usuarios.get(item.getUsuarioVinculado()).getNome() + "/" + usuarios.get(item.getUsuarioVinculado()).getId() +  " | "; 
			} 
		return listaFinal.substring(0, listaFinal.length() - 3);
		
	}
	/**
	 * Metodo que exibe uma lista de itens a partir de uma string de pesquisa, ordenada pelo seu nome.
	 * 
	 * @param desc
	 * @return
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String desc){ 
		this.validador.validaPesquisa(desc); 
		List<Item> listaQtd = new ArrayList<>(); 
		listaQtd.addAll(this.itens.values());
		listaQtd.sort(new ComparadorNome());
		String listaFinal = "";
		for (Item item: listaQtd) {
			if(item.getNome().trim().toLowerCase().contains(desc.toLowerCase().trim())) {
				listaFinal += item.toString() + " | "; 
			}
		} 
		return listaFinal.substring(0, listaFinal.length() - 3);
		
		}
	
	/**
	 * Tira todos os espacos e deixa tudo minusculo.
	 * 
	 * @param string
	 * @return
	 */
	
	private String formataString(String string) {
		return string.replace(" ", "").toLowerCase();
	}
	
	public String match(String idReceptor, Integer idItem) {
		String listagem = "";
		ArrayList<Item> listaItens = new ArrayList<>();
		
		if (this.itens.get(idItem) instanceof ItemNecessario) {
			for(Item i : itens.values()) {
				if(i instanceof ItemDoavel) {
					if(i.getNome().toLowerCase().equals(this.itens.get(idItem).getNome().toLowerCase()))
						listaItens.add(i);
				}
		}
		}
		if (this.itens.get(idItem) instanceof ItemNecessario) {
			for(Item i : itens.values()) {
				if(i instanceof ItemDoavel) {
					for (int j = 0; j < ((ItemDoavel) i).getTags().length; j++) {
						for (int k = 0; k < (this.itens.get(idItem).getTags().length); k++) {
							if(((ItemDoavel) i).getTags()[j].equals(this.itens.get(idItem).getTags()[k]) && j == k) {
								((ItemDoavel) i).setPontuacao(10);
								break;
							}
							if(((ItemDoavel) i).getTags()[j].equals(this.itens.get(idItem).getTags()[k])) {
								((ItemDoavel) i).setPontuacao(5);
								break;
							}
						}
					}
				}
			}
		}
					
		for(Item p: listaItens) {
			listagem += p.toString() + ", doador: " + usuarios.get(p.getUsuarioVinculado()).getNome() + "/" + usuarios.get(p.getUsuarioVinculado()).getId() + " | ";
		}
		
		
		if(listagem.length() == 0)
			return listagem;
		
		return listagem.substring(0, listagem.length() - 3);
	}

}