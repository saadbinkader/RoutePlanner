package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/9/13
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "LOCATION_TRIP")
public class LocationTrip {
    private int id;
    private int tripId;
    private String locationAddress;

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

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
}
