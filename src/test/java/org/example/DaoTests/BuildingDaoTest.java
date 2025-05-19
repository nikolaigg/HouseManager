package org.example.DaoTests;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.BuildingDao;
import org.example.entity.building.Building;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BuildingDaoTest {

    private BuildingDao buildingDao;
    private Building building1;
    private Building building2;
    private Session session;

    @BeforeEach
    void setUp() {
        buildingDao = new BuildingDao();

        building1 = new Building("Address1", 1,1);
        building2 = new Building("Address2", 2,2);

        session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();

    }
    @AfterEach
    void tearDown() {
        session.getTransaction().rollback();
        session.close();
    }


    @Test
    public void testInsertingBuilding() {
        buildingDao.insert(session, building1);

        Long id = building1.getBuildingId();
        Building insertedBuilding = buildingDao.getById(session, id);

        assertEquals(id, insertedBuilding.getBuildingId());
        assertEquals("Address1", insertedBuilding.getAddress());
        assertEquals(1, insertedBuilding.getNumOfFloors());
        assertEquals(1,insertedBuilding.getNumOfApartments());

    }

    @Test
    public void testInsertingManyBuildings() {
        buildingDao.insertMany(session, List.of(building1, building2));

        Long id1 = building1.getBuildingId();
        Long id2 = building2.getBuildingId();

        Building insertedBuilding1= buildingDao.getById(session, id1);
        Building insertedBuilding2 = buildingDao.getById(session, id2);

        assertEquals(id1, insertedBuilding1.getBuildingId());
        assertEquals("Address1", insertedBuilding1.getAddress());
        assertEquals(1, insertedBuilding1.getNumOfFloors());
        assertEquals(1,insertedBuilding1.getNumOfApartments());

        assertEquals(id2, insertedBuilding2.getBuildingId());
        assertEquals("Address2", insertedBuilding2.getAddress());
        assertEquals(2, insertedBuilding2.getNumOfFloors());
        assertEquals(2,insertedBuilding2.getNumOfApartments());


    }

    @Test
    public void testGettingBuildingById() {
        buildingDao.insert(session, building1);

        Long id = building1.getBuildingId();

        Building building = buildingDao.getById(session, id);
        assertEquals(building1,building);

    }

    @Test
    public void testGettingAllBuildings() {
        buildingDao.insert(session, building1);
        buildingDao.insert(session, building2);

        Long id1 = building1.getBuildingId();
        Long id2 = building2.getBuildingId();


        List<Building> buildings = buildingDao.getAll(session);

        assertTrue(buildings.contains(building1));
        assertTrue(buildings.contains(building2));
        assertEquals(2, buildings.size());

    }
    @Test
    public void testUpdatingBuilding() {
        buildingDao.insert(session, building1);

        building1.setAddress("Updated address");
        building1.setNumOfFloors(10);
        building1.setNumOfApartments(10);
        buildingDao.update(session, building1);

        Long id = building1.getBuildingId();

        Building updatedBuilding= buildingDao.getById(session, id);

        assertEquals("Updated address", updatedBuilding.getAddress());
        assertEquals(10,updatedBuilding.getNumOfFloors());
        assertEquals(10,updatedBuilding.getNumOfApartments());
    }

    @Test
    public void testDeletingBuilding() {
        buildingDao.insert(session, building1);
        buildingDao.delete(session, building1);

        Long id = building1.getBuildingId();

        Building deletedBuilding = buildingDao.getById(session, id);

        assertNull(deletedBuilding);
    }

    @Test
    public void testDeletingAllBuildings() {
        buildingDao.insert(session, building1);
        buildingDao.insert(session, building2);
        buildingDao.deleteAll(session);

        session.flush();
        session.clear();

        Long id1 = building1.getBuildingId();
        Long id2 = building2.getBuildingId();

        Building deletedBuilding1 = buildingDao.getById(session, id1);
        Building deletedBuilding2 = buildingDao.getById(session, id2);

        assertNull(deletedBuilding1);
        assertNull(deletedBuilding2);
    }
}
