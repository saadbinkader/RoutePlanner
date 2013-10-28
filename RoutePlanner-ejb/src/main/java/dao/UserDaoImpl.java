package dao;

import entity.User;
import entity.UserProfile;
import util.DataAccessLogger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 9/16/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    DataAccessLogger dataAccessLogger;

    @Override
    public User getUser(String userName) {
        User user;

        String queryParameter = "SELECT u FROM User u WHERE u.userName = :userName";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, User.class);
            query.setParameter("userName", userName);
            user = (User) query.getSingleResult();

        } catch (NoResultException ex) {

            user = null;

            dataAccessLogger.getLogger().error("Failed To Fetch User By userName[" + userName + "]");
        }
        return user;
    }

    @Override
    public void registerUser(User user) {

        entityManager.persist(user);

        dataAccessLogger.getLogger().info("Added - " + user);
    }

    @Override
    public void updateUser(User user) {

        entityManager.merge(user);

        dataAccessLogger.getLogger().info("Updated - " + user);
    }

    @Override
    public List<User> getUsers() {
        List<User> users;

        String queryParameter = "SELECT u FROM User u ORDER BY u.userName ASC";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter,User.class);
            users = query.getResultList();

        } catch (NoResultException ex) {

            users = new ArrayList<User>();

            dataAccessLogger.getLogger().error("Failed To Fetch Uses");
        }

        return users;
    }
}