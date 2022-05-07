package usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.Conection;

public class Usuario {
	private int id;
	private String nome;
	private String CPF;
	private String Senha;
	private boolean proprietario;

	public static List<Usuario> getUsuarios() {
		try (Statement stm = Conection.con.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM tbUSUARIO")) {
			/* criamos uma lista para inserir informações de login da banco dados */
			List<Usuario> Usuarios = new ArrayList<>();
			while (rs.next()) {
				Usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("CPF"),
						rs.getString("senha"), rs.getBoolean("proprietario")));
			}
			return Usuarios;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};

	public static Usuario usuarioLogado;
	private static int idCont = 0;

	public int getId() {
		return id;
	}

	private void setId() {
		Usuario.idCont++;
		this.id = idCont;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public boolean isProprietario() {
		return proprietario;
	}

	public void setProprietario(boolean proprietario) {
		this.proprietario = proprietario;
	}

	public String isProprietarioSimNao() {
		if (isProprietario()) {
			return "Sim";
		} else {
			return "Nao";
		}
	}

	public Usuario(int ID, String nome, String CPF, String senha, boolean proprietario) {
		super();
		this.nome = nome;
		this.CPF = CPF;
		this.Senha = senha;
		this.proprietario = proprietario;
		this.id = ID;
	}

	public static Usuario cadastraUsuarioInterface(String nome, String CPF, String senha, boolean proprietario) {
		/* Inserir as informações no banco de dados */
		try (PreparedStatement ps = Conection.con.prepareStatement(
				"insert into tbUSUARIO(nome, CPF, senha, proprietario) values" + "(?, ?, ?, ?) returning *")) {
			/* Aqui vai ser inserindo pela indexação das interrogações **/
			ps.setString(1, nome);
			ps.setString(2, CPF.replaceAll("[^\\d]+", ""));
			ps.setString(3, senha);
			ps.setBoolean(4, proprietario);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next()
						? new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("CPF"), rs.getString("senha"),
								rs.getBoolean("proprietario"))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deletaUsuario() {
		try (PreparedStatement ps = Conection.con.prepareStatement("delete from tbUSUARIO where id=?")) {
			/* Aqui vai ser delete pela indexação das interrogações **/
			ps.setInt(1, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Usuario login(String CPF, String senha) {

		try (PreparedStatement ps = Conection.con
				.prepareStatement("select * from tbUSUARIO where cpf = ? and senha = ?")) {
			/* Aqui vai ser feito o login */
			ps.setString(1, CPF.replaceAll("[^\\d]+", ""));
			ps.setString(2, senha);
			try (ResultSet rs = ps.executeQuery()) {
				/* rs.next ele verifica se vai ter um valor proximo */
				return rs.next()
						? new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("CPF"), rs.getString("senha"),
								rs.getBoolean("proprietario"))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Usuario getUsuario(String CPF) {
		try (PreparedStatement ps = Conection.con
				.prepareStatement("select * from tbUSUARIO where cpf = ?")) {
			/* Aqui vai ser feito o login */
			ps.setString(1, CPF.replaceAll("[^\\d]+", ""));
			try (ResultSet rs = ps.executeQuery()) {
				/* rs.next ele verifica se vai ter um valor proximo */
				return rs.next()
						? new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("CPF"), rs.getString("senha"),
								rs.getBoolean("proprietario"))
						: null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void atualizarUsuario() {
		/* Inserir as informações no banco de dados */
		try (PreparedStatement ps = Conection.con.prepareStatement(
				"update tbUSUARIO set nome = ?, CPF = ? , senha = ? , proprietario = ? where id=?" )) {
			/* Aqui vai ser inserindo pela indexação das interrogações **/
			ps.setString(1, nome);
			// Ignorando a formatação do CPF
			ps.setString(2, CPF.replaceAll("[^\\d]+", ""));
			ps.setString(3, Senha);
			ps.setBoolean(4, proprietario);
			ps.setInt(5, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
