package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        List<User> list;

        userService.createUsersTable();
        userService.saveUser("Amina", "Bakhtiyar", (byte) 10);
        userService.saveUser("Alinur", "Bakhtiyaruly", (byte) 5);
        userService.saveUser("Elnur", "Bakhtiyaruly", (byte) 1);
        userService.saveUser("Anara", "Umbetbayeva", (byte) 59);
        list = userService.getAllUsers();
        for (User user : list)
            System.out.println(user.toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}
