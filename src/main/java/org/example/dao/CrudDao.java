package org.example.dao;
import org.hibernate.Session;

import java.util.List;

public interface CrudDao<T, ID> {
    void insert(Session session, T entity);
    void insertMany(Session session,List<T> entities);
    T getById(Session session,ID id);
    List<T> getAll(Session session);
    void update(Session session,T entity);
    void delete(Session session,T entity);
    void deleteAll(Session session);
}
