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

		Parent FXMLUsuarioExclusao = FXMLLoader.load(getClass().getResource("/view/UsuarioExclusao.fxml"));
		cenaUsuarioExclusao= new Scene(FXMLUsuarioExclusao);

		Parent FXMLReservaCadastro= FXMLLoader.load(getClass().getResource("/view/ReservaCadastro.fxml"));
		cenaReservaCadastro = new Scene(FXMLReservaCadastro);

		Parent FXMLReservaListagem= FXMLLoader.load(getClass().getResource("/view/ReservaListagem.fxml"));
		cenaReservaListagem= new Scene(FXMLReservaListagem);

		Parent FXMLReservaExclusao = FXMLLoader.load(getClass().getResource("/view/ReservaExclusao.fxml"));
		cenaReservaExclusao= new Scene(FXMLReservaExclusao);

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

		default:
			break;
		}
	}
}
