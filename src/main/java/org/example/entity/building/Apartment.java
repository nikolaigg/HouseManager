package org.example.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long apartmentId;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    private int floorNumber;
    private int apartmentNumber;
    private BigDecimal apartmentArea;
    private boolean hasPet;

    @OneToOne(mappedBy = "apartmentOwned", cascade = CascadeType.ALL)
    private ApartmentOwner apartmentOwner;

    @OneToMany(mappedBy = "apartment")
    private ArrayList<Resident> residents;

    public Apartment() {

    }

    public Apartment(Building building, int floorNumber, int apartmentNumber, BigDecimal apartmentArea, boolean hasPet) {
        this.building = building;
        this.floorNumber = floorNumber;
        this.apartmentNumber = apartmentNumber;
        this.apartmentArea = apartmentArea;
        this.hasPet = hasPet;
        this.apartmentOwner = null;
        this.residents = new ArrayList<>();
    }

    public boolean usesElevator() {
        return floorNumber >= 3;
    }

    public int numOfResidentsOverSeven() {
        int total = 0;
        for(Resident resident : residents) {
            if(resident.getAge() > 7) {
                total += 1;
            }
        }
        return total;
    }
    public Long getApartmentId() {
        return apartmentId;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public BigDecimal getApartmentArea() {
        return apartmentArea;
    }

    public void setApartmentArea(BigDecimal apartmentArea) {
        this.apartmentArea = apartmentArea;
    }

    public boolean hasPet() {
        return hasPet;
    }

    public void setHasPet(boolean hasPet) {
        this.hasPet = hasPet;
    }

    public ApartmentOwner getApartmentOwner() {
        return apartmentOwner;
    }

    public void setApartmentOwner(ApartmentOwner apartmentOwner) {
        this.apartmentOwner = apartmentOwner;
    }

    public ArrayList<Resident> getResidents() {
        return residents;
    }

    public void setResidents(ArrayList<Resident> residents) {
        this.residents = residents;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", building=" + building +
                ", floorNumber=" + floorNumber +
                ", apartmentNumber=" + apartmentNumber +
                ", apartmentArea=" + apartmentArea +
                ", hasPet=" + hasPet +
                ", apartmentOwner=" + apartmentOwner +
                ", residents=" + residents +
                '}';
    }
}
