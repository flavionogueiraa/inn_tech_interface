package pacote_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReservaControler {
	@FXML
	void menuUsuario(ActionEvent event) {
		MainInterface.trocaTela("usuario");
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
