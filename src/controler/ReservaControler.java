package controler;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import financeiro.Pagamento;
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
	private TableColumn<Reserva, String> reserva_valor_diaria;

	@FXML
	private TableColumn<Reserva, String> reserva_data_hora_chegada;

	@FXML
	private TableColumn<Reserva, String> reserva_data_hora_saida;

	@FXML
	private TableColumn<Reserva, String> reserva_observacoes;

	@FXML
	private TableColumn<Reserva, String> reserva_quarto;

	@FXML
	private TableColumn<Reserva, String> reserva_pagamento_confirmado;

	@FXML
	private TableColumn<Reserva, String> reserva_usuario_criacao;

	@FXML
	private TextField campo_nome_hospede;

	@FXML
	private TextField campo_valor_diaria;

	@FXML
	private TextField campo_data_hora_chegada;

	@FXML
	private TextField campo_data_hora_saida;

	@FXML
	private TextField campo_quarto;

	@FXML
	private TextArea campo_observacoes;

	@FXML
	private CheckBox campo_pagamento_confirmado;

	@FXML
	private Label label_erro;

	@FXML
	private Label label_quartos_disponiveis;

	@FXML
	private Label label_ex_data_hora1;

	@FXML
	private Label label_ex_data_hora2;

	ObservableList<Reserva> lista = FXCollections.observableArrayList();

	private void limparCampos() {
		campo_nome_hospede.clear();
		campo_valor_diaria.clear();
		campo_data_hora_chegada.clear();
		campo_data_hora_saida.clear();
		campo_quarto.clear();
		campo_observacoes.clear();
		campo_pagamento_confirmado.setSelected(false);
	}

	private boolean validarCampos(String nomeHospede, String quarto, String valorDiaria) {
		if (nomeHospede.isEmpty() || quarto.isEmpty() || valorDiaria.isEmpty()) {
			return false;
		}

		try {
			Integer.parseInt(quarto);
		} catch (Exception error) {
			return false;
		}

		try {
			Double.parseDouble(valorDiaria);
		} catch (Exception error) {
			return false;
		}
		return true;
	}

	private void setaQuartosDisponiveis() {
		label_quartos_disponiveis.setText("Quartos disponiveis: " + Quarto.quartosDisponiveis());
	}

	@FXML
	void cadastrarReserva(ActionEvent event) {
		label_erro.setText("");
		Date dataEstimadaCheckin = null;
		try {
			dataEstimadaCheckin = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_chegada.getText());
		} catch (ParseException e) {
			System.err.println("Data e hora de chegada invalidas!");
		}
		Date dataEstimadaCheckout = null;
		try {
			dataEstimadaCheckout = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_saida.getText());
		} catch (ParseException e) {
			System.err.println("Data e hora de saida invalidas!");
		}

		System.out.println(dataEstimadaCheckin);
		System.out.println(dataEstimadaCheckout);
		if (dataEstimadaCheckin != null) {
			if (validarCampos(campo_nome_hospede.getText(), campo_quarto.getText(), campo_valor_diaria.getText())) {
				Quarto quarto = Quarto.getQuartoDisponivel(Integer.parseInt(campo_quarto.getText()),
						dataEstimadaCheckin, dataEstimadaCheckout);
				if (quarto != null && !quarto.isOcupado()) {
					String nomeHospede = campo_nome_hospede.getText();
					Boolean pagamentoConfirmado = campo_pagamento_confirmado.isSelected();
					Double valorDiaria = Double.parseDouble(campo_valor_diaria.getText());
					String observacoes = campo_observacoes.getText();
					if (observacoes.isEmpty()) {
						observacoes = "-";
					}

					Reserva nova_reserva = Reserva.cadastraReservaInterface(nomeHospede, valorDiaria,
							dataEstimadaCheckin, dataEstimadaCheckout, observacoes, pagamentoConfirmado, quarto);
					tabela_reservas.getItems().add(nova_reserva);

					if (nova_reserva.isPago()) {
						Pagamento.cadastraPagamentoInterface(valorDiaria, dataEstimadaCheckin,
								"Reserva do quarto " + quarto.getNumero(), nova_reserva);
					}

					limparCampos();
					setaQuartosDisponiveis();
				} else {
					label_erro.setText("Informe um quarto valido");
				}
			} else {
				label_erro.setText("Campos invalidos!");
			}
		} else {
			label_erro.setText("Data e hora de chegada invalidas!");
		}
	}

	@FXML
	void editarReserva(ActionEvent event) {
		label_erro.setText("");
		try {
			Reserva reserva = tabela_reservas.getSelectionModel().getSelectedItem();
			campo_nome_hospede.setText(reserva.getHospede());
			campo_valor_diaria.setText(reserva.getValor().toString());
			campo_data_hora_chegada.setText(reserva.getdataEstimadaCheckinFormatada());
			campo_data_hora_saida.setText(reserva.getdataEstimadaCheckoutFormatada());
			campo_observacoes.setText(reserva.getObservacoes());
			campo_quarto.setText(reserva.getQuarto().getNumero().toString());
			campo_pagamento_confirmado.setSelected(reserva.isPago());
		} catch (Exception error) {
			label_erro.setText("Nenhuma reserva selecionada");
		}
	}

	@FXML
	void salvarEdicaoReserva(ActionEvent event) {
		Reserva reserva = tabela_reservas.getSelectionModel().getSelectedItem();
		if (reserva != null) {
			Date dataEstimadaCheckin = null;
			try {
				dataEstimadaCheckin = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_chegada.getText());
			} catch (ParseException e) {
				System.err.println("Data e hora de chegada invalidas!");
			}
			Date dataEstimadaCheckout = null;
			try {
				dataEstimadaCheckout = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_saida.getText());
			} catch (ParseException e) {
				System.err.println("Data e hora de saida invalidas!");
			}
			String nomeHospede = campo_nome_hospede.getText();
			String observacoes = campo_observacoes.getText();
			Boolean pagamentoConfirmado = campo_pagamento_confirmado.isSelected();
			Quarto quarto = Quarto.getQuarto(campo_quarto.getText());
			Double valorDiaria = Double.parseDouble(campo_valor_diaria.getText());

			Boolean mudaOcupacaoQuarto;
			if (reserva.getdataEstimadaCheckoutFormatada() == "-") {
				mudaOcupacaoQuarto = true;
			} else {
				mudaOcupacaoQuarto = false;
			}

			reserva.setHospede(nomeHospede);
			reserva.setdataEstimadaCheckin(dataEstimadaCheckin);
			reserva.setdataEstimadaCheckout(dataEstimadaCheckout);
			reserva.setObservacoes(observacoes);
			reserva.setQuarto(quarto);

			if (!reserva.isPago()) {
				reserva.setValor(valorDiaria);
				reserva.setPago(pagamentoConfirmado);
				if (reserva.isPago()) {
					Pagamento.cadastraPagamentoInterface(valorDiaria, dataEstimadaCheckin,
							"Reserva do quarto " + quarto.getNumero(), reserva);
				}
			} else {
				label_erro.setText("A reserva ja foi paga!");
			}

			if (reserva.getdataEstimadaCheckout() != null && mudaOcupacaoQuarto) {
				quarto.atualizarQuartoOcupado();
			}

			setaQuartosDisponiveis();
			reserva.atualizarReserva();
			tabela_reservas.refresh();
			limparCampos();
		} else {
			label_erro.setText("Nenhuma reserva selecionada");
		}
	}

	@FXML
	void excluirReserva(ActionEvent event) {
		Reserva reserva = tabela_reservas.getSelectionModel().getSelectedItem();

		if (reserva.getdataEstimadaCheckout() == null) {
			reserva.getQuarto().atualizarQuartoOcupado();
			setaQuartosDisponiveis();
		}

		tabela_reservas.getItems().remove(reserva);
		reserva.deletaReserva();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		reserva_hospede.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHospede()));
		reserva_valor_diaria
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		reserva_data_hora_chegada.setCellValueFactory(
				data -> new SimpleStringProperty(data.getValue().getdataEstimadaCheckinFormatada()));
		reserva_data_hora_saida.setCellValueFactory(
				data -> new SimpleStringProperty(data.getValue().getdataEstimadaCheckoutFormatada()));
		reserva_observacoes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacoes()));
		reserva_quarto.setCellValueFactory(
				data -> new SimpleStringProperty(data.getValue().getQuarto().getNumero().toString()));
		reserva_pagamento_confirmado
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().isPagoSimNao()));

		setaQuartosDisponiveis();

		tabela_reservas.getItems().addAll(Reserva.getReservas());
	}
}
