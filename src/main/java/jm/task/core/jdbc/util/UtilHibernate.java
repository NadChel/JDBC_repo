package jm.task.core.jdbc.util;

import java.util.Properties;

public class UtilHibernate {
    private static final Properties props = new Properties();

    public static Properties getProps() {
        String[][] properties = {
                {"hibernate.connection.url", "jdbc:mysql://localhost:3306/users_db"},
                {"dialect", "org.hibernate.dialect.MySQLDialect"},
                {"hibernate.connection.username", "pp_user"},
                {"hibernate.connection.password", "pp_user"},
                {"hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver"},
                };
        for (String[] property : properties) {
            props.setProperty(property[0], property[1]);
        }
        return props;
    }
    /*
     Properties prop= new Properties();

    prop.setProperty("hibernate.connection.url", "jdbc:mysql://<your-host>:<your-port>/<your-dbname>");

    //You can use any database you want, I had it configured for Postgres
    prop.setProperty("dialect", "org.hibernate.dialect.PostgresSQL");

    prop.setProperty("hibernate.connection.username", "<your-user>");
    prop.setProperty("hibernate.connection.password", "<your-password>");
    prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
    */

}
