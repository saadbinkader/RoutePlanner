package action;

import dao.UserDao;
import entity.User;
import helper.SessionTracker;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 9/16/13
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class LoginAction {

    private String userName;
    private String password;
    private String message;

    @EJB
    private UserDao userDao;

    @Inject
    SessionTracker sessionTracker;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String login() {

        User user = userDao.getUser(userName);

        if(user == null || !user.getPassword().equals(password)) {
            message = "Invalid User Name or Password";
            return "/login";
        }

        sessionTracker.setCurrentUser(user);
        return "/home?faces-redirect=true";
    }
}
