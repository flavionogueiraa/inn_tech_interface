package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuControler {
	@FXML
    void caixaVisualizar(ActionEvent event) {

    }

    @FXML
    void relatorioEntradas(ActionEvent event) {

    }

    @FXML
    void relatorioSaidas(ActionEvent event) {

    }

    @FXML
    void reservaCadastrar(ActionEvent event) {
    	MainInterface.trocaTela("reservaCadastrar");
    }

    @FXML
    void reservaExcluir(ActionEvent event) {
    	MainInterface.trocaTela("reservaExcluir");
    }

    @FXML
    void reservaListar(ActionEvent event) {
    	MainInterface.trocaTela("reservaListar");
    }

    @FXML
    void saidaCadastrar(ActionEvent event) {

    }

    @FXML
    void saidaExcluir(ActionEvent event) {

    }

    @FXML
    void saidaListar(ActionEvent event) {

    }

    @FXML
    void usuarioCadastrar(ActionEvent event) {
    	MainInterface.trocaTela("usuarioCadastrar");
    }

    @FXML
    void usuarioExcluir(ActionEvent event) {
    	MainInterface.trocaTela("usuarioExcluir");
    }

    @FXML
    void usuarioListar(ActionEvent event) {
    	MainInterface.trocaTela("usuarioListar");
    }
    
	@FXML
	void sair(ActionEvent event) {
		MainInterface.trocaTela("login");
	}
}
