package org.example.dao;

import org.example.entity.company.TaxReceipt;
import org.hibernate.Session;

import java.time.YearMonth;
import java.util.List;

public class TaxReceiptDao implements CrudDao<TaxReceipt, Long> {

    @Override
    public void insert(Session session, TaxReceipt receipt) {
        session.persist(receipt);
    }

    @Override
    public void insertMany(Session session, List<TaxReceipt> receipts) {
        for (TaxReceipt receipt : receipts) {
            session.persist(receipt);
        }
    }

    @Override
    public TaxReceipt getById(Session session, Long id) {
        return session.get(TaxReceipt.class, id);
    }

    @Override
    public List<TaxReceipt> getAll(Session session) {
        return session.createQuery("from TaxReceipt", TaxReceipt.class).getResultList();
    }

    @Override
    public void update(Session session, TaxReceipt receipt) {
        session.merge(receipt);
    }

    @Override
    public void delete(Session session, TaxReceipt receipt) {
        session.remove(receipt);
    }

    @Override
    public void deleteAll(Session session) {
        session.createMutationQuery("delete from TaxReceipt").executeUpdate();
    }

    public boolean taxIsPaid(Session session, Long buildingId, YearMonth targetDate) {
        String hql = "SELECT COUNT(r) FROM TaxReceipt r WHERE r.building.id = :buildingId AND r.dateOfPayment = :targetDate";

        Long count = session.createQuery(hql, Long.class)
                .setParameter("buildingId", buildingId)
                .setParameter("targetDate", targetDate.atDay(1))
                .uniqueResult();

        return count != null && count > 0;
    }

}
