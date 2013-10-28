package helper;

import entity.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/13/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@SessionScoped
public class SessionTracker implements Serializable {

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public boolean isAdmin() {
        return "admin".equals(currentUser.getUserType());
    }

    public void hasAdminRole() throws IOException {
        if(currentUser == null || !"admin".equals(currentUser.getUserType())) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/home.xhtml");
        }
    }

    public void hasUserSession() throws IOException {
        if(currentUser != null) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/home.xhtml");
        }
    }
}
