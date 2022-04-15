package validacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import usuario.Usuario;

public class ValidacaoCPF {
	public static String validacaoNormal(Scanner sc, String nomeCampo, boolean pulaLinha) {
		String var;
		boolean erro = false;
		if (pulaLinha) {
			sc.nextLine();
		}
		do {
			System.out.println("Insira um valor no formato: 'xxx.xxx.xxx-xx'");
			System.out.println(nomeCampo);
			var = sc.nextLine();
			if (isCPF(var)) {
				return var;
			} else {
				System.out.println("Informe um CPF válido");
				erro = true;
			}
		} while (erro);
		return null;
	}

	private static boolean isCPF(String CPF) {
		CPF = CPF.replace(".", "").replace("-", "");

		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	private static boolean CPFunico(String CPF) {
		for (Usuario usuario : Usuario.usuarios) {
			if (usuario.getCPF().equals(CPF)) {
				return false;
			}
		}

		return true;
	}

	public static String validacaoCadastro(Scanner sc, String nomeCampo, boolean pulaLinha) {
		boolean erro = false;

		do {
			String var = validacaoNormal(sc, nomeCampo, pulaLinha);
			if (CPFunico(var)) {
				return var;
			} else {
				erro = true;
				pulaLinha = false;
				System.out.println("Desculpe, CPF ja cadastrado");
			}
		} while (erro);

		return null;
	}

	public static String validacaoLogin(Scanner sc, String nomeCampo, boolean pulaLinha) {
		boolean erro = false;

		do {
			String var = validacaoNormal(sc, nomeCampo, pulaLinha);
			if (!CPFunico(var)) {
				return var;
			} else {
				erro = true;
				System.out.println("Desculpe, CPF nao cadastrado");
			}
		} while (erro);

		return null;
	}

	public static String validacaoEdicao(Scanner sc, String nomeCampo, boolean pulaLinha, Usuario usuario) {
		boolean erro = false;

		do {
			String var = validacaoNormal(sc, nomeCampo, pulaLinha);
			if (!CPFunico(var)) {
				if (var.equals(usuario.getCPF())) {
					return var;
				} else {
					System.out.println("Desculpe, o CPF ja esta em uso");
					erro = true;
					pulaLinha = false;
				}
			} else {
				return var;
			}
		} while (erro);

		return null;
	}

}
