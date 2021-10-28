package interface_package;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InterfaceControler {
	@FXML
	private Button the_button;

	@FXML
	void testeFuncao(ActionEvent event) {
		System.out.println("Clicou");
		MainInterface.trocaTela("login");
	}
}
