package org.example.DaoTests;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.CompanyDao;
import org.example.dao.HouseManagerDao;
import org.example.entity.company.Company;
import org.example.entity.company.HouseManager;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HouseManagerDaoTest {

    private HouseManagerDao houseManagerDao;
    private HouseManager houseManager1;
    private HouseManager houseManager2;
    private Session session;

    @BeforeEach
    void setUp() {
        houseManagerDao = new HouseManagerDao();

        houseManager1 = new HouseManager("Manager One", 40);
        houseManager2 = new HouseManager("Manager Two", 45);

        session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

    @AfterEach
    void tearDown() {
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    public void testInsertingHouseManager() {
        houseManagerDao.insert(session, houseManager1);

        Long id = houseManager1.getPersonId();
        HouseManager insertedManager = houseManagerDao.getById(session, id);

        assertEquals(id, insertedManager.getPersonId());
        assertEquals("Manager One", insertedManager.getName());
        assertEquals(40, insertedManager.getAge());
        assertNotNull(insertedManager.getBuildingsManaged());
        assertEquals(0, insertedManager.getNumOfBuildingsManaged());
        assertNull(insertedManager.getCompany());
    }

    @Test
    public void testInsertingManyHouseManagers() {
        houseManagerDao.insertMany(session, List.of(houseManager1, houseManager2));

        Long id1 = houseManager1.getPersonId();
        Long id2 = houseManager2.getPersonId();

        HouseManager insertedManager1 = houseManagerDao.getById(session, id1);
        HouseManager insertedManager2 = houseManagerDao.getById(session, id2);

        assertEquals(id1, insertedManager1.getPersonId());
        assertEquals("Manager One", insertedManager1.getName());
        assertEquals(40, insertedManager1.getAge());

        assertEquals(id2, insertedManager2.getPersonId());
        assertEquals("Manager Two", insertedManager2.getName());
        assertEquals(45, insertedManager2.getAge());
    }

    @Test
    public void testGettingHouseManagerById() {
        houseManagerDao.insert(session, houseManager1);

        Long id = houseManager1.getPersonId();

        HouseManager savedManager = houseManagerDao.getById(session, id);

        assertEquals(houseManager1.getPersonId(), savedManager.getPersonId());
        assertEquals(houseManager1.getName(), savedManager.getName());
        assertEquals(houseManager1.getAge(), savedManager.getAge());
    }

    @Test
    public void testGettingAllHouseManagers() {
        houseManagerDao.insert(session, houseManager1);
        houseManagerDao.insert(session, houseManager2);

        List<HouseManager> managers = houseManagerDao.getAll(session);

        assertTrue(managers.contains(houseManager1));
        assertTrue(managers.contains(houseManager2));
        assertEquals(2, managers.size());

    }

    @Test
    public void testUpdatingHouseManager() {
        houseManagerDao.insert(session, houseManager1);

        houseManager1.setName("Updated Manager");
        houseManager1.setAge(50);
        houseManager1.setNumOfBuildingsManaged(3);

        houseManagerDao.update(session, houseManager1);

        HouseManager updatedManager = houseManagerDao.getById(session, houseManager1.getPersonId());

        assertEquals("Updated Manager", updatedManager.getName());
        assertEquals(50, updatedManager.getAge());
        assertEquals(3, updatedManager.getNumOfBuildingsManaged());
    }

    @Test
    public void testDeletingHouseManager() {
        houseManagerDao.insert(session, houseManager1);
        houseManagerDao.delete(session, houseManager1);

        HouseManager deletedManager = houseManagerDao.getById(session, houseManager1.getPersonId());
        assertNull(deletedManager);
    }

    @Test
    public void testDeletingAllHouseManagers() {
        houseManagerDao.insert(session, houseManager1);
        houseManagerDao.insert(session, houseManager2);
        houseManagerDao.deleteAll(session);

        session.flush();
        session.clear();

        HouseManager deleted1 = houseManagerDao.getById(session, houseManager1.getPersonId());
        HouseManager deleted2 = houseManagerDao.getById(session, houseManager2.getPersonId());

        assertNull(deleted1);
        assertNull(deleted2);
    }

    @Test
    public void testCountHouseManagersInCompany() {
        Company company = new Company();
        CompanyDao companyDao = new CompanyDao();
        company.setHouseManagers(List.of(houseManager1, houseManager2));
        houseManager1.setCompany(company);
        houseManager2.setCompany(company);

        companyDao.insert(session, company);

        houseManagerDao.insert(session, houseManager1);
        houseManagerDao.insert(session, houseManager2);

        assertEquals(2,houseManagerDao.countHouseManagersInCompany(session, company.getCompanyId()));
    }
}
