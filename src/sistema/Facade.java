package sistema;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import easyaccept.EasyAccept;

public class Facade {

	public static void main(String[] args) {
		args = new String[] { "sistema.Facade", "acceptance_test/use_case_1.txt"};
		EasyAccept.main(args);
		
		
		
	}
	
	
	Controller controle = new Controller();
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controle.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return controle.pesquisaUsuarioPorNome(nome);
	}
	
	
	public String pesquisaUsuarioPorId(String id) {
		return controle.pesquisaUsuarioPorId(id);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controle.atualizaUsuario(id, nome, email, celular);
	}
	
	public boolean removeUsuario(String id) {
		return controle.removeUsuario(id);
	}

}
