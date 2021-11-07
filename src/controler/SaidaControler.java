package controler;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

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

public class SaidaControler extends MenuControler implements Initializable{
	@FXML
    private TableView<Saida> tabela_saidas;

    @FXML
    private TableColumn<Saida, String> saida_motivo;

    @FXML
    private TableColumn<Saida, String> saida_valor;
    
	@FXML
    private TableColumn<Saida, String> saida_data_hora;

    @FXML
    private TableColumn<Saida, String> saida_observacoes;

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
    
//    private boolean validarCampos(String motivo, String valor, String dataCriacao) {
//		if (motivo.isEmpty() || valor.isEmpty() || dataCriacao.isEmpty()) {
//			return false;
//		}
//		return true;
//	}
    
    @FXML
    void cadastrarSaida(ActionEvent event) {
    	label_erro.setText("");
    	Date dataCriacao = null;
    	try {
			dataCriacao = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_criacao.getText());
		} catch (ParseException e) {
			
			System.err.println("Data e hora inválidas!");
		}
    	
    	Double valor = Double.parseDouble(campo_valor.getText());
    	String motivo = campo_motivo.getText();
    	String observacoes = campo_observacoes.getText();
    	Saida.cadastraSaidaInterface(valor, dataCriacao, motivo, observacoes);
    	
    	Saida nova_saida = Saida.cadastraSaidaInterface(valor, dataCriacao, motivo, observacoes);
		tabela_saidas.getItems().add(nova_saida);
		limparCampos();
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
			label_erro.setText("Nenhuma reserva selecionada");
		}
	}
    
    @FXML
	void salvarEdicaoSaida(ActionEvent event) {
		Saida saida = tabela_saidas.getSelectionModel().getSelectedItem();
		Date dataCriacao = null;
		try {
			dataCriacao = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_hora_criacao.getText());
		} catch (ParseException e) {
			System.err.println("Data e hora de chegada inválidas!");
		}
		Double valor = Double.parseDouble(campo_valor.getText());
    	String motivo = campo_motivo.getText();
    	String observacoes = campo_observacoes.getText();

		saida.setMotivo(motivo);
		saida.setValor(valor);
		saida.setDataCriacao(dataCriacao);
		saida.setObservacoes(observacoes);

		tabela_saidas.refresh();
		limparCampos();
	}
    
    @FXML
	void excluirSaida(ActionEvent event) {
    	Saida saida = tabela_saidas.getSelectionModel().getSelectedItem();
		tabela_saidas.getItems().remove(saida);
		saida.deletaSaida();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saida_motivo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMotivo()));
		saida_valor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValor().toString()));
		saida_data_hora.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDataCriacaoFormatada()));
		saida_observacoes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getObservacoes()));

		tabela_saidas.getItems().addAll(Saida.saidas);
		
	}
    
}
