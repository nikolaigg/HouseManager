package org.example.app.commands;

import org.hibernate.Session;

public class createCommand implements Command {

    @Override
    public void execute(Session session) {

        System.out.println("Create options: Resident, ");
        System.out.println("/create ");
        System.out.println("Create options:");
        System.out.println("Create options:");
    }
}
