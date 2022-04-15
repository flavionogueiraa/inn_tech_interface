package controler;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Set;

import financeiro.Entrada;
import financeiro.Saida;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RelatorioControler extends MenuControler implements Initializable {
	@FXML
	private TableView<Entrada> tabela_entradas;

	@FXML
	private TableColumn<Entrada, String> entrada_valor;

	@FXML
	private TableColumn<Entrada, String> entrada_data_hora;

	@FXML
	private TableColumn<Entrada, String> entrada_observacoes;

	ObservableList<Entrada> lista_entradas = FXCollections.observableArrayList();

	@FXML
	private TableView<Saida> tabela_relatorio_saidas;

	@FXML
	private TableColumn<Saida, String> relatorio_saida_motivo;

	@FXML
	private TableColumn<Saida, String> relatorio_saida_valor;

	@FXML
	private TableColumn<Saida, String> relatorio_saida_data_hora;

	@FXML
	private BarChart<String, Double> grafico_relatorio;

	ObservableList<Saida> lista_saidas = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		entrada_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		entrada_data_hora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		entrada_observacoes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacoes()));
		tabela_entradas.getItems().addAll(Entrada.entradas);

		relatorio_saida_motivo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotivo()));
		relatorio_saida_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		relatorio_saida_data_hora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		tabela_relatorio_saidas.getItems().addAll(Saida.saidas);

		XYChart.Series<String, Double> entradas = new XYChart.Series<String, Double>();
		entradas.setName("Entradas");

		LinkedHashMap<String, Double> entradasDicionario = Entrada.entradasMes();
		Set<String> chavesEntradas = entradasDicionario.keySet();
		for (String chave : chavesEntradas) {
			if (chave != null) {
				entradas.getData().add(new Data<String, Double>(chave, entradasDicionario.get(chave)));
			}
		}
		XYChart.Series<String, Double> saidas = new Series<String, Double>();
		saidas.setName("Saidas");

		LinkedHashMap<String, Double> saidasDicionario = Saida.saidasMes();
		Set<String> chavesSaidas = saidasDicionario.keySet();
		for (String chave : chavesSaidas) {
			if (chave != null) {
				saidas.getData().add(new Data<String, Double>(chave, saidasDicionario.get(chave)));
			}
		}

		grafico_relatorio.getData().add(entradas);
		grafico_relatorio.getData().add(saidas);
	}
}
