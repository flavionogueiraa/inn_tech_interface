package controler;

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
	private static Scene cenaUsuario;
	private static Scene cenaReserva;
	private static Scene cenaCaixa;
	private static Scene cenaRelatorio;
	private static Scene cenaSaida;

	@Override
	public void start(Stage primeiroEstagio) throws Exception {
		Arquivo.inicializaVariaveis();
		stage = primeiroEstagio;

		Parent FXMLLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		cenaLogin = new Scene(FXMLLogin);

		Parent FXMLMenu = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		cenaMenu = new Scene(FXMLMenu);

		Parent FXMLUsuario = FXMLLoader.load(getClass().getResource("/view/Usuario.fxml"));
		cenaUsuario = new Scene(FXMLUsuario);

		Parent FXMLReserva = FXMLLoader.load(getClass().getResource("/view/Reserva.fxml"));
		cenaReserva = new Scene(FXMLReserva);

		Parent FXMLCaixa = FXMLLoader.load(getClass().getResource("/view/Caixa.fxml"));
		cenaCaixa = new Scene(FXMLCaixa);

		Parent FXMLRelatorio = FXMLLoader.load(getClass().getResource("/view/Relatorio.fxml"));
		cenaRelatorio = new Scene(FXMLRelatorio);

		Parent FXMLSaida = FXMLLoader.load(getClass().getResource("/view/Saida.fxml"));
		cenaSaida = new Scene(FXMLSaida);

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

		case "usuario":
			stage.setTitle("Usuários");
			stage.setScene(cenaUsuario);
			break;

		case "reserva":
			stage.setTitle("Reservas");
			stage.setScene(cenaReserva);
			break;

		case "caixa":
			stage.setTitle("Caixa");
			stage.setScene(cenaCaixa);
			break;

		case "relatorio":
			stage.setTitle("Relatório");
			stage.setScene(cenaRelatorio);
			break;

		case "saida":
			stage.setTitle("Saídas");
			stage.setScene(cenaSaida);
			break;

		default:
			break;
		}
	}
}
