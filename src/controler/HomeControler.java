package controler;

import java.net.URL;
import java.util.ResourceBundle;

import financeiro.Pagamento;
import financeiro.Saida;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import usuario.Usuario;

public class HomeControler extends MenuControler implements Initializable {
	@FXML
	private Text valor_caixa;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Saida.createAllSaidas();
		Usuario.createAllUsuarios();
		
		Double valorCaixa = Pagamento.getTotalPagamentos() - Saida.getTotalSaidas();
		valor_caixa.setText("R$ " + (valorCaixa) + "0");
	}
}
