package reserva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bd.Conection;
import financeiro.Pagamento;
import quarto.Quarto;
import usuario.Usuario;

public class Reserva {
	private int id;
	private Date dataEstimadaCheckin;
	private Date dataEstimadaCheckout;
	private String nomeHospede;
	private double valorDiaria;
	private Quarto quarto;
	private boolean pagamentoConfirmado;
	private String observacoes;
	private Usuario usuarioCriacao;

	public int getId() {
		return id;
	}

	public Date getdataEstimadaCheckin() {
		return dataEstimadaCheckin;
	}

	public String getdataEstimadaCheckinFormatada() {
		if (this.dataEstimadaCheckin != null) {
			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatar.format(this.getdataEstimadaCheckin());
		} else {
			return "-";
		}
	}

	public void setdataEstimadaCheckin(Date dataEstimadaCheckin) {
		this.dataEstimadaCheckin = dataEstimadaCheckin;
	}

	public Date getdataEstimadaCheckout() {
		return dataEstimadaCheckout;
	}

	public String getdataEstimadaCheckoutFormatada() {
		if (this.dataEstimadaCheckout != null) {
			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatar.format(this.getdataEstimadaCheckout());
		} else {
			return "-";
		}
	}

	public void setdataEstimadaCheckout(Date dataEstimadaCheckout) {
		this.dataEstimadaCheckout = dataEstimadaCheckout;
	}

	public String getHospede() {
		return nomeHospede;
	}

	public void setHospede(String nomeHospede) {
		this.nomeHospede = nomeHospede;
	}

	public Double getValor() {
		return valorDiaria;
	}

	public void setValor(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public boolean isPago() {
		return pagamentoConfirmado;
	}

	public void setPago(boolean pagamentoConfirmado) {
		this.pagamentoConfirmado = pagamentoConfirmado;
	}

	public String isPagoSimNao() {
		if (this.isPago()) {
			return "Sim";
		} else {
			return "Nao";
		}
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Usuario getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(Usuario usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public String getNomeUsuario() {
		if (usuarioCriacao != null) {
			return usuarioCriacao.getNome();
		} else {
			return "-";
		}
	}

	public Reserva(int id, String nomeHospede, Double valorDiaria, Date dataEstimadaCheckin, Date dataEstimadaCheckout,
			String observacoes, boolean pagamentoConfirmado, Quarto quarto) {
		this.id = id;
		this.nomeHospede = nomeHospede;
		this.valorDiaria = valorDiaria;
		this.dataEstimadaCheckin = dataEstimadaCheckin;
		this.dataEstimadaCheckout = dataEstimadaCheckout;
		this.observacoes = observacoes;
		this.pagamentoConfirmado = pagamentoConfirmado;
		this.quarto = quarto;
		this.usuarioCriacao = Usuario.usuarioLogado;

	}

	public void deletaReserva() {
		try (PreparedStatement ps = Conection.con.prepareStatement("delete from tbRESERVA where id=?")) {
			ps.setInt(1, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Reserva getReserva(int reservaNumero) {
		try (PreparedStatement ps = Conection.con.prepareStatement(
				"SELECT TBRESERVA.* FROM TBRESERVA JOIN TBQUARTO ON NUMERO = ? AND IDQUARTO = TBQUARTO.ID")) {
			ps.setInt(1, reservaNumero);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next()
						? new Reserva(rs.getInt("id"), rs.getString("nomeHospede"), rs.getDouble("valorDiaria"),
								rs.getTimestamp("dataEstimadaCheckin"), rs.getTimestamp("dataEstimadaCheckout"),
								rs.getString("observacoes"), rs.getBoolean("pagamentoConfirmado"),
								Quarto.getQuarto(rs.getInt("idquarto")))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void finalizaReserva() {
		this.dataEstimadaCheckout = new Date();
		this.quarto.setOcupado(false);
		Pagamento.cadastraPagamentoInterface(this.valorDiaria, this.dataEstimadaCheckout,
				"Reserva do quarto " + this.quarto.getNumero(), this);
	}

	public static List<Reserva> getReservas() {
		try (Statement stm = Conection.con.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM tbRESERVA")) {
			/* criamos uma lista para inserir informa��es de login da banco dados */
			List<Reserva> Reservaa = new ArrayList<>();
			while (rs.next()) {
				Reservaa.add(new Reserva(rs.getInt("id"), rs.getString("nomeHospede"), rs.getDouble("valorDiaria"),
						rs.getTimestamp("dataEstimadaCheckin"), rs.getTimestamp("dataEstimadaCheckout"),
						rs.getString("observacoes"), rs.getBoolean("pagamentoConfirmado"),
						Quarto.getQuarto(rs.getInt("idquarto"))));
			}
			return Reservaa;
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};

	public static Reserva cadastraReservaInterface(String nomeHospede, Double valorDiaria, Date dataEstimadaCheckin,
			Date dataEstimadaCheckout, String observacoes, boolean pagamentoConfirmado, Quarto quarto) {
		try (PreparedStatement ps = Conection.con.prepareStatement(
				"insert into tbRESERVA(nomeHospede, valorDiaria, dataEstimadaCheckin, dataEstimadaCheckout, observacoes, pagamentoConfirmado, idquarto) values (?, ?, ?, ?, ?, ?, ?) returning *")) {
			ps.setString(1, nomeHospede);
			ps.setDouble(2, valorDiaria);
			ps.setTimestamp(3, new Timestamp(dataEstimadaCheckin.getTime()));
			ps.setTimestamp(4, dataEstimadaCheckout != null ? new Timestamp(dataEstimadaCheckout.getTime()) : null);
			ps.setString(5, observacoes);
			ps.setBoolean(6, pagamentoConfirmado);
			ps.setInt(7, quarto.getId());

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next()
						? new Reserva(rs.getInt("id"), rs.getString("nomeHospede"), rs.getDouble("valorDiaria"),
								rs.getTimestamp("dataEstimadaCheckin"), rs.getTimestamp("dataEstimadaCheckout"),
								rs.getString("observacoes"), rs.getBoolean("pagamentoConfirmado"),
								Quarto.getQuarto(rs.getInt("idquarto")))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
