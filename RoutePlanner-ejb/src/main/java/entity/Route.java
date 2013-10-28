package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/7/13
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "ROUTES")
public class Route {
    private String route;

    @Id
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "[Class: Route" +
                ", route: " + route + "]";

    }
}
