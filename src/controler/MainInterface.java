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

	private static Scene cenaUsuario;

	private static Scene cenaReservaCadastro;
	private static Scene cenaReservaListagem;
	private static Scene cenaReservaExclusao;

	private static Scene cenaCaixaVisualizar;

	private static Scene cenaRelatorioEntradas;
	private static Scene cenaRelatorioSaidas;

	private static Scene cenaSaidaCadastro;
	private static Scene cenaSaidaListagem;
	private static Scene cenaSaidaExclusao;

	@Override
	public void start(Stage primeiroEstagio) throws Exception {
		Arquivo.inicializaVariaveis();
		stage = primeiroEstagio;
		primeiroEstagio.setResizable(false);

		carregaTelasLogin();
		carregaTelasMenu();
		carregaTelasUsuario();
		carregaTelasReserva();
		carregaTelasCaixa();
		carregaTelasRelatorio();
		carregaTelasSaida();

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

		case "usuario":
			stage.setTitle("Usu�rios");
			stage.setScene(cenaUsuario);
			break;

		case "reservaCadastrar":
			stage.setTitle("Cadastro de reservas");
			stage.setScene(cenaReservaCadastro);
			break;

		case "reservaListar":
			stage.setTitle("Listagem de reservas");
			stage.setScene(cenaReservaListagem);
			break;

		case "reservaExcluir":
			stage.setTitle("Exclus�o de reservas");
			stage.setScene(cenaReservaExclusao);
			break;

		case "caixaVisualizar":
			stage.setTitle("Visualiza��o do caixa");
			stage.setScene(cenaCaixaVisualizar);
			break;

		case "relatorioEntradas":
			stage.setTitle("Relat�rio de entradas");
			stage.setScene(cenaRelatorioEntradas);
			break;

		case "relatorioSaidas":
			stage.setTitle("Relat�rio de sa�das");
			stage.setScene(cenaRelatorioSaidas);
			break;

		case "saidaCadastrar":
			stage.setTitle("Cadastro de sa�das");
			stage.setScene(cenaSaidaCadastro);
			break;

		case "saidaListar":
			stage.setTitle("Listagem de sa�das");
			stage.setScene(cenaSaidaListagem);
			break;

		case "saidaExcluir":
			stage.setTitle("Exclus�o de sa�das");
			stage.setScene(cenaSaidaExclusao);
			break;

		default:
			break;
		}
	}

	private void carregaTelasLogin() throws Exception {
		Parent FXMLLogin = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		cenaLogin = new Scene(FXMLLogin);
	}

	private void carregaTelasMenu() throws Exception {
		Parent FXMLMenu = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		cenaMenu = new Scene(FXMLMenu);
	}

	private void carregaTelasUsuario() throws Exception {
		Parent FXMLUsuario = FXMLLoader.load(getClass().getResource("/view/Usuario.fxml"));
		cenaUsuario = new Scene(FXMLUsuario);
	}

	private void carregaTelasReserva() throws Exception {
		Parent FXMLReservaCadastro = FXMLLoader.load(getClass().getResource("/view/ReservaCadastro.fxml"));
		cenaReservaCadastro = new Scene(FXMLReservaCadastro);

		Parent FXMLReservaListagem = FXMLLoader.load(getClass().getResource("/view/ReservaListagem.fxml"));
		cenaReservaListagem = new Scene(FXMLReservaListagem);

		Parent FXMLReservaExclusao = FXMLLoader.load(getClass().getResource("/view/ReservaExclusao.fxml"));
		cenaReservaExclusao = new Scene(FXMLReservaExclusao);
	}

	private void carregaTelasCaixa() throws Exception {
		Parent FXMLCaixaVisualiza = FXMLLoader.load(getClass().getResource("/view/CaixaVisualizar.fxml"));
		cenaCaixaVisualizar = new Scene(FXMLCaixaVisualiza);
	}

	private void carregaTelasRelatorio() throws Exception {
		Parent FXMLRelatorioEntradas = FXMLLoader.load(getClass().getResource("/view/RelatorioEntradas.fxml"));
		cenaRelatorioEntradas = new Scene(FXMLRelatorioEntradas);

		Parent FXMLRelatorioSaidas = FXMLLoader.load(getClass().getResource("/view/RelatorioSaidas.fxml"));
		cenaRelatorioSaidas = new Scene(FXMLRelatorioSaidas);
	}

	private void carregaTelasSaida() throws Exception {
		Parent FXMLSaidaCadastro = FXMLLoader.load(getClass().getResource("/view/SaidaCadastro.fxml"));
		cenaSaidaCadastro = new Scene(FXMLSaidaCadastro);

		Parent FXMLSaidaListagem = FXMLLoader.load(getClass().getResource("/view/SaidaListagem.fxml"));
		cenaSaidaListagem = new Scene(FXMLSaidaListagem);

		Parent FXMLSaidaExclusao = FXMLLoader.load(getClass().getResource("/view/SaidaExclusao.fxml"));
		cenaSaidaExclusao = new Scene(FXMLSaidaExclusao);
	}
}
