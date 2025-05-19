package org.example.configuration;

import org.example.entity.building.*;
import org.example.entity.company.Company;
import org.example.entity.company.HouseManager;
import org.example.entity.company.TaxReceipt;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Resident.class);
            configuration.addAnnotatedClass(ApartmentOwner.class);
            configuration.addAnnotatedClass(Apartment.class);
            configuration.addAnnotatedClass(Building.class);
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(HouseManager.class);
            configuration.addAnnotatedClass(TaxReceipt.class);

            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
