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
	void usuarioCadastrar(ActionEvent event) {
		MainInterface.trocaTela("usuarioCadastrar");
	}

	@FXML
	void usuarioExcluir(ActionEvent event) {
		MainInterface.trocaTela("usuarioExcluir");
	}

	@FXML
	void usuarioListar(ActionEvent event) {
		MainInterface.trocaTela("usuarioListar");
	}

	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
