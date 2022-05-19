package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
	/*
	 * foi criado um pacote e definido uma classe para que seja realizada a conexao
	 * com o banco de dados
	 */

	public static Connection con;

	/* defini��o das variaveis estaticas */
	static final String URL = "jdbc:postgresql://localhost:5432/intech"; // incica o caminho do banco de dados
	static final String USER = "postgres"; // aqui vai o nome usuario que vc quer acessar
	static final String PASS = "123mudar"; // aqui a senha do seu banco

	/* fun��o para conectar */
	public static void conectar() {
		/*
		 * Com o try a gente captura o erro, que n�o seja possivel conectar com o banco
		 */
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conexao feita com sucesso! Very nice! :D");
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] arg) {
		conectar();
	}

}
