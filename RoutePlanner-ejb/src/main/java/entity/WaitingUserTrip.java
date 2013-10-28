package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/9/13
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "WAITING_USER_TRIP")
public class WaitingUserTrip {
    private int id;
    private int tripId;
    private String userName;

    @Id @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
