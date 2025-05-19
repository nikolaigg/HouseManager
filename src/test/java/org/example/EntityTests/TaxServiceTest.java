package org.example.EntityTests;

import org.example.entity.building.Apartment;
import org.example.entity.building.Building;
import org.example.entity.building.Resident;
import org.example.entity.company.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaxServiceTest {


    @Test
    public void testTaxingNullApartment_ReturnsException() {
        TaxService taxService = new TaxService();

        assertThrows(IllegalArgumentException.class, () -> taxService.taxApartment(null));

    }

    @Test
    public void testTaxingEmptyApartment_ReturnsZero() {
        TaxService taxService = new TaxService();
        Apartment apartment = new Apartment();

        assertEquals(BigDecimal.ZERO, taxService.taxApartment(apartment));
    }

    @Test
    public void testTaxingValidApartment() {
        TaxService taxService = new TaxService(
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(5)
        );

        Apartment apartment = new Apartment(new Building(),1,1,BigDecimal.valueOf(40), true );
        Resident resident = new Resident("Test", 20);
        apartment.addResidents(resident);

        assertEquals(BigDecimal.valueOf(210), taxService.taxApartment(apartment));
    }

}
