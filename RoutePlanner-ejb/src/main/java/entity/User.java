package entity;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 9/16/13
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table ( name = "USERS")
public class User {

    private String userName;
    private String password;
    private String userType;
    private UserProfile userProfile;
    private int locationId;

    @Id
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "[Class: User" +
                ", userName: " + userName +
                ", userType: " + userType +
                ", locationId: " + locationId + "]";

    }
}
