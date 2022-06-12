package usuario;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import utils.ValueToTest;

class UsuarioTest extends TestCase {
	Usuario usuario;

	@Override
	public void setUp() {
		usuario = new Usuario(1, "", "", "", true);
	}

	private Usuario createUsuario(Integer i) {
		return new Usuario(ValueToTest.getIntegerValue(i), ValueToTest.getStringValue(i),
				ValueToTest.getStringValue(i), ValueToTest.getStringValue(i), ValueToTest.getBooleanValue(i));
	}

	@Test
	void usuarioTest() {
		for (int i = 0; i < 4; i++)
			assertNotEquals(createUsuario(i), null);
	}

	@Test
	void getIdTest() {
		setUp();
		assertEquals(usuario.getId(), 1);
	}

	@Test
	void getAndSetCPFTest() {
		setUp();
		assertEquals(usuario.getCPF(), "");
		usuario.setCPF("983.980.400-68");
		assertEquals(usuario.getCPF(), "983.980.400-68");
	}

	@Test
	void getAndSetNomeTest() {
		setUp();
		assertEquals(usuario.getNome(), "");
		usuario.setNome("Usuario Teste");
		assertEquals(usuario.getNome(), "Usuario Teste");
	}

	@Test
	void getAndSetSenhaTest() {
		setUp();
		assertEquals(usuario.getSenha(), "");
		usuario.setSenha("1234567");
		assertEquals(usuario.getSenha(), "1234567");
	}

	@Test
	void isAndSetProprietarioTest() {
		setUp();
		assertEquals(usuario.isProprietario(), true);
		usuario.setProprietario(false);
		assertEquals(usuario.isProprietario(), false);
	}

	@Test
	void isProprietarioSimNaoTest() {
		setUp();
		assertEquals(usuario.isProprietarioSimNao(), "Sim");
		usuario.setProprietario(false);
		assertEquals(usuario.isProprietarioSimNao(), "Nao");
	}
}
