package dao;

import entity.Designation;
import util.DataAccessLogger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 10/6/13
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class DesignationDaoImpl implements DesignationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    DataAccessLogger dataAccessLogger;

    @Override
    public void addDesignation(Designation designation) {

        entityManager.persist(designation);

        dataAccessLogger.getLogger().info("Added - " + designation);
    }

    @Override
    public void updateDesignation(Designation designation) {

        entityManager.merge(designation);

        dataAccessLogger.getLogger().info("Updated - " + designation);
    }

    @Override
    public List<Designation> getDesignations() {

        List<Designation> designations;

        String queryParameter = "SELECT d FROM Designation d ORDER BY d.designationRank ASC";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter,Designation.class);
            designations = query.getResultList();

        } catch (NoResultException ex) {

            designations = new ArrayList<Designation>();

            dataAccessLogger.getLogger().error("Failed To Fetch Designations");
        }

        return designations;
    }
}
