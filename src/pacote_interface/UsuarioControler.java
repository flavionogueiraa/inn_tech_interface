package pacote_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class UsuarioControler {
	@FXML
	void menuReserva(ActionEvent event) {
		MainInterface.trocaTela("reserva");
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
