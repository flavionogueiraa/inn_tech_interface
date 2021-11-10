package controler;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import financeiro.Entrada;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import quarto.Quarto;
import reserva.Reserva;

public class ReservaControler extends MenuControler implements Initializable {
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
	private TableColumn<Reserva, String> reserva_pago;

	@FXML
	private TextField campo_nome_hospede;

	@FXML
	private TextField campo_valor;

	@FXML
	private TextField campo_data_hora_chegada;

	@FXML
	private TextField campo_data_hora_saida;

	@FXML
	private TextField campo_quarto;

	@FXML
	private TextArea campo_observacoes;

	@FXML
	private CheckBox campo_pago;

	@FXML
	private Label label_erro;

	@FXML
	private Label label_quartos_disponiveis;

	ObservableList<Reserva> lista = FXCollections.observableArrayList();

	private void limparCampos() {
		campo_nome_hospede.clear();
		campo_valor.clear();
		campo_data_hora_chegada.clear();
		campo_data_hora_saida.clear();
		campo_quarto.clear();
		campo_observacoes.clear();
		campo_pago.setSelected(false);
	}

	private boolean validarCampos(String hospede, String quarto, String valor) {
		if (hospede.isEmpty() || quarto.isEmpty() || valor.isEmpty()) {
			return false;
		}

		try {
			Integer.parseInt(quarto);
		} catch (Exception error) {
			return false;
		}

		try {
			Double.parseDouble(valor);
		} catch (Exception error) {
			return false;
		}
		return true;
	}

	private void setaQuartosDisponiveis() {
		label_quartos_disponiveis.setText("Quartos disponíveis: " + Quarto.quartosDisponiveis());
	}

	@FXML
	void cadastrarReserva(ActionEvent event) {
		label_erro.setText("");
		Date dataChegada = null;
		try {
			dataChegada = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_chegada.getText());
		} catch (ParseException e) {
			System.err.println("Data e hora de chegada inválidas!");
		}
		if (dataChegada != null) {
			if (validarCampos(campo_nome_hospede.getText(), campo_quarto.getText(), campo_valor.getText())) {
				Quarto quarto = Quarto.getQuartoDisponivel(Integer.parseInt(campo_quarto.getText()));
				if (quarto != null) {
					String hospede = campo_nome_hospede.getText();
					Boolean pago = campo_pago.isSelected();
					Double valor = Double.parseDouble(campo_valor.getText());
					String observacoes = campo_observacoes.getText();
					if (observacoes.isEmpty()) {
						observacoes = "-";
					}

					Reserva nova_reserva = Reserva.cadastraReservaInterface(hospede, valor, dataChegada, null,
							observacoes, pago, quarto);
					tabela_reservas.getItems().add(nova_reserva);
					limparCampos();
					setaQuartosDisponiveis();
				} else {
					label_erro.setText("Informe um quarto válido");
				}
			} else {
				label_erro.setText("Campos inválidos!");
			}
		} else {
			label_erro.setText("Data e hora de chegada inválidas!");
		}
	}

	@FXML
	void editarReserva(ActionEvent event) {
		label_erro.setText("");
		try {
			Reserva reserva = tabela_reservas.getSelectionModel().getSelectedItem();
			campo_nome_hospede.setText(reserva.getHospede());
			campo_valor.setText(reserva.getValor().toString());
			campo_data_hora_chegada.setText(reserva.getDataChegadaFormatada());
			campo_data_hora_saida.setText(reserva.getDataSaidaFormatada());
			campo_observacoes.setText(reserva.getObservacoes());
			campo_quarto.setText(reserva.getQuarto().getNumero().toString());
			campo_pago.setSelected(reserva.isPago());
		} catch (Exception error) {
			label_erro.setText("Nenhuma reserva selecionada");
		}
	}

	@FXML
	void salvarEdicaoReserva(ActionEvent event) {
		Reserva reserva = tabela_reservas.getSelectionModel().getSelectedItem();
		if (reserva != null) {
			Date dataChegada = null;
			try {
				dataChegada = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_chegada.getText());
			} catch (ParseException e) {
				System.err.println("Data e hora de chegada inválidas!");
			}
			Date dataSaida = null;
			try {
				dataSaida = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_saida.getText());
			} catch (ParseException e) {
				System.err.println("Data e hora de saída inválidas!");
			}
			String nomeHospede = campo_nome_hospede.getText();
			String observacoes = campo_observacoes.getText();
			Boolean pago = campo_pago.isSelected();
			Quarto quarto = Quarto.getQuarto(Integer.parseInt(campo_quarto.getText()));
			Double valor = Double.parseDouble(campo_valor.getText());

			reserva.setHospede(nomeHospede);
			reserva.setValor(valor);
			reserva.setDataChegada(dataChegada);
			reserva.setDataSaida(dataSaida);
			reserva.setObservacoes(observacoes);
			reserva.setQuarto(quarto);
			if (!reserva.isPago()) {
				reserva.setPago(pago);
				if (reserva.isPago()) {
					new Entrada(valor, dataChegada, "Reserva do quarto " + quarto.getNumero(), true);
				}
			} else {
				label_erro.setText("A reserva já foi paga!");
			}

			tabela_reservas.refresh();
			limparCampos();
		} else {
			label_erro.setText("Nenhuma reserva selecionada");
		}
	}

	@FXML
	void excluirReserva(ActionEvent event) {
		Reserva reserva = tabela_reservas.getSelectionModel().getSelectedItem();
		tabela_reservas.getItems().remove(reserva);
		reserva.deletaReserva();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		reserva_hospede.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHospede()));
		reserva_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		reserva_data_hora_chegada
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataChegadaFormatada()));
		reserva_data_hora_saida
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataSaidaFormatada()));
		reserva_observacoes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacoes()));
		reserva_quarto.setCellValueFactory(
				data -> new SimpleStringProperty(data.getValue().getQuarto().getNumero().toString()));
		reserva_pago.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().isPagoSimNao()));

		setaQuartosDisponiveis();

		tabela_reservas.getItems().addAll(Reserva.reservas);
	}
}
