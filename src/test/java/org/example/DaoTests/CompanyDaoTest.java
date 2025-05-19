package org.example.DaoTests;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.CompanyDao;
import org.example.entity.company.Company;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyDaoTest {

    private CompanyDao companyDao;
    private Company company1;
    private Company company2;
    private Session session;

    @BeforeEach
    void setUp() {
        session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.createMutationQuery("delete from Company").executeUpdate();

        companyDao = new CompanyDao();

        company1 = new Company("Test Company 1");
        company2 = new Company("Test Company 2");


    }
    @AfterEach
    void tearDown() {
        session.getTransaction().rollback();
        session.close();
    }


    @Test
    public void testInsertingCompany() {
        companyDao.insert(session, company1);

        Long id = company1.getCompanyId();
        Company insertedCompany = companyDao.getById(session, id);

        assertEquals(id, insertedCompany.getCompanyId());
        assertEquals("Test Company 1", insertedCompany.getCompanyName());


    }

    @Test
    public void testInsertingManyCompanies() {
        companyDao.insertMany(session, List.of(company1, company2));

        Long id1 = company1.getCompanyId();
        Long id2 = company2.getCompanyId();

        Company insertedCompany1= companyDao.getById(session, id1);
        Company insertedCompany2 = companyDao.getById(session, id2);

        assertEquals(id1, insertedCompany1.getCompanyId());
        assertEquals("Test Company 1", insertedCompany1.getCompanyName());

        assertEquals(id2, insertedCompany2.getCompanyId());
        assertEquals("Test Company 2", insertedCompany2.getCompanyName());



    }

    @Test
    public void testGettingBuildingById() {
        companyDao.insert(session, company1);

        Long id = company1.getCompanyId();

        Company company = companyDao.getById(session, id);

        assertEquals(id,company.getCompanyId());

    }

    @Test
    public void testGettingAllCompanies() {
        companyDao.insert(session, company1);
        companyDao.insert(session, company2);

        Long id1 = company1.getCompanyId();
        Long id2 = company2.getCompanyId();

        List<Company> companies = companyDao.getAll(session);

        assertTrue(companies.contains(company1));
        assertTrue(companies.contains(company2));
        assertEquals(2, companies.size());

    }

    @Test
    public void testUpdatingCompany() {
        companyDao.insert(session, company1);

        company1.setCompanyName("Updated Company");
        companyDao.update(session, company1);

        Long id = company1.getCompanyId();
        Company updatedCompany = companyDao.getById(session, id);

        assertEquals("Updated Company", updatedCompany.getCompanyName());
    }

    @Test
    public void testDeletingCompany() {
        companyDao.insert(session, company1);
        companyDao.delete(session, company1);

        Long id = company1.getCompanyId();
        Company deletedCompany = companyDao.getById(session, id);

        assertNull(deletedCompany);
    }

    @Test
    public void testDeletingAllCompanies() {
        companyDao.insert(session, company1);
        companyDao.insert(session, company2);
        companyDao.deleteAll(session);

        session.flush();
        session.clear();

        Long id1 = company1.getCompanyId();
        Long id2 = company2.getCompanyId();

        Company deletedCompany1 = companyDao.getById(session, id1);
        Company deletedCompany2 = companyDao.getById(session, id2);

        assertNull(deletedCompany1);
        assertNull(deletedCompany2);
    }

}
