package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

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
    private ArrayList<Apartment> apartments;

    @ManyToOne
    @JoinColumn(name = "house_manager_id")
    private HouseManager houseManager;

    public Building() {

    }

    public Building(String address, int numOfFloors, int numOfApartments) {
        this.address = address;
        this.numOfFloors = numOfFloors;
        this.numOfApartments = numOfApartments;
        this.apartments = new ArrayList<>();
        this.houseManager = null;
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

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(ArrayList<Apartment> apartments) {
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
}
