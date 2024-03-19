package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connection = Util.getInstance().getConnection();
    public UserDaoJDBCImpl() {

    }

    //Создание таблицы;
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
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
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Добавление user;
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Users (name, lastName, age ) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Удаление из таблицы по id;
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Получение всех users;
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        //executeQuery - Возвращает объект ResultSet, который содержит результаты запроса.
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Users")) {
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
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
