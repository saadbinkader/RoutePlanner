package dao;

import entity.Location;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */

@Local
public interface LocationDao {

    public void addLocation(Location location);

    public Location getLocationById(int locationId);

    public List<Location> getLocationsByRoute(String route);

    public List<Location> getLocations();

}
