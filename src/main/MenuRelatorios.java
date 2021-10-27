package main;

import java.util.Scanner;

import financeiro.Entrada;
import financeiro.Saida;

public class MenuRelatorios {
	public static void Menu(Scanner sc) {
		int opcaoRelatorios;
		boolean executarMenu = true;

		do {
			System.out.println("[1] Entradas");
			System.out.println("[2] Saídas");
			System.out.println("[0] Voltar");

			opcaoRelatorios = sc.nextInt();

			switch (opcaoRelatorios) {
			case 1:
				Entrada.listaEntradas();
				break;

			case 2:
				Saida.listaSaidas();
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
