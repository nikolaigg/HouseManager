package org.example.entity;

import java.math.BigDecimal;

public class TaxService {

    private BigDecimal areaFee;
    private BigDecimal residentFee;
    private BigDecimal petFee;
    private BigDecimal elevatorFee;

    public BigDecimal taxApartment(Apartment apartment) {
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
}
