package controler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import quarto.Quarto;

public class QuartoControler extends MenuControler implements Initializable {
	@FXML
    private TextField campo_capacidade;

    @FXML
    private TextArea campo_descricao;

    @FXML
    private TextField campo_numero;

    @FXML
    private Label label_erro;

    @FXML
    private TableView<Quarto> tabela_quartos;

    @FXML
    private TableColumn<Quarto, String> quarto_numero;

    @FXML
    private TableColumn<Quarto, String> quarto_capacidade;

    @FXML
    private TableColumn<Quarto, String> quarto_descricao;

    @FXML
    private TableColumn<Quarto, Boolean> quarto_ocupado;

    @FXML
    void cadastrarQuarto(ActionEvent event) {

    }

    @FXML
    void editarQuarto(ActionEvent event) {

    }
    
    @FXML
    void salvarEdicaoQuarto(ActionEvent event) {

    }

    @FXML
    void excluirQuarto(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		quarto_numero.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumero().toString()));
		quarto_capacidade.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCapacidade().toString()));
		quarto_descricao.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));
		quarto_ocupado.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isOcupado()));
		
		tabela_quartos.getItems().addAll(Quarto.quartos);
	}
}
