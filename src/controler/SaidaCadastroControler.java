package controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import financeiro.Saida;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SaidaCadastroControler extends MenuControler {

    @FXML
    private TextArea campo_MotivoSaida;

    @FXML
    private TextField campo_dataSaida;

    @FXML
    private TextArea campo_observacaoSaida;

    @FXML
    private TextField campo_valorSaida;

    @FXML
    void cadastrarSaida(ActionEvent event) {
    	Date dataCriacao = null;
    	try {
			dataCriacao = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_dataSaida.getText());
		} catch (ParseException e) {
			
			System.err.println("Erro!");
		}
    	
    	Double valor = Double.parseDouble(campo_valorSaida.getText());
    	String motivo = campo_MotivoSaida.getText();
    	String observacao = campo_observacaoSaida.getText();
    	Saida.cadastraSaidaInterface(valor, dataCriacao, motivo, observacao);
    	
    	campo_dataSaida.clear();
    	campo_valorSaida.clear();
    	campo_MotivoSaida.clear();
    	campo_observacaoSaida.clear();
    	
    }
    
    
}
