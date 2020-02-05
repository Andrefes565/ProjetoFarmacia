package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	
public class ConexaoFactory {
	private static final String URL = "jdbc:mysql://localhost:3306/sistema";
	private static final String USUARIO = "root";
	private static final String SENHA = "123456"; 
	
	
	public static Connection conectar() throws SQLException, ClassNotFoundException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		//Class.forName("com.mysql.jdbc.Driver");
		return conexao;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		 
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conectado com Sucesso!");
		}catch(SQLException ex) {
			System.out.println("Conexão falhou!");
			ex.printStackTrace();
			
		}
	}
}
