package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_myConnection";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
     public static Connection getConnection() {
         Connection connection =null;
         try {
             connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             System.out.println("Connection ok!");
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.println("Connection ERROR!");
         }
         return connection;
     }

}

