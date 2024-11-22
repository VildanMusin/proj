package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        System.out.println( Util.getConnection());
        UserService userService = new UserServiceImpl();


        userService.createUsersTable();


          userService.saveUser("Ivan", "Ivanov", (byte) 25);
         userService.saveUser("Petr", "Petrov", (byte) 30);
         userService.saveUser("Anna", "Orlova", (byte) 20);
         userService.saveUser("Olga", "Smirnova", (byte) 35);


        System.out.println("Все пользователи в базе данных:");

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
    }




