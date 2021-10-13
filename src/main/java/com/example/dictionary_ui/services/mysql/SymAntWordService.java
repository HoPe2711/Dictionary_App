package com.example.dictionary_ui.services.mysql;

import static com.example.dictionary_ui.data.ConstantVariable.DB_URL;
import static com.example.dictionary_ui.data.ConstantVariable.PASSWORD;
import static com.example.dictionary_ui.data.ConstantVariable.USER_NAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SymAntWordService {

  public static void main(String args[]) {
    try {
      Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from t_dictionary_ant where word = 'absent'");
      while (rs.next()) {
        System.out.println(rs.getInt(1) + "  " + rs.getString(2)
            + "  " + rs.getString(3));
      }
      conn.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static Connection getConnection(String dbURL, String userName,
      String password) {
    Connection conn = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(dbURL, userName, password);
      System.out.println("connect successfully!");
    } catch (Exception ex) {
      System.out.println("connect failure!");
      ex.printStackTrace();
    }
    return conn;
  }
}
