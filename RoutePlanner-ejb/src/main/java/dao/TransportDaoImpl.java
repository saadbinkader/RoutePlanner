package dao;

import entity.Transport;
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
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
public class TransportDaoImpl implements TransportDao {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    DataAccessLogger dataAccessLogger;

    @Override
    public void addTransport(Transport transport) {

        entityManager.persist(transport);

        dataAccessLogger.getLogger().info("Added - " + transport);
    }

    @Override
    public Transport getTransportById(int transportId) {

        Transport transport;

        String queryParameter = "SELECT t FROM Transport t WHERE t.transportId = :transportId";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, Transport.class);
            query.setParameter("transportId", transportId);
            transport = (Transport) query.getSingleResult();

        } catch (NoResultException ex) {

            transport = null;

            dataAccessLogger.getLogger().error("Failed To Fetch Transport By Id[" + transportId + "]");
        }
        return transport;
    }

    @Override
    public List<Transport> getTransports() {

        List<Transport> transports;

        String queryParameter = "SELECT t FROM Transport t ORDER BY t.transportName ASC";

        try {

            TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter, Transport.class);
            transports = query.getResultList();

        } catch (NoResultException ex) {

            transports = new ArrayList<Transport>();

            dataAccessLogger.getLogger().error("Failed To Fetch Transports");
        }
        return transports;
    }
}
