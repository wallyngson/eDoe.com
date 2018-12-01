package sistema;

import java.io.IOException;
import easyaccept.EasyAccept;

/**
 * Classe de fachada que apenas recebe os metotos e chama os do controller.
 * Padrao de Projeto Facade.
 * 
 * @authors Dacio Bezerra, Felipe Nunes, Victor Paz e Wallyngson Guedes.
 *
 */
public class Facade {

	public static void main(String[] args) {
		args = new String[] { "sistema.Facade", "acceptance_test/use_case_1.txt", "acceptance_test/use_case_2.txt",
				"acceptance_test/use_case_3.txt" };
		EasyAccept.main(args);
	}

	EDoeController controller = new EDoeController();

	// CASE1

	public void lerReceptores(String arquivo) throws IOException {
		this.controller.lerReceptores(arquivo);
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return this.controller.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return this.controller.pesquisaUsuarioPorNome(nome);
	}

	public String pesquisaUsuarioPorId(String id) {
		return this.controller.pesquisaUsuarioPorId(id);
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return this.controller.atualizaUsuario(id, nome, email, celular);
	}

	public boolean removeUsuario(String id) {
		return this.controller.removeUsuario(id);
	}

	// CASE2

	public void adicionaDescritor(String descritor) {
		this.controller.adicionaDescritor(descritor);
	}

	public Integer adicionaItemParaDoacao(String idDoador, String descritor, int qtd, String tags) {
		return this.controller.adicionaItemParaDoacao(idDoador, descritor, qtd, tags);
	}

	public String exibeItem(Integer idItem, String id) {
		return this.controller.exibeItem(idItem, id);
	}

	public void removeItemParaDoacao(Integer idItem, String id) {
		this.controller.removeItemParaDoacao(idItem, id);
	}

	public String atualizaItemParaDoacao(Integer idItem, String id, int qtd, String tags) {
		return this.controller.atualizaItemParaDoacao(idItem, id, qtd, tags);
	}

	// CASE3

	public String listaDescritorDeItensParaDoacao() {
		return this.controller.listaDescritorDeItensParaDoacao();
	}
	
//	public String listaItensParaDoacao() {
//		return this.controller.listaItensParaDoacao();
//	}

}
