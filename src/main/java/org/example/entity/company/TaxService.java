package org.example.entity.company;

import org.example.entity.building.Apartment;

import java.math.BigDecimal;

public class TaxService {

    private BigDecimal areaFee;
    private BigDecimal residentFee;
    private BigDecimal petFee;
    private BigDecimal elevatorFee;

    public TaxService() {

    }
    public TaxService(BigDecimal areaFee, BigDecimal residentFee, BigDecimal petFee, BigDecimal elevatorFee) {
        this.areaFee = areaFee;
        this.residentFee = residentFee;
        this.petFee = petFee;
        this.elevatorFee = elevatorFee;
    }

    public BigDecimal taxApartment(Apartment apartment) {
        if(apartment == null) {
            throw new IllegalArgumentException("Apartment cannot be null");
        }

        if(apartment.getResidents().isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal totalTax = BigDecimal.ZERO;

        // area fees
        totalTax = totalTax.add(apartment.getApartmentArea().multiply(areaFee));

        // resident fees
        BigDecimal numOfResidentsOverSeven = BigDecimal.valueOf(apartment.numOfResidentsOverSeven());
        totalTax = totalTax.add(numOfResidentsOverSeven.multiply(residentFee));


        if(apartment.hasPet()) {
            totalTax = totalTax.add(petFee);
        }
        if(apartment.usesElevator()) {
            totalTax = totalTax.add(elevatorFee);
        }

        return totalTax;
    }

    public BigDecimal getAreaFee() {
        return areaFee;
    }

    public void setAreaFee(BigDecimal areaFee) {
        this.areaFee = areaFee;
    }

    public BigDecimal getResidentFee() {
        return residentFee;
    }

    public void setResidentFee(BigDecimal residentFee) {
        this.residentFee = residentFee;
    }

    public BigDecimal getPetFee() {
        return petFee;
    }

    public void setPetFee(BigDecimal petFee) {
        this.petFee = petFee;
    }

    public BigDecimal getElevatorFee() {
        return elevatorFee;
    }

    public void setElevatorFee(BigDecimal elevatorFee) {
        this.elevatorFee = elevatorFee;
    }

    @Override
    public String toString() {
        return "TaxService{" +
                "areaFee=" + areaFee +
                ", residentFee=" + residentFee +
                ", petFee=" + petFee +
                ", elevatorFee=" + elevatorFee +
                '}';
    }
}
