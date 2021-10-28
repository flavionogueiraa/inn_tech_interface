package interface_package;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainInterface extends Application {

	private static Stage stage;

	private static Scene cenaTeste;
	private static Scene cenaLogin;

	@Override
	public void start(Stage primeiroEstagio) throws Exception {
		stage = primeiroEstagio;

		Parent FXMLTeste = FXMLLoader.load(getClass().getResource("/interface_package/FxmlDoccument.fxml"));
		cenaTeste = new Scene(FXMLTeste);

		Parent FXMLLogin = FXMLLoader.load(getClass().getResource("/interface_package/Login.fxml"));
		cenaLogin = new Scene(FXMLLogin);

		primeiroEstagio.setTitle("Meu novo programa com interface");
		primeiroEstagio.setScene(cenaTeste);
		primeiroEstagio.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void trocaTela(String nomeTela) {
		switch (nomeTela) {
		case "teste":
			stage.setScene(cenaTeste);
			break;

		case "login":
			stage.setScene(cenaLogin);
			break;

		default:
			break;
		}
	}
}
