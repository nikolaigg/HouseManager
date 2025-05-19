package org.example.display;

import org.example.dao.CompanyDao;
import org.example.entity.company.Company;
import org.hibernate.Session;

import java.util.List;

public class DisplayCompany {

    public static void orderCompaniesByIncome(Session session) {
        CompanyDao companyDao = new CompanyDao();
        List<Company> filteredCompanies = companyDao.orderCompaniesByIncome(session);

        System.out.println("Companies ordered by income:");

        for (Company comp : filteredCompanies) {
            System.out.println(
                    "Company Name: " + comp.getCompanyName() +
                            ", Income: " + comp.getIncome()
            );
        }
    }

}