package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	Connection connection = null;
	Statement statement = null;
	
	public Database() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");	
	}
	
	public void connect() throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:src/gui/includes/database.db");
	    statement = connection.createStatement();
	    statement.setQueryTimeout(30);
	}
	
	public void setPositionLanceur(int x, int y) throws SQLException {
		statement.executeUpdate("insert into positionLanceur values("+x+", "+y+")");
		
		ResultSet rs = statement.executeQuery("select * from positionLanceur");
	      while(rs.next())
	      {
	        // read the result set
	    	System.out.println("x : " + rs.getInt("x"));
	        System.out.println("y : " + rs.getInt("y"));
	      }
	}
}
