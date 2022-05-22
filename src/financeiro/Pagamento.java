package financeiro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import bd.Conection;
import reserva.Reserva;

public class Pagamento {
	@SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
	private int id;
	private double valor;
	private Date dataCriacao;
	private String observacoes;
	private Reserva reserva;

	public static List<Pagamento> getPagamentos() {
		try (Statement stm = Conection.con.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM tbPAGAMENTO")) {
			List<Pagamento> pagamentos = new ArrayList<>();
			while (rs.next()) {
				pagamentos
						.add(new Pagamento(rs.getInt("id"), rs.getDouble("valortotal"), rs.getTimestamp("datacriacao"),
								rs.getString("observacoes"), Reserva.getReserva(rs.getInt("idreserva"))));
			}
			return pagamentos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static double getTotalPagamentos() {
		try (Statement stm = Conection.con.createStatement();
				ResultSet rs = stm.executeQuery("SELECT SUM(valortotal) as valortotal FROM tbPAGAMENTO")) {
			Double total = 0.0;
			while (rs.next()) {
				total += rs.getDouble("valortotal");
			}
			return total;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getId() {
		return id;
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

	public Pagamento(int id, double valor, Date dataCriacao, String observacoes, Reserva reserva) {
		this.id = id;
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.observacoes = observacoes;
		this.reserva = reserva;
	}

	public static double totalPagamentosMes(int mes, int ano) {
		double totalPagamentosMes = 0;
		for (Pagamento pagamento : Pagamento.getPagamentos()) {
			String[] data = pagamento.getDataCriacaoFormatada().split("/");
			int mesPagamento = Integer.parseInt(data[1]);
			int anoPagamento = Integer.parseInt(data[2].split(" ")[0]);

			if (mesPagamento == mes && anoPagamento == ano) {
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
			for (int mes = mesAtual; mes <= 12; mes++) {
				double totalMes = Pagamento.totalPagamentosMes(mes, anoPassado);
				pagamentosMes.put((mes + "/" + Integer.toString(anoPassado)), totalMes);
			}
			for (int mes = 1; mes <= mesAtual; mes++) {
				double totalMes = Pagamento.totalPagamentosMes(mes, anoAtual);
				pagamentosMes.put((mes + "/" + anoAtual), totalMes);
			}
		} else {
			for (int mes = mesInicial; mes <= mesAtual; mes++) {
				double totalMes = Pagamento.totalPagamentosMes(mes, anoAtual);
				pagamentosMes.put((mes + Integer.toString(anoAtual)), totalMes);
			}
		}

		return pagamentosMes;
	}

	public static Pagamento cadastraPagamentoInterface(double valor, Date dataCriacao, String observacoes,
			Reserva reserva) {
		try (PreparedStatement ps = Conection.con
				.prepareStatement("insert into tbPAGAMENTO(valortotal, datacriacao, observacoes, idreserva) values"
						+ "(?, ?, ?, ?) returning *")) {
			ps.setDouble(1, valor);
			ps.setTimestamp(2, new Timestamp(dataCriacao.getTime()));
			ps.setString(3, observacoes);
			ps.setInt(4, reserva.getId());
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next()
						? new Pagamento(rs.getInt("id"), rs.getDouble("valortotal"), rs.getTimestamp("datacriacao"),
								rs.getString("observacoes"), Reserva.getReserva(rs.getInt("idreserva")))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
