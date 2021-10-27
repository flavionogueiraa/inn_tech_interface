package main;

import java.util.Scanner;

import quarto.Quarto;

public class MenuQuarto {
	public static void Menu(Scanner sc) {
		boolean executarMenu = true;
		int opcaoUsuario;

		do {
			System.out.println("[1] Cadastrar");
			System.out.println("[2] Listar");
			System.out.println("[3] Editar");
			System.out.println("[4] Excluir");
			System.out.println("[0] Voltar");

			opcaoUsuario = sc.nextInt();

			switch (opcaoUsuario) {
			case 1:
				new Quarto(sc);
				break;

			case 2:
				Quarto.listaQuartos();
				break;

			case 3:
				Quarto.editaQuarto(sc);
				break;

			case 4:
				Quarto.excluiQuarto(sc);
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
