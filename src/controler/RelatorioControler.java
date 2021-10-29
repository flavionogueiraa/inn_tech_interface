package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RelatorioControler {
	@FXML
	void menuReserva(ActionEvent event) {
		MainInterface.trocaTela("reserva");
	}

	@FXML
	void visualizaCaixa(ActionEvent event) {
		MainInterface.trocaTela("caixa");
	}
	
	@FXML
	void menuSaida(ActionEvent event) {
		MainInterface.trocaTela("saida");
	}
	
	@FXML
	void menuUsuario(ActionEvent event) {
		MainInterface.trocaTela("usuario");
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
