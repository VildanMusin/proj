package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


 public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    public UserDaoJDBCImpl() {
       connection = Util.getConnection();
    }

    public void createUsersTable() {
       String sql = "CREATE TABLE IF NOT EXISTS \"User\" (" +
               "id SERIAL PRIMARY KEY," +
               "name VARCHAR(100)," +
               "lastname VARCHAR(100)," +
               "age INT" +
               ")";

       try (Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {

          statement.executeUpdate(sql);
          System.out.println("Таблица 'User' успешно создана!");

       } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Ошибка при создании таблицы!");
       }
    }

    public void dropUsersTable() {
       String sql = "DROP TABLE IF EXISTS \"User\"";

       try (Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {

          statement.executeUpdate(sql);
          System.out.println("Таблица 'User' удалена!");

       } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Ошибка при удалении таблицы!");
       }
    }

    public void saveUser(String name, String lastname, byte age) {

       String sql = "INSERT INTO \"User\" (name, lastname, age) VALUES (?, ?, ?)";

       try (Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

          preparedStatement.setString(1, name);
          preparedStatement.setString(2, lastname);
          preparedStatement.setByte(3, age);

          preparedStatement.executeUpdate();
          System.out.println("Пользователь добавлен: " + name + " " + lastname);

       } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Ошибка при добавлении пользователя!");
       }
    }

    public void removeUserById(long id) {
       String sql = "DELETE FROM \"User\" WHERE id = ?";

       try (Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

          preparedStatement.setLong(1, id);
          preparedStatement.executeUpdate();
          System.out.println("Пользователь с id = " + id + " удалён!");

       } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Ошибка при удалении пользователя!");
       }
    }

    public List<User> getAllUsers() {

       List<User> users = new ArrayList<>();
       String sql = "SELECT * FROM \"User\"";

       try (Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

          while (resultSet.next()) {
             // Извлечение данных из ResultSet
             long id = resultSet.getLong("id");
             String name = resultSet.getString("name");
             String lastName = resultSet.getString("lastname");
             byte age = resultSet.getByte("age");

             // Проверка значений
             System.out.println("id: " + id + ", name: " + name + ", lastName: " + lastName + ", age: " + age);


          }

       } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Ошибка при получении пользователей!");
       }

       return users;
            }

    public void cleanUsersTable() {
       String sql = "DELETE FROM \"User\"";

       try (Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

          preparedStatement.executeUpdate();
          System.out.println("Таблица 'User' очищена!");

       } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Ошибка при очистке таблицы!");
       }
    }

}


