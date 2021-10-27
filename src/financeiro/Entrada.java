package financeiro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import arquivo.ConfigArquivoEntradas;
import validacao.ValidacaoInt;

public class Entrada {
	static double totalEntradas = 0;

	private double valor;
	private Date dataCriacao;
	private String observacoes;

	public static ArrayList<Entrada> entradas = new ArrayList<>();

	public static double getTotalEntradas() {
		return totalEntradas;
	}

	public static void setTotalEntradas(double totalEntradas) {
		Entrada.totalEntradas = totalEntradas;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Entrada(double valor, Date dataCriacao, String observacoes, boolean Arq) {
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.observacoes = observacoes;
		Entrada.totalEntradas += valor;
		entradas.add(this);
		if (Arq) {
			ConfigArquivoEntradas.cadastraEntrada(this);
		}

	}

	public static void listaEntradas() {
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		int i = 1;
		for (Entrada entrada : entradas) {
			System.out.println("ID: " + i);
			System.out.println("Valor " + entrada.valor);
			System.out.println("Data e Horário: " + formatar.format(entrada.dataCriacao));
			System.out.println("Observações " + entrada.observacoes);
			i++;
		}
	}

	public static void excluiEntrada(Scanner sc) {
		Entrada.listaEntradas();

		int id = ValidacaoInt.validacao(sc, "Digite o ID da entrada que deseja deletar:", false);
		Entrada entrada;
		try {
			entrada = Entrada.entradas.get(id - 1);
		} catch (Exception e) {
			entrada = null;
		}

		if (entrada != null) {
			Entrada.entradas.remove(entrada);
			entrada = null;
			ConfigArquivoEntradas.atualizaEntradas();
		} else {
			System.out.println("ID inválido");
		}
	}
}
