package quarto;

import java.util.ArrayList;
import java.util.Scanner;

import arquivo.ConfigArquivoQuartos;
import validacao.ValidacaoInt;
import validacao.ValidacaoQuarto;

public class Quarto {
	private int numero, capacidade;
	private String descricao;
	private boolean ocupado;

	public static ArrayList<Quarto> quartos = new ArrayList<>();

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Quarto(Scanner sc) {
		System.out.println("Digite as informações do quarto:");
		this.numero = ValidacaoQuarto.validacao(sc);
		this.capacidade = ValidacaoInt.validacao(sc, "Capacidade do Quarto:", false);
		System.out.println("Descrição do quarto:");
		setDescricao(sc.nextLine());
		this.ocupado = false;
		quartos.add(this);
		ConfigArquivoQuartos.cadastraQuarto(this);
	}

	public Quarto(int numero, int capacidade, String descricao, boolean ocupado) {
		this.numero = numero;
		this.capacidade = capacidade;
		this.descricao = descricao;
		this.ocupado = ocupado;
		quartos.add(this);
	}
	
	public static Quarto cadastraQuartoInterface(int numero, int capacidade, String descricao) {
		Quarto novo_quarto = new Quarto(numero, capacidade, descricao, false);
		ConfigArquivoQuartos.atualizaQuartos();
		
		return novo_quarto;
	}

	public static void listaQuartos() {
		int i = 1;
		for (Quarto quarto : quartos) {
			System.out.println("ID: " + i);
			System.out.println("Número: " + quarto.numero);
			System.out.println("Capacidade: " + quarto.capacidade);
			System.out.println("Descrição: " + quarto.descricao);
			System.out.println("Ocupado: " + quarto.ocupado);
			System.out.println();
			i++;
		}
	}

	public static Quarto getQuarto(int numero) {
		for (Quarto quarto : quartos) {
			if (quarto.numero == numero) {
				return quarto;
			}
		}
		return null;
	}

	public static Quarto getQuartoDisponivel(int numero) {
		for (Quarto quarto : quartos) {
			if (quarto.numero == numero && quarto.isOcupado() == false) {
				return quarto;
			}
		}
		return null;
	}

	public static void editaQuarto(Scanner sc) {
		Quarto.listaQuartos();
		int opcaoSaida = ValidacaoInt.validacao(sc, "Informe um ID para editar:", true);

		Quarto quarto;
		try {
			quarto = Quarto.quartos.get(opcaoSaida - 1);
		} catch (Exception e) {
			quarto = null;
		}

		if (quarto != null) {
			System.out.println("Número atual: " + quarto.numero);
			quarto.setNumero(ValidacaoInt.validacao(sc, "Novo número:", false));

			System.out.println("Capacidade atual: " + quarto.capacidade);
			quarto.setCapacidade(ValidacaoInt.validacao(sc, "Nova capacidade:", false));

			System.out.println("Descrição atual: " + quarto.descricao);
			System.out.print("Nova descrição: ");
			quarto.setDescricao(sc.nextLine());

			ConfigArquivoQuartos.atualizaQuartos();
		} else {
			System.out.println("ID inválido");
		}
	}

	public static void excluiQuarto(Scanner sc) {
		Quarto.listaQuartos();

		int id = ValidacaoInt.validacao(sc, "Digite o ID da quarto que deseja deletar:", true);
		Quarto quarto;
		try {
			quarto = Quarto.quartos.get(id - 1);
		} catch (Exception e) {
			quarto = null;
		}

		if (quarto != null) {
			Quarto.quartos.remove(quarto);
			quarto = null;
			ConfigArquivoQuartos.atualizaQuartos();
		} else {
			System.out.println("ID inválido");
		}
	}

	public static String quartosDisponiveis() {
		String quartosDisponiveis = "";
		for (Quarto quarto : Quarto.quartos) {
			if (!quarto.isOcupado()) {
				quartosDisponiveis += quarto.getNumero() + " ";
			}
		}
		if (quartosDisponiveis.isEmpty()) {
			quartosDisponiveis = "Nenhum";
		}
		return quartosDisponiveis;
	}
	
	public void deletaQuarto() {
		Quarto.quartos.remove(this);
		ConfigArquivoQuartos.atualizaQuartos();
	}
}
