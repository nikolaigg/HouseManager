package org.example.EntityTests;

import org.example.entity.building.Apartment;
import org.example.entity.building.Resident;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApartmentTest {

    @Test
    void addSingleResident_successfullyAdds() {

        Apartment apartment = new Apartment();
        Resident resident = new Resident();

        apartment.addResidents(resident);

        assertTrue(apartment.getResidents().contains(resident));
        assertEquals(apartment, resident.getApartment());
    }

    @Test
    void addDuplicateResident_doesNotAddAgain() {
        Apartment apartment = new Apartment();
        Resident resident = new Resident();

        apartment.addResidents(resident);
        apartment.addResidents(resident);

        assertEquals(1, apartment.getResidents().size());
    }

    @Test
    void addNullResident_ignoredSafely() {
        Apartment apartment = new Apartment();
        apartment.addResidents((Resident) null);

        assertTrue(apartment.getResidents().isEmpty());
    }

    @Test
    void addResidents_nullArray_ignoredSafely() {
        Apartment apartment = new Apartment();
        apartment.addResidents((Resident[]) null);

        assertTrue(apartment.getResidents().isEmpty());
    }
}
