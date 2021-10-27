package main;

import java.util.Scanner;

import arquivo.Arquivo;
import usuario.Usuario;

public class InnTech {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Arquivo.inicializaVariaveis();

		Login.realizarLogin(sc);

		if (Usuario.usuarioLogado.isProprietario()) {
			MenuInicial.MenuProprietario(sc);
		} else {
			MenuInicial.MenuFuncionario(sc);
		}

		sc.close();
	}
}