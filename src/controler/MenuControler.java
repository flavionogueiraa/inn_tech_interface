package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import usuario.Usuario;

public class MenuControler {

	@FXML
	void home(MouseEvent event) throws Exception {
		Parent FXMLHome = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
		MainInterface.cenaHome = new Scene(FXMLHome);
		MainInterface.trocaTela("home");
	}

	@FXML
	void usuario(MouseEvent event) throws Exception {
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.isProprietario()) {
			Parent FXMLUsuario = FXMLLoader.load(getClass().getResource("/view/Usuario.fxml"));
			MainInterface.cenaUsuario = new Scene(FXMLUsuario);
			MainInterface.trocaTela("usuario");
		}
	}

	@FXML
	void reserva(MouseEvent event) throws Exception {
		Parent FXMLReserva = FXMLLoader.load(getClass().getResource("/view/Reserva.fxml"));
		MainInterface.cenaReserva = new Scene(FXMLReserva);
		MainInterface.trocaTela("reserva");
	}

	@FXML
	void quarto(MouseEvent event) throws Exception {
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.isProprietario()) {
			Parent FXMLQuarto = FXMLLoader.load(getClass().getResource("/view/Quarto.fxml"));
			MainInterface.cenaQuarto = new Scene(FXMLQuarto);
			MainInterface.trocaTela("quarto");
		}
	}

	@FXML
	void relatorio(MouseEvent event) throws Exception {
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.isProprietario()) {
			Parent FXMLRelatorioPagamentos = FXMLLoader.load(getClass().getResource("/view/RelatorioPagamentos.fxml"));
			MainInterface.cenaRelatorioPagamentos = new Scene(FXMLRelatorioPagamentos);

			Parent FXMLRelatorioSaidas = FXMLLoader.load(getClass().getResource("/view/RelatorioSaidas.fxml"));
			MainInterface.cenaRelatorioSaidas = new Scene(FXMLRelatorioSaidas);

			Parent FXMLRelatorio = FXMLLoader.load(getClass().getResource("/view/Relatorio.fxml"));
			MainInterface.cenaRelatorio = new Scene(FXMLRelatorio);
			MainInterface.trocaTela("relatorio");
		}
	}

	@FXML
	void saida(MouseEvent event) throws Exception {
		Parent FXMLSaida = FXMLLoader.load(getClass().getResource("/view/Saida.fxml"));
		MainInterface.cenaSaida = new Scene(FXMLSaida);
		MainInterface.trocaTela("saida");
	}

	@FXML
	void sair(MouseEvent event) throws Exception {
		MainInterface.trocaTela("login");
	}
}
