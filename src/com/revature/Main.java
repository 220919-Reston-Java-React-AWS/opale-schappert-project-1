package com.revature;

import com.revature.menu.MainMenu;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        MainMenu mainMenuObject = new MainMenu();//Instantiating a mainmenu object

        mainMenuObject.display();


    }
}
