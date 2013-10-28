package dao;

import entity.Route;
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
 * Date: 10/7/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class RouteDaoImpl implements RouteDao {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    DataAccessLogger dataAccessLogger;

    @Override
    public void addRoute(Route route) {

        entityManager.persist(route);

        dataAccessLogger.getLogger().info("Added - " + route);
    }

    @Override
    public List<Route> getRoutes() {

        List<Route> routes;

        String queryParameter = "SELECT r FROM Route r ORDER BY r.route ASC";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter,Route.class);
            routes = query.getResultList();

        } catch (NoResultException ex) {

            routes = new ArrayList<Route>();

            dataAccessLogger.getLogger().error("Failed To Fetch Routes");
        }
        return routes;
    }
}
