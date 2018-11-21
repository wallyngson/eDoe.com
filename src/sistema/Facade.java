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
	
	public Long adicionaDoador(Long id, String nome, String email, String celular, String classe) {
		return controle.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return controle.pesquisarUsuarioPorNome(nome);
	}
	
	
	public String pesquisaUsuarioPorId(Long id) {
		return controle.pesquisaUsuarioPorId(id);
	}
	
	public boolean removeUsuario(Long id) {
		return controle.removeUsuario(id);
	}

}
