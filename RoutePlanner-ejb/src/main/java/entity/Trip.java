package entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "TRIPS")
public class Trip {
    private int tripId;
    private int transportId;
    private String route;
    private Timestamp startTimestamp;
    private long duration;
    private String status;
    private String transportLocationAddress;

    private List<String> passengerList = new ArrayList<String>();
    private List<String> locationAddresses = new ArrayList<String>();
    private List<String> passengerWaitingList = new ArrayList<String>();

    @Id @GeneratedValue
    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportId) {
        this.transportId = transportId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransportLocationAddress() {
        return transportLocationAddress;
    }

    public void setTransportLocationAddress(String transportLocationAddress) {
        this.transportLocationAddress = transportLocationAddress;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="USER_TRIP", joinColumns=@JoinColumn(name="tripId"))
    @Column(name="userName")
    public List<String> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<String> userNames) {
        this.passengerList = userNames;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="WAITING_USER_TRIP", joinColumns=@JoinColumn(name="tripId"))
    @Column(name="userName")
    public List<String> getPassengerWaitingList() {
        return passengerWaitingList;
    }

    public void setPassengerWaitingList(List<String> passengerWaitingList) {
        this.passengerWaitingList = passengerWaitingList;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="LOCATION_TRIP", joinColumns=@JoinColumn(name="tripId"))
    @Column(name="locationAddress")
    public List<String> getLocationAddresses() {
        return locationAddresses;
    }

    public void setLocationAddresses(List<String> locationAddresses) {
        this.locationAddresses = locationAddresses;
    }

    @Override
    public String toString() {
        return "[Class: Trip" +
                ", tripId: " + tripId +
                ", transportId: " + transportId +
                ", route: " + route +
                ", startTimeStamp: " + startTimestamp + "]";

    }
}
