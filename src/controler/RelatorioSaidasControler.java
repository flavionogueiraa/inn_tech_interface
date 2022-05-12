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

public class RelatorioSaidasControler extends MenuControler implements Initializable{
    @FXML
    private TableView<Saida> tabela_relatorio_saidas;

    @FXML
    private TableColumn<Saida, String> relatorio_saida_motivo;

    @FXML
    private TableColumn<Saida, String> relatorio_saida_valor;
    
	@FXML
    private TableColumn<Saida, String> relatorio_saida_data_hora;

    @FXML
    private TableColumn<Saida, String> relatorio_saida_observacoes;
    
    ObservableList<Saida> lista = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		relatorio_saida_motivo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotivo()));
		relatorio_saida_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		relatorio_saida_data_hora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		relatorio_saida_observacoes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacoes()));

		tabela_relatorio_saidas.getItems().addAll(Saida.getSaidas());
	}
}
