package arquivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import quarto.Quarto;

public class ConfigArquivoQuartos {
	public static void inicializaQuartos(String Caminho) {
		try {
			FileReader arq = new FileReader(Caminho);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			try {
				linha = lerArq.readLine();
				while (linha != null) {
					String[] palavras = linha.split("\t");
					int numero = Integer.parseInt(palavras[0]);
					int capacidade = Integer.parseInt(palavras[1]);
					String descricao = palavras[2];
					boolean ocupado = Boolean.parseBoolean(palavras[3]);

					new Quarto(numero, capacidade, descricao, ocupado);
					linha = lerArq.readLine();
				}
				arq.close();
			} catch (IOException ex) {
				System.out.println("Erro na linha");
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: Arquivo não encontrado!");
		}
	}

	public static void atualizaQuartos() {
		String caminho = "src/quarto/quartos.txt";
		String texto = "";
		for (Quarto quarto : Quarto.quartos) {
			texto += "" + quarto.getNumero() + "\t" + quarto.getCapacidade() + "\t" + quarto.getDescricao() + "\t"
					+ quarto.isOcupado() + "\n";
		}

		if (Arquivo.Write(caminho, texto)) {
			System.out.println("Arquivo salvo com sucesso!");
		} else {
			System.out.println("Erro ao salvar o arquivo!");
		}
	}

	public static void cadastraQuarto(Quarto quarto) {
		String arquivo = "src/quarto/quartos.txt";
		String texto = "" + quarto.getNumero() + "\t" + quarto.getCapacidade() + "\t" + quarto.getDescricao() + "\t"
				+ "false\n";

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
