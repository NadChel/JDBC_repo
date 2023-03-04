package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users_db", "pp_user",
                    "pp_user");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }
}
