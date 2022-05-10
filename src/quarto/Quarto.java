package quarto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.Conection;
import usuario.Usuario;

public class Quarto {
	private int id, numero, capacidade;
	private String descricao;
	private boolean ocupado;
	private Usuario usuarioCriacao;

	public static List<Quarto> getQuartos() {
		try (Statement stm = Conection.con.createStatement();
				ResultSet rs = stm.executeQuery("select * from tbQUARTO")) {
			List<Quarto> Quartos = new ArrayList<>();
			while (rs.next()) {
				Quartos.add(new Quarto(rs.getInt("id"), rs.getInt("numero"), rs.getInt("capacidade"),
						rs.getString("descricao"), rs.getBoolean("ocupado"),
						Usuario.getUsuario(rs.getInt("id_usuario"))));
			}
			return Quartos;
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};

	public static ArrayList<Quarto> quartos = new ArrayList<>();

	public int getId() {
		return id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public String isOcupadoSimNao() {
		if (this.isOcupado()) {
			return "Sim";
		} else {
			return "Nao";
		}
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

	public Quarto(int id, int numero, int capacidade, String descricao, boolean ocupado, Usuario usuarioCriacao) {
		this.id = id;
		this.numero = numero;
		this.capacidade = capacidade;
		this.descricao = descricao;
		this.ocupado = ocupado;
		if (usuarioCriacao != null) {
			this.usuarioCriacao = usuarioCriacao;
		} else {
			this.usuarioCriacao = Usuario.usuarioLogado;
		}

		quartos.add(this);
	}

	public static Quarto cadastraQuartoInterface(int numero, int capacidade, String descricao) {
		try (PreparedStatement ps = Conection.con
				.prepareStatement("insert into tbQUARTO(numero, capacidade, descricao, id_usuario) values"
						+ "(?, ?, ?, ?) returning *")) {
			int idUsuario = Usuario.usuarioLogado.getId();

			ps.setInt(1, numero);
			ps.setInt(2, capacidade);
			ps.setString(3, descricao);
			ps.setInt(4, idUsuario);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next()
						? new Quarto(rs.getInt("id"), rs.getInt("numero"), rs.getInt("capacidade"),
								rs.getString("descricao"), rs.getBoolean("ocupado"),
								Usuario.getUsuario(rs.getString("id_usuario")))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Quarto getQuarto(int id) {
		try (PreparedStatement ps = Conection.con.prepareStatement("select * from tbQUARTO where id = ?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next()
						? new Quarto(rs.getInt("id"), rs.getInt("numero"), rs.getInt("capacidade"),
								rs.getString("descricao"), rs.getBoolean("ocupado"),
								Usuario.getUsuario(rs.getString("id_usuario")))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Criada pois na listagem n�o existe id,
	// sendo assim precisamos pegar pelo n�mero
	public static Quarto getQuarto(String numero) {
		try (PreparedStatement ps = Conection.con.prepareStatement("select * from tbQUARTO where numero = ?")) {
			int numeroInt = Integer.parseInt(numero);

			ps.setInt(1, numeroInt);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next()
						? new Quarto(rs.getInt("id"), rs.getInt("numero"), rs.getInt("capacidade"),
								rs.getString("descricao"), rs.getBoolean("ocupado"),
								Usuario.getUsuario(rs.getString("id_usuario")))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Quarto getQuartoDisponivel(int numero) {
		for (Quarto quarto : quartos) {
			if (quarto.numero == numero && quarto.isOcupado() == false) {
				return quarto;
			}
		}
		return null;
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
		try (PreparedStatement ps = Conection.con.prepareStatement("delete from tbQUARTO where id=?")) {
			ps.setInt(1, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizarQuarto() {
		try (PreparedStatement ps = Conection.con.prepareStatement(
				"update tbQUARTO set numero = ?, capacidade = ? , descricao = ? , ocupado = ? where id=?")) {
			ps.setInt(1, numero);
			ps.setInt(2, capacidade);
			ps.setString(3, descricao);
			ps.setBoolean(4, ocupado);
			ps.setInt(5, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
