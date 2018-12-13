package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistema.EDoeController;

class testController {

	EDoeController controle;

	@BeforeEach
	void iniciaTeste() {
		controle = new EDoeController();
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

//	@Test
//	void testPesquisaUsuariosPorId() {
//		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
//		assertEquals("Victor Braga/12345678910, victor@ccc.com, 9999-1231, status: doador",
//				controle.pesquisaUsuarioPorId("12345678910"));
//	}

	@Test
	void testPesquisaUsuarioPorIdNuloOuVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("  "));
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId(null));
	}

	@Test
	void testPesquisaUsuarioPorIdUsuarioNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("12312378910"));
	}

//	@Test
//	void testPesquisaUsuariosPorNome() {
//		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
//		assertEquals("Victor Braga/12345678910, victor@ccc.com, 9999-1231, status: doador",
//				controle.pesquisaUsuarioPorNome("Victor Braga"));
//	}

	@Test
	void testPesquisaUsuarioPorNomeNuloOuVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorNome("  "));
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorNome(null));
	}

	@Test
	void testPesquisaUsuarioPorNomeUsuarioNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("Paulo Coelho"));
	}

//	@Test
//	void testAtualizaUsuario() {
//		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
//		assertEquals("Victor Braga/12345678910, victor@ccc.com, 9999-1231, status: doador",
//				controle.pesquisaUsuarioPorId("12345678910"));
//		assertEquals("Victor Paz/12345678910, victor@ccc.ufcg.com, 9999-1231, status: doador",
//				controle.atualizaUsuario("12345678910", "Victor Paz", "victor@ccc.ufcg.com", "9999-1231"));
//	}

	@Test
	void testAtualizaUsuarioIdNuloOuVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> controle.atualizaUsuario(" ", "victor braga", "victor@ccc.com", "9999-1231"));
		assertThrows(IllegalArgumentException.class,
				() -> controle.atualizaUsuario(null, "victor braga", "victor@ccc.com", "9999-1231"));
	}

//	@Test
//	void testRemoveUsuario() {
//		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
//		assertEquals("Victor Braga/12345678910, victor@ccc.com, 9999-1231, status: doador",
//				controle.pesquisaUsuarioPorId("12345678910"));
//		assertTrue(controle.removeUsuario("12345678910"));
//		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("12345678910"));
//
//	}

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
		assertThrows(IllegalArgumentException.class, ()-> controle.adicionaDescritor(null));
	}
	
	@Test
	void testAdicionaDescritorVazio() {
		assertThrows(IllegalArgumentException.class, ()-> controle.adicionaDescritor("  "));
	}
	

	@Test
	void testAdicionaItemParaDoacao() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		// pq nao consigo receber o id desse metodo? 
		// quero fazer isso de baixo e nao consigo. pq?
		// assertEquals(1, controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada"));
		
	}
	
	@Test
	void testAdicionaItemNecessario() {
		
	}
	
	@Test
	void testExibeItem() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		assertEquals("1 - cadeira de rodas, tags: [roda grande,  motorizada], quantidade: 3", controle.exibeItem(1, "12345678910"));
	}
	
	@Test
	void testExibeItemIdItemNulo() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		assertThrows(IllegalArgumentException.class, ()-> controle.exibeItem(null, "12345678910"));
	}
	
	@Test
	void testExibeItemIdUsuarioNulo() {
		assertThrows(IllegalArgumentException.class, ()-> controle.exibeItem(1, null));
	}
	
	@Test
	void testExibeItemIdUsuarioVazio() {
		assertThrows(IllegalArgumentException.class, ()-> controle.exibeItem(1, "  "));
	}
	
	@Test
	void removeItem() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		controle.adicionaItemParaDoacao("12345678910", "cadeira de rodas", 3, "roda grande, motorizada");
		assertEquals("1 - cadeira de rodas, tags: [roda grande,  motorizada], quantidade: 3", controle.exibeItem(1, "12345678910"));
		controle.removeItem(1, "12345678910");
		assertThrows(IllegalArgumentException.class, ()-> controle.exibeItem(1, "12345678910"));
	}
	

}
