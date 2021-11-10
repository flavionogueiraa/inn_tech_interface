package controler;

import java.net.URL;
import java.util.ResourceBundle;

import financeiro.Entrada;
import financeiro.Saida;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class HomeControler extends MenuControler implements Initializable {
	@FXML
	private Text valor_caixa;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Double valorCaixa = Entrada.getTotalEntradas() - Saida.getTotalSaidas();
		valor_caixa.setText("R$ " + (valorCaixa) + "0");
	}
}
