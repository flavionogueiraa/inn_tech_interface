package financeiro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import reserva.Reserva;

public class Pagamento {
	static double totalPagamentos = 0;

	private int id;
	private double valor;
	private Date dataCriacao;
	private String observacoes;
	private Reserva reserva;

	public static ArrayList<Pagamento> pagamentos = new ArrayList<>();
	private static int idCont = 0;

	public int getId() {
		return id;
	}

	private void setId() {
		Pagamento.idCont++;
		this.id = idCont;
	}

	public static double getTotalPagamentos() {
		return totalPagamentos;
	}

	public static void setTotalPagamentos(double totalPagamentos) {
		Pagamento.totalPagamentos = totalPagamentos;
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

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Integer getIdReserva() {
		if (reserva != null) {
			return reserva.getId();
		} else {
			return -1;
		}
	}

	public Pagamento(double valor, Date dataCriacao, String observacoes, boolean Arq, Reserva reserva) {
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.observacoes = observacoes;
		this.setId();
		this.reserva = reserva;

		Pagamento.totalPagamentos += valor;
		pagamentos.add(this);
		
	}

	public static double totalPagamentosMes(int mes) {
		double totalPagamentosMes = 0;
		for (Pagamento pagamento : Pagamento.pagamentos) {
			String[] data = pagamento.getDataCriacaoFormatada().split("/");
			int mesPagamento = Integer.parseInt(data[1]);
			if (mesPagamento == mes) {
				totalPagamentosMes += pagamento.getValor();
			}
		}

		return totalPagamentosMes;
	}

	@SuppressWarnings("deprecation")
	public static LinkedHashMap<String, Double> pagamentosMes() {
		LinkedHashMap<String, Double> pagamentosMes = new LinkedHashMap<String, Double>();
		int mesAtual = new Date().getMonth() + 1;
		int mesInicial = mesAtual - 11;

		int anoAtual = new Date().getYear() + 1900;
		int anoPassado = anoAtual - 1;

		if (mesInicial <= 0) {
			for (int i = mesAtual; i <= 12; i++) {
				double totalMes = Pagamento.totalPagamentosMes(i);
				pagamentosMes.put((i + "/" + Integer.toString(anoPassado)), totalMes);
			}
			for (int i = 1; i <= mesAtual; i++) {
				double totalMes = Pagamento.totalPagamentosMes(i);
				pagamentosMes.put((i + "/" + Integer.toString(anoAtual)), totalMes);
			}
		} else {
			for (int i = mesInicial; i <= mesAtual; i++) {
				double totalMes = Pagamento.totalPagamentosMes(i);
				pagamentosMes.put((i + Integer.toString(anoAtual)), totalMes);
			}
		}

		return pagamentosMes;
	}
}
