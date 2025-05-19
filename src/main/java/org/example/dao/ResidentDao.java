package org.example.dao;

import org.example.entity.building.Building;
import org.example.entity.building.Resident;
import org.hibernate.Session;

import java.util.List;

public class ResidentDao implements CrudDao<Resident, Long>{

    @Override
    public void insert(Session session,Resident resident) {
        session.persist(resident);
    }
    @Override
    public void insertMany(Session session,List<Resident> residents) {
        for (Resident resident : residents) {
            session.persist(resident);
        }
    }

    @Override
    public Resident getById(Session session,Long id) {
        return session.get(Resident.class, id);
    }

    public List<Resident> getByBuilding(Session session, Building building) {
        String query = "from Resident r where r.apartment.building = :building";
        return session.createQuery(query, Resident.class)
                .setParameter("building", building)
                .getResultList();
    }

    @Override
    public List<Resident> getAll(Session session) {
        return session.createQuery("from Resident", Resident.class).getResultList();
    }
    @Override
    public void update(Session session,Resident resident) {
        session.merge(resident);
    }
    @Override
    public void delete(Session session,Resident resident) {
        session.remove(resident);
    }

    @Override
    public void deleteAll(Session session) {
        session.createMutationQuery("delete from Resident").executeUpdate();
    }

    public List<Resident> orderResidentsByName(Session session, OrderDirection direction) {
        String query = "SELECT r FROM Resident r ORDER BY r.name " + direction.name();
        return session.createQuery(query, Resident.class).getResultList();
    }

    public List<Resident> orderResidentsByAgeAsc(Session session, OrderDirection direction){
        String query = "SELECT r FROM Resident r ORDER BY r.age " + direction.name();
        return session.createQuery(query, Resident.class).getResultList();
    }

    public Long countResidentsInBuilding(Session session, Long buildingId) {
        String query = "SELECT COUNT(r) FROM Resident r WHERE r.apartment.building.id = :buildingId";

        return (Long) session.createQuery(query, Long.class)
                .setParameter("buildingId", buildingId)
                .uniqueResult();
    }
}
