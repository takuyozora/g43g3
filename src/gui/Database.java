package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

	Connection connection = null;
	static Statement statement = null;
	
	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:src/gui/database.db");
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
	
	public void addProgram(String name, int mur, int x, int y) {
		try {
		//	statement.executeUpdate("drop table if exists programs");
		//	statement.executeUpdate("create table programs (id int primary key, name string, mur int, x int, y int)");
			statement.executeUpdate("insert into programs values(null, '"+name+"',"+mur+", "+x+", "+y+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getProgram() {
		ArrayList<String> liste = new ArrayList<String>();
		String mur = "";
		ResultSet rs;
		try {
			rs = statement.executeQuery("select * from programs");
			while(rs.next())
		      {
				  if(rs.getInt("mur") == 10) {
					  mur = "mur nord";
				  }
				  else if(rs.getInt("mur") == 11) {
					  mur = "mur sud";
				  }
				  else if(rs.getInt("mur") == 12) {
					  mur = "mur est";
				  }
				  if(rs.getInt("mur") == 13) {
					  mur = "mur ouest";
				  }
		    	  liste.add(rs.getString("name")+" - "+mur);
		      }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return liste; 
	}
	
}
