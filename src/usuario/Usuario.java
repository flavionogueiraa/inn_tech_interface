package usuario;

import java.util.ArrayList;

import arquivo.ConfigArquivoUsuarios;

public class Usuario {
	private String nome;
	private String CPF;
	private String Senha;
	private boolean proprietario;

	public static ArrayList<Usuario> usuarios = new ArrayList<>();
	public static Usuario usuarioLogado;

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public boolean isProprietario() {
		return proprietario;
	}

	public void setProprietario(boolean proprietario) {
		this.proprietario = proprietario;
	}

	public String isProprietarioSimNao() {
		if (isProprietario()) {
			return "Sim";
		} else {
			return "Não";
		}
	}

	public Usuario(String nome, String CPF, String senha, boolean proprietario) {
		super();
		this.nome = nome;
		this.CPF = CPF;
		this.Senha = senha;
		this.proprietario = proprietario;
		usuarios.add(this);
	}

	public static Usuario cadastraUsuarioInterface(String nome, String CPF, String senha, boolean proprietario) {
		Usuario novo_usuario = new Usuario(nome, CPF, senha, proprietario);
		ConfigArquivoUsuarios.atualizaUsuarios();
		return novo_usuario;
	}

	public void deletaUsuario() {
		Usuario.usuarios.remove(this);
		ConfigArquivoUsuarios.atualizaUsuarios();
	}

	public static Usuario login(String CPF, String senha) {
		Usuario.usuarioLogado = getUsuario(CPF);
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.Senha.equals(senha)) {
			return Usuario.usuarioLogado;
		}

		return null;
	}

	public static Usuario getUsuario(String CPF) {
		for (Usuario usuario : usuarios) {
			if (usuario.CPF.equals(CPF)) {
				return usuario;
			}
		}

		return null;
	}
}
