package arquivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import financeiro.Entrada;

public class ConfigArquivoEntradas {
	public static void cadastraEntrada(Entrada entrada) {
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String arquivo = "src/financeiro/entradas.txt";
		String texto = "" + entrada.getValor() + "\t" + formatar.format(entrada.getDataCriacao()) + "\t"
				+ entrada.getObservacoes() + "\n";

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

	public static void atualizaEntradas() {
		String caminho = "src/financeiro/entradas.txt";
		String texto = "";
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		for (Entrada entrada : Entrada.entradas) {

			texto += "" + entrada.getValor() + "\t" + formatar.format(entrada.getDataCriacao()) + "\t"
					+ entrada.getObservacoes() + "\n";

		}

		if (Arquivo.Write(caminho, texto)) {
			System.out.println("Arquivo salvo com sucesso!");
		} else {
			System.out.println("Erro ao salvar o arquivo!");
		}
	}

	public static void inicializaEntradas(String Caminho) {
		try {
			FileReader arq = new FileReader(Caminho);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			try {
				linha = lerArq.readLine();
				while (linha != null) {
					String[] palavras = linha.split("\t");
					double valor = Double.parseDouble(palavras[0]);
					String dataCriacao = palavras[1];
					Date data = null;
					try {
						data = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataCriacao);
					} catch (ParseException ex) {
						Logger.getLogger(ConfigArquivoEntradas.class.getName()).log(Level.SEVERE, null, ex);
					}

					String observacoes = palavras[2];

					new Entrada(valor, data, observacoes, false);

					linha = lerArq.readLine();
				}
				arq.close();
			} catch (IOException ex) {
				System.out.println("Erro na linha");
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: Arquivo nao encontrado!");
		}
	}

}
