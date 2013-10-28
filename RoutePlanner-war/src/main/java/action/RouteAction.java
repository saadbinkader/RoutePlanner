package action;

import dao.RouteDao;
import entity.Route;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/9/13
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class RouteAction {

    private String routeName;

    @EJB
    private RouteDao routeDao;

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String addRoute() {

        Route route = new Route();
        route.setRoute(routeName);
        routeDao.addRoute(route);

        return "/management";
    }

    public Map<String,String> getRouteRouteMap(){

        Map<String,String> routes = new HashMap<String, String>();

        List<Route> routeList = routeDao.getRoutes();

        for(Route route : routeList) {
            routes.put(route.getRoute(),route.getRoute());
        }

        return routes;
    }
}
