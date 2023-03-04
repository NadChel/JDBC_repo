package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS users_db.users (
                      id int NOT NULL AUTO_INCREMENT,
                      name varchar(15),
                      last_name varchar(25),
                      age tinyint,
                      PRIMARY KEY (id)
                    );""");
            System.out.println("Таблица Users была создана или уже существовала");
        } catch (SQLException e) {
            System.err.println("An SQLException was thrown: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS users_db.users;");
            System.out.println("Таблица Users была удалена или её не существовало");
        } catch (SQLException e) {
            System.err.println("An SQLException was thrown: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format
                    ("INSERT INTO users_db.users (name, last_name, age) VALUES('%s','%s',%d);", name, lastName, age));
            System.out.printf("User %s %s добавлен в базу данных\n", name, lastName);
        } catch (SQLException e) {
            System.err.println("An SQLException was thrown: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(String.format
                    ("DELETE FROM users_db.users WHERE id = %d;", id));
        } catch (SQLException e) {
            System.err.println("An SQLException was thrown: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
                ResultSet rs = connection.prepareStatement("SELECT * FROM users_db.users").executeQuery()) {
            while(rs.next()) {
                User user;
                userList.add(user = new User(rs.getString("name"), rs.getString("last_name"),
                        rs.getByte("age")));
                user.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void clearUsersTable() {
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE users_db.users");
        } catch (SQLException e) {
            System.err.println("An SQLException was thrown: " + e.getMessage());
        }
    }
}
