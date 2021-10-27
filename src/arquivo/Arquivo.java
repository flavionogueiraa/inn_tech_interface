package arquivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Arquivo {
	public static String Read(String Caminho) {
		String conteudo = "";
		try {
			FileReader arq = new FileReader(Caminho);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			try {
				linha = lerArq.readLine();
				while (linha != null) {
					conteudo += linha + "\n";
					linha = lerArq.readLine();
				}
				arq.close();
				return conteudo;
			} catch (IOException ex) {
				return "";
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: Arquivo não encontrado!");
			return "";
		}
	}

	public static boolean Write(String Caminho, String Texto) {
		try {
			FileWriter arq = new FileWriter(Caminho);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.append(Texto);
			gravarArq.close();
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static void inicializaVariaveis() {
		ConfigArquivoQuartos.inicializaQuartos("src/quarto/quartos.txt");
		ConfigArquivoUsuarios.inicializaUsuarios("src/usuario/usuarios.txt");
		ConfigArquivoReservas.inicializaReservas("src/reserva/reservas.txt");
		ConfigArquivoSaidas.inicializaSaidas("src/financeiro/saidas.txt");
		ConfigArquivoEntradas.inicializaEntradas("src/financeiro/entradas.txt");
	}

}
