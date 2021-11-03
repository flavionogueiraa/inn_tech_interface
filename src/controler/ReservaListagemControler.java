package controler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import reserva.Reserva;

public class ReservaListagemControler extends MenuControler implements Initializable {
	@FXML
	private TableView<Reserva> tabela_reservas;

	@FXML
	private TableColumn<Reserva, String> reserva_hospede;

	@FXML
	private TableColumn<Reserva, String> reserva_valor;

	@FXML
	private TableColumn<Reserva, String> reserva_data_hora_chegada;

	@FXML
	private TableColumn<Reserva, String> reserva_data_hora_saida;

	@FXML
	private TableColumn<Reserva, String> reserva_observacoes;
	
	@FXML
	private TableColumn<Reserva, String> reserva_quarto;

	@FXML
	private TableColumn<Reserva, Boolean> reserva_pago;


	ObservableList<Reserva> lista = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		reserva_hospede.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHospede()));
		reserva_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		reserva_data_hora_chegada.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataChegadaFormatada()));
		reserva_data_hora_saida.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataSaidaFormatada()));
		reserva_observacoes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacoes()));
		reserva_quarto.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQuarto().getNumero().toString()));
		reserva_pago.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isPago()));

		tabela_reservas.getItems().addAll(Reserva.reservas);
	}
}
