package com.revature.menu;

import com.revature.Main;

public class LoginMenu {
    public void Logdisplay() {
        while (true) {
            System.out.println("Welcome to the Login page.");
            System.out.println("Please select an option");
            System.out.println("1.) Back to Main Menu");
            System.out.println("2.) Enter Username and Password");
            System.out.println("3.) Exit");

            String choice = Main.sc.nextLine();


            if (choice.equals("1")) {
                //Back to main menu
                return;


            } else if (choice.equals("2")) {
                // Username and Password Prompt
                System.out.println("Please enter your username");
                String username = Main.sc.nextLine();
                System.out.println("Please enter your password");
                String password = Main.sc.nextLine();

            } else if (choice.equals("3")) {

                System.exit(3);
            } else {
                System.out.println("Invalid choice. You will be returned to the Login Menu");

            }
        }
    }
}
