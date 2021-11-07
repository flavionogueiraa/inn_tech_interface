package controler;

import java.net.URL;
import java.util.ResourceBundle;

import financeiro.Saida;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SaidaListagemControler extends MenuControler implements Initializable{
    @FXML
    private TableView<Saida> tabela_saidas;

    @FXML
    private TableColumn<Saida, String> saida_motivo;

    @FXML
    private TableColumn<Saida, String> saida_valor;
    
	@FXML
    private TableColumn<Saida, String> saida_data_hora;

    @FXML
    private TableColumn<Saida, String> saida_observacoes;
    
    ObservableList<Saida> lista = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saida_motivo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotivo()));
		saida_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		saida_data_hora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		saida_observacoes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacoes()));

		tabela_saidas.getItems().addAll(Saida.saidas);
	}
}
