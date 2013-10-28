package dao;

import entity.Location;
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
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class LocationDaoImpl implements LocationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    DataAccessLogger dataAccessLogger;

    @Override
    public void addLocation(Location location) {

        entityManager.persist(location);

        dataAccessLogger.getLogger().info("Added - " + location);
    }

    @Override
    public Location getLocationById(int locationId) {

        Location location;

        String queryParameter = "SELECT l FROM Location l WHERE l.locationId = :locationId";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, Location.class);
            query.setParameter("locationId", locationId);
            location = (Location) query.getSingleResult();

        } catch (NoResultException ex) {

            location = null;

            dataAccessLogger.getLogger().error("Failed To Fetch Location By Id[" + locationId + "]");

        }
        return location;
    }

    @Override
    public List<Location> getLocationsByRoute(String route) {

        List<Location> locations;

        String queryParameter = "SELECT l FROM Location l WHERE l.route = :route ORDER BY l.locationAddress ASC";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter,Location.class);
            query.setParameter("route", route);
            locations = query.getResultList();

        } catch (NoResultException ex) {

            locations = new ArrayList<Location>();

            dataAccessLogger.getLogger().error("Failed To Fetch Location By Route[" + route + "]");

        }
        return locations;
    }

    @Override
    public List<Location> getLocations() {

        List<Location> locations;

        String queryParameter = "SELECT l FROM Location l ORDER BY l.locationAddress ASC";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter,Location.class);
            locations = query.getResultList();

        } catch (NoResultException ex) {

            locations = new ArrayList<Location>();

            dataAccessLogger.getLogger().error("Failed To Fetch Locations");
        }
        return locations;
    }
}
