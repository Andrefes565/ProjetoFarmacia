package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {
	public void salvar(Fornecedores f) throws ClassNotFoundException, SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES(?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}

	public void excluir(Fornecedores f) throws ClassNotFoundException, SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();

	}

	public void editar(Fornecedores f) throws ClassNotFoundException, SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();

	}
	
	public Fornecedores buscarPorCodigo(Fornecedores f) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}
	
	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f) throws ClassNotFoundException, SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1,"%" + f.getDescricao() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);
		}
		return lista;
	}
	
	public ArrayList<Fornecedores> listar() throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
		}
		return lista;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		  Fornecedores f1 = new Fornecedores(); 
		  f1.setDescricao("Bruno");
		  Fornecedores f2 = new Fornecedores(); 
		  f2.setDescricao("margarida");
		 	   
		  FornecedoresDAO fdao = new FornecedoresDAO();
		  
		  try {
			  fdao.salvar(f1);
			  fdao.salvar(f2);
			  System.out.println("Salva com Sucesso!");
		  }catch(SQLException e) {
			  e.printStackTrace(); 
			  System.out.println("Erro ao Salvar!!"); 
		  }
		
		
		
		
		/*
		  Fornecedores f1 = new Fornecedores();
		  Fornecedores f2 = new Fornecedores(); 
		  Fornecedores f3 = new Fornecedores(); 
		  f1.setCodigo(6L);
		  f2.setCodigo(4L);
		  f3.setCodigo(5L);
		  FornecedoresDAO fdao = new FornecedoresDAO();
		  
		  try { 
			  fdao.excluir(f1);
			  fdao.excluir(f2);
			  fdao.excluir(f3);
			  System.out.println("Excluido com sucesso!"); 
		  }catch(SQLException e) {
			  e.printStackTrace();
			  System.out.println("Erro ao Excluir!!"); 
		  }
		 */
		/*
		Fornecedores f1 = new Fornecedores(); 
		f1.setCodigo(2L);
		f1.setDescricao("André Felipe");
		  
		FornecedoresDAO fdao = new FornecedoresDAO();
		  
		try { 
			fdao.editar(f1); System.out.println("Editado com sucesso!"); 
		}catch(SQLException e) { 
			e.printStackTrace();
			System.out.println("Erro ao Editar!!"); 
		}
		*/
		
		/*
		Fornecedores f1 = new Fornecedores(); 
		f1.setCodigo(4L);
		  
		Fornecedores f2 = new Fornecedores(); 
		f2.setCodigo(3L);
		  
		FornecedoresDAO fdao = new FornecedoresDAO();
		  
		try {
			Fornecedores novo1 = fdao.buscarPorCodigo(f1);
			Fornecedores novo2 = fdao.buscarPorCodigo(f2);
			System.out.println(novo1);
			System.out.println(novo2);
		}catch(SQLException e) {
			e.printStackTrace(); 
			System.out.println("Erro ao Salvar!!"); 
		}
		*/
		
		/*
		FornecedoresDAO fdao = new FornecedoresDAO();
		  
		try {
			ArrayList<Fornecedores> lista = fdao.listar();
			for(Fornecedores f: lista) {
				System.out.println("Resultado: " + f);
			}
			
		}catch(SQLException e) {
			e.printStackTrace(); 
			System.out.println("Erro ao Salvar!!"); 
		}
		*/
		/*
		Fornecedores f1 = new Fornecedores(); 
		f1.setDescricao("des");
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			ArrayList<Fornecedores> lista = fdao.buscarPorDescricao(f1);
			for(Fornecedores f: lista) {
				System.out.println("Resultado: " + f);
			}
			
		}catch(SQLException e) {
			e.printStackTrace(); 
			System.out.println("Erro ao buscar!!"); 
		}
		*/
		
	}
}
