package br.com.tills.dungeonsdragons.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao {
	
	
	public static Connection conecta() throws SQLException {
		
//		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm93042", "251088");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm93399", "190494");
//		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm93915", "060696");
//		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm92920", "210497");
		return con;
		
		
	}
	
	
	
	

}
