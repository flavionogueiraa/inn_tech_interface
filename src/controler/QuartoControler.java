package controler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import quarto.Quarto;
import validacao.ValidacaoQuartoInterface;

public class QuartoControler extends MenuControler implements Initializable {
	@FXML
	private TextField campo_capacidade;

	@FXML
	private TextArea campo_descricao;

	@FXML
	private TextField campo_numero;

	@FXML
	private Label label_erro;

	@FXML
	private TableView<Quarto> tabela_quartos;

	@FXML
	private TableColumn<Quarto, String> quarto_numero;

	@FXML
	private TableColumn<Quarto, String> quarto_capacidade;

	@FXML
	private TableColumn<Quarto, String> quarto_descricao;

	@FXML
	private TableColumn<Quarto, String> quarto_ocupado;

	@FXML
	private TableColumn<Quarto, String> usuario_criacao;

	private void limparCampos() {
		campo_numero.clear();
		campo_capacidade.clear();
		campo_descricao.clear();
	}

	private boolean validarCampos(String numero, String capacidade, String descricao) {
		if (numero.isEmpty() || capacidade.isEmpty() || descricao.isEmpty()) {
			return false;
		}

		try {
			Integer.parseInt(numero);
		} catch (Exception error) {
			return false;
		}

		try {
			Integer.parseInt(capacidade);
		} catch (Exception error) {
			return false;
		}

		return true;
	}

	@FXML
	void cadastrarQuarto(ActionEvent event) {
		label_erro.setText("");
		String numero = campo_numero.getText();
		String capacidade = campo_capacidade.getText();
		String descricao = campo_descricao.getText();

		if (validarCampos(numero, capacidade, descricao)) {
			if (Quarto.getQuarto(numero) == null) {
				Quarto novo_quarto = Quarto.cadastraQuartoInterface(Integer.parseInt(numero),
						Integer.parseInt(capacidade), descricao);
				tabela_quartos.getItems().add(novo_quarto);
				limparCampos();
			} else {
				label_erro.setText("Quarto ja existente!");
			}
		} else {
			label_erro.setText("Campos invalidos!");
		}
	}

	@FXML
	void editarQuarto(ActionEvent event) {
		label_erro.setText("");
		try {
			Quarto quarto = tabela_quartos.getSelectionModel().getSelectedItem();
			campo_numero.setText(quarto.getNumero().toString());
			campo_capacidade.setText(quarto.getCapacidade().toString());
			campo_descricao.setText(quarto.getDescricao());
		} catch (Exception error) {
			label_erro.setText("Nenhum quarto selecionado!");
		}
	}

	@FXML
	void salvarEdicaoQuarto(ActionEvent event) {
		label_erro.setText("");
		Quarto quarto = tabela_quartos.getSelectionModel().getSelectedItem();
		String numero = campo_numero.getText();
		String capacidade = campo_capacidade.getText();
		String descricao = campo_descricao.getText();

		if (validarCampos(numero, capacidade, descricao)) {
			if (ValidacaoQuartoInterface.validacaoEdicao(quarto, numero)) {
				quarto.setNumero(Integer.parseInt(numero));
				quarto.setCapacidade(Integer.parseInt(capacidade));
				quarto.setDescricao(descricao);
				quarto.atualizarQuarto();

				tabela_quartos.refresh();
				limparCampos();
			} else {
				label_erro.setText("Quarto ja existente!");
			}
		} else {
			label_erro.setText("Preencha todos os campos!");
		}
	}

	@FXML
	void excluirQuarto(ActionEvent event) {
		label_erro.setText("");
		try {
			Quarto quarto = tabela_quartos.getSelectionModel().getSelectedItem();
			tabela_quartos.getItems().remove(quarto);
			quarto.deletaQuarto();
		} catch (Exception error) {
			label_erro.setText("Nenhum quarto selecionado!");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		quarto_numero.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumero().toString()));
		quarto_capacidade
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCapacidade().toString()));
		quarto_descricao.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescricao()));
		quarto_ocupado.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().isOcupadoSimNao()));
		usuario_criacao.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomeUsuario()));

		tabela_quartos.getItems().addAll(Quarto.getQuartos());
	}
}
