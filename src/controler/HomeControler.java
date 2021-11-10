package controler;

import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Set;

import financeiro.Entrada;
import financeiro.Saida;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.text.Text;
//import javafx.scene.chart.XYChart.Series;

public class HomeControler extends MenuControler implements Initializable {
	@FXML
    private Text valor_caixa;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		valor_caixa.setText("R$ " + (Entrada.getTotalEntradas() - Saida.getTotalSaidas()));
		
		
		XYChart.Series<String, Double> entradas = new XYChart.Series<String, Double>();
		entradas.setName("Entradas");
		
		Hashtable<String, Double> entradasDicionario = Entrada.entradasMes();
		Set<String> chaves = entradasDicionario.keySet();
		for(String chave : chaves) {
			if(chave != null) {
				entradas.getData().add(new Data<String, Double>(chave, entradasDicionario.get(chave)));
			}

		}
		
//		XYChart.Series<String, Double> saidas = new Series<String, Double>();
//		saidas.setName("Saídas");
//		saidas.getData().add(new Data<String, Double>("05/21", 200.0));
//		saidas.getData().add(new Data<String, Double>("06/21", 350.0));
//		saidas.getData().add(new Data<String, Double>("07/21", 360.0));
//		saidas.getData().add(new Data<String, Double>("07/21", 400.0));
//		saidas.getData().add(new Data<String, Double>("08/21", 690.0));
//		saidas.getData().add(new Data<String, Double>("09/21", 600.0));
//		saidas.getData().add(new Data<String, Double>("10/21", 580.0));
//		
//		grafico_entradas_saidas.getData().add(entradas);
//		grafico_entradas_saidas.getData().add(saidas);
	}
}
