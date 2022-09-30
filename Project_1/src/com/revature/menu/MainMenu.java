package com.revature.menu;

import com.revature.Main; //Importing the main class from com.revature package

public class MainMenu {
    public void display() {
        while (true) {
            System.out.println("Welcome to employee reimbursement system.");
            System.out.println("Please select and option");
            System.out.println("1.) Register");
            System.out.println("2.) Login");
            System.out.println("3.) Exit");

            String choice = Main.sc.nextLine();


            if (choice.equals("1")) {
                //registration prompt
                System.out.println("Registration menu");
                RegistrationMenu registrationMenuObject = new RegistrationMenu();

                registrationMenuObject.Regdisplay();
//                Main.sc.nextLine();

            } else if (choice.equals("2")) {
                // login prompt

                LoginMenu loginMenuObject = new LoginMenu();

                loginMenuObject.Logdisplay();
//                Main.sc.nextLine();


            } else if (choice.equals("3")) {
                return;

            } else {
                System.out.println("Invalid choice. You will be returned to the Main Menu");
//                MainMenu mainMenuObject = new MainMenu();
//
//                mainMenuObject.display();
//                Main.sc.nextLine();
            }
        }
    }
}