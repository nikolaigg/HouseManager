package org.example.DaoTests;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.ApartmentDao;
import org.example.dao.BuildingDao;
import org.example.dao.ResidentDao;
import org.example.entity.building.Apartment;
import org.example.entity.building.Building;
import org.example.entity.building.Resident;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ResidentDaoTest {

    private ResidentDao residentDao;
    private Resident testResident1;
    private Resident testResident2;
    private Session session;

    @BeforeEach
    void setUp() {
        residentDao = new ResidentDao();
        testResident1 = new Resident("Test Resident", 20);
        testResident2 = new Resident("Test Resident 2", 20);
        session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }
    @AfterEach
    void tearDown() {
        session.getTransaction().rollback();
        session.close();
    }


    @Test
    public void testInsertingResident() {

        residentDao.insert(session, testResident1);

        Long id = testResident1.getPersonId();
        Resident insertedResident = residentDao.getById(session, id);

        assertEquals(id,insertedResident.getPersonId());
        assertEquals("Test Resident",insertedResident.getName());
        assertEquals(20,insertedResident.getAge());

    }

    @Test
    public void testInsertingManyResidents() {
        residentDao.insertMany(session, List.of(testResident1, testResident2));

        Long id1 = testResident1.getPersonId();
        Long id2 = testResident2.getPersonId();

        Resident insertedResident1 = residentDao.getById(session, id1);
        Resident insertedResident2 = residentDao.getById(session, id2);

        assertEquals(id1,insertedResident1.getPersonId());
        assertEquals("Test Resident",insertedResident1.getName());
        assertEquals(20,insertedResident1.getAge());

        assertEquals(id2,insertedResident2.getPersonId());
        assertEquals("Test Resident 2",insertedResident2.getName());
        assertEquals(20,insertedResident2.getAge());


    }

    @Test
    public void testGettingResidentById() {
        residentDao.insert(session, testResident1);

        Long id = testResident1.getPersonId();

        Resident savedResident = residentDao.getById(session, id);
        assertEquals(testResident1,savedResident);

    }

    @Test
    public void testGettingResidentByBuilding(){
        ApartmentDao apartmentDao = new ApartmentDao();
        BuildingDao buildingDao = new BuildingDao();

        Apartment apartment = new Apartment();
        Building building = new Building();

        apartmentDao.insert(session, apartment);
        buildingDao.insert(session, building);

        apartment.setBuilding(building);
        testResident1.setApartment(apartment);

        residentDao.insert(session, testResident1);

        List<Resident> resident =residentDao.getByBuilding(session, testResident1.getApartment().getBuilding());

        assertEquals(testResident1,resident.get(0));

    }
    @Test
    public void testGettingAllResidents() {
        residentDao.insert(session, testResident1);
        residentDao.insert(session, testResident2);

        List<Resident> savedResidents = residentDao.getAll(session);

        assertTrue(savedResidents.contains(testResident1));
        assertTrue(savedResidents.contains(testResident2));
        assertEquals(2, savedResidents.size());

    }

    @Test
    public void testUpdatingResident() {
        residentDao.insert(session, testResident1);

        testResident1.setName("New Resident");
        testResident1.setAge(30);

        residentDao.update(session, testResident1);

        Long id = testResident1.getPersonId();

        Resident updatedResident = residentDao.getById(session, id);
        assertEquals("New Resident", updatedResident.getName());
        assertEquals(30,updatedResident.getAge());
    }

    @Test
    public void testDeletingResident() {
        residentDao.insert(session, testResident1);
        residentDao.delete(session, testResident1);
        Long id = testResident1.getPersonId();

        Resident deletedResident = residentDao.getById(session, id);
        assertNull(deletedResident);
    }

    @Test
    public void testDeletingAllResidents() {
        residentDao.insert(session, testResident1);
        residentDao.insert(session, testResident2);
        residentDao.deleteAll(session);

        session.flush();
        session.clear();

        Long id1 = testResident1.getPersonId();
        Long id2 = testResident2.getPersonId();

        Resident deletedResident1 = residentDao.getById(session, id1);
        Resident deletedResident2 = residentDao.getById(session, id2);

        assertNull(deletedResident1);
        assertNull(deletedResident2);
    }

    @Test
    public void testCountResidentsInBuilding() {
        ApartmentDao apartmentDao = new ApartmentDao();
        BuildingDao buildingDao = new BuildingDao();

        Apartment apartment = new Apartment();
        Building building = new Building();

        apartment.setBuilding(building);
        apartmentDao.insert(session, apartment);
        buildingDao.insert(session, building);
        testResident1.setApartment(apartment);

        residentDao.insert(session, testResident1);


        assertEquals(1, residentDao.countResidentsInBuilding(session, building.getBuildingId()));
    }
}
