package main;

import java.util.Scanner;

import reserva.Reserva;

public class MenuReserva {
	public static void Menu(Scanner sc) {
		int opcaoReserva;
		boolean executarMenu = true;

		do {
			System.out.println("[1] Cadastrar");
			System.out.println("[2] Listar");
			System.out.println("[3] Editar");
			System.out.println("[4] Excluir");
			System.out.println("[5] Finalizar reserva");
			System.out.println("[0] Voltar");

			opcaoReserva = sc.nextInt();

			switch (opcaoReserva) {
			case 1:
				MenuCriacaoReserva.Menu(sc);
				break;

			case 2:
				Reserva.listaReservas();
				break;

			case 3:
				Reserva.editaReserva(sc);
				break;

			case 4:
				Reserva.excluiReserva(sc);
				break;

			case 5:
				MenuFinalizacaoReserva.Menu(sc);
				break;

			case 0:
				executarMenu = false;
				break;
			default:
				break;
			}
		} while (executarMenu);
	}
}
