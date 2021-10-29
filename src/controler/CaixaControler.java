package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CaixaControler {
	@FXML
	void menuUsuario(ActionEvent event) {
		MainInterface.trocaTela("usuario");
	}
	
	@FXML
	void menuRelatorio(ActionEvent event) {
		MainInterface.trocaTela("relatorio");
	}

	@FXML
	void menuReserva(ActionEvent event) {
		MainInterface.trocaTela("reserva");
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
