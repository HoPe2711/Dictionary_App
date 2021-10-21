package com.example.dictionary_ui.services.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.example.dictionary_ui.data.ConstantVariable.*;

public class SymAntWordService {

  public static String getSynonym(String wordRequest) {
    String result = "";
    try {
      Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
      PreparedStatement stmt = conn.prepareStatement("select * from t_dictionary_synonyms where word = ?");
      stmt.setString(1, wordRequest);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = rs.getString(3).replace(",","\n");
      }
      conn.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  public static String getAntonym(String wordRequest) {
    String result = "";
    try {
      Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
      PreparedStatement stmt = conn.prepareStatement("select * from t_dictionary_ant where word = ?");
      stmt.setString(1, wordRequest);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        result = rs.getString(3).replace(",","\n");
      }
      conn.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  public static Connection getConnection(String dbURL, String userName,
      String password) {
    Connection conn = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(dbURL, userName, password);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return conn;
  }
}
