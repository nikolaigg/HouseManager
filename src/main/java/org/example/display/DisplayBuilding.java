package org.example.display;

import org.example.dao.ApartmentDao;
import org.example.dao.BuildingDao;
import org.example.entity.building.Apartment;
import org.example.entity.building.Building;
import org.hibernate.Session;

import java.util.List;

public class DisplayBuilding {

    public static void buildingInfo(Session session) {
        BuildingDao buildingDao = new BuildingDao();
        List<Building> buildings = buildingDao.getAll(session);

        System.out.println("Buildings Info:");

        for (Building building : buildings) {
            String houseManager = (building.getHouseManager() != null)
                    ? building.getHouseManager().getName()
                    : "No house manager";

            System.out.println(
                    "Address: " + building.getAddress() +
                            ", Apartments: " + building.getNumOfApartments() +
                            ", Floors: " + building.getNumOfFloors() +
                            ", Managed by: " + houseManager
            );
        }
    }



    public static void apartmentInfo(Session session, Building building) {
        ApartmentDao apartmentDao = new ApartmentDao();
        List<Apartment> apartments = apartmentDao.getByBuilding(session, building.getBuildingId());

        System.out.println("Apartments info - Address: " + building.getAddress());

        for (Apartment apartment : apartments) {
            System.out.println(
                    "Apartment Number: " + apartment.getApartmentNumber() +
                            ", Floor: " + apartment.getFloorNumber() +
                            ", Area: " + apartment.getApartmentArea() + " sqm" +
                            ", Has Pet: " + apartment.hasPet()
            );
        }
    }


    public static void getTotalApartmentsInBuilding(Session session, Building building) {
        ApartmentDao apartmentDao = new ApartmentDao();

        Long totalApartments = apartmentDao.countApartmentsInBuilding(session, building.getBuildingId());

        System.out.println("Building address: " + building.getAddress());
        System.out.println("Total apartments in building: " + totalApartments);

    }

}
