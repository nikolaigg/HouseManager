package org.example.dao;

import org.example.entity.building.Building;
import org.hibernate.Session;

import java.util.List;

public class BuildingDao implements CrudDao<Building, Long>{

    @Override
    public void insert(Session session,Building building) {
        session.persist(building);
    }
    @Override
    public void insertMany(Session session,List<Building> buildings) {
        for (Building building : buildings) {
            session.persist(building);
        }
    }

    @Override
    public Building getById(Session session,Long id) {
        return session.get(Building.class, id);
    }

    @Override
    public List<Building> getAll(Session session) {
        return session.createQuery("from Building", Building.class).getResultList();
    }

    @Override
    public void update(Session session,Building building) {
        session.merge(building);
    }

    @Override
    public void delete(Session session,Building building) {
        session.remove(building);
    }

    @Override
    public void deleteAll(Session session) {
        session.createMutationQuery("delete from Building").executeUpdate();
    }
}
