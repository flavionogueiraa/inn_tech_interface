package usuario;

import java.util.ArrayList;
import java.util.Scanner;

import arquivo.ConfigArquivoUsuarios;
import validacao.ValidacaoCPF;
import validacao.ValidacaoInt;

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

	public Usuario(Scanner sc) {
		super();
		System.out.println("Nome:");
		setNome(sc.next());

		setCPF(ValidacaoCPF.validacaoCadastro(sc, "CPF:", true));

		System.out.println("Senha:");
		setSenha(sc.next());

		System.out.println("É proprietário?");
		System.out.println("[1] Sim");
		System.out.println("[0] Não");

		int proprietario = sc.nextInt();
		this.setProprietario(proprietario == 1);
		usuarios.add(this);
		ConfigArquivoUsuarios.cadastraUsuario(this);
	}

	public Usuario(String nome, String CPF, String senha, boolean proprietario) {
		super();
		this.nome = nome;
		this.CPF = CPF;
		this.Senha = senha;
		this.proprietario = proprietario;
		usuarios.add(this);
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

	public static void listaUsuarios() {
		int i = 1;
		for (Usuario usuario : usuarios) {
			System.out.println("ID: " + i);
			System.out.println("Nome: " + usuario.nome);
			System.out.println("CPF: " + usuario.CPF);
			System.out.println("Proprietário: " + usuario.proprietario);
			i++;
		}
	}

	public static void editaUsuario(Scanner sc) {
		Usuario.listaUsuarios();
		int opcaoSaida = ValidacaoInt.validacao(sc, "Informe um ID para editar:", true);

		Usuario usuario;
		try {
			usuario = Usuario.usuarios.get(opcaoSaida - 1);
		} catch (Exception e) {
			usuario = null;
		}

		if (usuario != null) {
			System.out.println("Nome atual:");
			System.out.println(usuario.nome);
			System.out.println("Novo nome:");
			usuario.setNome(sc.next());

			System.out.println("CPF atual:");
			System.out.println(usuario.CPF);
			usuario.setCPF(ValidacaoCPF.validacaoEdicao(sc, "Novo CPF:", true, usuario));

			System.out.println("Senha atual:");
			System.out.println(usuario.Senha);
			System.out.println("Nova senha:");
			usuario.setSenha(sc.next());

			System.out.println("Status atual de propriedade: ");
			if (usuario.proprietario) {
				System.out.println("É um proprietário");
				System.out.println("Continua sendo?");
			} else {
				System.out.println("Não é um proprietário");
				System.out.println("Passa a ser?");
			}
			System.out.println();
			System.out.println("[1] Sim");
			System.out.println("[0] Não");

			int proprietario = sc.nextInt();
			usuario.setProprietario(proprietario == 1);

			ConfigArquivoUsuarios.atualizaUsuarios();
		} else {
			System.out.println("ID inválido");
		}
	}

	public static void excluiUsuario(Scanner sc) {
		Usuario.listaUsuarios();
		System.out.println("");

		System.out.println("Digite o CPF do usuario que deseja deletar:");
		String getCPFdeletar = sc.next();
		Usuario usuario = getUsuario(getCPFdeletar);

		if (usuario != null) {
			Usuario.usuarios.remove(usuario);
			usuario = null;
			ConfigArquivoUsuarios.atualizaUsuarios();
		}
	}
}
