package financeiro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import arquivo.ConfigArquivoSaidas;
import validacao.ValidacaoData;
import validacao.ValidacaoDouble;
import validacao.ValidacaoInt;

public class Saida {
	static double totalSaidas;

	private double valor;
	private Date dataCriacao;
	private String motivo;
	private String observacoes;

	public static ArrayList<Saida> saidas = new ArrayList<>();

	public static double getTotalSaidas() {
		return totalSaidas;
	}

	public static void setTotalSaidas(double totalSaidas) {
		Saida.totalSaidas = totalSaidas;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public String getDataCriacaoFormatada() {
		if (this.dataCriacao != null) {
			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatar.format(this.getDataCriacao());
		} else {
			return "-";
		}
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Saida(Scanner sc) {
		setValor(ValidacaoDouble.validacao(sc, "Valor:", true));

		System.out.println("Motivo: ");
		setMotivo(sc.next());

		System.out.println("Observacoes: ");
		setObservacoes(sc.next());

		this.dataCriacao = ValidacaoData.validacao(sc, "Informe a data e a hora:", true);

		ConfigArquivoSaidas.cadastraSaida(this);
		saidas.add(this);
		Saida.totalSaidas += this.valor;
	}

	public Saida(double valor, Date dataCriacao, String motivo, String observacoes) {
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.motivo = motivo;
		this.observacoes = observacoes;
		saidas.add(this);
		Saida.totalSaidas += this.valor;
	}

	public static void listaSaidas() {
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		int i = 1;
		for (Saida saida : saidas) {
			System.out.println("ID: " + i);
			System.out.println("Valor: " + saida.getValor() + " ");
			System.out.println("Motivo: " + saida.getMotivo() + " ");
			System.out.println("Observações: " + saida.getObservacoes() + " ");
			System.out.println("Data e Horário: " + formatar.format(saida.getDataCriacao()));
			i++;
			System.out.println();
		}
	}

	public static void editaSaida(Scanner sc) {
		Saida.listaSaidas();
		int opcaoSaida = ValidacaoInt.validacao(sc, "Informe um ID para editar:", true);
		Saida saida;
		try {
			saida = Saida.saidas.get(opcaoSaida - 1);
		} catch (Exception e) {
			saida = null;
		}

		if (saida != null) {
			double valorAntigo = saida.valor;
			System.out.println("Valor atual: " + saida.valor);
			saida.setValor(ValidacaoDouble.validacao(sc, "Novo valor:", false));

			System.out.println("Motivo atual: " + saida.motivo);
			System.out.print("Novo motivo: ");
			saida.setMotivo(sc.next());

			System.out.println("Observacoes atuais: " + saida.observacoes);
			System.out.print("Novas observações: ");
			saida.setObservacoes(sc.next());

			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			System.out.println("Data atual: " + formatar.format(saida.dataCriacao));
			saida.dataCriacao = ValidacaoData.validacao(sc, "Informe a data e a hora:", true);

			if (valorAntigo != saida.valor) {
				Saida.totalSaidas -= valorAntigo;
				Saida.totalSaidas += saida.valor;
			}
			ConfigArquivoSaidas.atualizaSaida();
		} else {
			System.out.println("ID inválido");
		}
	}

	public static void excluiSaida(Scanner sc) {
		Saida.listaSaidas();

		int id = ValidacaoInt.validacao(sc, "Digite o ID da saída que deseja deletar:", true);
		Saida saida;
		try {
			saida = Saida.saidas.get(id - 1);
		} catch (Exception e) {
			saida = null;
		}

		if (saida != null) {
			Saida.saidas.remove(saida);
			saida = null;
			ConfigArquivoSaidas.atualizaSaida();
		} else {
			System.out.println("ID inválido");
		}
	}
}
