package action;

import dao.UserDao;
import entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/13/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class UserRoleAction {

    private String userType;
    private String userName;

    @EJB
    UserDao userDao;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void updateRole() {
        User user = userDao.getUser(userName);
        user.setUserType(userType);
        userDao.updateUser(user);
    }
}
