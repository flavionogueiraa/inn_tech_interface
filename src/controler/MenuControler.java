package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuControler {
	@FXML
	void caixaVisualizar(ActionEvent event) {
		MainInterface.trocaTela("caixaVisualizar");
	}

	@FXML
	void relatorioEntradas(ActionEvent event) {
		MainInterface.trocaTela("relatorioEntradas");
	}

	@FXML
	void relatorioSaidas(ActionEvent event) {
		MainInterface.trocaTela("relatorioSaidas");
	}

	@FXML
	void reserva(ActionEvent event) {
		MainInterface.trocaTela("reserva");
	}
	
	@FXML
    void saida(ActionEvent event) {
		MainInterface.trocaTela("saida");
    }

	@FXML
	void usuario(ActionEvent event) {
		MainInterface.trocaTela("usuario");
	}

	@FXML
	void home(ActionEvent event) {
		MainInterface.trocaTela("home");
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
