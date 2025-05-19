package org.example.service;

import org.example.dao.BuildingDao;
import org.example.dao.HouseManagerDao;
import org.example.dao.TaxReceiptDao;
import org.example.entity.building.Apartment;
import org.example.entity.building.Building;
import org.example.entity.company.Contract;
import org.example.entity.company.ContractManager;
import org.example.entity.company.HouseManager;
import org.example.entity.company.TaxReceipt;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class HouseManagerService {

    private final HouseManagerDao houseManagerDao;
    private final BuildingDao buildingDao;
    private final TaxReceiptDao taxReceiptDao;

    public HouseManagerService(HouseManagerDao houseManagerDao, BuildingDao buildingDao, TaxReceiptDao taxReceiptDao) {
        this.houseManagerDao = houseManagerDao;
        this.buildingDao = buildingDao;
        this.taxReceiptDao = taxReceiptDao;
    }

    public void assignBuildingToManager(Session session, HouseManager houseManager, Building building) {
        houseManager.getBuildingsManaged().add(building);
        houseManager.setNumOfBuildingsManaged(houseManager.getNumOfBuildingsManaged() + 1);

        building.setHouseManager(houseManager);

        houseManagerDao.update(session,houseManager);
        buildingDao.update(session,building);
    }

    public void getHired(Session session, HouseManager houseManager) {
        houseManagerDao.update(session,houseManager);
    }
    public void getFired(Session session, HouseManager houseManager) {
        houseManagerDao.delete(session,houseManager);
    }

    public void collectTax(Session session, HouseManager houseManager, YearMonth targetDate) {
        ContractManager contractManager = houseManager.getCompany().getContractManager();

        for(Building building : houseManager.getBuildingsManaged()) {
            Contract contract = contractManager.getContractByBuilding(building);
            YearMonth contractSignedOn = YearMonth.from(contract.getSignedOn());

            if(targetDate.isBefore(contractSignedOn)) continue;

            List<YearMonth> unpaidMonths = getUnpaidMonths(session,building,contractSignedOn, targetDate);

            for(YearMonth unpaidMonth : unpaidMonths) {
                issueReceipts(session, building,houseManager,unpaidMonth);
            }

        }
    }

    public List<YearMonth> getUnpaidMonths(Session session,Building building, YearMonth from, YearMonth targetDate) {
        List<YearMonth> unpaidMonths = new ArrayList<>();

        while(from.isBefore(targetDate)) {

            if(!taxReceiptDao.taxIsPaid(session, building.getBuildingId(), from)) {
                unpaidMonths.add(YearMonth.from(from));
            }

            from = from.plusMonths(1);
        }

        return unpaidMonths;
    }

    public void issueReceipts(Session session, Building building, HouseManager houseManager, YearMonth date) {
        for(Apartment apartment : building.getApartments()) {
            BigDecimal taxAmount =  houseManager.getCompany().getTaxService().taxApartment(apartment);
            houseManager.getCompany().setIncome(houseManager.getCompany().getIncome().add(taxAmount));
            TaxReceipt receipt = new TaxReceipt(building, houseManager,taxAmount,date.atDay(1));
            taxReceiptDao.insert(session,receipt);
        }
    }



}
