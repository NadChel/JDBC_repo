package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceHibernateImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Сергей", "Золотарёв", (byte) 26);
//        User me = new User("Сергей", "Золотарёв", (byte) 26);
//        User mickey = new User("Микки", "Маус", (byte) 95);
//        User donald = new User("Дональд", "Дак", (byte) 89);
//        User scrooge = new User("Скрудж", "Макдак", (byte) 76);
//        userService.saveUsers(me, mickey, donald, scrooge);
        System.out.println(userService.getAllUsers());
    }
}
