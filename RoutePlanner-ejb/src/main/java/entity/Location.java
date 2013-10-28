package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/3/13
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "LOCATIONS")
public class Location {
    private int locationId;
    private String locationAddress;
    private String route;

    @Id @GeneratedValue
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "[Class: Location" +
                ", locationId: " + locationId +
                ", locationAddress: " + locationAddress +
                ", route: " + route + "]";

    }
}
