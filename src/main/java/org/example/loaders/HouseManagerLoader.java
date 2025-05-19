package org.example.loaders;

import org.example.dao.HouseManagerDao;
import org.example.entity.company.HouseManager;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class HouseManagerLoader {

    public static void loadHouseManagers(Session session) {
        HouseManagerDao houseManagerDao = new HouseManagerDao();

        // Check if already loaded
        Long count = (Long) session
                .createQuery("SELECT COUNT(h.id) FROM HouseManager h", Long.class)
                .uniqueResult();

        if (count != null && count > 0) {
            System.out.println("House managers already loaded. Skipping.");
            return;
        }

        // Init house managers
        List<HouseManager> managers = Arrays.asList(
                new HouseManager("Georgi Milenov", 35),
                new HouseManager("Milen Georgiev", 33),
                new HouseManager("Yulian Genov", 27),
                new HouseManager("Stamat Georgiev", 42),
                new HouseManager("Karamfil Ognqnov", 29),
                new HouseManager("Kostadin Kocev", 31)
        );

        // Insert
        houseManagerDao.insertMany(session, managers);
        System.out.println("House managers loaded successfully.");
    }
}
