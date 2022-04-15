package validacao;

import java.util.Scanner;

public class ValidacaoInt {
	public static int validacao(Scanner sc, String nomeCampo, boolean pulaLinha) {
		String var;
		boolean erro = false;
		if (pulaLinha) {
			sc.nextLine();
		}
		do {
			try {
				System.out.println(nomeCampo);
				var = sc.nextLine();
				return Integer.parseInt(var);

			} catch (Exception error) {
				erro = true;
				System.out.println("Insira um valor numerico");
			}
		} while (erro);
		return 0;
	}
}
