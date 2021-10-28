package pacote_interface;

import arquivo.Arquivo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainInterface extends Application {
	private static Stage stage;

	private static Scene cenaLogin;
	private static Scene cenaMenu;

	@Override
	public void start(Stage primeiroEstagio) throws Exception {
		Arquivo.inicializaVariaveis();
		stage = primeiroEstagio;

		Parent FXMLLogin = FXMLLoader.load(getClass().getResource("/pacote_interface/Login.fxml"));
		cenaLogin = new Scene(FXMLLogin);

		Parent FXMLMenu = FXMLLoader.load(getClass().getResource("/pacote_interface/Menu.fxml"));
		cenaMenu = new Scene(FXMLMenu);

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

		default:
			break;
		}
	}
}
