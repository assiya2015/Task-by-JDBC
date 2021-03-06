package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static long id = 0;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {
            System.out.println("Creating table in selected database...");
            String SQL = "DROP TABLE IF EXISTS user";
            statement.executeUpdate(SQL);
            SQL = "CREATE TABLE user (id bigint not null auto_increment primary key, name varchar(45), lastName varchar(45), age TINYINT UNSIGNED)";
            statement.executeUpdate(SQL);
            System.out.println("Table successfully created...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {
            System.out.println("Dropping users table...");
            String SQL = "DROP TABLE user";
            statement.executeUpdate(SQL);
            System.out.println("Table was dropped");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO user (name, lastName, age) VALUES(?, ?, ?)";
        try (Connection connection = new Util().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User with name " + name + " was saved to DB");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        System.out.println("Removing user with id = " + id);
        String SQL = "DELETE FROM user WHERE id=?";
        try (
                Connection connection = new Util().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        System.out.println("Getting records...");
        String SQL = "SELECT * FROM user";
        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastName, age);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        System.out.println("Deleting records...");
        String SQL = "DELETE FROM user";
        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
            System.out.println("Table was cleaned");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
