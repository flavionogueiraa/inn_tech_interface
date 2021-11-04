package controler;

import java.net.URL;
import java.util.ResourceBundle;

import financeiro.Entrada;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RelatorioEntradasControler extends MenuControler implements Initializable{
    @FXML
    private TableView<Entrada> tabela_entradas;

    @FXML
    private TableColumn<Entrada, String> entrada_valor;
    
	@FXML
    private TableColumn<Entrada, String> entrada_data_hora;

    @FXML
    private TableColumn<Entrada, String> entrada_observacoes;
    
    ObservableList<Entrada> lista = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		entrada_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		entrada_data_hora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		entrada_observacoes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacoes()));

		tabela_entradas.getItems().addAll(Entrada.entradas);
	}
}
