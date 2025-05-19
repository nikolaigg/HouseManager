package org.example.dao;

import org.example.entity.company.Company;
import org.hibernate.Session;

import java.util.List;

public class CompanyDao implements CrudDao<Company, Long>{

    @Override
    public void insert(Session session, Company company) {
        session.persist(company);
    }
    @Override
    public void insertMany(Session session,List<Company> companies) {
        for (Company company : companies) {
            session.persist(company);
        }
    }
    @Override
    public Company getById(Session session,Long id) {
        return  session.get(Company.class, id);
    }
    @Override
    public List<Company> getAll(Session session) {
        return session.createQuery("from Company", Company.class).getResultList();
    }

    @Override
    public void update(Session session,Company company) {
        session.merge(company);
    }

    @Override
    public void delete(Session session,Company company) {
        session.remove(company);
    }

    @Override
    public void deleteAll(Session session) {
        session.createMutationQuery("delete from Company").executeUpdate();
    }

    public List<Company> orderCompaniesByIncome(Session session) {
        String query = "SELECT c FROM Company c ORDER BY c.income DESC";
        return session.createQuery(query, Company.class).getResultList();
    }


}
