package org.example.DaoTests;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.ApartmentDao;
import org.example.dao.ApartmentOwnerDao;
import org.example.entity.building.Apartment;
import org.example.entity.building.ApartmentOwner;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApartmentOwnerDaoTest {

    private ApartmentOwnerDao apartmentOwnerDao;
    private ApartmentDao apartmentDao;
    private ApartmentOwner apartmentOwner1;
    private ApartmentOwner apartmentOwner2;
    private Apartment apartment1;
    private Apartment apartment2;
    private Session session;

    @BeforeEach
    void setUp() {

        apartmentOwnerDao = new ApartmentOwnerDao();
        apartmentDao = new ApartmentDao();

        apartment1 = new Apartment();
        apartment2 = new Apartment();

        apartmentOwner1 = new ApartmentOwner("Test", 20, apartment1);
        apartmentOwner2 = new ApartmentOwner("Test2", 20, apartment2);

        session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();

        apartmentDao.insert(session, apartment1);
        apartmentDao.insert(session, apartment2);
    }
    @AfterEach
    void tearDown() {
        session.getTransaction().rollback();
        session.close();
    }


    @Test
    public void testInsertingApartmentOwner() {
        apartmentOwnerDao.insert(session, apartmentOwner1);

        Long id = apartmentOwner1.getPersonId();
        ApartmentOwner insertedApartmentOwner = apartmentOwnerDao.getById(session, id);

        assertEquals(id, insertedApartmentOwner.getPersonId());
        assertEquals("Test", insertedApartmentOwner.getName());
        assertEquals(20, insertedApartmentOwner.getAge());

    }

    @Test
    public void testInsertingManyApartmentOwners() {
        apartmentOwnerDao.insertMany(session, List.of(apartmentOwner1, apartmentOwner2));

        Long id1 = apartmentOwner1.getPersonId();
        Long id2 = apartmentOwner2.getPersonId();

        ApartmentOwner insertedApartmentOwner1 = apartmentOwnerDao.getById(session, id1);
        ApartmentOwner insertedApartmentOwner2 = apartmentOwnerDao.getById(session, id2);

        assertEquals(id1,insertedApartmentOwner1.getPersonId());
        assertEquals("Test",insertedApartmentOwner1.getName());
        assertEquals(20,insertedApartmentOwner1.getAge());

        assertEquals(id2,insertedApartmentOwner2.getPersonId());
        assertEquals("Test2",insertedApartmentOwner2.getName());
        assertEquals(20,insertedApartmentOwner2.getAge());


    }

    @Test
    public void testGettingApartmentOwnerById() {
        apartmentOwnerDao.insert(session, apartmentOwner1);

        Long id = apartmentOwner1.getPersonId();

        ApartmentOwner apartmentOwner = apartmentOwnerDao.getById(session, id);
        assertEquals(apartmentOwner1,apartmentOwner);

    }

    @Test
    public void testGettingAllApartmentOwners() {
        apartmentOwnerDao.insert(session, apartmentOwner1);
        apartmentOwnerDao.insert(session, apartmentOwner2);

        Long id1 = apartmentOwner1.getPersonId();
        Long id2 = apartmentOwner2.getPersonId();

        List<ApartmentOwner> apartmentOwners = apartmentOwnerDao.getAll(session);

        assertTrue(apartmentOwners.contains(apartmentOwner1));
        assertTrue(apartmentOwners.contains(apartmentOwner2));
        assertEquals(2, apartmentOwners.size());

    }
    @Test
    public void testUpdatingApartmentOwner() {
        apartmentOwnerDao.insert(session, apartmentOwner1);

        apartmentOwner1.setName("Updated owner");
        apartmentOwner1.setAge(30);
        apartmentOwnerDao.update(session, apartmentOwner1);

        Long id = apartmentOwner1.getPersonId();

        ApartmentOwner updatedApartmentOwner = apartmentOwnerDao.getById(session, id);
        assertEquals("Updated owner", updatedApartmentOwner.getName());
        assertEquals(30,updatedApartmentOwner.getAge());
    }

    @Test
    public void testDeletingApartmentOwner() {
        apartmentOwnerDao.insert(session, apartmentOwner1);
        apartmentDao.delete(session, apartment1);
        apartmentOwnerDao.delete(session, apartmentOwner1);

        Long id = apartmentOwner1.getPersonId();

        ApartmentOwner deletedApartmentOwner = apartmentOwnerDao.getById(session, id);

        assertNull(deletedApartmentOwner);
    }

    @Test
    public void testDeletingAllApartmentOwners() {
        apartmentOwnerDao.insert(session, apartmentOwner1);
        apartmentOwnerDao.insert(session, apartmentOwner2);
        apartmentOwnerDao.deleteAll(session);

        session.flush();
        session.clear();

        Long id1 = apartmentOwner1.getPersonId();
        Long id2 = apartmentOwner2.getPersonId();

        ApartmentOwner deletedApartmentOwner1 = apartmentOwnerDao.getById(session, id1);
        ApartmentOwner deletedApartmentOwner2 = apartmentOwnerDao.getById(session, id2);

        assertNull(deletedApartmentOwner1);
        assertNull(deletedApartmentOwner2);
    }
}
