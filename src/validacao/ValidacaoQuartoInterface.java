package validacao;

import quarto.Quarto;

public class ValidacaoQuartoInterface {
	public static boolean validacaoEdicao(Quarto quarto, int numero) {
		Quarto quartoBusca = Quarto.getQuarto(numero);
		if(quartoBusca != null) {
			if(quartoBusca == quarto) {
				return true;
			}
		} else {
			return true;
		}
		
		return false;
	}
}
