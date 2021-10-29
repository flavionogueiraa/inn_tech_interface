package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SaidaControler {
	@FXML
	void menuRelatorio(ActionEvent event) {
		MainInterface.trocaTela("relatorio");
	}

	@FXML
	void menuReserva(ActionEvent event) {
		MainInterface.trocaTela("reserva");
	}

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
