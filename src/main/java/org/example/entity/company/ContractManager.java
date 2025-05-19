package org.example.entity.company;

import org.example.entity.building.Building;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContractManager {

    private ArrayList<Contract> contracts;

    public ContractManager() {
        this.contracts = new ArrayList<>();
    }

    public void signContract(Company company, Building building) {
        Contract contract = new Contract(company, building, LocalDate.now(), 3);
        contracts.add(contract);
        System.out.println("Contract between " + company.getCompanyName() + " and building at address " + building.getAddress() +
                " signed until " + contract.getSignedUntil());
    }
    public void printCompanyContracts() {
        for(Contract contract : contracts) {
            contract.printContract();
        }
    }
    public Contract getContractByBuilding(Building building) {
        for(Contract contract : contracts) {
            if(contract.getBuilding().equals(building)) {
                return contract;
            }
        }
        return null;
    }
    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }
}
