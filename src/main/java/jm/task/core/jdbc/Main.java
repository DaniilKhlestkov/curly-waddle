package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Двниил","Великий",(byte) 22);
        userDao.saveUser("Артём","Быстрый",(byte) 18);
        userDao.saveUser("Михаил","Гордый",(byte) 31);
        userDao.saveUser("Конор","Грозный",(byte) 27);

        userDao.createUsersTable();
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();


    }
}