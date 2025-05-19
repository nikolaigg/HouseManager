package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "house_manager")
public class HouseManager extends Person{

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "houseManager")
    private ArrayList<Building> buildingsManaged;
    private int numOfBuildingsManaged;

    public HouseManager() {

    }
    public HouseManager(String name, int age) {
        super(name, age);
        this.company = null;
        this.buildingsManaged = new ArrayList<>();
        this.numOfBuildingsManaged = 0;
    }

    public HouseManager(Resident resident) {
        this(resident.getName(), resident.getAge());
    }
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ArrayList<Building> getBuildingsManaged() {
        return buildingsManaged;
    }

    public void setBuildingsManaged(ArrayList<Building> buildingsManaged) {
        this.buildingsManaged = buildingsManaged;
    }

    public int getNumOfBuildingsManaged() {
        return numOfBuildingsManaged;
    }

    public void setNumOfBuildingsManaged(int numOfBuildingsManaged) {
        this.numOfBuildingsManaged = numOfBuildingsManaged;
    }

    @Override
    public String toString() {
        return "HouseManager{" +
                "company=" + company +
                ", buildingsManaged=" + buildingsManaged +
                ", numOfBuildingsManaged=" + numOfBuildingsManaged +
                '}';
    }
}
