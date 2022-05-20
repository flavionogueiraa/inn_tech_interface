package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import usuario.Usuario;

public class MenuControler {
	public static MainInterface main = new MainInterface();

	@FXML
	void home(MouseEvent event) throws Exception {
		main.trocaTela("home");
	}

	@FXML
	void usuario(MouseEvent event) throws Exception {
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.isProprietario()) {
			main.trocaTela("usuario");
		}
	}

	@FXML
	void reserva(MouseEvent event) throws Exception {
		main.trocaTela("reserva");
	}

	@FXML
	void quarto(MouseEvent event) throws Exception {
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.isProprietario()) {
			main.trocaTela("quarto");
		}
	}

	@FXML
	void relatorio(MouseEvent event) throws Exception {
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.isProprietario()) {
			main.trocaTela("relatorio");
		}
	}

	@FXML
	void saida(MouseEvent event) throws Exception {
		main.trocaTela("saida");
	}

	@FXML
	void sair(ActionEvent event) throws Exception {
		main.trocaTela("login");
	}
}
