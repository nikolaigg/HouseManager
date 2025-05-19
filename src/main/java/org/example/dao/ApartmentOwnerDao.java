package org.example.dao;

import org.example.entity.building.ApartmentOwner;
import org.hibernate.Session;


import java.util.List;

public class ApartmentOwnerDao implements CrudDao<ApartmentOwner, Long>{

    @Override
    public void insert(Session session, ApartmentOwner owner) {
        session.merge(owner);
    }

    @Override
    public void insertMany(Session session,List<ApartmentOwner> owners) {
        for (ApartmentOwner owner : owners) {
            session.merge(owner);
        }
    }

    @Override
    public ApartmentOwner getById(Session session,Long id) {
        return session.get(ApartmentOwner.class, id);
    }

    @Override
    public List<ApartmentOwner> getAll(Session session) {
        return session.createQuery("from ApartmentOwner", ApartmentOwner.class).getResultList();
    }

    @Override
    public void update(Session session,ApartmentOwner owner) {
        session.merge(owner);
    }

    @Override
    public void delete(Session session,ApartmentOwner owner) {
        session.remove(owner);
    }

    @Override
    public void deleteAll(Session session) {
        session.createMutationQuery("delete from ApartmentOwner").executeUpdate();
    }
}
