package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "TRANSPORTS")
public class Transport {
    private int transportId;
    private String transportName;
    private int capacity;
    private String driverName;

    @Id @GeneratedValue
    public int getTransportId() {
        return transportId;
    }

    public void setTransportId(int transportID) {
        this.transportId = transportID;
    }

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

    @Override
    public String toString() {
        return "[Class: Transport" +
                ", transportId: " + transportId +
                ", transportName: " + transportName +
                ", capacity: " + capacity +
                ", driverName:" + driverName +"]";

    }
}
