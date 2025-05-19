package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resident")
public class Resident extends Person {

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Resident() {
    }

    public Resident(String name, int age) {
        super(name, age);
        this.apartment = null;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "apartment=" + apartment +
                '}';
    }
}
