package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main_interface.LoginInterface;

public class LoginControler {
	@FXML
	private TextField campo_cpf;

	@FXML
	private TextField campo_senha;

	@FXML
	private Label mensagem_erro;

	@FXML
	void login(ActionEvent event) {
		String CPF = campo_cpf.getText();
		String senha = campo_senha.getText();

		if (LoginInterface.realizarLoginInterface(CPF, senha) != null) {
			campo_cpf.clear();
			campo_senha.clear();

			mensagem_erro.setText("");

			MainInterface.trocaTela("home");
		} else {
			mensagem_erro.setText("Informações incorretas!");
		}
	}
}
