package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String SQL = "DROP TABLE IF EXISTS user";
        try {
            transaction = session.beginTransaction();
            session.createNativeQuery(SQL).executeUpdate();
            SQL = "CREATE TABLE user (id bigint not null auto_increment primary key, name varchar(45), lastName varchar(45), age TINYINT UNSIGNED)";
            session.createNativeQuery(SQL).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String SQL = " DROP user";
        try {
            transaction = session.beginTransaction();
            session.createNativeQuery(SQL);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user = new User(name, lastName, age);

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("User with name " + name + " was saved to DB");
            System.out.println("User with name " + name + " has id = " + user.getId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    public void removeUserById(long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list;
        list = session.createQuery(" FROM User", User.class).list();
        return list;
    }

    public void cleanUsersTable() {
        System.out.println("Deleting records...");
        String HQL = "DELETE FROM User";
        session.createQuery(HQL).executeUpdate();
    }
}
