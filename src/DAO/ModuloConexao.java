package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloConexao {

	public static Connection conector() {
		java.sql.Connection conexao = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost/banco";
		String user = "root";
		String password = "mysqlroot";
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
		    return conexao;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
} 