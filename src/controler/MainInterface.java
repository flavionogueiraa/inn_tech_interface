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

	private static Scene cenaReserva;

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

		case "menu":
			stage.setScene(cenaMenu);
			break;

		case "usuario":
			stage.setScene(cenaUsuario);
			break;

		case "reserva":
			stage.setScene(cenaReserva);
			break;

		case "caixaVisualizar":
			stage.setScene(cenaCaixaVisualizar);
			break;

		case "relatorioEntradas":
			stage.setScene(cenaRelatorioEntradas);
			break;

		case "relatorioSaidas":
			stage.setScene(cenaRelatorioSaidas);
			break;

		case "saidaCadastrar":
			stage.setScene(cenaSaidaCadastro);
			break;

		case "saidaListar":
			stage.setScene(cenaSaidaListagem);
			break;

		case "saidaExcluir":
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
		Parent FXMLReserva = FXMLLoader.load(getClass().getResource("/view/Reserva.fxml"));
		cenaReserva = new Scene(FXMLReserva);
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
