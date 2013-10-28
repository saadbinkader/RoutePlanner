package dao;

import entity.Designation;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */

@Local
public interface DesignationDao {

    public void addDesignation(Designation designation);

    public void updateDesignation(Designation designation);

    public List<Designation> getDesignations();

}
