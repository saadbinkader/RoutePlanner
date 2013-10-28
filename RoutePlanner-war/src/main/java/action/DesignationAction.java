package action;

import dao.DesignationDao;
import entity.Designation;

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
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class DesignationAction {

    private String designationTitle;
    private int designationRank;

    @EJB
    private DesignationDao designationDao;

    public String getDesignationTitle() {
        return designationTitle;
    }

    public void setDesignationTitle(String designationTitle) {
        this.designationTitle = designationTitle;
    }

    public int getDesignationRank() {
        return designationRank;
    }

    public void setDesignationRank(int designationRank) {
        this.designationRank = designationRank;
    }

    public String addDesignation() {

        Designation designation = new Designation();
        designation.setDesignationTitle(designationTitle);
        designation.setDesignationRank(designationRank);

        designationDao.addDesignation(designation);

        return "/management";
    }

    public Map<String, String> getDesignationTitleTitleMap() {

        Map<String, String> designationMap = new HashMap<String, String>();
        List<Designation> designations = designationDao.getDesignations();

        for(Designation designation : designations) {
            designationMap.put(designation.getDesignationTitle(),designation.getDesignationTitle());
        }

        return designationMap;
    }
}
