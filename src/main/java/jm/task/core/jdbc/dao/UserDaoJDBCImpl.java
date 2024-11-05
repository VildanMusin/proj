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
       String sql = "CREATE TABLE IF NOT EXISTS user (id BIGINT AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age TINYINT, PRIMARY KEY (id))";
       try (Statement statement = connection.createStatement()) {
          statement.executeUpdate(sql);
          System.out.println("Таблица создана.");
       } catch (SQLException e) {
          System.out.println("Ошибка создания таблицы: " + e.getMessage());
       }
    }

    public void dropUsersTable() {
       String sql = "DROP TABLE IF EXISTS user";
       try (Statement statement = connection.createStatement()) {
          statement.executeUpdate(sql);
          System.out.println("Таблица удалена.");
       } catch (SQLException e) {
          System.out.println("Ошибка удаления таблицы: " + e.getMessage());
       }
    }

    public void saveUser(String name, String lastname, byte age) {
       String sql = "INSERT INTO `my-project`.user (name, lastName, age) VALUES (?, ?, ?)";
       try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
          preparedStatement.setString(1, name);
          preparedStatement.setString(2, lastname);
          preparedStatement.setByte(3, age);
          preparedStatement.executeUpdate();
          System.out.println("Пользователь добавлен: " + name + " " + lastname);
       } catch (SQLException e) {
          System.out.println("Ошибка добавления пользователя: " + e.getMessage());
       }
    }

    public void removeUserById(long id) {
       String sql = "DELETE FROM user WHERE id = ?";
       try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
          preparedStatement.setLong(1, id);
          int rowsDeleted = preparedStatement.executeUpdate();
          if (rowsDeleted > 0) {
             System.out.println("Пользователь с ID " + id + " удален.");
          } else {
             System.out.println("Пользователь с таким ID не найден.");
          }
       } catch (SQLException e) {
          System.out.println("Ошибка удаления пользователя: " + e.getMessage());
       }
    }

    public List<User> getAllUsers() {

       List<User> users = new ArrayList<>();
       String sql = "SELECT * FROM user";
       try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
          while (resultSet.next()) {
             User user = new User();
             user.setId(resultSet.getLong("id"));
             user.setName(resultSet.getString("name"));
             user.setLastName(resultSet.getString("lastName"));
             user.setAge(resultSet.getByte("age"));
             users.add(user);
          }
          System.out.println("Список пользователей получен.");
       } catch (SQLException e) {
          System.out.println("Ошибка получения пользователей: " + e.getMessage());
       }
       return users;
            }

    public void cleanUsersTable() {
       String sql = "TRUNCATE TABLE user";
       try (Statement statement = connection.createStatement()) {
          statement.executeUpdate(sql);
          System.out.println("Таблица очищена.");
       } catch (SQLException e) {
          System.out.println("Ошибка очистки таблицы: " + e.getMessage());
       }
    }

}


