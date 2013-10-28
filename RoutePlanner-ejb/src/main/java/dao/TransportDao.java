package dao;

import entity.Transport;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */

@Local
public interface TransportDao {

    public void addTransport(Transport transport);

    public Transport getTransportById(int transportId);

    public List<Transport> getTransports();

}
