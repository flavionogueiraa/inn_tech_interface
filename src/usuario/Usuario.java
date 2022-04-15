package usuario;

import java.util.ArrayList;

import arquivo.ConfigArquivoUsuarios;

public class Usuario {
	private int id;
	private String nome;
	private String CPF;
	private String senha;
	private boolean proprietario;

	public static ArrayList<Usuario> usuarios = new ArrayList<>();
	public static Usuario usuarioLogado;
	private static int idCont = 0;
	
	public int getId() {
		return id;
	}

	private void setId() {
		Usuario.idCont++;
		this.id = idCont;
	}

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
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
			return "Nao";
		}
	}

	public Usuario(String nome, String CPF, String senha, boolean proprietario) {
		super();
		this.nome = nome;
		this.CPF = CPF;
		this.senha = senha;
		this.proprietario = proprietario;
		this.setId();
		
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
		if (Usuario.usuarioLogado != null && Usuario.usuarioLogado.senha.equals(senha)) {
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
