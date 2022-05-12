package controler;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Set;

import financeiro.Pagamento;
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
	private TableView<Pagamento> tabela_pagamentos;

	@FXML
	private TableColumn<Pagamento, String> pagamento_valor;

	@FXML
	private TableColumn<Pagamento, String> pagamento_data_hora;

	@FXML
	private TableColumn<Pagamento, String> pagamento_reserva;

	ObservableList<Pagamento> lista_pagamentos = FXCollections.observableArrayList();

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
		pagamento_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		pagamento_data_hora
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		pagamento_reserva
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdReserva().toString()));
		tabela_pagamentos.getItems().addAll(Pagamento.getPagamentos());

		relatorio_saida_motivo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotivo()));
		relatorio_saida_valor
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		relatorio_saida_data_hora
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		tabela_relatorio_saidas.getItems().addAll(Saida.getSaidas());

		XYChart.Series<String, Double> pagamentos = new XYChart.Series<String, Double>();
		pagamentos.setName("Pagamentos");

		LinkedHashMap<String, Double> pagamentosDicionario = Pagamento.pagamentosMes();
		Set<String> chavesPagamentos = pagamentosDicionario.keySet();
		for (String chave : chavesPagamentos) {
			if (chave != null) {
				pagamentos.getData().add(new Data<String, Double>(chave, pagamentosDicionario.get(chave)));
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

		grafico_relatorio.getData().add(pagamentos);
		grafico_relatorio.getData().add(saidas);
	}
}
