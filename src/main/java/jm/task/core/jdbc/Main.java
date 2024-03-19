package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public class Main {

    private final static UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) throws ClassNotFoundException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = Util.getInstance().getConnection();
            if (connection != null) {
                System.out.println("Соединение установлено!");
            } else {
                System.out.println("Не удалось установить соединение!");
            }
        // реализуйте алгоритм здесь
//        userService.createUsersTable();
//
        //userService.saveUser("Лирой", "Джексон", (byte) 45);
//        userService.saveUser("Генри", "Кавилл", (byte) 35);
//        userService.saveUser("Доннальд", "Макдональд", (byte) 25);
//        userService.saveUser("Бред", "Пит", (byte) 15);

        //userService.removeUserById(5);

        //userService.getAllUsers();

        userService.cleanUsersTable();

        //userService.dropUsersTable();


    }
}
