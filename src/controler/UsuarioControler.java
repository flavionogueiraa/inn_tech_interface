package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class UsuarioControler {
	@FXML
	void menuReserva(ActionEvent event) {
		MainInterface.trocaTela("reserva");
	}

	@FXML
	void visualizaCaixa(ActionEvent event) {
		MainInterface.trocaTela("caixa");
	}
	
	@FXML
	void menuRelatorio(ActionEvent event) {
		MainInterface.trocaTela("relatorio");
	}
	
	@FXML
	void menuSaida(ActionEvent event) {
		MainInterface.trocaTela("saida");
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
