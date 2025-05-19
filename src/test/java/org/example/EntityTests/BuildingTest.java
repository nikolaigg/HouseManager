package org.example.EntityTests;

import org.example.entity.building.Apartment;
import org.example.entity.building.Building;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuildingTest {

    @Test
    void addSingleApartment_successfullyAdds() {
        Building building = new Building();
        Apartment apartment = new Apartment();

        building.addApartments(apartment);

        assertTrue(building.getApartments().contains(apartment));
        assertEquals(building, apartment.getBuilding());
    }

    @Test
    void addDuplicateApartment_doesNotAddAgain() {
        Building building = new Building();
        Apartment apartment = new Apartment();

        building.addApartments(apartment);
        building.addApartments(apartment);

        assertEquals(1, building.getApartments().size());
    }

    @Test
    void addNullApartment_ignoredSafely() {
        Building building = new Building();
        building.addApartments((Apartment) null);

        assertTrue(building.getApartments().isEmpty());
    }

    @Test
    void addApartments_nullArray_ignoredSafely() {
        Building building = new Building();
        building.addApartments((Apartment[]) null); // array is null

        assertTrue(building.getApartments().isEmpty());
    }
}
