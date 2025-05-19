package org.example.dao;

import org.example.entity.company.HouseManager;
import org.hibernate.Session;

import java.util.List;

public class HouseManagerDao implements CrudDao<HouseManager,Long> {

    @Override
    public void insert(Session session, HouseManager houseManager) {
        session.persist(houseManager);
    }
    @Override
    public void insertMany(Session session, List<HouseManager> houseManagers) {
        for (HouseManager houseManager : houseManagers) {
            session.persist(houseManager);
        }
    }

    @Override
    public HouseManager getById(Session session, Long id) {
        return session.get(HouseManager.class, id);
    }

    @Override
    public List<HouseManager> getAll(Session session) {
        return session.createQuery("from HouseManager", HouseManager.class).getResultList();
    }

    @Override
    public void update(Session session, HouseManager houseManager) {
        session.merge(houseManager);
    }

    @Override
    public void delete(Session session, HouseManager houseManager) {
        session.remove(houseManager);
    }

    @Override
    public void deleteAll(Session session) {
        session.createMutationQuery("delete from HouseManager").executeUpdate();
    }

    public List<HouseManager> orderHouseManagersByName(Session session, OrderDirection direction) {
        String query = "SELECT h FROM HouseManager h ORDER BY h.name " + direction.name();
        return session.createQuery(query, HouseManager.class).getResultList();
    }

    public List<HouseManager> orderHouseManagersByNumOfBuildings(Session session, OrderDirection direction) {
        String query = "SELECT h FROM HouseManager h ORDER BY h.numOfBuildingsManaged " + direction.name();
        return session.createQuery(query, HouseManager.class).getResultList();
    }

    public Long countHouseManagersInCompany(Session session, Long companyId) {
        String query = "SELECT COUNT(hm) FROM HouseManager hm WHERE hm.company.id = :companyId";
        return (Long) session.createQuery(query, Long.class)
                .setParameter("companyId", companyId)
                .uniqueResult();
    }
}
