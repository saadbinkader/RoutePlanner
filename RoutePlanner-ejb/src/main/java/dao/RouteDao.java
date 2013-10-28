package dao;

import entity.Route;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/7/13
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */

@Local
public interface RouteDao {

    public void addRoute(Route route);

    public List<Route> getRoutes();

}
