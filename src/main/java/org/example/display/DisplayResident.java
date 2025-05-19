package org.example.display;

import org.example.dao.OrderDirection;
import org.example.dao.ResidentDao;
import org.example.entity.building.Building;
import org.example.entity.building.Resident;
import org.hibernate.Session;

import java.util.List;

public class DisplayResident {

    public static void orderResidentsByName(Session session, OrderDirection direction) {
        ResidentDao residentDao = new ResidentDao();
        List<Resident> residents = residentDao.orderResidentsByName(session, direction);

        System.out.println("Residents ordered by name (" + direction + "):");

        for (Resident resident : residents) {
            System.out.println(
                    resident.getName() + ", " +
                            resident.getAge() + " years old, " +
                            "living at apartment #" + resident.getApartment().getApartmentNumber()
            );
        }
    }


    public static void orderResidentsByAge(Session session, OrderDirection direction) {
        ResidentDao residentDao = new ResidentDao();
        List<Resident> residents = residentDao.orderResidentsByAgeAsc(session, direction);

        System.out.println("Residents by age " + direction.name() + " ending:");

        for (Resident resident : residents) {
            System.out.println(
                    resident.getName() + ", " +
                            resident.getAge() + " years old, " +
                            "living at apartment #" + resident.getApartment().getApartmentNumber()
            );
        }
    }



    public static void residentsInfo(Session session, Building building) {
        ResidentDao residentDao = new ResidentDao();
        List<Resident> residents = residentDao.getByBuilding(session, building);

        System.out.println("Residents living at: " + building.getAddress());

        for (Resident resident : residents) {
            System.out.println(
                    resident.getName() + ", " + resident.getAge() + " years old"
            );
        }
    }


    public static void getTotalResidentsInBuilding(Session session, Building building) {
        ResidentDao residentDao = new ResidentDao();

        Long totalResidents = residentDao.countResidentsInBuilding(session, building.getBuildingId());

        System.out.println("Building address: " + building.getAddress());
        System.out.println("Total residents in building: " + totalResidents);

    }

}
