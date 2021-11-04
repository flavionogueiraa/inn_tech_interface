package controler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import usuario.Usuario;

public class UsuarioControler extends MenuControler implements Initializable {
	@FXML
    private TextField campo_cpf;

    @FXML
    private TextField campo_nome;

    @FXML
    private CheckBox campo_proprietario;

    @FXML
    private TextField campo_senha;

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
	
	ObservableList<Usuario> lista = FXCollections.observableArrayList();

    @FXML
    void cadastrarUsuario(ActionEvent event) {
    	String nome = campo_nome.getText();
		String CPF = campo_cpf.getText();
		String senha = campo_senha.getText();
		Boolean proprietario = campo_proprietario.isSelected();

		Usuario novo_usuario = Usuario.cadastraUsuarioInterface(nome, CPF, senha, proprietario);
		tabela_usuarios.getItems().add(novo_usuario);
    }

    @FXML
    void editarUsuario(ActionEvent event) {
    	System.out.println(tabela_usuarios.getSelectionModel().getSelectedItem().getNome());
    }

    @FXML
    void excluirUsuario(ActionEvent event) {
    	Usuario usuario = tabela_usuarios.getSelectionModel().getSelectedItem();
    	tabela_usuarios.getItems().remove(usuario);
    	usuario.deletaUsuario();
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nome_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
		cpf_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCPF()));
		senha_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSenha()));
		proprietario_usuario.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isProprietario()));

		tabela_usuarios.getItems().addAll(Usuario.usuarios);
	}
}
