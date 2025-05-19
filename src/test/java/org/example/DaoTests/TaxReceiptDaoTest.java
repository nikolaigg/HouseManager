package org.example.DaoTests;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.TaxReceiptDao;
import org.example.dao.BuildingDao;
import org.example.dao.HouseManagerDao;
import org.example.entity.company.TaxReceipt;
import org.example.entity.building.Building;
import org.example.entity.company.HouseManager;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaxReceiptDaoTest {

    private TaxReceiptDao taxReceiptDao;
    private BuildingDao buildingDao;
    private HouseManagerDao houseManagerDao;

    private Session session;

    private Building building;
    private HouseManager houseManager;
    private TaxReceipt receipt1;
    private TaxReceipt receipt2;

    @BeforeEach
    void setUp() {
        taxReceiptDao = new TaxReceiptDao();
        buildingDao = new BuildingDao();
        houseManagerDao = new HouseManagerDao();

        session = SessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Prepare dependencies
        building = new Building();
        buildingDao.insert(session, building);

        houseManager = new HouseManager("Manager Test", 50);
        houseManagerDao.insert(session, houseManager);

        // Prepare receipts
        receipt1 = new TaxReceipt(building, houseManager, new BigDecimal("1000.00"), LocalDate.now());
        receipt2 = new TaxReceipt(building, houseManager, new BigDecimal("1500.00"), LocalDate.now().minusDays(1));
    }

    @AfterEach
    void tearDown() {
        session.getTransaction().rollback();
        session.close();
    }

    @Test
    public void testInsertTaxReceipt() {
        taxReceiptDao.insert(session, receipt1);

        Long id = receipt1.getReceiptId();
        TaxReceipt inserted = taxReceiptDao.getById(session, id);

        assertEquals(id, inserted.getReceiptId());
        assertEquals(building.getBuildingId(), inserted.getBuilding().getBuildingId());
        assertEquals(houseManager.getPersonId(), inserted.getHouseManager().getPersonId());
        assertEquals(new BigDecimal("1000.00"), inserted.getAmount());
        assertEquals(LocalDate.now(), inserted.getDate());
    }

    @Test
    public void testInsertManyTaxReceipts() {
        taxReceiptDao.insertMany(session, List.of(receipt1, receipt2));

        Long id1 = receipt1.getReceiptId();
        Long id2 = receipt2.getReceiptId();

        TaxReceipt inserted1 = taxReceiptDao.getById(session, id1);
        TaxReceipt inserted2 = taxReceiptDao.getById(session, id2);

        assertEquals(id1, inserted1.getReceiptId());
        assertEquals(id2, inserted2.getReceiptId());
    }

    @Test
    public void testGetTaxReceiptById() {
        taxReceiptDao.insert(session, receipt1);

        TaxReceipt fetched = taxReceiptDao.getById(session, receipt1.getReceiptId());

        assertEquals(receipt1.getReceiptId(), fetched.getReceiptId());
        assertEquals(receipt1.getAmount(), fetched.getAmount());
    }

    @Test
    public void testGetAllTaxReceipts() {
        taxReceiptDao.insert(session, receipt1);
        taxReceiptDao.insert(session, receipt2);

        List<TaxReceipt> receipts = taxReceiptDao.getAll(session);

        assertTrue(receipts.contains(receipt1));
        assertTrue(receipts.contains(receipt2));
        assertEquals(2, receipts.size());

    }

    @Test
    public void testUpdateTaxReceipt() {
        taxReceiptDao.insert(session, receipt1);

        receipt1.setAmount(new BigDecimal("2000.00"));
        receipt1.setDate(LocalDate.now().plusDays(1));

        taxReceiptDao.update(session, receipt1);

        TaxReceipt updated = taxReceiptDao.getById(session, receipt1.getReceiptId());

        assertEquals(new BigDecimal("2000.00"), updated.getAmount());
        assertEquals(LocalDate.now().plusDays(1), updated.getDate());
    }

    @Test
    public void testDeleteTaxReceipt() {
        taxReceiptDao.insert(session, receipt1);

        taxReceiptDao.delete(session, receipt1);

        TaxReceipt deleted = taxReceiptDao.getById(session, receipt1.getReceiptId());
        assertNull(deleted);
    }

    @Test
    public void testDeleteAllTaxReceipts() {
        taxReceiptDao.insert(session, receipt1);
        taxReceiptDao.insert(session, receipt2);

        taxReceiptDao.deleteAll(session);

        session.flush();
        session.clear();

        TaxReceipt deleted1 = taxReceiptDao.getById(session, receipt1.getReceiptId());
        TaxReceipt deleted2 = taxReceiptDao.getById(session, receipt2.getReceiptId());

        assertNull(deleted1);
        assertNull(deleted2);
    }
}
