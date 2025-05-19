package org.example.entity.company;

import jakarta.persistence.*;
import org.example.entity.building.Building;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(name = "company_name")
    private String companyName;
    private BigDecimal income;

    @OneToMany(mappedBy = "company")
    private List<HouseManager> houseManagers;

    @Transient
    private ArrayList<Building> buildings;

    @Transient
    private TaxService taxService;
    @Transient
    private ContractManager contractManager;

    public Company() {
    }

    public Company(String companyName) {
        this.companyName = companyName;
        this.income = BigDecimal.ZERO;
        this.houseManagers = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    @PostLoad
    private void initContractManager() {
        this.taxService = new TaxService(BigDecimal.valueOf(10),BigDecimal.valueOf(10),BigDecimal.valueOf(10),BigDecimal.valueOf(10));
        this.contractManager = new ContractManager();
    }
    public HouseManager getLeastBusyManager() {
        if (houseManagers == null || houseManagers.isEmpty()) {
            return null;
        }

        HouseManager leastBusyManager = houseManagers.get(0);
        for(HouseManager houseManager : houseManagers) {
            if(leastBusyManager.getNumOfBuildingsManaged() > houseManager.getNumOfBuildingsManaged()) {
                leastBusyManager = houseManager;
            }
        }
        return leastBusyManager;
    }


    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public List<HouseManager> getHouseManagers() {
        return houseManagers;
    }

    public void setHouseManagers(List<HouseManager> houseManagers) {
        this.houseManagers = houseManagers;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public TaxService getTaxService() {
        return taxService;
    }

    public void setTaxService(TaxService taxService) {
        this.taxService = taxService;
    }

    public ContractManager getContractManager() {
        return contractManager;
    }

    public void setContractManager(ContractManager contractManager) {
        this.contractManager = contractManager;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", income=" + income +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return companyId != null && companyId.equals(company.companyId);
    }

    @Override
    public int hashCode() {
        return companyId != null ? companyId.hashCode() : 0;
    }

}
