package reserva;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import bd.Conection;
import junit.framework.TestCase;
import quarto.Quarto;
import usuario.Usuario;
import utils.ValueToTest;

class ReservaTest extends TestCase {
	Date date;
	Quarto quarto, quartoInterface;
	Usuario usuario, usuarioInterface;

	@Override
	public void setUp() {
		Conection.conectar();
		date = new Date();
		usuario = new Usuario(1, "Teste", "778.908.100-40", "example", true);
		usuarioInterface = Usuario.cadastraUsuarioInterface("Teste", "778.908.100-40", "example", true);
		Usuario.usuarioLogado = usuarioInterface;
		quarto = new Quarto(1, 1, 2, "Exemplo", false, null);
		quartoInterface = Quarto.cadastraQuartoInterface(1, 2, "Exemplo");
	}

	private Reserva createReserva(Integer i) {
		return new Reserva(
				ValueToTest.getIntegerValue(i),
				ValueToTest.getStringValue(i),
				ValueToTest.getDoubleValue(i),
				ValueToTest.getDateValue(i),
				ValueToTest.getDateValue(i),
				ValueToTest.getStringValue(i),
				ValueToTest.getBooleanValue(i),
				quarto,
				usuario);
	}

	@Test
	void reservaTest() {
		for (int i = 0; i < 4; i++)
			assertNotEquals(createReserva(i), null);
	}

	@Test
	void cadastraReservaInterfaceTest() {
		setUp();
		Conection.conectar();
		for (int i = 0; i < 4; i++) {
			Reserva reserva = Reserva.cadastraReservaInterface(
				ValueToTest.getStringValue(i),
				ValueToTest.getDoubleValue(i),
				ValueToTest.getDateValue(i),
				ValueToTest.getDateValue(i),
				ValueToTest.getStringValue(i),
				ValueToTest.getBooleanValue(i),
				quartoInterface,
				usuarioInterface);
			assertNotEquals(reserva, null);
		}
	}
	
	@Override
	public void tearDown() {
		
	}
}
