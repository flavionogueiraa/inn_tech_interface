package validacao;

import quarto.Quarto;

public class ValidacaoQuartoInterface {
	public static boolean validacaoEdicao(Quarto quarto, String numero) {
		Quarto quartoBusca = Quarto.getQuarto(numero);

		if (quartoBusca != null) {
			if (quartoBusca.getId() == quarto.getId()) {
				return true;
			}
		} else {
			return true;
		}

		return false;
	}
}
