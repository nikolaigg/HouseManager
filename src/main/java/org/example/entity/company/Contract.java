package org.example.entity.company;

import org.example.entity.building.Building;

import java.time.LocalDate;

public class Contract {

    private Company company;
    private Building building;
    private LocalDate signedOn;
    private int contractDurationInYears;
    private LocalDate signedUntil;

    public Contract(Company company, Building building, LocalDate signedOn, int contractDurationInYears) {
        this.company = company;
        this.building = building;
        this.signedOn = signedOn;
        this.contractDurationInYears = contractDurationInYears;
        this.signedUntil = signedOn.plusYears(contractDurationInYears);
    }

    public void printContract() {
        System.out.println("=================================");
        System.out.println("           Contract Info         ");
        System.out.println("=================================");
        System.out.println("Company Name:    " + company.getCompanyName());
        System.out.println("Building Address:   " + building.getAddress());
        System.out.println("Signed On:       " + signedOn);
        System.out.println("Signed Until:    " + signedUntil);
        System.out.println("=================================");
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public LocalDate getSignedOn() {
        return signedOn;
    }

    public void setSignedOn(LocalDate signedOn) {
        this.signedOn = signedOn;
    }

    public int getContractDurationInYears() {
        return contractDurationInYears;
    }

    public void setContractDurationInYears(int contractDurationInYears) {
        this.contractDurationInYears = contractDurationInYears;
    }

    public LocalDate getSignedUntil() {
        return signedUntil;
    }

    public void setSignedUntil(LocalDate signedUntil) {
        this.signedUntil = signedUntil;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "company=" + company +
                ", building=" + building +
                ", signedOn=" + signedOn +
                ", signedUntil=" + signedUntil +
                '}';
    }
}
