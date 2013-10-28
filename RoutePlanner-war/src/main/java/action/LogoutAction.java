package action;

import helper.SessionTracker;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/8/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class LogoutAction {

    @Inject
    SessionTracker sessionTracker;

    public String logout() {

        sessionTracker.setCurrentUser(null);

        return "/login?faces-redirect=true";

    }

}
