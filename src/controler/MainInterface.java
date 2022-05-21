package controler;

import bd.Conection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainInterface extends Application {
	private static Stage stage;

	private static Scene cenaLogin;
	private static Scene cenaHome;
	private static Scene cenaUsuario;
	private static Scene cenaReserva;
	private static Scene cenaQuarto;
	private static Scene cenaSaida;
	private static Scene cenaRelatorioPagamentos;
	private static Scene cenaRelatorioSaidas;
	private static Scene cenaRelatorio;

	@Override
	public void start(Stage primeiroEstagio) throws Exception {
		Conection.conectar();
		stage = primeiroEstagio;
		primeiroEstagio.setResizable(false);

		carregaTodasTelas();
		primeiroEstagio.getIcons().add(new Image(getClass().getResourceAsStream("/img/18x18_pixel_icon.png")));
		primeiroEstagio.setTitle("Inn Tech");
		primeiroEstagio.setScene(cenaLogin);
		primeiroEstagio.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void trocaTela(String nomeTela) throws Exception {
		switch (nomeTela) {
			case "login":
				carregaTelasLogin();
				stage.setScene(cenaLogin);
				break;

			case "home":
				carregaTelasMenu();
				stage.setScene(cenaHome);
				break;

			case "usuario":
				carregaTelasUsuario();
				stage.setScene(cenaUsuario);
				break;

			case "reserva":
				carregaTelasReserva();
				stage.setScene(cenaReserva);
				break;

			case "quarto":
				carregaTelasQuarto();
				stage.setScene(cenaQuarto);
				break;

			case "saida":
				carregaTelasSaida();
				stage.setScene(cenaSaida);
				break;

			case "relatorioPagamentos":
				carregaTelasRelatorio();
				stage.setScene(cenaRelatorioPagamentos);
				break;

			case "relatorioSaidas":
				carregaTelasSaida();
				stage.setScene(cenaRelatorioSaidas);
				break;

			case "relatorio":
				carregaTelasRelatorio();
				stage.setScene(cenaRelatorio);
				break;

			default:
				break;
		}
	}

	public Parent getFXMLLoader(String caminhoTela) throws Exception {
		return FXMLLoader.load(getClass().getResource(caminhoTela));
	}

	public void carregaTelasLogin() throws Exception {
		Parent FXMLLogin = getFXMLLoader("/view/Login.fxml");
		cenaLogin = new Scene(FXMLLogin);
	}

	public void carregaTelasMenu() throws Exception {
		Parent FXMLHome = getFXMLLoader("/view/Home.fxml");
		cenaHome = new Scene(FXMLHome);
	}

	public void carregaTelasUsuario() throws Exception {
		Parent FXMLUsuario = getFXMLLoader("/view/Usuario.fxml");
		cenaUsuario = new Scene(FXMLUsuario);
	}

	public void carregaTelasReserva() throws Exception {
		Parent FXMLReserva = getFXMLLoader("/view/Reserva.fxml");
		cenaReserva = new Scene(FXMLReserva);
	}

	public void carregaTelasQuarto() throws Exception {
		Parent FXMLQuarto = getFXMLLoader("/view/Quarto.fxml");
		cenaQuarto = new Scene(FXMLQuarto);
	}

	public void carregaTelasRelatorio() throws Exception {
		Parent FXMLRelatorio = getFXMLLoader("/view/Relatorio.fxml");
		cenaRelatorio = new Scene(FXMLRelatorio);
	}

	public void carregaTelasSaida() throws Exception {
		Parent FXMLSaida = getFXMLLoader("/view/Saida.fxml");
		cenaSaida = new Scene(FXMLSaida);
	}

	public void carregaTodasTelas() throws Exception {
		carregaTelasLogin();
		carregaTelasMenu();
		carregaTelasUsuario();
		carregaTelasReserva();
		carregaTelasQuarto();
		carregaTelasRelatorio();
		carregaTelasSaida();
	}
}
