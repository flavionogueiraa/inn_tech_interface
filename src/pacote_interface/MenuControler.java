package pacote_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuControler {
	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
