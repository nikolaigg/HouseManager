package org.example.service;

import org.example.entity.building.Building;
import org.example.entity.company.Company;
import org.example.entity.company.HouseManager;
import org.example.exceptions.HouseManagerAlreadyHiredException;
import org.example.exceptions.HouseManagerNotInCompanyException;
import org.example.exceptions.NoManagersAvailableException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CompanyService {

    private final HouseManagerService houseManagerService;

    public CompanyService(HouseManagerService houseManagerService) {
        this.houseManagerService = houseManagerService;
    }

    public void signContract(Session session, Company company, Building building) {
        company.getContractManager().signContract(company, building);
        HouseManager houseManager = company.getLeastBusyManager();

        houseManagerService.assignBuildingToManager(session,houseManager, building);
    }

    public void hireHouseManager(Session session,Company company, HouseManager houseManager) {
        if(company.getHouseManagers().contains(houseManager)) {
           throw new HouseManagerAlreadyHiredException("House manager already hired by this company");
        }

        houseManager.setCompany(company);
        company.getHouseManagers().add(houseManager);
        houseManagerService.getHired(session,houseManager);
    }

    public void fireHouseManager(Session session,Company company, HouseManager houseManager) {
        if(!company.getHouseManagers().contains(houseManager)) {
            throw new HouseManagerNotInCompanyException("House manager is not in this company");
        }
        company.getHouseManagers().remove(houseManager);
        houseManagerService.getFired(session,houseManager);
    }

    public void distributeBuildingsToManagers(Session session,Company company, HouseManager fromManager) {
        List<HouseManager> managers = new ArrayList<>(company.getHouseManagers());
        managers.remove(fromManager);

        if (managers.isEmpty()) {
            throw new NoManagersAvailableException("No house managers available for distribution.");
        }

        List<Building> buildingsToDistribute = new ArrayList<>(fromManager.getBuildingsManaged());

        int managerIndex = 0;
        for (Building building : buildingsToDistribute) {
            HouseManager targetManager = managers.get(managerIndex % managers.size());

            houseManagerService.assignBuildingToManager(session,targetManager, building);
            managerIndex++;
        }

        // Fire the manager from the company
        fireHouseManager(session,company, fromManager);
    }


}
