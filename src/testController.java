import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistema.Controller;

class testController {

	Controller controle;

	@BeforeEach
	void iniciaTeste() {
		controle = new Controller();
	}

	@Test
	void testAdicionaUsuarioDoador() {
		assertEquals("12345678910",
				controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA"));
		assertEquals("Victor Braga/123.456.789-10, victor@ccc.com, 9999-1231, status: doador",
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
	void pesquisaUsuariosPorId() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertEquals("Victor Braga/123.456.789-10, victor@ccc.com, 9999-1231, status: doador",
				controle.pesquisaUsuarioPorId("12345678910"));
	}

	@Test
	void pesquisaUsuarioPorIdNuloOuVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("  "));
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId(null));
	}
	
	@Test
	void pesquisaUsuarioPorIdUsuarioNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("12312378910"));
	}
	
	@Test
	void pesquisaUsuariosPorNome() {
		controle.adicionaDoador("12345678910", "Victor Braga", "victor@ccc.com", "9999-1231", "PESSOA_FISICA");
		assertEquals("Victor Braga/123.456.789-10, victor@ccc.com, 9999-1231, status: doador",
				controle.pesquisaUsuarioPorNome("Victor Braga"));
	}
	
	@Test
	void pesquisaUsuarioPorNomeNuloOuVazio() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorNome("  "));
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorNome(null));
	}
	
	@Test
	void pesquisaUsuarioPorNomeUsuarioNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controle.pesquisaUsuarioPorId("Paulo Coelho"));
	}
	
	
	

}
