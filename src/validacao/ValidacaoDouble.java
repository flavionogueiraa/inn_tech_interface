package validacao;

import java.util.Scanner;

public class ValidacaoDouble {
	public static double validacao(Scanner sc, String nomeCampo, boolean pulaLinha) {
		String var;
		boolean erro = false;
		if (pulaLinha) {
			sc.nextLine();
		}
		do {
			try {
				System.out.println(nomeCampo);
				var = sc.nextLine();
				return Double.parseDouble(var);

			} catch (Exception error) {
				erro = true;
				System.out.println("Insira um valor númerico");
			}
		} while (erro);
		return 0;
	}
}
