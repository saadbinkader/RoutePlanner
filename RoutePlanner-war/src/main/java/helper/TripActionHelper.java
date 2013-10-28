package helper;

import dao.*;
import entity.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/9/13
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class TripActionHelper {

    private int tripTransportId;
    private String route;
    private Transport transport;
    private Date tripTime;
    private Map<Integer,String> locationIdAddressMap;
    private Map<String,Integer> designationTitleRankMap;

    private int targetTripId;
    private String notGoingUser;
    private String targetTripStatus;
    private String targetTripTransportLocation;

    private List<String> tripPassengers;
    private List<String> tripPassengersWaitingList;
    private Set<String> locationWayPointSet;

    @EJB
    private UserDao userDao;
    @EJB
    private LocationDao locationDao;
    @EJB
    private TransportDao transportDao;
    @EJB
    private DesignationDao designationDao;
    @EJB
    private TripDao tripDao;

    public int getTripTransportId() {
        return tripTransportId;
    }

    public void setTripTransportId(int tripTransportId) {
        this.tripTransportId = tripTransportId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getTripTime() {
        return tripTime;
    }

    public void setTripTime(Date tripTime) {
        this.tripTime = tripTime;
    }

    public int getTargetTripId() {
        return targetTripId;
    }

    public void setTargetTripId(int targetTripId) {
        this.targetTripId = targetTripId;
    }

    public String getNotGoingUser() {
        return notGoingUser;
    }

    public void setNotGoingUser(String notGoingUser) {
        this.notGoingUser = notGoingUser;
    }

    public String getTargetTripStatus() {
        return targetTripStatus;
    }

    public void setTargetTripStatus(String targetTripStatus) {
        this.targetTripStatus = targetTripStatus;
    }

    public String getTargetTripTransportLocation() {
        return targetTripTransportLocation;
    }

    public void setTargetTripTransportLocation(String targetTripTransportLocation) {
        this.targetTripTransportLocation = targetTripTransportLocation;
    }

    public String createTrip() {

        locationIdAddressMap = getLocationIdAddressMap();

        transport = transportDao.getTransportById(tripTransportId);

        designationTitleRankMap = getDesignationTitleRankMap();

        List<TripCandidate> tripCandidates = getTripCandidate();

        Collections.sort(tripCandidates, new TripCandidateComparable());

        generatePassengerList(tripCandidates);

        addTheTrip();

        return "/home?faces-redirect=true";
    }

    private List<TripCandidate> getTripCandidate() {

        List<User> users = userDao.getUsers();

        List<TripCandidate> tripCandidates = new ArrayList<TripCandidate>();

        for(User user : users) {

            if(isInThisTrip(user.getLocationId()) && "passenger".equals(user.getUserType())) {

                TripCandidate tripCandidate = new TripCandidate();
                tripCandidate.setUserName(user.getUserName());
                tripCandidate.setUserRank(designationTitleRankMap.get(user.getUserProfile().getDesignationTitle()));
                tripCandidate.setUserLocation(locationIdAddressMap.get(user.getLocationId()));

                tripCandidates.add(tripCandidate);
            }
        }

        return tripCandidates;
    }

    private void generatePassengerList(List<TripCandidate> tripCandidates) {

        int passengerCount = 0;
        tripPassengers = new ArrayList<String>();
        tripPassengersWaitingList = new ArrayList<String>();
        locationWayPointSet = new HashSet<String>();

        for(TripCandidate candidate : tripCandidates) {
            if(passengerCount < transport.getCapacity()) {
                passengerCount++;
                tripPassengers.add(candidate.getUserName());
                locationWayPointSet.add(candidate.getUserLocation());
            }
            else {
                tripPassengersWaitingList.add(candidate.getUserName());
            }
        }

    }

    private void addTheTrip() {

        Trip trip = new Trip();

        trip.setTransportId(transport.getTransportId());

        Timestamp timestamp = new Timestamp(tripTime.getTime());
        trip.setStartTimestamp(timestamp);

        trip.setRoute(route);

        trip.setDuration(0);

        trip.setStatus("Waiting");

        trip.setPassengerList(tripPassengers);

        trip.setPassengerWaitingList(tripPassengersWaitingList);

        List<String> wayPoints = new ArrayList<String>(locationWayPointSet);
        trip.setLocationAddresses(wayPoints);

        trip.setTransportLocationAddress("start");

        tripDao.addTrip(trip);

    }

    private Map<Integer,String> getLocationIdAddressMap() {

        Map<Integer,String> locationIdAddressMap = new HashMap<Integer, String>();

        List<Location> locations = locationDao.getLocationsByRoute(route);

        for(Location location : locations) {
            locationIdAddressMap.put(location.getLocationId(),location.getLocationAddress());
        }

        return locationIdAddressMap;
    }

    private Map<String, Integer> getDesignationTitleRankMap() {

        Map<String, Integer> designationTitleRankMap = new HashMap<String, Integer>();

        List<Designation> designations = designationDao.getDesignations();

        for(Designation designation : designations) {
            designationTitleRankMap.put(designation.getDesignationTitle(),designation.getDesignationRank());
        }

        return designationTitleRankMap;
    }

    private boolean isInThisTrip(int locationId) {
        return locationIdAddressMap.containsKey(locationId);
    }

    public String updateTrip() {

        Trip trip = tripDao.getTripById(targetTripId);
        List<String> passengerList = tripDao.getPassengerListByTripId(targetTripId);
        List<String> waitingPassengerList = tripDao.getWaitingPassengerListByTripId(targetTripId);

        passengerList.remove(notGoingUser);


        if(!waitingPassengerList.isEmpty()) {
            passengerList.add(waitingPassengerList.get(0));
            waitingPassengerList.remove(waitingPassengerList.get(0));
        }

        Set<String> locationWayPointSet = new HashSet<String>();

        for(String passenger : passengerList) {
            User user = userDao.getUser(passenger);
            String passengerAddress = locationDao.getLocationById(user.getLocationId()).getLocationAddress();
            locationWayPointSet.add(passengerAddress);
        }

        trip.setPassengerList(passengerList);
        trip.setPassengerWaitingList(waitingPassengerList);

        List<String> wayPoints = new ArrayList<String>(locationWayPointSet);
        trip.setLocationAddresses(wayPoints);

        tripDao.updateTrip(trip);

        return "/home?faces-redirect=true";
    }

    public String updateTripTransportLocation() {

        Trip trip = tripDao.getTripById(targetTripId);
        trip.setTransportLocationAddress(targetTripTransportLocation);

        tripDao.updateTrip(trip);

        return "/route?faces-redirect=true";
    }

    public String updateTripStatus() {

        Trip trip = tripDao.getTripById(targetTripId);
        trip.setStatus(targetTripStatus);

        tripDao.updateTrip(trip);

        return "/route?faces-redirect=true";
    }

    private class TripCandidate {
        private String userName;
        private int userRank;
        private String userLocation;

        private String getUserName() {
            return userName;
        }

        private void setUserName(String userName) {
            this.userName = userName;
        }

        private int getUserRank() {
            return userRank;
        }

        private void setUserRank(int userRank) {
            this.userRank = userRank;
        }

        private String getUserLocation() {
            return userLocation;
        }

        private void setUserLocation(String userLocation) {
            this.userLocation = userLocation;
        }
    }

    private class TripCandidateComparable implements Comparator<TripCandidate>{
        @Override
        public int compare(TripCandidate candidateOne, TripCandidate candidateTwo) {
            return (candidateOne.getUserRank() < candidateTwo.getUserRank() ? -1 : (candidateOne.getUserRank() == candidateTwo.getUserRank() ? 0 : 1));
        }
    }

}
