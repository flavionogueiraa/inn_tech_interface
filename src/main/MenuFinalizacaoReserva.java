package main;

import java.util.Scanner;

import reserva.Reserva;

public class MenuFinalizacaoReserva {
	public static void Menu(Scanner sc) {
		boolean executarMenu = true;
		int opcaoFinalizacao, numeroQuarto;

		do {
			Reserva.listaReservas();
			System.out.print("Informe o número do quarto desejado: ");
			numeroQuarto = sc.nextInt();

			Reserva reserva = Reserva.getReserva(numeroQuarto);

			if (reserva != null) {
				System.out.println(
						"Ao finalizar a reserva uma nova entrada será criada no valor de R$ " + reserva.getValor());
				System.out.println("Tem certeza?");
				System.out.println("[1] Sim");
				System.out.println("[0] Voltar");

				opcaoFinalizacao = sc.nextInt();

				switch (opcaoFinalizacao) {
				case 1:
					reserva.finalizaReserva();
					System.out.println("Reserva finalizada, quarto " + reserva.getQuarto().getNumero() + " livre!");
					executarMenu = false;

					break;

				case 0:
					executarMenu = false;
					break;

				default:
					System.out.println("Opção inválida");
					break;
				}
			}

		} while (executarMenu);
	}
}
