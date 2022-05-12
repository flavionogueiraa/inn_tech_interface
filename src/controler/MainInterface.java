package controler;


import bd.Conection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainInterface extends Application {
	public static Stage stage;

	public static Scene cenaLogin;
	public static Scene cenaHome;

	public static Scene cenaUsuario;

	public static Scene cenaReserva;

	public static Scene cenaQuarto;
	
	public static Scene cenaSaida;

	public static Scene cenaRelatorioPagamentos;
	public static Scene cenaRelatorioSaidas;
	public static Scene cenaRelatorio;

	@Override
	public void start(Stage primeiroEstagio) throws Exception {
		//Arquivo.inicializaVariaveis();
		Conection.conectar();
		stage = primeiroEstagio;
		primeiroEstagio.setResizable(false);

		carregaTelasLogin();
		carregaTelasMenu();
		carregaTelasUsuario();
		carregaTelasReserva();
		carregaTelasQuarto();
		carregaTelasRelatorio();
		carregaTelasSaida();

		primeiroEstagio.getIcons().add(new Image(getClass().getResourceAsStream("/img/18x18_pixel_icon.png")));
		primeiroEstagio.setTitle("Inn Tech");
		primeiroEstagio.setScene(cenaLogin);
		primeiroEstagio.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void trocaTela(String nomeTela) {
		switch (nomeTela) {
		case "login":
			stage.setScene(cenaLogin);
			break;

		case "home":
			stage.setScene(cenaHome);
			break;

		case "usuario":
			stage.setScene(cenaUsuario);
			break;

		case "reserva":
			stage.setScene(cenaReserva);
			break;

		case "quarto":
			stage.setScene(cenaQuarto);
			break;
			
		case "saida":
			stage.setScene(cenaSaida);
			break;

		case "relatorioPagamentos":
			stage.setScene(cenaRelatorioPagamentos);
			break;

		case "relatorioSaidas":
			stage.setScene(cenaRelatorioSaidas);
			break;

		case "relatorio":
			stage.setScene(cenaRelatorio);
			break;

		default:
			break;
		}
	}

	public void carregaTelasLogin() throws Exception {
		Parent FXMLLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		cenaLogin = new Scene(FXMLLogin);
	}

	public void carregaTelasMenu() throws Exception {
		Parent FXMLHome = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
		cenaHome = new Scene(FXMLHome);
	}

	public void carregaTelasUsuario() throws Exception {
		Parent FXMLUsuario = FXMLLoader.load(getClass().getResource("/view/Usuario.fxml"));
		cenaUsuario = new Scene(FXMLUsuario);
	}

	public void carregaTelasReserva() throws Exception {
		Parent FXMLReserva = FXMLLoader.load(getClass().getResource("/view/Reserva.fxml"));
		cenaReserva = new Scene(FXMLReserva);
	}

	public void carregaTelasQuarto() throws Exception {
		Parent FXMLQuarto = FXMLLoader.load(getClass().getResource("/view/Quarto.fxml"));
		cenaQuarto = new Scene(FXMLQuarto);
	}

	public void carregaTelasRelatorio() throws Exception {
		Parent FXMLRelatorioPagamentos = FXMLLoader.load(getClass().getResource("/view/RelatorioPagamentos.fxml"));
		cenaRelatorioPagamentos = new Scene(FXMLRelatorioPagamentos);

		Parent FXMLRelatorioSaidas = FXMLLoader.load(getClass().getResource("/view/RelatorioSaidas.fxml"));
		cenaRelatorioSaidas = new Scene(FXMLRelatorioSaidas);

		Parent FXMLRelatorio = FXMLLoader.load(getClass().getResource("/view/Relatorio.fxml"));
		cenaRelatorio= new Scene(FXMLRelatorio);
	}

	public void carregaTelasSaida() throws Exception {
		Parent FXMLSaida = FXMLLoader.load(getClass().getResource("/view/Saida.fxml"));
		cenaSaida = new Scene(FXMLSaida);
	}
}
