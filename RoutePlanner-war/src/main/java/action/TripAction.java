package action;

import dao.LocationDao;
import dao.TransportDao;
import dao.TripDao;
import dao.UserDao;
import entity.Transport;
import entity.Trip;
import entity.User;
import helper.SessionTracker;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/8/13
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@SessionScoped
public class TripAction implements Serializable {

    private int tripId;
    private int transportId;

    @EJB
    private TripDao tripDao;
    @EJB
    private UserDao userDao;
    @EJB
    private LocationDao locationDao;
    @EJB
    private TransportDao transportDao;

    @Inject
    SessionTracker sessionTracker;

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

    public List<Trip> getTrips() {
        return getTripsByRole();
    }

    private List<Trip> getTripsByRole() {

        if(!sessionTracker.isLoggedIn())
            return new ArrayList<Trip>();

        else if("passenger".equals(sessionTracker.getCurrentUser().getUserType()))  {
            int userLocationId = sessionTracker.getCurrentUser().getLocationId();
            String userTripRoute = locationDao.getLocationById(userLocationId).getRoute();
            return tripDao.getTripsByRoute(userTripRoute);
        }

        else {
            return tripDao.getTrips();
        }
    }

    public List<String> getWayPoints() {
        return tripDao.getWayPointsById(tripId);
    }

    public List<String> getPassengerInfo() {

        List<String> passengers = tripDao.getPassengerListByTripId(tripId);
        List<String> passengerInfo = new ArrayList<String>();

        for(String passenger : passengers) {

            User user = userDao.getUser(passenger);

            String passengerName = user.getUserProfile().getFirstName() + " " + user.getUserProfile().getLastName();
            String passengerAddress = locationDao.getLocationById(user.getLocationId()).getLocationAddress();
            passengerInfo.add("name:" + passengerName + ";location:" + passengerAddress);
        }

        return passengerInfo;
    }

    public List<String> getPassengers() {
        return tripDao.getPassengerListByTripId(tripId);
    }

    public List<String> getWaitingPassengers() {
        return tripDao.getWaitingPassengerListByTripId(tripId);
    }

    public Transport getTransport() {
        return transportDao.getTransportById(transportId);
    }

    public String getTransportLocationAddress() {
        return tripDao.getTripById(tripId).getTransportLocationAddress();
    }

    public String showTripDetails() {
        return "/trip-details?faces-redirect=true";
    }

    public void checkHasATripSet() throws IOException {
        if(!sessionTracker.isLoggedIn() || tripId == 0) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/home.xhtml");
        }
    }
}
