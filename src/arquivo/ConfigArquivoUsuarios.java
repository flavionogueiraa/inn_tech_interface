package arquivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import usuario.Usuario;

public class ConfigArquivoUsuarios {
	public static void inicializaUsuarios(String Caminho) {
		try {
			FileReader arqUsuario = new FileReader(Caminho);
			BufferedReader lerArqUsuario = new BufferedReader(arqUsuario);
			String linha = "";
			try {
				linha = lerArqUsuario.readLine();
				while (linha != null) {
					String[] palavras = linha.split("\t");
					String nome = palavras[0];
					String cPF = palavras[1];
					String senha = palavras[2];
					boolean proprietario = Boolean.parseBoolean(palavras[3]);

					new Usuario(nome, cPF, senha, proprietario);
					linha = lerArqUsuario.readLine();
				}
				arqUsuario.close();
			} catch (IOException ex) {
				System.out.println("Erro na linha");
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: Arquivo não encontrado!");
		}
	}

	public static void atualizaUsuarios() {
		String caminho = "src/usuario/usuarios.txt";
		String texto = "";
		for (Usuario usuario : Usuario.usuarios) {
			texto += "" + usuario.getNome() + "\t" + usuario.getCPF() + "\t" + usuario.getSenha() + "\t"
					+ usuario.isProprietario() + "\n";
		}

		if (Arquivo.Write(caminho, texto)) {
			System.out.println("Arquivo salvo com sucesso!");
		} else {
			System.out.println("Erro ao salvar o arquivo!");
		}
	}

	public static void cadastraUsuario(Usuario usuario) {
		String arquivo = "src/usuario/usuarios.txt";
		String texto = "" + usuario.getNome() + "\t" + usuario.getCPF() + "\t" + usuario.getSenha() + "\t"
				+ usuario.isProprietario() + "\n";

		String textoArquivo = Arquivo.Read(arquivo);
		if (texto.isEmpty())
			textoArquivo = "";

		texto = textoArquivo += texto;

		if (Arquivo.Write(arquivo, texto)) {
			System.out.println("Arquivo salvo com sucesso!");
		} else {
			System.out.println("Erro ao salvar o arquivo!");
		}
	}
}
