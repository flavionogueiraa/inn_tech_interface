package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class QuartoControler extends MenuControler{
	@FXML
    private TextField campo_capacidade;

    @FXML
    private TextArea campo_descricao;

    @FXML
    private TextField campo_numero;

    @FXML
    private Label label_erro;

    @FXML
    private TableColumn<?, ?> quarto_capacidade;

    @FXML
    private TableColumn<?, ?> quarto_descricao;

    @FXML
    private TableColumn<?, ?> quarto_numero;

    @FXML
    private TableColumn<?, ?> quarto_ocupado;

    @FXML
    private TableView<?> tabela_quartos;

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
}
