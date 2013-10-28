package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/3/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "DESIGNATIONS")
public class Designation {
    private String designationTitle;
    private int designationRank;

    @Id
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

    @Override
    public String toString() {
        return "[Class: Designation" +
                ", designationTitle: " + designationTitle +
                ", designationRank: " + designationRank + "]";

    }
}
