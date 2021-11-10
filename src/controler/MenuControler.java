package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import usuario.Usuario;

public class MenuControler {
	@FXML
	void relatorio(ActionEvent event) {
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.isProprietario()) {
			MainInterface.trocaTela("relatorio");
		}
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
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.isProprietario()) {
			MainInterface.trocaTela("usuario");
		}
	}

	@FXML
	void home(ActionEvent event) {
		MainInterface.trocaTela("home");
	}

	@FXML
	void quarto(ActionEvent event) {
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.isProprietario()) {
			MainInterface.trocaTela("quarto");
		}
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
