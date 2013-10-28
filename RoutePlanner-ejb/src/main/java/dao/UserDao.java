package dao;

import entity.User;
import entity.UserProfile;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 9/16/13
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */

@Local
public interface UserDao {

    public User getUser(String userName);

    public void registerUser(User user);

    public void updateUser(User user);

    public List<User> getUsers();

}
