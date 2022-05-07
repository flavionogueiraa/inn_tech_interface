package main_interface;

import usuario.Usuario;
import validacao.ValidacaoCPFInterface;

public class LoginInterface {
	public static Usuario realizarLoginInterface(String CPF, String senha) {
		String cpfValidado = ValidacaoCPFInterface.validacaoNormal(CPF);
		if (cpfValidado != null) {
			Usuario usuario = Usuario.login(CPF, senha);
			if (usuario != null) {
				System.out.println("Login efetuado com sucesso");
			} else {
				System.out.println("Falha no login, senha incorreta");
			}
			Usuario.usuarioLogado = usuario;
			return usuario;
		}
		return null;
	}
}
