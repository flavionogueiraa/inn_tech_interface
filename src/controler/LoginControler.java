package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginControler {
	@FXML
	private TextField campo_cpf;

	@FXML
	private TextField campo_senha;

	@FXML
	private Label mensagem_erro;

	@FXML
	void login(ActionEvent event) throws Exception {
		String CPF = campo_cpf.getText();
		String senha = campo_senha.getText();

		if (LoginInterface.realizarLoginInterface(CPF, senha) != null) {
			campo_cpf.clear();
			campo_senha.clear();

			mensagem_erro.setText("");

			MenuControler.main.trocaTela("home");
		} else {
			mensagem_erro.setText("Informacoes incorretas!");
		}
	}
}
