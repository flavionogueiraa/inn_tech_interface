package financeiro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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

	public static double totalEntradasMes(int mes) {
		double totalEntradasMes = 0;
		for (Entrada entrada : Entrada.entradas) {
			String[] data = entrada.getDataCriacaoFormatada().split("/");
			int mesEntrada = Integer.parseInt(data[1]);
			if (mesEntrada == mes) {
				totalEntradasMes += entrada.getValor();
			}
		}

		return totalEntradasMes;
	}

	@SuppressWarnings("deprecation")
	public static LinkedHashMap<String, Double> entradasMes() {
		LinkedHashMap<String, Double> entradasMes = new LinkedHashMap<String, Double>();
		int mesAtual = new Date().getMonth() + 1;
		int mesInicial = mesAtual - 11;
		
		int anoAtual = new Date().getYear() + 1900;
		int anoPassado = anoAtual - 1;
		
		if (mesInicial <= 0) {
			for (int i = mesAtual; i <= 12; i++) {
				double totalMes = Entrada.totalEntradasMes(i);
				entradasMes.put((i + "/" + Integer.toString(anoPassado)), totalMes);
			}
			for (int i = 1; i <= mesAtual; i++) {
				double totalMes = Entrada.totalEntradasMes(i);
				entradasMes.put((i + "/" + Integer.toString(anoAtual)), totalMes);
			}
		} else {
			for (int i = mesInicial; i <= mesAtual; i++) {
				double totalMes = Entrada.totalEntradasMes(i);
				entradasMes.put((i + Integer.toString(anoAtual)), totalMes);
			}
		}
		
		return entradasMes;
	}
}
