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
	
	public long adicionaDoador(long id, String nome, String email, String celular, String classe) {
		return controle.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return controle.pesquisarUsuarioPorNome(nome);
	}
	
	
	public String pesquisaUsuarioPorId(long id) {
		return controle.pesquisaUsuarioPorId(id);
	}
	
	public boolean removeUsuario(long id) {
		return controle.removeUsuario(id);
	}

}
