package financeiro.pagamento;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import financeiro.Pagamento;
import junit.framework.TestCase;
import quarto.Quarto;
import reserva.Reserva;
import utils.ValueToTest;

class PagamentoTest extends TestCase {
	Pagamento pagamento;

	@Override
	public void setUp() {
		pagamento = new Pagamento(1, 1.0, null, "", null);
	}

	private Pagamento createPagamento(Integer i) {
		return new Pagamento(ValueToTest.getIntegerValue(i), ValueToTest.getDoubleValue(i), ValueToTest.getDateValue(i),
				ValueToTest.getStringValue(i), null);
	}

	@Test
	void pagamentoTest() {
		for (int i = 0; i < 4; i++)
			assertNotEquals(createPagamento(i), null);
	}

	@Test
	void getIdTest() {
		setUp();
		assertEquals(pagamento.getId(), 1);
	}

	@Test
	void getAndSetValorTest() {
		setUp();
		assertEquals(pagamento.getValor(), (Double) 1.0);
		pagamento.setValor(2.0);
		assertEquals(pagamento.getValor(), (Double) 2.0);
	}

	@Test
	void getAndSetDataCriacaoTest() {
		setUp();
		assertEquals(pagamento.getDataCriacao(), null);
		pagamento.setDataCriacao(ValueToTest.getDateValue(1));
		assertEquals(pagamento.getDataCriacao(), ValueToTest.getDateValue(1));
	}

	@Test
	void getAndSetDataCriacaoFormatadaTest() throws ParseException {
		setUp();
		assertEquals(pagamento.getDataCriacaoFormatada(), "-");
		Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("12/06/2022 09:36");
		pagamento.setDataCriacao(newDate);
		assertEquals(pagamento.getDataCriacaoFormatada(), "12/06/2022 09:36");
	}

	@Test
	void getAndSetObservacoesTest() {
		setUp();
		assertEquals(pagamento.getObservacoes(), "");
		pagamento.setObservacoes("teste");
		assertEquals(pagamento.getObservacoes(), "teste");
	}

	@Test
	void getAndSetReservaTest() {
		setUp();
		assertEquals(pagamento.getReserva(), null);
		Quarto quarto = new Quarto(1, 1, 2, "Exemplo", false, null);
		Reserva reserva = new Reserva(1, "", 1.0, null, null, "", true, quarto, null);
		pagamento.setReserva(reserva);
		assertEquals(pagamento.getReserva(), reserva);
	}

	@Test
	void getIdReservaTest() {
		setUp();
		assertEquals((int) pagamento.getIdReserva(), -1);
		Quarto quarto = new Quarto(1, 1, 2, "Exemplo", false, null);
		Reserva reserva = new Reserva(1, "", 1.0, null, null, "", true, quarto, null);
		pagamento.setReserva(reserva);
		assertEquals((int) pagamento.getIdReserva(), 1);
	}
}
