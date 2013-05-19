package gui.includes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{
  public static void main(String[] args) throws ClassNotFoundException
  {
	// Chargement du driver JDBC
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try
    {
      // Connexion � la base de donn�es des programmes
      connection = DriverManager.getConnection("jdbc:sqlite:src/gui/includes/programs.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // timeout au bout de 30 secondes.

   //   statement.executeUpdate("drop table if exists programs");
   //   statement.executeUpdate("create table programs (id int, name string, type integer, mur1_x float(3), mur1_y float(3))");
   //   statement.executeUpdate("insert into programs values(1, 'Programme 1', 1, 0.054, 1.345)");
   //   statement.executeUpdate("insert into programs values(2, 'Programme 2', 2, 1.054, 0.345)");
      ResultSet rs = statement.executeQuery("select * from programs");
      while(rs.next())
      {
        // read the result set
    	System.out.println("id = " + rs.getInt("id"));
        System.out.println("name = " + rs.getString("name"));
        System.out.println("type = " + rs.getString("type"));
        System.out.println("X = " + rs.getString("mur1_x"));
        System.out.println("Y = " + rs.getString("mur1_y"));
      }
    }
    catch(SQLException e)
    {
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        System.err.println(e);
      }
    }
  }
}