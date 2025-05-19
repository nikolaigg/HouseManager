package org.example.app;

import org.example.app.commands.createCommand;
import org.example.app.commands.loadInitialDataCommand;
import org.hibernate.Session;

import java.util.Scanner;

public class AppMenu {

    private final loadInitialDataCommand loadInitialData;
    private final createCommand create;

    public AppMenu() {
        this.loadInitialData = new loadInitialDataCommand();
    }

    public void run(Session session) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Electronic House Management ===");
            System.out.println("\n--- Menu ---");
            System.out.println("/load -- Load Initial Data");
            System.out.println("/create -- Create an object");
            System.out.println("/exit -- Exit application");
            System.out.print("Choose an option: ");

            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "/load":
                    loadInitialData.execute(session);
                    break;
                case "/create":
                    create.execute(session);
                    break;
                case "/exit":
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

}
