package entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/3/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "USER_PROFILES")
public class UserProfile {
    private String userName;
    private User user;
    private String firstName;
    private String lastName;
    private String email;
    private String designationTitle;

    @Id
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getDesignationTitle() {
        return designationTitle;
    }

    public void setDesignationTitle(String designationTitle) {
        this.designationTitle = designationTitle;
    }

    @Override
    public String toString() {
        return "[Class: UserProfile" +
                ", userName: " + userName +
                ", firstName: " + firstName +
                ", lastName: " + lastName +
                ", email: " + email +
                ", designationTitle: " + designationTitle + "]";

    }
}
