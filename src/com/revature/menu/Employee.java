package com.revature.menu;

import com.revature.Main;

public class Employee {
    public void display() {
        while (true) {
            System.out.println("Welcome to the Home Page.");
            System.out.println("Please select an option");
            System.out.println("1.) Check Reimbursement Status");
            System.out.println("2.) Add new Reimbursement Request");
            System.out.println("3.) Log out");

            String choice = Main.sc.nextLine();
        }
    }
}