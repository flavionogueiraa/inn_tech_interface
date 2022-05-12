package controler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import usuario.Usuario;
import validacao.ValidacaoCPFInterface;

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
	private TableColumn<Usuario, String> proprietario_usuario;

	@FXML
	private Label label_erro;

	ObservableList<Usuario> lista = FXCollections.observableArrayList();

	private void limparCampos() {
		campo_nome.clear();
		campo_cpf.clear();
		campo_senha.clear();
		campo_proprietario.setSelected(false);
	}

	@FXML
	void cadastrarUsuario(ActionEvent event) {
		label_erro.setText("");
		String CPF = ValidacaoCPFInterface.validacaoNormal(campo_cpf.getText());
		if (CPF != null) {
			if (Usuario.getUsuario(CPF)==null) {
				String nome = campo_nome.getText();
				String senha = campo_senha.getText();
				Boolean proprietario = campo_proprietario.isSelected();

				if (!nome.isEmpty() && !senha.isEmpty()) {
					Usuario novo_usuario = Usuario.cadastraUsuarioInterface(nome, CPF, senha, proprietario);
					tabela_usuarios.getItems().add(novo_usuario);
					limparCampos();
				} else {
					label_erro.setText("Preencha todos os campos");
				}
			} else {
				label_erro.setText("CPF ja cadastrado");
			}

		} else {
			label_erro.setText("CPF invalido");
		}
	}

	@FXML
	void editarUsuario(ActionEvent event) {
		label_erro.setText("");
		try {
			Usuario usuarioEscolhido = tabela_usuarios.getSelectionModel().getSelectedItem();
			campo_nome.setText(usuarioEscolhido.getNome());
			campo_cpf.setText(usuarioEscolhido.getCPF());
			campo_senha.setText(usuarioEscolhido.getSenha());
			campo_proprietario.setSelected((usuarioEscolhido.isProprietario()));
		} catch (Exception error) {
			label_erro.setText("Nenhum usuario selecionado");
		}
	}

	@FXML
	void salvarEdicaoUsuario(ActionEvent event) {
		label_erro.setText("");
		Usuario usuario = tabela_usuarios.getSelectionModel().getSelectedItem();
		String CPF = ValidacaoCPFInterface.validacaoNormal(campo_cpf.getText());
		if (CPF != null) {
			Usuario usuarioCadastrado = Usuario.getUsuario(CPF);
			if (usuarioCadastrado == null || usuarioCadastrado.getId() == usuario.getId()) {
				String nome = campo_nome.getText();
				String senha = campo_senha.getText();
				Boolean proprietario = campo_proprietario.isSelected();

				if (!nome.isEmpty() && !senha.isEmpty()) {
					usuario.setNome(nome);
					usuario.setCPF(CPF);
					usuario.setSenha(senha);
					usuario.setProprietario(proprietario);
					usuario.atualizarUsuario();
					
					tabela_usuarios.refresh();
					limparCampos();
				} else {
					label_erro.setText("Preencha todos os campos");
				}
			} else {
				label_erro.setText("CPF ja esta em uso");
			}

		} else {
			label_erro.setText("CPF invalido");
		}
	}

	@FXML
	void excluirUsuario(ActionEvent event) {
		label_erro.setText("");
		try {
			Usuario usuario = tabela_usuarios.getSelectionModel().getSelectedItem();
			tabela_usuarios.getItems().remove(usuario);
			usuario.deletaUsuario();
		} catch (Exception error) {
			label_erro.setText("Nenhum usuario selecionado");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nome_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
		cpf_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCPF()));
		senha_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSenha()));
		proprietario_usuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().isProprietarioSimNao()));

		tabela_usuarios.getItems().addAll(Usuario.usuarios);
	}
}
