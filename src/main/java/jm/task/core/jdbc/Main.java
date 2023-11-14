package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Двниил","Великий",(byte) 22);
        userService.saveUser("Артём","Быстрый",(byte) 18);
        userService.saveUser("Михаил","Гордый",(byte) 31);
        userService.saveUser("Конор","Грозный",(byte) 27);


        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}