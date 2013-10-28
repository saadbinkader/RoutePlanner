package action;

import dao.UserDao;
import entity.User;
import helper.SessionTracker;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/13/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class ProfileAction {

    @EJB
    UserDao userDao;

    @Inject
    SessionTracker sessionTracker;

    private String password;
    private String newPassword;
    private String reTypedNewPassword;
    private String locationId;
    private String designation;

    private String newPasswordInfoMessage;
    private String oldPasswordErrorMessage;
    private String updateMessage;

    private User user;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReTypedNewPassword() {
        return reTypedNewPassword;
    }

    public void setReTypedNewPassword(String reTypedNewPassword) {
        this.reTypedNewPassword = reTypedNewPassword;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNewPasswordInfoMessage() {
        return newPasswordInfoMessage;
    }

    public void setNewPasswordInfoMessage(String newPasswordInfoMessage) {
        this.newPasswordInfoMessage = newPasswordInfoMessage;
    }

    public String getOldPasswordErrorMessage() {
        return oldPasswordErrorMessage;
    }

    public void setOldPasswordErrorMessage(String oldPasswordErrorMessage) {
        this.oldPasswordErrorMessage = oldPasswordErrorMessage;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String updateProfile() {

        setUpdateMessage("Updated Profile");

        if(!sessionTracker.getCurrentUser().getPassword().equals(password)) {
            oldPasswordErrorMessage = "Enter Correct Password";
            return "/profile";
        }

        user = sessionTracker.getCurrentUser();

        if(testPasswordUpdate())
            return "/profile";

        if(isSet(locationId)) {
            updateMessage += ", (LocationId)";
            user.setLocationId(Integer.parseInt(locationId));
        }

        if(isSet(designation)) {
            updateMessage += ", (Designation)";
            user.getUserProfile().setDesignationTitle(designation);
        }

        userDao.updateUser(user);

        return "/profile";
    }

    private boolean isSet(String targetAttribute) {
        return targetAttribute != null && !"".equals(targetAttribute);
    }

    private boolean hasValidLength(String targetAttribute) {
        return targetAttribute.length() > 2;
    }

    private boolean testPasswordUpdate() {

        if(isSet(newPassword)) {

            if(!newPassword.equals(reTypedNewPassword)) {
                newPasswordInfoMessage = "Passwords didn't match";
                return true;
            }

            if(!hasValidLength(newPassword)) {
                newPasswordInfoMessage = "Password Too Short";
                return true;
            }

            updateMessage += ", (Password)";
            user.setPassword(newPassword);
        }

        return false;
    }
}
