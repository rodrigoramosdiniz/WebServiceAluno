/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alunows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class ConnectionFactory {
  private static String url = "jdbc:mysql://localhost:3306/loja_db?useTimezone=true&serverTimezone=UTC";
  private static String user = "root";
  private static String password ="";        
    
  public static Connection getConnection() throws SQLException, ClassNotFoundException{
      Class.forName("com.mysql.cj.jdbc.Driver");
      return DriverManager.getConnection(url, user, password);
  }  
}
