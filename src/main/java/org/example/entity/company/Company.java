package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table (name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    private int profit;

    @OneToMany(mappedBy = "company")
    private ArrayList<HouseManager> houseManagers;

    private ArrayList<Building> buildings;

    @Transient
    private TaxService taxService;

    public Company() {
        this.profit = 0;
        this.houseManagers = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public ArrayList<HouseManager> getHouseManagers() {
        return houseManagers;
    }

    public void setHouseManagers(ArrayList<HouseManager> houseManagers) {
        this.houseManagers = houseManagers;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", profit=" + profit +
                ", houseManagers=" + houseManagers +
                ", buildings=" + buildings +
                '}';
    }
}
