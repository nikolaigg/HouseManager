package org.example.entity.building;

import jakarta.persistence.*;
import org.example.entity.company.HouseManager;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingId;

    private String address;
    private int numOfFloors;
    private int numOfApartments;

    @OneToMany (mappedBy = "building")
    private List<Apartment> apartments;

    @ManyToOne
    @JoinColumn(name = "house_manager_id")
    private HouseManager houseManager;

    public Building() {
        this.apartments = new ArrayList<>();
    }

    public Building(String address, int numOfFloors, int numOfApartments) {
        this.address = address;
        this.numOfFloors = numOfFloors;
        this.numOfApartments = numOfApartments;
        this.apartments = new ArrayList<>();
        this.houseManager = null;
    }
    public void addApartments(Apartment... apartments) {
        if (apartments == null) return;

        for (Apartment apartment : apartments) {
            if (apartment == null) continue;

            if (!this.apartments.contains(apartment)) {
                this.apartments.add(apartment);
                apartment.setBuilding(this);
            }
        }
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumOfFloors() {
        return numOfFloors;
    }

    public void setNumOfFloors(int numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    public int getNumOfApartments() {
        return numOfApartments;
    }

    public void setNumOfApartments(int numOfApartments) {
        this.numOfApartments = numOfApartments;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public HouseManager getHouseManager() {
        return houseManager;
    }

    public void setHouseManager(HouseManager houseManager) {
        this.houseManager = houseManager;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", address='" + address + '\'' +
                ", numOfFloors=" + numOfFloors +
                ", numOfApartments=" + numOfApartments +
                ", apartments=" + apartments +
                ", houseManager=" + houseManager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Building building = (Building) o;

        return buildingId != null && buildingId.equals(building.buildingId);
    }

    @Override
    public int hashCode() {
        return buildingId != null ? buildingId.hashCode() : 0;
    }

}
