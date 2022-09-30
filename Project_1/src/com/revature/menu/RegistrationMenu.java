package com.revature.menu;

import com.revature.Main;

public class RegistrationMenu {
    public void Regdisplay() {
        while (true) {
            System.out.println("Welcome to the Registration page.");
            System.out.println("Please select an option");
            System.out.println("1.) Back to Main Menu");
            System.out.println("2.) Sign up for an account");
            System.out.println("3.) Exit");

            String choice = Main.sc.nextLine();


            if (choice.equals("1")) {
                //Back to main menu
                return;


            } else if (choice.equals("2")) {
                // Username and Password Prompt
                System.out.println("Follow the prompts listed below");
                System.out.println("Please enter your first name:");
                String firstName = Main.sc.nextLine();
                System.out.println("Please enter your last name");
                String lastName = Main.sc.nextLine();
                System.out.println("Please enter a new username");
                String username = Main.sc.nextLine();
                System.out.println("Please enter a new password");
                String password = Main.sc.nextLine();
                System.out.println("Please enter your email address");
                String email = Main.sc.nextLine();

            } else if (choice.equals("3")) {

                System.exit(3);
            } else {
                System.out.println("Invalid choice. You will be returned to the Registration Menu");

            }
        }
    }
}
