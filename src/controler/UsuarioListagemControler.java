package controler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import usuario.Usuario;

public class UsuarioListagemControler extends MenuControler implements Initializable {
	@FXML
	private TableView<Usuario> tabela_usuarios;

	@FXML
	private TableColumn<Usuario, String> nome_usuario;

	@FXML
	private TableColumn<Usuario, String> cpf_usuario;

	@FXML
	private TableColumn<Usuario, String> senha_usuario;

	@FXML
	private TableColumn<Usuario, Boolean> proprietario_usuario;

	@FXML
	private TableColumn<Usuario, String> acoes_usuario;

	ObservableList<Usuario> lista = FXCollections.observableArrayList();

	public void atualizar() {
		try {
			tabela_usuarios.refresh();
		} catch(Exception e) {
			System.out.println("Error");
		}
		System.out.println("Entrou");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nome_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
		cpf_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCPF()));
		senha_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSenha()));
		proprietario_usuario.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isProprietario()));
		acoes_usuario.setCellValueFactory(data -> new SimpleStringProperty("Teste"));

		tabela_usuarios.getItems().addAll(Usuario.usuarios);
	}
}
