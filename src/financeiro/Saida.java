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
import usuario.Usuario;

public class Saida {
	private int id;
	private double valor;
	private Date dataCriacao;
	private String motivo;
	private String observacoes;
	private Usuario usuarioCriacao;

	public static List<Saida> getSaidas() {
		try (Statement stm = Conection.con.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM tbSAIDA")) {
			/* criamos uma lista para inserir informacoes de saida no banco dados */

			List<Saida> saidas = new ArrayList<>();
			while (rs.next()) {
				saidas.add(new Saida(rs.getInt("id"), rs.getDouble("valor"), rs.getTimestamp("dataCriacao"),
						rs.getString("motivo"), rs.getString("observacoes"),
						Usuario.getUsuario(rs.getInt("id_usuariosaida"))));
			}
			return saidas;
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Integer getId() {
		return id;
	}

	public static double getTotalSaidas() {
		try (Statement stm = Conection.con.createStatement();
				ResultSet rs = stm.executeQuery("SELECT SUM(valor) as valor FROM tbSAIDA")) {
			Double total = 0.0;
			while (rs.next()) {
				total += rs.getDouble("valor");
			}
			return total;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
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

	public Saida(int id, double valor, Date dataCriacao, String motivo, String observacoes, Usuario usuarioCriacao) {
		this.id = id;
		this.valor = valor;
		this.dataCriacao = dataCriacao;
		this.motivo = motivo;
		this.observacoes = observacoes;
		if (usuarioCriacao != null) {
			this.usuarioCriacao = usuarioCriacao;
		} else {
			this.usuarioCriacao = Usuario.usuarioLogado;
		}
	}

	public static Saida cadastraSaidaInterface(double valor, Date dataCriacao, String motivo, String observacoes,
			Usuario usuarioCriacao) {
		/* Inserir as informacoes no banco de dados */
		try (PreparedStatement ps = Conection.con
				.prepareStatement("insert into tbSAIDA(valor, datacriacao, motivo, observacoes, id_usuariosaida) values"
						+ "(?, ?, ?, ?, ?) returning *")) {
			/* Aqui vai ser inserindo pela indexacao das interrogacoes **/
			ps.setDouble(1, valor);
			ps.setTimestamp(2, new Timestamp(dataCriacao.getTime()));
			ps.setString(3, motivo);
			ps.setString(4, observacoes);
			ps.setInt(5, usuarioCriacao.getId());
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next()
						? new Saida(rs.getInt("id"), rs.getDouble("valor"), rs.getTimestamp("dataCriacao"),
								rs.getString("motivo"), rs.getString("observacoes"),
								Usuario.getUsuario(rs.getInt("id_usuariosaida")))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deletaSaida() {
		try (PreparedStatement ps = Conection.con.prepareStatement("delete from tbSAIDA where id=?")) {
			/* Aqui vai ser delete pela indexacao das interrogacoes **/
			ps.setInt(1, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static double totalSaidaMes(int mes, int ano) {
		double totalSaidaMes = 0;
		for (Saida saida : Saida.getSaidas()) {
			String[] data = saida.getDataCriacaoFormatada().split("/");
			int mesPagamento = Integer.parseInt(data[1]);
			int anoPagamento = Integer.parseInt(data[2].split(" ")[0]);

			if (mesPagamento == mes && anoPagamento == ano) {
				totalSaidaMes += saida.getValor();
			}
		}

		return totalSaidaMes;
	}

	@SuppressWarnings("deprecation")
	public static LinkedHashMap<String, Double> saidasMes() {
		LinkedHashMap<String, Double> saidasMes = new LinkedHashMap<String, Double>();
		int mesAtual = new Date().getMonth() + 1;
		int mesInicial = mesAtual - 11;

		int anoAtual = new Date().getYear() + 1900;
		int anoPassado = anoAtual - 1;

		if (mesInicial <= 0) {
			for (int mes = mesAtual; mes <= 12; mes++) {
				double totalMes = Saida.totalSaidaMes(mes, anoPassado);
				saidasMes.put((mes + "/" + anoPassado), totalMes);
			}
			for (int mes = 1; mes <= mesAtual; mes++) {
				double totalMes = Saida.totalSaidaMes(mes, anoAtual);
				saidasMes.put((mes + "/" + anoAtual), totalMes);
			}
		} else {
			for (int mes = mesInicial; mes <= mesAtual; mes++) {
				double totalMes = Saida.totalSaidaMes(mes, anoAtual);
				saidasMes.put((mes + Integer.toString(anoAtual)), totalMes);
			}
		}

		return saidasMes;
	}

	public void atualizarSaida() {
		/* Inserir as informacoes no banco de dados */
		try (PreparedStatement ps = Conection.con.prepareStatement(
				"update tbSAIDA set valor = ?, datacriacao = ?, motivo = ?, observacoes = ?, id_usuariosaida = ? where id=?")) {
			/* Aqui vai ser inserindo pela indexacao das interrogacoes **/
			ps.setDouble(1, valor);
			ps.setTimestamp(2, new Timestamp(dataCriacao.getTime()));
			ps.setString(3, motivo);
			ps.setString(4, observacoes);
			ps.setInt(5, usuarioCriacao.getId());
			ps.setInt(6, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
