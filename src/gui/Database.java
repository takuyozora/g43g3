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
		//	statement.executeUpdate("create table programs (id integer primary key, name string, mur int, x int, y int)");
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
			int i = 1;
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
		    	  liste.add(rs.getInt("id")+". "+rs.getString("name")+" - "+mur);
		    	  i++;
		      }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return liste; 
	}
	public int getId(int row) {
		int[] tab = new int[getProgram().size()+2];
		ResultSet rs;
		
		int i = 1;
		try {
			rs = statement.executeQuery("select * from programs");
			while(rs.next())
		      {
				tab[i] = rs.getInt("id");
			//	System.out.println(i+" "+rs.getInt("id"));
				i++;
		      }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tab[row];
	}
	
	public void deleteProgram(int row) {
		try {
			
			statement.executeUpdate("delete from programs where id="+getId(row));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int[] getProgramById(int id) {
	//	ArrayList<String> liste = new ArrayList<String>();
		int[] tab = new int[3];
		ResultSet rs;
		try {
			rs = statement.executeQuery("select * from programs where id="+getId(id));
			while(rs.next())
		    {
				tab[0] = rs.getInt("x");
				tab[1] = rs.getInt("y");
				tab[2] = rs.getInt("mur");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return tab; 
	}
	
}
