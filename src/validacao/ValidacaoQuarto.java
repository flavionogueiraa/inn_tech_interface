package validacao;

import java.util.Scanner;
import quarto.Quarto;

public class ValidacaoQuarto {
	public static int validacao(Scanner sc) {
		boolean erro = false;
		// Chamamos uma vez fora e outra dentro para evitar problemas com scanner.
		int numero = ValidacaoInt.validacao(sc, "Informe o número do quarto:", true);
		do {
			if (Quarto.getQuarto(numero) == null) {
				return numero;
			} else {
				System.out.println("Já existe um quarto com esse número!");
				erro = true;
				numero = ValidacaoInt.validacao(sc, "Informe o número do quarto:", false);
			}
		} while (erro);
		return -1;
	}

	public static Quarto validacaoOcupado(Scanner sc) {
		boolean erro = false;
		// Chamamos uma vez fora e outra dentro para evitar problemas com scanner.
		int numero = ValidacaoInt.validacao(sc, "Informe o número do quarto:", true);
		do {
			Quarto quarto = Quarto.getQuarto(numero);
			if (quarto != null) {
				if (!quarto.isOcupado()) {
					return quarto;
				} else {
					System.out.println("Quarto ocupado");
					erro = true;
					numero = ValidacaoInt.validacao(sc, "Informe o número do quarto:", false);
				}
			} else {
				System.out.println("Quarto inexistente!");
				erro = true;
				numero = ValidacaoInt.validacao(sc, "Informe o número do quarto:", false);
			}
		} while (erro);
		return null;
	}
}
