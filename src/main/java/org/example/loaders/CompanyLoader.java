package org.example.loaders;

import org.example.dao.CompanyDao;
import org.example.entity.company.Company;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class CompanyLoader {

    public static void loadCompany(Session session) {
        CompanyDao companyDao = new CompanyDao();

        // Check if already loaded
        Long count = (Long) session
                .createQuery("SELECT COUNT(c.id) FROM Company c", Long.class)
                .uniqueResult();

        if (count != null && count > 0) {
            System.out.println("Companies already loaded. Skipping.");
            return;
        }

        // Init companies
        List<Company> companies = Arrays.asList(
                new Company("Company 1"),
                new Company("Company 2")
        );


        // Insert
        companyDao.insertMany(session,companies);
        System.out.println("Companies loaded successfully.");

    }
}
