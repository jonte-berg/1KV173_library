package model;

import java.time.LocalTime;
import java.util.Scanner;

public class MainRun {

    public static void main(String[] args) {

        //Variabler
        Scanner input = new Scanner(System.in);
        int option;



        do {

            //Skriv ut menyn och registrera vilket val användaren gör.
            printMainMenue();
            option = Integer.parseInt(input.nextLine());
            System.out.println();


            switch(option) {
                case 1:
                    addNewMember(); //Robin klar med denna
                    break;

                case 2:
                    searchForAMember(); //Robin klar med denna. (kan förbättras genom att ej tillåta strängar)
                    break;

                case 3:
                    System.out.println("You have chosen 3!"); //Robin jobbar på denna.
                    break;

                case 4:
                    System.out.println("You have chosen 4!");
                    break;

                case 5:
                    System.out.println("You have chosen 5!");
                    break;

                    default:
                        System.out.println("Goodbye!");
            }

        } while (option < 6 && option > 0);

    }




    public static void printMainMenue() {
        System.out.println("\nLibrary Management System \n==========================");

        System.out.println("1. Add new member.");
        System.out.println("2. Search for member.");
        System.out.println("3. Search for book.");
        System.out.println("4. Add loan.");
        System.out.println("5. Delete loan.");
        System.out.println("6. Exit");
        System.out.print("\nEnter your choise: ");
    }



    public static void addNewMember() {
        Scanner input = new Scanner(System.in);
        MemberService service = new MemberService();
        MemberManager memberManager = new MemberManager(service);

        int id = 0, maxLoans = 0, memberType;
        String sName, lName;


        System.out.println("\n(1) - Add a new member. \n==========================");
        System.out.print("First name: ");
        sName = input.next();
        System.out.print("Last name: ");
        lName = input.next();


        //Valet av medlems typ kommer köras tills man anger antingen 1, 2, 3 eller 4.
        do {
            System.out.println("1 = Undergraduate, 2 = Master, 3 = Phd 4 = Teacher ");
            System.out.print("Enter member type:  ");
            memberType = input.nextInt();

            switch (memberType) {
                case 1:
                    maxLoans = 3;
                    id = 1;
                    break;

                case 2:
                    maxLoans = 5;
                    id = 2;
                    break;

                case 3:
                    maxLoans = 7;
                    id = 3;
                    break;

                case 4:
                    maxLoans = 10;
                    id = 4;
                    break;
            }

        } while (memberType < 1 || memberType > 4);


        //Genererar fullt id efter vald första siffra (1, 2, 3 eller 4).
        id = generateID(id);

        Member newMember = new Member(id, sName, lName, 0, maxLoans, 0);
        memberManager.addMember(newMember);

    }


    public static int generateID(int idStartsWith) {
        //Skapar ett random membersID med hjälp av typen på medlem (1, 2, 3 eller 4) + en random sekvens på fyra siffror.
        //Inte optimalt men good enough här. Det värsta som kan hända är att vi får error om det id redan finns.
        //Men då är det bara att göra om processen, chansen är liten att man får samma id.

        LocalTime time = LocalTime.now();
        String nanoStr = String.valueOf(time.getNano());
        StringBuilder uniqueID = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            uniqueID.append(nanoStr.charAt(i));
        }

        return Integer.parseInt(idStartsWith + String.valueOf(uniqueID));
    }



   public static void searchForAMember() {
       Scanner input = new Scanner(System.in);
       MemberService service = new MemberService();
       MemberManager memberManager = new MemberManager(service);
       boolean endLoop = false;


       // Förbättringsmöjligheter - Vi skulle kunna lägga till någon ting för att förhindra strängar.
       System.out.println("\n(2) - Search for a member. \n==========================");
           System.out.print("Enter member id (5 digits): ");
          int membersID = input.nextInt();



          //Frågar efter member id tills man hittat ett som finns eller att användaren väljer att avsluta.
       do {
           if (memberManager.searchForMember(membersID)) {
               System.out.println("YES - The member id \"" + membersID + "\" exists.");
               endLoop = true;

           } else {
               System.out.println("NO - The member id \"" + membersID + "\" do NOT exist.");
               System.out.print("Try again (0 to exit): ");
               membersID = input.nextInt();

               if (membersID == 0) {
                   endLoop = true;
               }
           }
       } while (endLoop == false);

    }




}
