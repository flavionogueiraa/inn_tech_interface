package main;

import java.util.Scanner;

import quarto.Quarto;
import reserva.Reserva;
import validacao.ValidacaoQuarto;

public class MenuCriacaoReserva {
	public static void Menu(Scanner sc) {
		Quarto.listaQuartos();

		Quarto quarto = ValidacaoQuarto.validacaoOcupado(sc);

		if (quarto != null) {
			new Reserva(sc, quarto);
			System.out.println("Reserva feita!");
		}
	}
}
