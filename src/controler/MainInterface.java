package controler;

import arquivo.Arquivo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainInterface extends Application {
	private static Stage stage;

	private static Scene cenaLogin;
	private static Scene cenaMenu;
	private static Scene cenaUsuarioCadastro;
	private static Scene cenaUsuarioListagem;

	@Override
	public void start(Stage primeiroEstagio) throws Exception {
		Arquivo.inicializaVariaveis();
		stage = primeiroEstagio;

		Parent FXMLLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		cenaLogin = new Scene(FXMLLogin);

		Parent FXMLMenu = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		cenaMenu = new Scene(FXMLMenu);

		Parent FXMLUsuarioCadastro= FXMLLoader.load(getClass().getResource("/view/UsuarioCadastro.fxml"));
		cenaUsuarioCadastro = new Scene(FXMLUsuarioCadastro);

		Parent FXMLUsuarioListagem= FXMLLoader.load(getClass().getResource("/view/UsuarioListagem.fxml"));
		cenaUsuarioListagem= new Scene(FXMLUsuarioListagem);

		primeiroEstagio.getIcons().add(new Image(getClass().getResourceAsStream("/img/18x18_pixel_icon.png")));
		primeiroEstagio.setTitle("Login");
		primeiroEstagio.setScene(cenaLogin);
		primeiroEstagio.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void trocaTela(String nomeTela) {
		switch (nomeTela) {
		case "login":
			stage.setTitle("Login");
			stage.setScene(cenaLogin);
			break;

		case "menu":
			stage.setTitle("Menu");
			stage.setScene(cenaMenu);
			break;

		case "usuarioCadastrar":
			stage.setTitle("Cadastro de usuários");
			stage.setScene(cenaUsuarioCadastro);
			break;

		case "usuarioListar":
			stage.setTitle("Listagem de usuários");
			stage.setScene(cenaUsuarioListagem);
			break;

		default:
			break;
		}
	}
}
