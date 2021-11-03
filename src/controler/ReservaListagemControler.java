package controler;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import reserva.Reserva;

public class ReservaListagemControler extends MenuControler {
    @FXML
    private TableView<Reserva> tabela_reservas;
    
	@FXML
    private TableColumn<Reserva, Date> reserva_data_hora_chegada;

    @FXML
    private TableColumn<Reserva, String> reserva_data_hora_saida;

    @FXML
    private TableColumn<Reserva, String> reserva_hospede;

    @FXML
    private TableColumn<Reserva, String> reserva_observacoes;

    @FXML
    private TableColumn<Reserva, Boolean> reserva_pago;

    @FXML
    private TableColumn<Reserva, Integer> reserva_quarto;

    @FXML
    private TableColumn<Reserva, Double> reserva_valor;
    
}
