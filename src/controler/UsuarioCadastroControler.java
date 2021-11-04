package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import usuario.Usuario;

public class UsuarioCadastroControler extends MenuControler {
	@FXML
	private TextField campo_nome;

	@FXML
	private TextField campo_cpf;

	@FXML
	private TextField campo_senha;

	@FXML
	private CheckBox campo_proprietario;

	@FXML
	void cadastrarUsuario(ActionEvent event) {
		String nome = campo_nome.getText();
		String CPF = campo_cpf.getText();
		String senha = campo_senha.getText();
		Boolean proprietario = campo_proprietario.isSelected();

		Usuario.cadastraUsuarioInterface(nome, CPF, senha, proprietario);
	}
}
