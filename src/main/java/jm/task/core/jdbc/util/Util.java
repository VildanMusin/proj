package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String DB_USERNAME = "bestuser";
    private static final String DB_PASSWORD = "bestuser";
     public static Connection getConnection() {
         Connection connection =null;
         try {
             Class.forName(DB_DRIVER);
             connection= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection ok!");
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
             System.out.println("Connection ERROR!");
         }
         return connection;
     }

}

