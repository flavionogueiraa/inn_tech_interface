package validacao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidacaoData {
	public static Date validacao(Scanner sc, String nomeCampo, boolean pulaLinha) {
		String var;
		boolean erro = false;
		if (pulaLinha) {
			sc.nextLine();
		}
		do {
			try {
				System.out.println(nomeCampo);
				System.out.println("Insira um valor no formato: 'dd/MM/yyyy HH:mm'");
				var = sc.nextLine();
				return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(var);

			} catch (Exception error) {
				erro = true;
			}
		} while (erro);
		return null;
	}
}
