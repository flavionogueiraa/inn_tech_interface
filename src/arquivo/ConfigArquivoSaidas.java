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

import financeiro.Saida;

public class ConfigArquivoSaidas {
	public static void inicializaSaidas(String Caminho) {
		try {
			FileReader arqSaida = new FileReader(Caminho);
			BufferedReader lerArqSaida = new BufferedReader(arqSaida);
			String linha = "";
			try {
				linha = lerArqSaida.readLine();
				while (linha != null) {
					String[] palavras = linha.split("\t");
					float valor = Float.parseFloat(palavras[0]);

					String dataString = palavras[1];
					Date data = null;
					try {
						data = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataString);
					} catch (ParseException ex) {
						Logger.getLogger(ConfigArquivoSaidas.class.getName()).log(Level.SEVERE, null, ex);
					}
					String motivo = palavras[2];
					String observacoes = palavras[3];

					new Saida(valor, data, motivo, observacoes);
					linha = lerArqSaida.readLine();
				}
				arqSaida.close();
			} catch (IOException ex) {
				System.out.println("Erro na linha");
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: Arquivo não encontrado!");
		}
	}

	public static void atualizaSaida() {
		String caminho = "src/financeiro/saidas.txt";
		String texto = "";
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		for (Saida saida : Saida.saidas) {
			texto += "" + saida.getValor() + "\t" + formatar.format(saida.getDataCriacao()) + "\t";
			texto += saida.getMotivo() + "\t" + saida.getObservacoes() + "\n";
		}

		if (Arquivo.Write(caminho, texto)) {
			System.out.println("Arquivo salvo com sucesso!");
		} else {
			System.out.println("Erro ao salvar o arquivo!");
		}
	}

	public static void cadastraSaida(Saida saida) {
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String arquivo = "src/financeiro/saidas.txt";
		String texto = "" + saida.getValor() + "\t" + formatar.format(saida.getDataCriacao()) + "\t" + saida.getMotivo()
				+ "\t" + saida.getObservacoes() + "\n";

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
