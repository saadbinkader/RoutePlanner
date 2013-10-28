package dao;

import entity.Trip;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 6:39 PM
 * To change this template use File | Settings | File Templates.
 */

@Local
public interface TripDao {

    public void addTrip(Trip trip);

    public void updateTrip(Trip trip);

    public List<Trip> getTrips();

    public List<Trip> getTripsByRoute(String route);

    public Trip getTripById(int tripId);

    public List<String> getWayPointsById(int tripId);

    public List<String> getPassengerListByTripId(int tripId);

    public List<String> getWaitingPassengerListByTripId(int tripId);

}
