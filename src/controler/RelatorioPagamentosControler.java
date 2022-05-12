package controler;

import java.net.URL;
import java.util.ResourceBundle;

import financeiro.Pagamento;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RelatorioPagamentosControler extends MenuControler implements Initializable {
	@FXML
	private TableView<Pagamento> tabela_pagamentos;

	@FXML
	private TableColumn<Pagamento, String> pagamento_valor;

	@FXML
	private TableColumn<Pagamento, String> pagamento_data_hora;

	@FXML
	private TableColumn<Pagamento, String> pagamento_reserva;

	ObservableList<Pagamento> lista = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pagamento_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		pagamento_data_hora
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		pagamento_reserva
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdReserva().toString()));

		tabela_pagamentos.getItems().addAll(Pagamento.getPagamentos());
	}
}
