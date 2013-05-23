package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	Connection connection = null;
	Statement statement = null;
	
	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/gui/includes/database.db");
			statement = connection.createStatement();
		    statement.setQueryTimeout(30);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void logout() {
		 if(connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void setPositionLanceur(int x, int y) {
		try {
			statement.executeUpdate("drop table if exists positionLanceur");
			statement.executeUpdate("create table positionLanceur (x int, y int)");
			statement.executeUpdate("insert into positionLanceur values("+x+", "+y+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int[] getPositionLanceur() {
		int[] tab = new int[2];
		ResultSet rs;
		try {
			rs = statement.executeQuery("select * from positionLanceur");
			while(rs.next())
		      {
		    	  tab[0] = rs.getInt("x");
		    	  tab[1] = rs.getInt("y");
		      }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return tab; 
	}
}
