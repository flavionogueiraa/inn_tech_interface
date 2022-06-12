package financeiro.saida;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import financeiro.Saida;
import junit.framework.TestCase;
import usuario.Usuario;
import utils.ValueToTest;

class SaidaTest extends TestCase {
	Saida saida;

	@Override
	public void setUp() {
		saida = new Saida(1, 1.0, null, "", "", null);
	}

	private Saida createSaida(Integer i) {
		return new Saida(ValueToTest.getIntegerValue(i), ValueToTest.getDoubleValue(i), ValueToTest.getDateValue(i),
				ValueToTest.getStringValue(i), ValueToTest.getStringValue(i), null);
	}

	@Test
	void saidaTest() {
		for (int i = 0; i < 4; i++)
			assertNotEquals(createSaida(i), null);
	}

	@Test
	void getIdTest() {
		setUp();
		assertEquals((int) saida.getId(), 1);
	}

	@Test
	void getAndSetValorTest() {
		setUp();
		assertEquals(saida.getValor(), 1.0);
		saida.setValor(2.0);
		assertEquals(saida.getValor(), 2.0);
	}

	@Test
	void getAndSetDataCriacaoTest() {
		setUp();
		assertEquals(saida.getDataCriacao(), null);
		saida.setDataCriacao(ValueToTest.getDateValue(1));
		assertEquals(saida.getDataCriacao(), ValueToTest.getDateValue(1));
	}

	@Test
	void getDataCriacaoFormatadaTest() throws ParseException {
		setUp();
		assertEquals(saida.getDataCriacaoFormatada(), "-");
		Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("12/06/2022 09:51");
		saida.setDataCriacao(newDate);
		assertEquals(saida.getDataCriacaoFormatada(), "12/06/2022 09:51");
	}	

	@Test
	void getAndSetMotivoTest() {
		setUp();
		assertEquals(saida.getMotivo(), "");
		saida.setMotivo("teste");
		assertEquals(saida.getMotivo(), "teste");
	}

	@Test
	void getAndSetObservacoesTest() {
		setUp();
		assertEquals(saida.getObservacoes(), "");
		saida.setObservacoes("teste");
		assertEquals(saida.getObservacoes(), "teste");
	}

	@Test
	void getAndSetUsuarioCriacaoTest() {
		setUp();
		assertEquals(saida.getUsuarioCriacao(), null);
		Usuario usuario = new Usuario(1, "Teste", "778.908.100-40", "example", true);
		saida.setUsuarioCriacao(usuario);
		assertEquals(saida.getUsuarioCriacao(), usuario);

	}

	@Test
	void getNomeUsuarioTest() {
		setUp();
		assertEquals(saida.getNomeUsuario(), "-");
		Usuario usuario = new Usuario(1, "Teste", "778.908.100-40", "example", true);
		saida.setUsuarioCriacao(usuario);
		assertEquals(saida.getNomeUsuario(), "Teste");
	}
}
