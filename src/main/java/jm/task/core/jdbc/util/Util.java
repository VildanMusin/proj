package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_myConnection";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";

    public static SessionFactory sessionFactory;


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

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Конфигурация Hibernate через код Java (без XML)
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                        .setProperty("hibernate.connection.url", DB_URL)
                        .setProperty("hibernate.connection.username", DB_USERNAME)
                        .setProperty("hibernate.connection.password", DB_PASSWORD)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                        .setProperty("hibernate.hbm2ddl.auto", "update")  // Автоматическое обновление схемы
                        .setProperty("hibernate.show_sql", "true")         // Показ SQL-запросов в консоли
                        .addAnnotatedClass(User.class);                   // Добавление аннотированного класса User

                // Создаем ServiceRegistry для SessionFactory
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}

