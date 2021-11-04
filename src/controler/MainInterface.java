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
	private static Scene cenaUsuarioExclusao;

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

		case "usuarioCadastrar":
			stage.setTitle("Cadastro de usuários");
			stage.setScene(cenaUsuarioCadastro);
			break;

		case "usuarioListar":
			stage.setTitle("Listagem de usuários");
			stage.setScene(cenaUsuarioListagem);
			break;

		case "usuarioExcluir":
			stage.setTitle("Exclusão de usuários");
			stage.setScene(cenaUsuarioExclusao);
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
			stage.setTitle("Exclusão de reservas");
			stage.setScene(cenaReservaExclusao);
			break;

		case "caixaVisualizar":
			stage.setTitle("Visualização do caixa");
			stage.setScene(cenaCaixaVisualizar);
			break;

		case "relatorioEntradas":
			stage.setTitle("Relatório de entradas");
			stage.setScene(cenaRelatorioEntradas);
			break;

		case "relatorioSaidas":
			stage.setTitle("Relatório de saídas");
			stage.setScene(cenaRelatorioSaidas);
			break;

		case "saidaCadastrar":
			stage.setTitle("Cadastro de saídas");
			stage.setScene(cenaSaidaCadastro);
			break;

		case "saidaListar":
			stage.setTitle("Listagem de saídas");
			stage.setScene(cenaSaidaListagem);
			break;

		case "saidaExcluir":
			stage.setTitle("Exclusão de saídas");
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
		Parent FXMLUsuarioCadastro = FXMLLoader.load(getClass().getResource("/view/UsuarioCadastro.fxml"));
		cenaUsuarioCadastro = new Scene(FXMLUsuarioCadastro);

		Parent FXMLUsuarioListagem = FXMLLoader.load(getClass().getResource("/view/UsuarioListagem.fxml"));
		cenaUsuarioListagem = new Scene(FXMLUsuarioListagem);

		Parent FXMLUsuarioExclusao = FXMLLoader.load(getClass().getResource("/view/UsuarioExclusao.fxml"));
		cenaUsuarioExclusao = new Scene(FXMLUsuarioExclusao);
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
