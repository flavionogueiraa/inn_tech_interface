package controler;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import financeiro.Pagamento;
import financeiro.Saida;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import usuario.Usuario;

public class SaidaControler extends MenuControler implements Initializable {
	@FXML
	private TableView<Saida> tabela_saidas;

	@FXML
	private TableColumn<Saida, String> saida_id;

	@FXML
	private TableColumn<Saida, String> saida_motivo;

	@FXML
	private TableColumn<Saida, String> saida_valor;

	@FXML
	private TableColumn<Saida, String> saida_data_hora;

	@FXML
	private TableColumn<Saida, String> saida_observacoes;

	@FXML
	private TableColumn<Saida, String> saida_usuario_criacao;

	@FXML
	private TextField campo_motivo;

	@FXML
	private TextField campo_valor;

	@FXML
	private TextField campo_data_hora_criacao;

	@FXML
	private TextArea campo_observacoes;

	@FXML
	private Label label_erro;

	ObservableList<Saida> lista = FXCollections.observableArrayList();

	private void limparCampos() {
		campo_motivo.clear();
		campo_valor.clear();
		campo_data_hora_criacao.clear();
		campo_observacoes.clear();
	}

	private boolean validarCampos(String motivo, String valor) {
		if (motivo.isEmpty() || (valor.isEmpty())) {
			label_erro.setText("Preencha todos os campos que contem um '*'");
			return false;
		}

		return true;
	}

	private boolean validarValorSaida(String valor) {
		Double valorCaixaAtual = Pagamento.getTotalPagamentos() - Saida.getTotalSaidas();
		Double valorSaida = Double.parseDouble(valor);

		Boolean valorInvalido = valorSaida <= 0;
		if (valorInvalido) {
			label_erro.setText("O valor da saida deve ser maior do que zero");
			return false;
		}

		Boolean caixaNegativo = valorCaixaAtual - valorSaida < 0;
		if (caixaNegativo) {
			label_erro.setText("Valor de saida maior que o valor do caixa!");
			return false;
		}

		return true;
	}

	@FXML
	void cadastrarSaida(ActionEvent event) {
		label_erro.setText("");
		Date dataCriacao = null;
		try {
			dataCriacao = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_criacao.getText());
		} catch (ParseException e) {
			label_erro.setText("Data e hora invalidas!");
			System.err.println("Data e hora invalidas!");
		}

		if (dataCriacao != null) {
			String motivo = campo_motivo.getText();
			String valor = campo_valor.getText();
			String observacoes = campo_observacoes.getText();
			if (observacoes.isEmpty()) {
				observacoes = "-";
			}

			if (!validarCampos(motivo, valor)) {
				return;
			}

			if (!validarValorSaida(valor)) {
				return;
			}

			Saida nova_saida = Saida.cadastraSaidaInterface(Double.parseDouble(valor), dataCriacao, motivo,
					observacoes, Usuario.usuarioLogado);
			tabela_saidas.getItems().add(nova_saida);
			limparCampos();
		}
	}

	@FXML
	void editarSaida(ActionEvent event) {
		label_erro.setText("");
		try {
			Saida saida = tabela_saidas.getSelectionModel().getSelectedItem();
			campo_motivo.setText(saida.getMotivo());
			campo_valor.setText(saida.getValor().toString());
			campo_data_hora_criacao.setText(saida.getDataCriacaoFormatada());
			campo_observacoes.setText(saida.getObservacoes());
		} catch (Exception error) {
			label_erro.setText("Nenhuma saida selecionada");
		}
	}

	@FXML
	void salvarEdicaoSaida(ActionEvent event) {
		Saida saida = tabela_saidas.getSelectionModel().getSelectedItem();
		Date dataCriacao = null;
		try {
			dataCriacao = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_criacao.getText());
		} catch (ParseException e) {
			System.err.println("Data e hora de chegada invalidas!");
		}
		Double valor = Double.parseDouble(campo_valor.getText());
		String motivo = campo_motivo.getText();
		String observacoes = campo_observacoes.getText();

		saida.setMotivo(motivo);
		saida.setValor(valor);
		saida.setDataCriacao(dataCriacao);
		saida.setObservacoes(observacoes);
		saida.atualizarSaida();

		tabela_saidas.refresh();
		limparCampos();

	}

	@FXML
	void excluirSaida(ActionEvent event) {
		label_erro.setText("");
		try {
			Saida saida = tabela_saidas.getSelectionModel().getSelectedItem();
			tabela_saidas.getItems().remove(saida);
			saida.deletaSaida();
		} catch (Exception error) {
			label_erro.setText("Nenhuma saida selecionada");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saida_id.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId().toString()));
		saida_motivo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotivo()));
		saida_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		saida_data_hora
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		saida_observacoes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacoes()));
		saida_usuario_criacao.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomeUsuario()));

		tabela_saidas.getItems().addAll(Saida.getSaidas());
	}

}
