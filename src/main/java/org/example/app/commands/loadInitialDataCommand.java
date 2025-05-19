package org.example.app.commands;

import org.example.loaders.BuildingApartmentLoader;
import org.example.loaders.CompanyLoader;
import org.example.loaders.HouseManagerLoader;
import org.hibernate.Session;

public class loadInitialDataCommand implements Command {


    @Override
    public void execute(Session session) {
        try{
            session.beginTransaction();

            BuildingApartmentLoader.loadClasses(session);
            CompanyLoader.loadCompany(session);
            HouseManagerLoader.loadHouseManagers(session);

            session.getTransaction().commit();
            System.out.println("Initial data loaded");
        } catch(Exception e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Failed to load initial data: " + e.getMessage());
        }
    }
}
