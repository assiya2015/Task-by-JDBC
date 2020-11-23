package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory factory = null;
        try {
            factory = new Util().getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            String SQL = "DROP TABLE IF EXISTS user";
            System.out.println("Creating table in selected database...");
            session.createQuery(SQL).executeUpdate();
            session.getTransaction().commit();
            SQL = "CREATE TABLE user (id bigint not null auto_increment primary key, name varchar(45), lastName varchar(45), age TINYINT UNSIGNED)";
            session.createQuery(SQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table successfully created...");
        } finally {
            if (factory != null){
                factory.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory factory = null;
        try {
            factory = new Util().getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            System.out.println("Dropping users table...");
            String SQL = "DROP TABLE user";
            session.createQuery(SQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table was dropped");
        } finally {
            if (factory != null){
                factory.close();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO user (id, name, lastName, age) VALUES(?, ?, ?, ?)";

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
