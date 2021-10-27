package main;

import java.util.Scanner;

import usuario.Usuario;
import validacao.ValidacaoCPF;

public class Login {
	public static Usuario realizarLogin(Scanner sc) {
		String cpf;
		String senha;
		boolean executarLogin = true;
		boolean erro = false;
		Usuario usuario;

		do {
			System.out.println("========== login ==========");
			System.out.println("Insira suas informações");
			cpf = ValidacaoCPF.validacaoLogin(sc, "CPF:", erro);

			System.out.print("Senha: ");
			senha = sc.next();

			usuario = Usuario.login(cpf, senha);
			if (usuario != null) {
				System.out.println("Login efetuado com sucesso");
				executarLogin = false;
			} else {
				System.out.println("Falha no login, senha incorreta");
				erro = true;
			}
		} while (executarLogin);
		return usuario;
	}
}
