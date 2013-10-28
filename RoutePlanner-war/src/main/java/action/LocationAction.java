package action;

import dao.LocationDao;
import entity.Location;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class LocationAction {

    private String locationAddress;
    private String route;

    @EJB
    private LocationDao locationDao;

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

    public String addLocation() {

        Location location = new Location();
        location.setLocationAddress(locationAddress);
        location.setRoute(route);
        locationDao.addLocation(location);

        return "/management";
    }

    public Map<String,Integer> getLocationAddressIdMap() {

        List<Location> locations = locationDao.getLocations();
        Map<String,Integer> locationMap = new HashMap<String, Integer>();

        for(Location location : locations) {
            locationMap.put(location.getLocationAddress(), location.getLocationId()) ;
        }

        return locationMap;
    }

    public List<Location> getLocationList() {
        return locationDao.getLocations();
    }
}
