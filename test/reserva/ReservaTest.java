package reserva;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import quarto.Quarto;
import usuario.Usuario;
import utils.ValueToTest;

class ReservaTest extends TestCase {
	Date date;
	Quarto quarto;
	Usuario usuario;
	Reserva reserva;

	@Override
	public void setUp() {
		// Conection.conectar();
		date = new Date();
		usuario = new Usuario(1, "Teste", "778.908.100-40", "example", true);
		quarto = new Quarto(1, 1, 2, "Exemplo", false, null);
		reserva = new Reserva(1, "", 1.0, null, null, "", true, quarto, null);
	}

	private Reserva createReserva(Integer i) {
		return new Reserva(ValueToTest.getIntegerValue(i), ValueToTest.getStringValue(i), ValueToTest.getDoubleValue(i),
				ValueToTest.getDateValue(i), ValueToTest.getDateValue(i), ValueToTest.getStringValue(i),
				ValueToTest.getBooleanValue(i), quarto, usuario);
	}

	@Test
	void reservaTest() {
		for (int i = 0; i < 4; i++)
			assertNotEquals(createReserva(i), null);
	}

	@Test
	void getIdTest() {
		setUp();
		assertEquals(reserva.getId(), 1);
	}

	@Test
	void getAndSetDataEstimadaCheckinTest() {
		setUp();
		assertEquals(reserva.getdataEstimadaCheckin(), null);
		reserva.setdataEstimadaCheckin(date);
		assertEquals(reserva.getdataEstimadaCheckin(), date);
	}

	@Test
	void getAndSetDataEstimadaCheckoutTest() {
		setUp();
		assertEquals(reserva.getdataEstimadaCheckout(), null);
		reserva.setdataEstimadaCheckout(date);
		assertEquals(reserva.getdataEstimadaCheckout(), date);
	}

	@Test
	void getDataEstimadaCheckinFormatadaTest() throws ParseException {
		setUp();
		assertEquals(reserva.getdataEstimadaCheckinFormatada(), "-");
		Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("10/06/2022 21:37");
		reserva.setdataEstimadaCheckin(newDate);
		assertEquals(reserva.getdataEstimadaCheckinFormatada(), "10/06/2022 21:37");
	}

	@Test
	void getDataEstimadaCheckoutFormatadaTest() throws ParseException {
		setUp();
		assertEquals(reserva.getdataEstimadaCheckoutFormatada(), "-");
		Date newDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("11/06/2022 14:55");
		reserva.setdataEstimadaCheckout(newDate);
		assertEquals(reserva.getdataEstimadaCheckoutFormatada(), "11/06/2022 14:55");
	}

	@Test
	void getAndSetHospedeTest() {
		setUp();
		assertEquals(reserva.getHospede(), "");
		reserva.setHospede("Exemplo");
		assertEquals(reserva.getHospede(), "Exemplo");
	}

	@Test
	void getAndSetValorTest() {
		setUp();
		assertEquals(reserva.getValor(), 1.0);
		reserva.setValor(100.0);
		assertEquals(reserva.getValor(), 100.0);
	}

	@Test
	void getAndSetQuartoTest() {
		setUp();
		assertEquals(reserva.getQuarto(), quarto);

		Quarto newQuarto = new Quarto(2, 2, 2, "Exemplo", false, null);
		reserva.setQuarto(newQuarto);
		assertEquals(reserva.getQuarto(), newQuarto);
	}

	@Test
	void isAndSetPagoTest() {
		setUp();
		assertEquals(reserva.isPago(), true);
		reserva.setPago(false);
		assertEquals(reserva.isPago(), false);
	}

	@Test
	void isPagoSimNaoTest() {
		setUp();
		assertEquals(reserva.isPagoSimNao(), "Sim");
		reserva.setPago(false);
		assertEquals(reserva.isPagoSimNao(), "Nao");
	}

	@Test
	void getAndSetObservacoesTest() {
		setUp();
		assertEquals(reserva.getObservacoes(), "");
		reserva.setObservacoes("Exemplo");
		assertEquals(reserva.getObservacoes(), "Exemplo");
	}

	@Test
	void getAndSetUsuarioCriacaoTest() {
		setUp();
		assertEquals(reserva.getUsuarioCriacao(), null);
		Usuario newUsuario = new Usuario(2, "Teste", "253.800.750-02", "example", true);
		reserva.setUsuarioCriacao(newUsuario);
		assertEquals(reserva.getUsuarioCriacao(), newUsuario);
	}

	@Test
	void getNomeUsuarioCriacaoTest() {
		setUp();
		assertEquals(reserva.getNomeUsuarioCriacao(), null);
		Usuario newUsuario = new Usuario(2, "Teste2", "253.800.750-02", "example", true);
		reserva.setUsuarioCriacao(newUsuario);
		assertEquals(reserva.getNomeUsuarioCriacao(), "Teste2");
	}
}
