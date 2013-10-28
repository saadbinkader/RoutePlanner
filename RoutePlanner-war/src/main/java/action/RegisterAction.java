package action;

import dao.UserDao;
import entity.User;
import entity.UserProfile;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class RegisterAction {

    private String userName;
    private String password;
    private String reTypedPassword;
    private String userType;
    private int locationID;

    private String firstName;
    private String lastName;
    private String email;
    private String designation;

    private String userNameErrorMessage;
    private String passwordErrorMessage;

    @EJB
    private UserDao userDao;

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

    public String getReTypedPassword() {
        return reTypedPassword;
    }

    public void setReTypedPassword(String reTypedPassword) {
        this.reTypedPassword = reTypedPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUserNameErrorMessage() {
        return userNameErrorMessage;
    }

    public void setUserNameErrorMessage(String userNameErrorMessage) {
        this.userNameErrorMessage = userNameErrorMessage;
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public void setPasswordErrorMessage(String passwordErrorMessage) {
        this.passwordErrorMessage = passwordErrorMessage;
    }

    public String register() {

        if(userDao.getUser(userName) != null) {
            userNameErrorMessage = "User Already Exists";
            return "/register";
        }

        if(!password.equals(reTypedPassword)) {
            passwordErrorMessage = "Passwords Didn't Match";
            return "/register";
        }

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setUserType(userType);
        user.setLocationId(locationID);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setUserName(userName);
        userProfile.setFirstName(firstName);
        userProfile.setLastName(lastName);
        userProfile.setEmail(email);
        userProfile.setDesignationTitle(designation);

        user.setUserProfile(userProfile);

        userDao.registerUser(user);

        return "/login?faces-redirect=true";
    }
}
