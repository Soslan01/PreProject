package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() { }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createSQLQuery("CREATE TABLE Users " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT," +
                    " name VARCHAR(64)," +
                    " lastName VARCHAR(64)," +
                    " age INT);").executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            // Начинаем транзакцию
            transaction = session.beginTransaction();

            // Удаляем таблицу User(ов), если она существует
            session.createSQLQuery("DROP TABLE Users").executeUpdate();

            // Фиксируем изменения
            transaction.commit();
        } catch (Exception e) {
            // В случае ошибки откатываем транзакцию
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            //save the user object;
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            // Начинаем транзакцию
            transaction = session.beginTransaction();

            // Очищаем содержимое таблицы User(ов)
            session.createQuery("DELETE FROM User").executeUpdate();

            // Фиксируем изменения
            transaction.commit();
        } catch (Exception e) {
            // В случае ошибки откатываем транзакцию
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
