package model;

import java.util.Scanner;

public class MainRun {

    public static void main(String[] args) {

        //Variabler
        Scanner input = new Scanner(System.in);
        int option;


        //Header
        System.out.println("Library Management System \n==========================");


        //Main- Meny-val

        do {
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. Exit");
            System.out.print("\n==> ");
            option = Integer.parseInt(input.nextLine());
            System.out.println();

            if (option == 1) {
                System.out.println("You have chosen 1");
            }


            if (option == 2) {
                System.out.println("You have chosen 2");
            }

            if (option == 3) {
                System.out.println("Goodbye!");
            }

        } while (option != 3);


    }
}
