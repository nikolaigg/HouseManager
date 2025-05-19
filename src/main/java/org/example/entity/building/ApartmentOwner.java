package org.example.entity.building;

import jakarta.persistence.*;

@Entity
@Table(name = "apartment_owner")
public class ApartmentOwner extends Person {

    @OneToOne
    @JoinColumn (name = "apartment_id", unique = true)
    private Apartment apartmentOwned;

    public ApartmentOwner() {

    }
    public ApartmentOwner(String name, int age, Apartment apartmentOwned) {
        super(name, age);
        this.apartmentOwned = apartmentOwned;
        apartmentOwned.setApartmentOwner(this);
    }
    public ApartmentOwner(Resident resident, Apartment apartmentOwned) {
        this(resident.getName(), resident.getAge(), apartmentOwned);
    }

    public Apartment getApartmentOwned() {
        return apartmentOwned;
    }

    public void setApartmentOwned(Apartment apartmentOwned) {
        this.apartmentOwned = apartmentOwned;
    }

    @Override
    public String toString() {
        return "ApartmentOwner{" +
                "apartmentOwned=" + apartmentOwned.getApartmentId() +
                '}';
    }
}
