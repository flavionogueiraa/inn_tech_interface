package main;

import java.util.Scanner;

import financeiro.Entrada;
import financeiro.Saida;

public class MenuInicial {
	public static void MenuProprietario(Scanner sc) {
		boolean executarMenu = true;
		int opcao;

		do {
			System.out.println("========== Inn Tech ==========");
			System.out.println("[1] Usuários");
			System.out.println("[2] Reservas");
			System.out.println("[3] Caixa");
			System.out.println("[4] Relatórios");
			System.out.println("[5] Saídas");
			System.out.println("[6] Quartos");
			System.out.println("[0] Encerrar");

			System.out.print("Informe sua opção: ");
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				MenuUsuario.Menu(sc);
				break;

			case 2:
				MenuReserva.Menu(sc);
				break;

			case 3:
				System.out.printf("Total: %.2f\n ", (Entrada.getTotalEntradas() - Saida.getTotalSaidas()));
				break;

			case 4:
				MenuRelatorios.Menu(sc);
				break;

			case 5:
				MenuSaida.Menu(sc);
				break;

			case 6:
				MenuQuarto.Menu(sc);
				break;

			case 0:
				executarMenu = false;
				break;

			default:
				System.out.println("Opção inválida");
				break;
			}
		} while (executarMenu);

		System.out.println("Obrigado por usar a Inn Tech");
		sc.close();
	}

	public static void MenuFuncionario(Scanner sc) {
		boolean executarMenu = true;
		int opcao;

		do {
			System.out.println("========== Inn Tech ==========");
			System.out.println("[1] Reservas");
			System.out.println("[2] Caixa");
			System.out.println("[3] Saídas");
			System.out.println("[0] Encerrar");

			System.out.print("Informe sua opção: ");
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				MenuReserva.Menu(sc);
				break;

			case 2:
				System.out.printf("Total: %.2f\n ", (Entrada.getTotalEntradas() - Saida.getTotalSaidas()));
				break;

			case 3:
				MenuSaida.Menu(sc);
				break;

			case 0:
				executarMenu = false;
				break;

			default:
				System.out.println("Opção inválida");
				break;
			}
		} while (executarMenu);

		System.out.println("Obrigado por usar a Inn Tech");
		sc.close();
	}
}
