package org.example.entity.building;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "apartment", orphanRemoval = true)
    private List<Resident> residents;

    public Apartment() {
        this.residents = new ArrayList<>();
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

    public void addResidents(Resident... residents) {
        if (residents == null) return;

        for (Resident resident : residents) {
            if (resident == null) continue;

            if (!this.residents.contains(resident)) {
                this.residents.add(resident);
                resident.setApartment(this);
            }
        }
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

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentId=" + apartmentId +
                ", building=" + building.getAddress() +
                ", floorNumber=" + floorNumber +
                ", apartmentNumber=" + apartmentNumber +
                ", apartmentArea=" + apartmentArea +
                ", hasPet=" + hasPet +
                ", apartmentOwner=" + apartmentOwner.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        return apartmentId != null && apartmentId.equals(apartment.apartmentId);
    }

    @Override
    public int hashCode() {
        return apartmentId != null ? apartmentId.hashCode() : 0;
    }


}
