package org.example.dao;

import org.example.entity.building.Apartment;
import org.hibernate.Session;


import java.util.List;

public class ApartmentDao implements CrudDao<Apartment, Long> {

    @Override
    public void insert(Session session, Apartment apartment) {
        session.persist(apartment);
    }

    @Override
    public void insertMany(Session session,List<Apartment> apartments) {
        for (Apartment apartment : apartments) {
            session.persist(apartment);
        }
    }

    @Override
    public Apartment getById(Session session,Long id) {
        return session.get(Apartment.class, id);
    }
    public List<Apartment> getByBuilding(Session session, Long buildingId) {
        String query = "from Apartment where building.id = :buildingId";
        return session.createQuery(query, Apartment.class)
                .setParameter("buildingId", buildingId)
                .getResultList();
    }
    @Override
    public List<Apartment> getAll(Session session) {
        return session.createQuery("from Apartment", Apartment.class).getResultList();
    }

    @Override
    public void update(Session session,Apartment apartment) {
        session.merge(apartment);
    }

    @Override
    public void delete(Session session,Apartment apartment) {
        session.remove(apartment);
    }

    @Override
    public void deleteAll(Session session) {
        session.createMutationQuery("delete from Apartment").executeUpdate();
    }

    public Long countApartmentsInBuilding(Session session, Long buildingId) {
        String query = "SELECT COUNT(a) FROM Apartment a WHERE a.building.id = :buildingId";

        return (Long) session.createQuery(query, Long.class)
                .setParameter("buildingId", buildingId)
                .uniqueResult();
    }
}
