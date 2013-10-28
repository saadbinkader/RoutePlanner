package dao;

import entity.LocationTrip;
import entity.Trip;
import entity.UserTrip;
import entity.WaitingUserTrip;
import util.DataAccessLogger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class TripDaoImpl implements TripDao {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    DataAccessLogger dataAccessLogger;

    @Override
    public void addTrip(Trip trip) {

        entityManager.persist(trip);

        dataAccessLogger.getLogger().info("Added - " + trip);
    }

    @Override
    public void updateTrip(Trip trip) {

        entityManager.merge(trip);

        dataAccessLogger.getLogger().info("Updated - " + trip);
    }

    @Override
    public List<Trip> getTrips() {

        List<Trip> trips;

        String queryParameter = "SELECT t FROM Trip t ORDER BY t.tripId DESC";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, Trip.class);
            trips = query.getResultList();

        } catch (NoResultException ex) {

            trips = new ArrayList<Trip>();

            dataAccessLogger.getLogger().error("Failed To Fetch Trips");

        }
        return trips;
    }

    @Override
    public List<Trip> getTripsByRoute(String route) {

        List<Trip> trips;

        String queryParameter = "SELECT t FROM Trip t WHERE t.route = :route ORDER BY t.tripId DESC";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, Trip.class);
            query.setParameter("route", route);
            trips = query.getResultList();

        } catch (NoResultException ex) {

            trips = new ArrayList<Trip>();

            dataAccessLogger.getLogger().error("Failed To Fetch Trips");

        }
        return trips;

    }

    @Override
    public Trip getTripById(int tripId) {

        Trip trip;

        String queryParameter = "SELECT t FROM Trip t WHERE t.tripId = :tripId";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, Trip.class);
            query.setParameter("tripId", tripId);
            trip = (Trip) query.getSingleResult();

        } catch (NoResultException ex) {

            trip = null;

            dataAccessLogger.getLogger().error("Failed To Fetch Trip By Id[" + tripId + "]");
        }
        return trip;
    }

    public List<String> getWayPointsById(int tripId) {

        List<String> wayPoints = new ArrayList<String>();
        List<LocationTrip> locationTripList;

        String queryParameter = "SELECT l FROM LocationTrip l WHERE l.tripId = :tripId ORDER BY l.locationAddress ASC";

        try {
            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, LocationTrip.class);
            query.setParameter("tripId", tripId);
            locationTripList = query.getResultList();

            for(LocationTrip locationTrip : locationTripList) {
                wayPoints.add(locationTrip.getLocationAddress());
            }

        } catch (NoResultException ex) {
            dataAccessLogger.getLogger().error("Failed To Fetch Trip By Id[" + tripId + "]");
        }
        return wayPoints;
    }

    @Override
    public List<String> getPassengerListByTripId(int tripId) {

        List<String> passengers = new ArrayList<String>();
        List<UserTrip> userTripList;

        String queryParameter = "SELECT u FROM UserTrip u WHERE u.tripId = :tripId ORDER BY u.userName ASC";

        try {
            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, UserTrip.class);
            query.setParameter("tripId", tripId);
            userTripList = query.getResultList();

            for(UserTrip userTrip : userTripList) {
                passengers.add(userTrip.getUserName());
            }

        } catch (NoResultException ex) {
            dataAccessLogger.getLogger().error("Failed To Fetch Trip By Id[" + tripId + "]");
        }
        return passengers;
    }

    @Override
    public List<String> getWaitingPassengerListByTripId(int tripId) {

        List<String> waitingPassengers = new ArrayList<String>();
        List<WaitingUserTrip> waitingUserTripList;

        String queryParameter = "SELECT w FROM WaitingUserTrip w WHERE w.tripId = :tripId ORDER BY w.userName ASC";

        try {
            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, WaitingUserTrip.class);
            query.setParameter("tripId", tripId);
            waitingUserTripList = query.getResultList();

            for(WaitingUserTrip waitingUserTrip : waitingUserTripList) {
                waitingPassengers.add(waitingUserTrip.getUserName());
            }

        } catch (NoResultException ex) {
            dataAccessLogger.getLogger().error("Failed To Fetch Trip By Id[" + tripId + "]");
        }
        return waitingPassengers;
    }
}
