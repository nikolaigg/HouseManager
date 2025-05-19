package org.example.display;

import org.example.dao.HouseManagerDao;
import org.example.dao.OrderDirection;
import org.example.entity.building.Building;
import org.example.entity.company.HouseManager;
import org.hibernate.Session;

import java.util.List;

public class DisplayHouseManager {

    public static void orderHouseManagersByName(Session session, OrderDirection direction) {
        HouseManagerDao houseManagerDao = new HouseManagerDao();
        List<HouseManager> houseManagers = houseManagerDao.orderHouseManagersByName(session, direction);

        System.out.println("House Managers ordered by name (" + direction + "):");

        for (HouseManager houseManager : houseManagers) {
            System.out.println("- " + houseManager.getName());
        }
    }

    public static void orderHouseManagersByNumOfBuildingsManaged(Session session, OrderDirection direction) {
        HouseManagerDao houseManagerDao = new HouseManagerDao();
        List<HouseManager> houseManagers = houseManagerDao.orderHouseManagersByNumOfBuildings(session, direction);

        System.out.println("House Managers ordered by number of buildings managed (" + direction + "):");

        for (HouseManager houseManager : houseManagers) {
            System.out.println(
                    houseManager.getName() + " - Buildings Managed: " + houseManager.getNumOfBuildingsManaged()
            );
        }
    }


    public static void houseManagerInfo(Session session) {
        HouseManagerDao houseManagerDao = new HouseManagerDao();
        List<HouseManager> houseManagers = houseManagerDao.getAll(session);

        System.out.println("House Managers Info:");

        for (HouseManager houseManager : houseManagers) {
            if (houseManager.getCompany() != null) {
                System.out.println(
                        houseManager.getName() + " manages " + houseManager.getNumOfBuildingsManaged() + " building(s)"
                );

                for (Building building : houseManager.getBuildingsManaged()) {
                    System.out.println(
                            "  Building ID: " + building.getBuildingId() + ", Address: " + building.getAddress()
                    );
                }

                if (houseManager.getNumOfBuildingsManaged() == 0) {
                    System.out.println("  (No buildings managed)");
                }
            } else {
                System.out.println(houseManager.getName() + " does not work at any company.");
            }

            System.out.println();
        }
    }

    public static void countHouseManagersInCompany(Session session, Long companyId) {
        HouseManagerDao houseManagerDao = new HouseManagerDao();

        Long count = houseManagerDao.countHouseManagersInCompany(session, companyId);

        System.out.println("Company ID: " + companyId);
        System.out.println("Number of house managers in company: " + count);

    }

}
