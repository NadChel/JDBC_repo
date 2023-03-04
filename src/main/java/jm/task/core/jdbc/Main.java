package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Сергей", "Золотарёв", (byte) 26);
        userService.saveUser("Микки", "Маус", (byte) 95);
        userService.saveUser("Дональд", "Дак", (byte) 89);
        userService.saveUser("Скрудж", "Макдак", (byte) 76);
        System.out.println(userService.getAllUsers());
    }
}
