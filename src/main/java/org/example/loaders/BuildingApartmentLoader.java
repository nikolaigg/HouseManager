package org.example.loaders;

import org.example.dao.ApartmentDao;
import org.example.dao.ApartmentOwnerDao;
import org.example.dao.BuildingDao;
import org.example.dao.ResidentDao;
import org.example.entity.building.Apartment;
import org.example.entity.building.ApartmentOwner;
import org.example.entity.building.Building;
import org.example.entity.building.Resident;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Arrays;

public class BuildingApartmentLoader {

    public static void loadClasses(Session session) {
        BuildingDao buildingDao = new BuildingDao();
        ApartmentDao apartmentDao = new ApartmentDao();
        ResidentDao residentDao = new ResidentDao();
        ApartmentOwnerDao apartmentOwnerDao = new ApartmentOwnerDao();

        // Check if already loaded
        Long count = (Long) session
                .createQuery("SELECT COUNT(b.id) FROM Building b", Long.class)
                .uniqueResult();

        if (count != null && count > 0) {
            System.out.println("Buildings, apartments, and residents already loaded. Skipping.");
            return;
        }

        // Init buildings
        Building building1 = new Building("G.M Dimitrov", 3, 5);
        Building building2 = new Building("Akademik Boris 3", 3, 5);
        Building building3 = new Building("Tsar Boris 2", 3, 5);

        // Init apartments
        Apartment apartment1_1 = new Apartment(building1,1,1, BigDecimal.valueOf(40), true);
        Apartment apartment1_2 = new Apartment(building1,1,2, BigDecimal.valueOf(35), true);
        Apartment apartment1_3 = new Apartment(building1,2,3, BigDecimal.valueOf(80), false);
        Apartment apartment1_4 = new Apartment(building1,2,4, BigDecimal.valueOf(45), true);
        Apartment apartment1_5 = new Apartment(building1,3,5, BigDecimal.valueOf(52), false);

        Apartment apartment2_1 = new Apartment(building2,1,1, BigDecimal.valueOf(50), true);
        Apartment apartment2_2 = new Apartment(building2,1,2, BigDecimal.valueOf(20), true);
        Apartment apartment2_3 = new Apartment(building2,2,3, BigDecimal.valueOf(35), false);
        Apartment apartment2_4 = new Apartment(building2,2,4, BigDecimal.valueOf(40), true);
        Apartment apartment2_5 = new Apartment(building2,3,5, BigDecimal.valueOf(70), false);

        Apartment apartment3_1 = new Apartment(building3,1,1, BigDecimal.valueOf(55), true);
        Apartment apartment3_2 = new Apartment(building3,1,2, BigDecimal.valueOf(37), true);
        Apartment apartment3_3 = new Apartment(building3,2,3, BigDecimal.valueOf(48), false);
        Apartment apartment3_4 = new Apartment(building3,2,4, BigDecimal.valueOf(60), true);
        Apartment apartment3_5 = new Apartment(building3,3,5, BigDecimal.valueOf(40), false);


        // Init residents && assign to apartments
        Resident resident1 = new Resident("Aleksandar Kolev", 28);
        Resident resident2 = new Resident("Raya Koleva", 30);
        Resident resident3 = new Resident("Teodor Kolev", 15);
        Resident resident4 = new Resident("Simona Kolev", 19);
        apartment1_1.addResidents(resident1, resident2, resident3, resident4);

        Resident resident5 = new Resident("Kaloyan Stoyanov", 42);
        Resident resident6 = new Resident("Vesela Marinova", 41);
        apartment1_2.addResidents(resident5,resident6);

        Resident resident7 = new Resident("Dimitar Hristov", 28);
        Resident resident8 = new Resident("Mariya Hristova", 31);
        Resident resident9 = new Resident("Borislav Hristov", 8);
        Resident resident10 = new Resident("Yana Hristova", 4);
        apartment1_3.addResidents(resident7,resident8,resident9,resident10);

        Resident resident11 = new Resident("Petya Zheleva", 31);
        Resident resident12 = new Resident("Tihomir Asenov", 27);
        Resident resident13 = new Resident("Eva Asenova", 10);
        apartment1_4.addResidents(resident11,resident12,resident13);

        Resident resident14 = new Resident("Kristiyan Vasilev", 29);
        Resident resident15 = new Resident("Svetla Nikolova", 36);
        apartment1_5.addResidents(resident14,resident15);

        Resident resident16 = new Resident("Nadezhda Tsvetkova", 40);
        Resident resident17 = new Resident("Ivaylo Tsvetkov", 41);
        apartment2_1.addResidents(resident16,resident17);

        Resident resident18 = new Resident("Velislava Radeva", 21);
        Resident resident19 = new Resident("Martin Krastev", 46);
        apartment2_2.addResidents(resident18,resident19);

        Resident resident20 = new Resident("Rositsa Gencheva", 33);
        Resident resident21 = new Resident("Angel Pavlov", 5);
        Resident resident22 = new Resident("Lora Pavlova", 7);
        apartment2_3.addResidents(resident20,resident21,resident22);

        Resident resident23 = new Resident("Stefan Bonev", 62);
        Resident resident24 = new Resident("Iliana Boneva", 58);
        apartment2_4.addResidents(resident23,resident24);

        Resident resident25 = new Resident("Bozhidar Enchev", 21);
        apartment2_5.addResidents(resident25);

        Resident resident26 = new Resident("Minka Grozdova", 24);
        apartment3_1.addResidents(resident26);

        Resident resident27 = new Resident("Boqn Hadjidimitrov", 40);
        Resident resident28 = new Resident("Boqna Hadjidimitrova", 40);
        apartment3_2.addResidents(resident27,resident28);

        Resident resident29 = new Resident("Atanas Mitrev", 44);
        Resident resident30 = new Resident("Yoana Mitreva", 39);
        Resident resident31 = new Resident("Georgi Mitrev", 11);
        Resident resident32 = new Resident("Ivana Mitreva", 2);
        apartment3_3.addResidents(resident29, resident30,resident31,resident32);

        Resident resident33 = new Resident("Nikolai Kamenov", 24);
        apartment3_4.addResidents(resident33);

        Resident resident34 = new Resident("Mariya Hristova", 31);
        apartment3_5.addResidents(resident34);

        // Init apartment owners && assign to apartments
        ApartmentOwner apartmentOwner1 = new ApartmentOwner(resident1,apartment1_1);
        ApartmentOwner apartmentOwner2 = new ApartmentOwner(resident5, apartment1_2);
        ApartmentOwner apartmentOwner3 = new ApartmentOwner(resident7, apartment1_3);
        ApartmentOwner apartmentOwner4 = new ApartmentOwner("Iliyan Kolev", 51, apartment1_4);
        ApartmentOwner apartmentOwner5 = new ApartmentOwner("Georgi Georgiev", 56, apartment1_5);
        ApartmentOwner apartmentOwner6 = new ApartmentOwner(resident17, apartment2_1);
        ApartmentOwner apartmentOwner7 = new ApartmentOwner(resident19, apartment2_2);
        ApartmentOwner apartmentOwner8 = new ApartmentOwner("Petko Penev", 41, apartment2_3);
        ApartmentOwner apartmentOwner9 = new ApartmentOwner(resident23, apartment2_4);
        ApartmentOwner apartmentOwner10 = new ApartmentOwner("Kalyoan Minkov", 50, apartment2_5);
        ApartmentOwner apartmentOwner11 = new ApartmentOwner("Mitko Emilov", 39, apartment3_1);
        ApartmentOwner apartmentOwner12 = new ApartmentOwner(resident28, apartment3_2);
        ApartmentOwner apartmentOwner13 = new ApartmentOwner(resident29,apartment3_3);
        ApartmentOwner apartmentOwner14 = new ApartmentOwner("Anatolii Spasov", 71, apartment3_4);
        ApartmentOwner apartmentOwner15 = new ApartmentOwner("Simeon Petrov", 32, apartment3_5);

        // assign apartments to buildings
        building1.addApartments(apartment1_1,apartment1_2,apartment1_3,apartment1_4,apartment1_5);
        building2.addApartments(apartment2_1,apartment2_2,apartment2_3,apartment2_4,apartment2_5);
        building3.addApartments(apartment3_1,apartment3_2,apartment3_3,apartment3_4,apartment3_5);

        // Insert buildings
        buildingDao.insertMany(session,Arrays.asList(building1, building2,building3));

        // Insert apartments
        apartmentDao.insertMany(session,Arrays.asList(apartment1_1,apartment1_2,apartment1_3,apartment1_4,apartment1_5,
                apartment2_1,apartment2_2,apartment2_3,apartment2_4, apartment2_5,
                apartment3_1,apartment3_2,apartment3_3,apartment3_4,apartment3_5));

        // Insert residents
        residentDao.insertMany(session,Arrays.asList(resident1,resident2,resident3,resident4,resident5,resident6,resident7,resident8,
                resident9,resident10,resident11,resident12,resident13,resident14,resident15,resident16,resident17,resident18,
                resident19, resident20, resident21, resident22, resident23, resident24,resident25, resident26, resident27,
                resident28,resident29, resident30, resident31,resident32,resident33, resident34));

        // Insert apartment owners
        apartmentOwnerDao.insertMany(session,Arrays.asList(apartmentOwner1, apartmentOwner2,apartmentOwner3, apartmentOwner4, apartmentOwner5,
                apartmentOwner6, apartmentOwner7, apartmentOwner8, apartmentOwner9, apartmentOwner10, apartmentOwner11,
                apartmentOwner12, apartmentOwner13, apartmentOwner14, apartmentOwner15));
    }



}
