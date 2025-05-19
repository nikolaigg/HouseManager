package org.example;

import org.example.app.AppMenu;
import org.example.configuration.SessionFactoryUtil;

import org.hibernate.Session;


public class Main {
    public static void main(String[] args) {

        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            AppMenu appMenu = new AppMenu();
            appMenu.run(session);

        }
    }
}






