# eDoe.com

FIZ UMAS ANOTACOES AÍ JÁ SOBRE O CASO DE USO 1.


metodos CRUD 1 do controller:


public String adicionaDoador(String id, String nome, String email, String celular, String classe){
	return id;
}

public String pesquisaUsuarioPorId(String id){
	return this.colecaoUsers.get(id).toString();
}



// metodo pra retornar user pelo nome
// e mecanismo com booleano justOneUser para ajustar a String de retorno em caso de haver mais de um usuario com aquele nome

public String pesquisaUsuarioPorNome(String nome){
	boolean justOneUser = true;
	for User user : colecaoUsers {
		
		if(user.getNome().equals(nome)){
		   if(justOneUser == true){
			justOneUser = false;			
			return user.toString();
	           }else {
			return " | " + user.toString();	 


		   }	
		
		}
	
	}
	throw new illegalArgumentException("Usuario nao encontrado: " + user.getNome());
}

// metodo que remove um usuario verificando se o usuario esta no MAPA de usurios. Ja que podem ter mesmo nome mas nao mesmo id, sugiro que façamos um mapa    HashMap<String, User>() colecaoUsers; Sendo a chave o id e o valor o obj user.
public boolean removeUsuario(String id){
	if(colecaoUsers.containsKey(id)){
		colecaoUsers.remove(id);
		return true;
	}
	
	return false;
}
	





- Tipos de usuários: usuário receptor e doador.

	Receptores são usuários especiais que tem a autorização para receber as doações e serão criados pelo sistema eDoe.com através de um arquivo de configuração do sistema. 
	Na inicialização do sistema o arquivo de usuários  receptores é lido e estes usuários são automaticamente criados. 
	Veja aqui um exemplo do arquivo de configuração de receptores. Os demais usuários inseridos no sistema são  criados como apenas doador. 
	Os doadores apenas doam, os usuários autorizados a receber podem receber e doar itens.

- classes: pessoa fisica, igreja, órgão público municipal, órgão público estadual, órgão público federal, ONG, associação e sociedade.
- Documento: cpf para pessoas físicas e cnpj para as demais.



// to String de um usuario
// usuario parece ficar bem como uma classe abstrata, e seus tipos sendo classes comuns (dessa forma teremos facilidade em agrupar em uma so colecao)

	
public String toString(){
	return "nome/documento, email, celular, status: {doador|receptor}";
}
