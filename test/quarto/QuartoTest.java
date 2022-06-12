package quarto;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import usuario.Usuario;
import utils.ValueToTest;

class QuartoTest extends TestCase {
	Quarto quarto;

	@Override
	public void setUp() {
		quarto = new Quarto(1, 1, 1, "", false, null);
	}

	private Quarto createQuarto(Integer i) {
		return new Quarto(ValueToTest.getIntegerValue(i), ValueToTest.getIntegerValue(i),
				ValueToTest.getIntegerValue(i), ValueToTest.getStringValue(i), ValueToTest.getBooleanValue(i), null);
	}

	@Test
	void quartoTest() {
		for (int i = 0; i < 4; i++)
			assertNotEquals(createQuarto(i), null);
	}

	@Test
	void getIdTest() {
		setUp();
		assertEquals(quarto.getId(), 1);
	}

	@Test
	void getAndSetNumeroTest() {
		setUp();
		assertEquals(quarto.getNumero(), (Integer) 1);
		quarto.setNumero(2);
		assertEquals(quarto.getNumero(), (Integer) 2);
	}

	@Test
	void isAndSetOcupadoTest() {
		setUp();
		assertEquals(quarto.isOcupado(), false);
		quarto.setOcupado(true);
		assertEquals(quarto.isOcupado(), true);
	}

	@Test
	void isOcupadoSimNaoTest() {
		setUp();
		assertEquals(quarto.isOcupadoSimNao(), "Nao");
		quarto.setOcupado(true);
		assertEquals(quarto.isOcupadoSimNao(), "Sim");
	}

	@Test
	void getAndSetCapacidadeTest() {
		setUp();
		assertEquals(quarto.getCapacidade(), (Integer) 1);
		quarto.setCapacidade(2);
		assertEquals(quarto.getCapacidade(), (Integer) 2);
	}

	@Test
	void getAndSetDescricaoTest() {
		setUp();
		assertEquals(quarto.getDescricao(), "");
		quarto.setDescricao("teste");
		assertEquals(quarto.getDescricao(), "teste");
	}

	@Test
	void getAndSetUsuarioCriacaoTest() {
		setUp();
		assertEquals(quarto.getUsuarioCriacao(), null);
		Usuario usuario = new Usuario(1, "Teste", "778.908.100-40", "example", true);
		quarto.setUsuarioCriacao(usuario);
		assertEquals(quarto.getUsuarioCriacao(), usuario);
	}

	@Test
	void getNomeUsuarioTest() {
		setUp();
		assertEquals(quarto.getNomeUsuario(), "-");
		Usuario usuario = new Usuario(1, "Teste", "778.908.100-40", "example", true);
		quarto.setUsuarioCriacao(usuario);
		assertEquals(quarto.getNomeUsuario(), "Teste");
	}
}
