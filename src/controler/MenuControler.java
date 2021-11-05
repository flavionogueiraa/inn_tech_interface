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
	void reservaCadastrar(ActionEvent event) {
		MainInterface.trocaTela("reservaCadastrar");
	}

	@FXML
	void reservaExcluir(ActionEvent event) {
		MainInterface.trocaTela("reservaExcluir");
	}

	@FXML
	void reservaListar(ActionEvent event) {
		MainInterface.trocaTela("reservaListar");
	}

	@FXML
	void saidaCadastrar(ActionEvent event) {
		MainInterface.trocaTela("saidaCadastrar");
	}

	@FXML
	void saidaExcluir(ActionEvent event) {
		MainInterface.trocaTela("saidaExcluir");
	}

	@FXML
	void saidaListar(ActionEvent event) {
		MainInterface.trocaTela("saidaListar");
	}

	@FXML
	void usuario(ActionEvent event) {
		MainInterface.trocaTela("usuario");
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
