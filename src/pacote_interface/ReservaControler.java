package pacote_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReservaControler {
	@FXML
	void menuUsuario(ActionEvent event) {
		MainInterface.trocaTela("usuario");
	}

	@FXML
	void visualizaCaixa(ActionEvent event) {
		MainInterface.trocaTela("caixa");
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
