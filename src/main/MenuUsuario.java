package main;

import java.util.Scanner;

import usuario.Usuario;

public class MenuUsuario {
	public static void Menu(Scanner sc) {
		boolean executarMenu = true;
		int opcaoUsuario;

		do {
			System.out.println("[1] Cadastrar");
			System.out.println("[2] Listar");
			System.out.println("[3] Editar");
			System.out.println("[4] Excluir");
			System.out.println("[0] Voltar");

			System.out.print("Informe sua opção: ");
			opcaoUsuario = sc.nextInt();

			switch (opcaoUsuario) {
			case 1:
				new Usuario(sc);
				break;

			case 2:
				Usuario.listaUsuarios();
				break;

			case 3:
				Usuario.editaUsuario(sc);
				break;

			case 4:
				Usuario.excluiUsuario(sc);
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
