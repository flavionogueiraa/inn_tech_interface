package financeiro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import arquivo.ConfigArquivoEntradas;

public class Entrada {
	static double totalEntradas = 0;

	private int id;
	private double valor;
	private Date dataCriacao;
	private String observacoes;

	public static ArrayList<Entrada> entradas = new ArrayList<>();
	private static int idCont = 0;

	public int getId() {
		return id;
	}

	private void setId() {
		Entrada.idCont++;
		this.id = idCont;
	}

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
		this.setId();

		Entrada.totalEntradas += valor;
		entradas.add(this);
		if (Arq) {
			ConfigArquivoEntradas.cadastraEntrada(this);
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
