package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistema.EDoeController;

class testController {

	EDoeController controle;

	@BeforeEach
	void iniciaTeste() {
		controle = new EDoeController();
		try {
			controle.lerReceptores("arquivos_sistema/novosReceptores.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			controle.lerReceptores("arquivos_sistema/atualizaReceptores.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// CASE 1

	@Test
	void testAdicionaUsuarioDoador() {
		assertEquals("12345678910",
				controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA"));
		assertEquals("Victor Braga/12345678910, victor@ccc.com, 9999-1231, status: doador",
				controle.pesquisaUsuarioPorId("12345678910"));
	}

	@Test
	void testAdicionaUsuarioIdNuloOuVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> controle.adicionaDoador(null, "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class,
				() -> controle.adicionaDoador("  ", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA"));
	}

	@Test
	void testAdicionaUsuarioNomeNuloOuVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> controle.adicionaDoador("12345678910", "  ", "victor@ccc.com", "9999-1231", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class,
				() -> controle.adicionaDoador("12345678910", null, "victor@ccc.com", "9999-1231", "PESSOA_FISICA"));
	}

	@Test
	void testAdicionaUsuarioEmailNuloOuVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> controle.adicionaDoador("12345678910", "Victor Braga", "   ", "9999-1231", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class,
				() -> controle.adicionaDoador("12345678910", "Victor Braga", null, "9999-1231", "PESSOA_FISICA"));
	}

	@Test
	void testAdicionaUsuarioCelularNuloOuVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "   ", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class,
				() -> controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", null, "PESSOA_FISICA"));
	}

	@Test
	void testPesquisaUsuariosPorId() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertEquals("Victor Braga/12345678910, victor@ccc.com, 9999-1231, status: doador",
				controle.pesquisaUsuarioPorId("12345678910"));
	}

	@Test
	void testPesquisaUsuarioPorIdNuloOuVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("  "));
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId(null));
	}

	@Test
	void testPesquisaUsuarioPorIdUsuarioNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("12312378910"));
	}

	@Test
	void testPesquisaUsuariosPorNome() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertEquals("Victor Braga/12345678910, victor@ccc.com, 9999-1231, status: doador",
				controle.pesquisaUsuarioPorNome("Victor Braga"));
	}

	@Test
	void testPesquisaUsuarioPorNomeNuloOuVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorNome("  "));
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorNome(null));
	}

	@Test
	void testPesquisaUsuarioPorNomeUsuarioNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("Paulo Coelho"));
	}

	@Test
	void testAtualizaUsuario() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertEquals("Victor Braga/12345678910, victor@ccc.com, 9999-1231, status: doador",
				controle.pesquisaUsuarioPorId("12345678910"));
		assertEquals("Victor Paz/12345678910, victor@ccc.ufcg.com, 9999-1231, status: doador",
				controle.atualizaUsuario("12345678910", "Victor Paz", "victor@ccc.ufcg.com", "9999-1231"));
	}

	@Test
	void testAtualizaUsuarioIdNuloOuVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> controle.atualizaUsuario(" ", "victor braga", "victor@ccc.com", "9999-1231"));
		assertThrows(IllegalArgumentException.class,
				() -> controle.atualizaUsuario(null, "victor braga", "victor@ccc.com", "9999-1231"));
	}

	@Test
	void testRemoveUsuario() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertEquals("Victor Braga/12345678910, victor@ccc.com, 9999-1231, status: doador",
				controle.pesquisaUsuarioPorId("12345678910"));
		assertTrue(controle.removeUsuario("12345678910"));
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("12345678910"));

	}

	@Test
	void testRemoveUsuarioUsuarioNaoEncontrado() {
		assertThrows(IllegalArgumentException.class, () -> controle.removeUsuario("12345678910"));

	}

	@Test
	void testRemoveUsuarioIdNuloOuVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.removeUsuario("   "));
		assertThrows(IllegalArgumentException.class, () -> controle.removeUsuario(null));

	}

	// CASE 2

	@Test
	void testAdicionaDescritor() {
		assertTrue(controle.getDescritores().isEmpty());
		controle.adicionaDescritor("cadeira de rodas");
		assertFalse(controle.getDescritores().isEmpty());
		assertTrue(controle.getDescritores().containsKey("cadeiraderodas"));
	}

	@Test
	void testAdicionaDescritorNulo() {
		assertThrows(IllegalArgumentException.class, () -> controle.adicionaDescritor(null));
	}

	@Test
	void testAdicionaDescritorVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.adicionaDescritor("  "));
	}

	@Test
	void testAdicionaItemParaDoacao() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		int i = controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		assertEquals(1, i);

	}

	@Test
	void testAdicionaItemNecessario() {

	}

	@Test
	void testExibeItem() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		assertEquals("1 - cadeira de rodas, tags: [roda grande,  motorizada], quantidade: 3",
				controle.exibeItem(1, "12345678910"));
	}

	@Test
	void testExibeItemIdItemNulo() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		assertThrows(IllegalArgumentException.class, () -> controle.exibeItem(null, "12345678910"));
	}

	@Test
	void testExibeItemIdUsuarioNulo() {
		assertThrows(IllegalArgumentException.class, () -> controle.exibeItem(1, null));
	}

	@Test
	void testExibeItemIdUsuarioVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.exibeItem(1, "  "));
	}

	@Test
	void testRemoveItem() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		assertEquals("1 - cadeira de rodas, tags: [roda grande,  motorizada], quantidade: 3",
				controle.exibeItem(1, "12345678910"));
		controle.removeItem(1, "12345678910");
		assertThrows(IllegalArgumentException.class, () -> controle.exibeItem(1, "12345678910"));
	}

	@Test
	void testRemoveItemIdItemNulo() {
		assertThrows(IllegalArgumentException.class, () -> controle.exibeItem(null, "12345678910"));
	}

	void testRemoveItemIdUsuarioNulo() {
		assertThrows(IllegalArgumentException.class, () -> controle.exibeItem(1, null));
	}

	@Test
	void testRemoveItemUsuarioInesistente() {
		assertThrows(IllegalArgumentException.class, () -> controle.exibeItem(1, "12345678910"));
	}

	@Test
	void testRemoveItemInesistente() {
		assertThrows(IllegalArgumentException.class, () -> controle.exibeItem(1, "12345678910"));
	}

	@Test
	void testAtualizaItem() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		assertEquals("1 - cadeira de rodas, tags: [roda grande,  motorizada], quantidade: 3",
				controle.exibeItem(1, "12345678910"));
		controle.atualizaItem(1, "12345678910", 10, "automatica, verde");
		assertEquals("1 - cadeira de rodas, tags: [automatica,  verde], quantidade: 10",
				controle.exibeItem(1, "12345678910"));
	}
	
	@Test
	void testAtualizaItemTagNula() {
		assertThrows(IllegalArgumentException.class, () -> controle.atualizaItem(1, "12345678910", 20, null));
	}
	
	@Test
	void testAtualizaItemUsuarioInexistente() {
		assertThrows(IllegalArgumentException.class, () -> controle.atualizaItem(1, "12345678910", 10, "verde"));
	}
	
	@Test
	void testAtualizaItemUsuarioItemInexistente() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertThrows(IllegalArgumentException.class, () -> controle.atualizaItem(1, "12345678910", 10, "verde"));
	}
	
	@Test
	void testAtualizaItemUsuaioNulo() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertThrows(IllegalArgumentException.class, () -> controle.atualizaItem(1, null, 10, "verde"));
	}
	
	@Test
	void testAtualizaItemUsuaioVazio() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertThrows(IllegalArgumentException.class, () -> controle.atualizaItem(1, "    ", 10, "verde"));
	}
	
	@Test
	void testAtualizaItemNulo() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertThrows(IllegalArgumentException.class, () -> controle.atualizaItem(null, "12345678910", 10, "verde"));
	}
	
	@Test
	void testAtualizaItemQtdInvalida() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertThrows(IllegalArgumentException.class, () -> controle.atualizaItem(1, "12345678910", -200, "verde"));
	}

	// Case 3
	@Test
	void testListaDescritorItem() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaDoador("12345678912", "dacBez Bez", "dacio@ccc.com", "9999-2231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		controle.adicionaItemParaDoacao("12345678910", "colchao", 5, "colchao kingsize,conforto,dormir");
		controle.adicionaItemParaDoacao("12345678912", "cobertor", 10, "lencol,conforto");		
		
		assertEquals("3 - cadeira de rodas | 10 - cobertor | 5 - colchao",controle.listaDescritorDeItensParaDoacao());
		assertEquals("3 - cobertor, tags: [lencol, conforto], quantidade: 10, doador: dacBez Bez/12345678912 | 2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5, doador: Victor Braga/12345678910 | 1 - cadeira de rodas, tags: [roda grande,  motorizada], quantidade: 3, doador: Victor Braga/12345678910", controle.listaItensParaDoacao());
		
	}
	@Test
	void pesquisaPorDescricaoSuccess() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaDoador("12345678912", "dacBez Bez", "dacio@ccc.com", "9999-2231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de praia", 5, "dobravel");
		controle.adicionaItemParaDoacao("12345678912", "cadeira reclinavel", 10, "couro");
		
		assertEquals("2 - cadeira de praia, tags: [dobravel], quantidade: 5 | 1 - cadeira de rodas, tags: [roda grande,  motorizada], quantidade: 3 | 3 - cadeira reclinavel, tags: [couro], quantidade: 10", controle.pesquisaItemParaDoacaoPorDescricao("Cadeira"));
	}
	@Test
	void pesquisaPorDescricaoFail() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaItemParaDoacaoPorDescricao(""));
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaItemParaDoacaoPorDescricao(null));
	}
	// Case 4

	@Test
	void pesquisaPorId() {
		int i = controle.adicionaItemNecessario("84473712044", "Livro", 1, "Infantil,Matematica,Didatico");
		assertEquals(1, i);
		i = controle.adicionaItemNecessario("31862316040", "Toalha de Banho", 3, "Adulto,TAM G,Azul");
		assertEquals(2, i);
	}

}
