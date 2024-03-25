package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    //Создание таблицы;
    @Override
    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE Users\n" +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, \n" +
                    "name VARCHAR(64), \n" +
                    "lastName VARCHAR(64), \n" +
                    "age INT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Удаление таблицы;
    @Override
    public void dropUsersTable() {
        try {
            try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
                statement.executeUpdate("DROP TABLE IF EXISTS Users");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Добавление user;
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            try (Connection connection = Util.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Users (name, lastName, age ) VALUES (?, ?, ?)")) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Удаление из таблицы по id;
    @Override
    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Получение всех users;
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        //executeQuery - Возвращает объект ResultSet, который содержит результаты запроса.
        try (Connection connection = Util.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Users")) {
            while(resultSet.next()) {
                User user = new User (resultSet.getString("name"),
                                      resultSet.getString("lastName"),
                                      resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    //Очистить содержимое таблицы;
    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
