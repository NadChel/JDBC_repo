package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.UtilHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(User.class)
            .addProperties(UtilHibernate.getProps())
            .buildSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("""
                    CREATE TABLE IF NOT EXISTS users_db.users (
                      id int NOT NULL AUTO_INCREMENT,
                      name varchar(15),
                      last_name varchar(25),
                      age tinyint,
                      PRIMARY KEY (id)
                    );""").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users_db.users;").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
    }

    public void saveUsers(User... users) {
     Session session = sessionFactory.openSession();
     session.beginTransaction();
     for (User user : users) {
         session.save(user);
     }
    int length = users.length;
    if (length == 0) {
        System.out.println("Экземпляров класса User не предоставлено, таблица осталась без изменений\n");
    } else {
        System.out.printf("%d %s класса User %s добавлено в таблицу\n",
                length, (length == 1 ? "экземпляр" : length < 5 ? "экземпляра" : "экземпляров"),
                (length == 1 ? "был" : "было"));
    }
     session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery(String.format
                ("DELETE FROM users_db.users WHERE id = %d;", id));
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> userList = new ArrayList<>();
        Query query = session.createSQLQuery("SELECT * FROM users_db.users");
        for (Object o : query.getResultList()) {
            userList.add((User) o);
        }
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void clearUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE users_db.users");
        session.getTransaction().commit();
    }
}
