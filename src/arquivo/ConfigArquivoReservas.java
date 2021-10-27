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

import quarto.Quarto;
import reserva.Reserva;

public class ConfigArquivoReservas {
	public static void inicializaReservas(String Caminho) {
		try {
			FileReader arq = new FileReader(Caminho);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			try {
				linha = lerArq.readLine();
				while (linha != null) {
					String[] palavras = linha.split("\t");
					String hospede = palavras[0];
					Double valor = Double.parseDouble(palavras[1]);

					String dataString = palavras[2];
					Date data = null;
					try {
						data = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dataString);
					} catch (ParseException ex) {
						Logger.getLogger(ConfigArquivoReservas.class.getName()).log(Level.SEVERE, null, ex);
					}

					Date horarioSaida = null;
					if (!palavras[3].equals("null")) {
						String horarioSaidaString = palavras[3];
						try {
							horarioSaida = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(horarioSaidaString);
						} catch (ParseException ex) {
							Logger.getLogger(ConfigArquivoReservas.class.getName()).log(Level.SEVERE, null, ex);
						}
					}

					String observacoes = palavras[4];

					boolean pago = Boolean.parseBoolean(palavras[5]);

					Quarto quarto = Quarto.getQuarto(Integer.parseInt(palavras[6]));

					new Reserva(hospede, valor, data, horarioSaida, observacoes, pago, quarto);
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

	public static void atualizaReservas() {
		String caminho = "src/reserva/reservas.txt";
		String texto = "";
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		for (Reserva reserva : Reserva.reservas) {
			if (reserva.getDataSaida() != null) {
				texto += "" + reserva.getHospede() + "\t" + reserva.getValor() + "\t";
				texto += formatar.format(reserva.getDataChegada()) + "\t" + formatar.format(reserva.getDataSaida())
						+ "\t";
				texto += reserva.getObservacoes() + "\t" + reserva.isPago() + "\t" + reserva.getQuarto().getNumero()
						+ "\n";
			} else {
				texto += "" + reserva.getHospede() + "\t" + reserva.getValor() + "\t";
				texto += formatar.format(reserva.getDataChegada()) + "\t" + "null" + "\t";
				texto += reserva.getObservacoes() + "\t" + reserva.isPago() + "\t" + reserva.getQuarto().getNumero()
						+ "\n";
			}
		}

		if (Arquivo.Write(caminho, texto)) {
			System.out.println("Arquivo salvo com sucesso!");
		} else {
			System.out.println("Erro ao salvar o arquivo!");
		}
	}

	public static void cadastraReserva(Reserva reserva) {
		String arquivo = "src/reserva/reservas.txt";
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		String texto = "" + reserva.getHospede() + "\t" + reserva.getValor() + "\t";
		texto += formatar.format(reserva.getDataChegada()) + "\t" + "null" + "\t";
		texto += reserva.getObservacoes() + "\t" + reserva.isPago() + "\t" + reserva.getQuarto().getNumero() + "\n";

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
