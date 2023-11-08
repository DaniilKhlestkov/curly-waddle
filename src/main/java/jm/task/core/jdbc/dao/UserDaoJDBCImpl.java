package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    private static String sql;

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)";
            statement.executeUpdate(sql);

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            sql = "DROP TABLE IF EXISTS users";

            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
        try (Connection connection1 = Util.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);

            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        sql = "SELECT * FROM users";

        try (Connection connection1 = Util.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection1 = Util.getConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            sql = "TRUNCATE TABLE users";
            statement.executeUpdate(sql);

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}