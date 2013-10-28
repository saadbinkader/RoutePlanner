package action;

import dao.TransportDao;
import dao.UserDao;
import entity.Transport;
import entity.User;

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
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class TransportAction {

    private String transportName;
    private int capacity;
    private String driverName;

    @EJB
    private TransportDao transportDao;
    @EJB
    private UserDao userDao;

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String addTransport() {

        Transport transport = new Transport();
        transport.setTransportName(transportName);
        transport.setCapacity(capacity);
        transport.setDriverName(driverName);
        transportDao.addTransport(transport);

        return "/management";
    }

    public Map<String,Integer> getTransportNameIdMap() {

        List<Transport> transportList = transportDao.getTransports();

        Map<String,Integer> transportIds = new HashMap<String, Integer>();

        for(Transport transport : transportList) {
             transportIds.put(transport.getTransportName(),transport.getTransportId());
        }

        return transportIds;
    }

    public Map<String,String> getDriverNameNameMap() {

        List<User> userList = userDao.getUsers();

        Map<String,String> divers = new HashMap<String, String>();

        for(User user : userList) {
            if("driver".equals(user.getUserType())) {
                divers.put(user.getUserName(),user.getUserName());
            }
        }

        return divers;
    }

}
