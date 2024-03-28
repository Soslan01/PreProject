package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        UserService userService = new UserServiceImpl();

        // реализуйте алгоритм здесь
        //userService.createUsersTable();

//        userService.saveUser("Лирой", "Джексон", (byte) 45);
//        userService.saveUser("Генри", "Кавилл", (byte) 35);
//        userService.saveUser("Доннальд", "Макдональд", (byte) 25);
//        userService.saveUser("Бред", "Пит", (byte) 15);

        //userService.removeUserById(1);

        //userService.getAllUsers();

        //userService.cleanUsersTable();

        //userService.dropUsersTable();


    }
}
