package controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import quarto.Quarto;
import reserva.Reserva;

public class ReservaCadastroControler extends MenuControler {
	@FXML
	private TextField campo_data_chegada;

	@FXML
	private TextField campo_hospede;

	@FXML
	private TextArea campo_observacoes;

	@FXML
	private CheckBox campo_pago;

	@FXML
	private TextField campo_quarto;

	@FXML
	private TextField campo_valor;

	@FXML
	void cadastrarReserva(ActionEvent event) {
		Date dataChegada = null;
		try {
			dataChegada = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(campo_data_chegada.getText());
		} catch (ParseException e) {
			System.err.println("Saída inválida");
		}
		String hospede = campo_hospede.getText();
		String observacoes = campo_observacoes.getText();
		Boolean pago = campo_pago.isSelected();
		Quarto quarto = Quarto.getQuarto(Integer.parseInt(campo_quarto.getText()));
		Double valor = Double.parseDouble(campo_valor.getText());

		Reserva.cadastraReservaInterface(hospede, valor, dataChegada, null, observacoes, pago, quarto);

		campo_data_chegada.clear();
		campo_hospede.clear();
		campo_observacoes.clear();
		campo_pago.setSelected(false);
		campo_quarto.clear();
		campo_valor.clear();
	}
}
