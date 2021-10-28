package pacote_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main_interface.LoginInterface;

public class LoginControler {
	@FXML
	private TextField campo_cpf;

	@FXML
	private TextField campo_senha;

	@FXML
	void login(ActionEvent event) {
		String CPF = campo_cpf.getText();
		String senha = campo_senha.getText();

		if (LoginInterface.realizarLoginInterface(CPF, senha) != null) {
			MainInterface.trocaTela("menu");
		}
	}
}
