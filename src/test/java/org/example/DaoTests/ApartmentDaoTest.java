package org.example.DaoTests;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.ApartmentDao;
import org.example.dao.BuildingDao;
import org.example.entity.building.Apartment;
import org.example.entity.building.Building;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApartmentDaoTest {

    private ApartmentDao apartmentDao;
    private Apartment apartment1;
    private Apartment apartment2;

    private BuildingDao buildingDao;
    private Building building;

    private Session session;

    @BeforeEach
    public void setUp() {
        buildingDao = new BuildingDao();
        apartmentDao = new ApartmentDao();
        building = new Building();

        apartment1 = new Apartment(building, 1,2, BigDecimal.valueOf(20),true);
        apartment2 = new Apartment(building, 2,3, BigDecimal.valueOf(30),false);

        building.setApartments(List.of(apartment1, apartment2));

        session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();

        buildingDao.insert(session,building);
    }
    @AfterEach
    void tearDown() {
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    public void testInsertingApartment() {

        apartmentDao.insert(session, apartment1);

        Long id = apartment1.getApartmentId();
        Apartment insertedApartment = apartmentDao.getById(session, id);

        assertEquals(id,insertedApartment.getApartmentId());
        assertEquals(1,insertedApartment.getFloorNumber());
        assertEquals(2,insertedApartment.getApartmentNumber());
        assertEquals(BigDecimal.valueOf(20),insertedApartment.getApartmentArea());
        assertTrue(apartment1.hasPet());

    }

    @Test
    public void testInsertingManyApartments() {
        apartmentDao.insertMany(session, List.of(apartment1, apartment2));

        Long id1 = apartment1.getApartmentId();
        Long id2 = apartment2.getApartmentId();

        Apartment insertedApartment1 = apartmentDao.getById(session, id1);
        Apartment insertedApartment2 = apartmentDao.getById(session, id2);

        assertEquals(id1,insertedApartment1.getApartmentId());
        assertEquals(1,insertedApartment1.getFloorNumber());
        assertEquals(2,insertedApartment1.getApartmentNumber());
        assertEquals(BigDecimal.valueOf(20),insertedApartment1.getApartmentArea());
        assertTrue(apartment1.hasPet());

        assertEquals(id2,insertedApartment2.getApartmentId());
        assertEquals(2,insertedApartment2.getFloorNumber());
        assertEquals(3,insertedApartment2.getApartmentNumber());
        assertEquals(BigDecimal.valueOf(30),insertedApartment2.getApartmentArea());
        assertFalse(apartment2.hasPet());


    }

    @Test
    public void testGettingApartmentById() {
        apartmentDao.insert(session, apartment1);

        Long id = apartment1.getApartmentId();

        Apartment savedApartment = apartmentDao.getById(session, id);
        assertEquals(apartment1,savedApartment);

    }

    @Test
    public void testGettingApartmentByBuilding(){
        apartmentDao.insert(session, apartment1);

        List<Apartment> apartments =apartmentDao.getByBuilding(session, building.getBuildingId());

        assertEquals(apartment1,apartments.get(0));

    }
    @Test
    public void testGettingAllApartments() {
        apartmentDao.insert(session, apartment1);
        apartmentDao.insert(session, apartment2);

        List<Apartment> savedApartments = apartmentDao.getAll(session);

        assertTrue(savedApartments.contains(apartment1));
        assertTrue(savedApartments.contains(apartment2));
        assertEquals(2, savedApartments.size());

    }

    @Test
    public void testUpdatingApartment() {
        apartmentDao.insert(session, apartment1);

        apartment1.setApartmentNumber(100);

        apartmentDao.update(session, apartment1);

        Long id = apartment1.getApartmentId();

        Apartment updatedApartment = apartmentDao.getById(session, id);
        assertEquals(100,updatedApartment.getApartmentNumber());

    }

    @Test
    public void testDeletingApartment() {
        apartmentDao.insert(session, apartment1);
        apartmentDao.delete(session, apartment1);
        Long id = apartment1.getApartmentId();

        Apartment deletedApartment = apartmentDao.getById(session, id);
        assertNull(deletedApartment);
    }

    @Test
    public void testDeletingAllApartments() {
        apartmentDao.insert(session, apartment1);
        apartmentDao.insert(session, apartment2);
        apartmentDao.deleteAll(session);

        session.flush();
        session.clear();

        Long id1 = apartment1.getApartmentId();
        Long id2 = apartment2.getApartmentId();

        Apartment deletedApartment1 = apartmentDao.getById(session, id1);
        Apartment deletedApartment2 = apartmentDao.getById(session, id2);

        assertNull(deletedApartment1);
        assertNull(deletedApartment2);
    }

    @Test
    public void testCountApartmentsInBuilding() {
        apartmentDao.insert(session, apartment1);

        assertEquals(1, apartmentDao.countApartmentsInBuilding(session, building.getBuildingId()));
    }
}

