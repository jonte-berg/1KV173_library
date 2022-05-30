/*      Kurs: 1IK173
        Projekt
        Kursdeltagare: Dennis Schill, Robin Sjöberg, Jonathan Berg, Konstantinos Karidas
        Termin och datum: VT22 30/5   */

package model;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainRun {

    public static void main(String[] args) throws SQLException {

        //Variabler
        Scanner input = new Scanner(System.in);
        int option;

        do {

            //Skriv ut menyn och registrera vilket val användaren gör.
            printMainMenu();
            option = Integer.parseInt(input.nextLine());
            System.out.println();


            switch(option) {
                case 1:
                    addNewMember(); //Robin klar med denna
                    break;

                case 2:
                    deleteMember(); // Klar Dennis
                    break;

                case 3:
                    searchForAMember(); //Robin klar med denna. (kan förbättras genom att ej tillåta strängar)
                    break;

                case 4:
                    searchForABook(); //Robin jobbar på denna.
                    break;

                case 5:
                    addLoan(); // Ej klar Dennis
                    break;

                case 6:
                    deleteLoan();
                    break;

                case 7:
                    System.out.println("Goodbye!");
                    break;

                    default:
                        System.out.println("Please us the given numbers to navigate!");
            }

        } while (option != 7 && option > 0);

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

    public static void deleteMember() {

            Scanner input = new Scanner(System.in);
            MemberService service = new MemberService();
            MemberManager memberManager = new MemberManager(service);
            boolean endLoop = false;

            System.out.println("\n(2) - Delete a member. \n==========================");
            System.out.print("Enter member id (5 digits): ");
            int memberID = input.nextInt();


            do {
                if (memberManager.deleteMember(memberID)) {
                    System.out.println("\nYES - The member with id \"" + memberID + "\" is now deleted!");
                    endLoop = true;

                } else {
                    System.out.println("NO - The member id \"" + memberID + "\" does NOT exist.");
                    System.out.print("Try again (0 to exit): ");
                    memberID = input.nextInt();

                    if (memberID == 0) {
                        endLoop = true;
                    }
                }
            } while (endLoop == false);

    }

   public static void searchForAMember() {
       Scanner input = new Scanner(System.in);
       MemberService service = new MemberService();
       MemberManager memberManager = new MemberManager(service);
       boolean endLoop = false;


       // Förbättringsmöjligheter - Vi skulle kunna lägga till någon ting för att förhindra strängar.
       System.out.println("\n(3) - Search for a member. \n==========================");
           System.out.print("Enter member id (5 digits): ");
          int membersID = input.nextInt();

       Member theMember = memberManager.searchForMemberInfo(membersID);

          //Frågar efter member id tills man hittat ett som finns eller att användaren väljer att avsluta.
       do {
           if (theMember != null) {
               System.out.println("\nYES - The member id \"" + membersID + "\" exists.");
               System.out.println("MemberID: " + theMember.getId());
               System.out.println("Name: " + theMember.getsName() + " "+ theMember.getlName());
               System.out.println("Maxloans: " + theMember.getMaxLoans());
               System.out.println("Currentloans: " + theMember.getCurrentLoan());


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

    public static void searchForABook() {
        Scanner input = new Scanner(System.in);
        LoanService service = new LoanService();
        LoanManager loanManager = new LoanManager(service);
        boolean endLoop = false;
        int isbnNr = 0;
        String title = "";


        System.out.println("\n(4) - Search for a book. \n==========================");
        System.out.print("Enter ISBN (6 digits) OR Title: ");


        do {

            //inputs
            if (input.hasNextInt()) {
                isbnNr = input.nextInt();
            } else {
                title = input.nextLine();
            }


            //isbn finns
            if (isbnNr != 0 && loanManager.searchForBookISBN(isbnNr)) {
                System.out.println("\nYES - The book with ISBN \"" + isbnNr + "\" is available.");
                endLoop = true;
            }

            //titel finns
            else if (!title.equals("") && loanManager.searchForBookTitle(title) ) {
                System.out.println("\nYES - The book \"" + title + "\" is available.");
                endLoop = true;
            }

            else if (isbnNr == -1){
                endLoop = true;
            }


            //boken finns inte
            else {
                if (isbnNr != 0) {
                    System.out.println("NO - A book with ISBN \"" + isbnNr + "\" is NOT available.");
                    System.out.print("Try again (-1 to exit): ");
                }
                else if (!title.equals("")){
                    System.out.println("NO - A book with titel \"" + title + "\" is NOT available.");
                    System.out.print("Try again (-1 to exit): ");

                }
                //"nollställer"
                isbnNr = 0;
                title = "";
            }

        } while (endLoop == false);

    }

    public static void addLoan() throws SQLException {

        Scanner input = new Scanner(System.in);
        LoanService service = new LoanService();
        LoanManager loanManager = new LoanManager(service);
        MemberService membService = new MemberService();
        MemberManager memberManager = new MemberManager(membService);
        List<Integer> listOfBooks = new ArrayList<>();
        boolean endLoop = false;


        System.out.println("\n(5) - Add Loan. \n==========================");
        System.out.print("Enter member id (5 digits): ");
        int membersID = input.nextInt();


        do {

            if (memberManager.searchForMember(membersID)) {


                System.out.println("Enter Book ISBN (6 Digits) to continue adding books to loan (0 if finished, to create loan/exit): ");
                int isbnNr = input.nextInt();

                if (isbnNr == 0)
                    endLoop = true;

                if (isbnNr != 0 && loanManager.searchForBookISBN(isbnNr)) {
                    listOfBooks.add(isbnNr);
                    System.out.println("Book was added!\n");

                } else if(isbnNr!=0)
                    System.out.println("The book ISBN does not exist, try again");

            } else {

                System.out.println("NO - The member id \"" + membersID + "\" do NOT exist.");
                System.out.print("Try again (0 to exit): ");
                membersID = input.nextInt();

                if (membersID == 0) {
                    endLoop = true;
                }

            }


        } while (endLoop == false);


        if(loanManager.addLoan(membersID,listOfBooks))
            System.out.println("Loan was added!");

        else
            System.out.println("Loan failed to be added!");

    }




    public static void deleteLoan(){

    Scanner input = new Scanner(System.in);
    LoanService service = new LoanService();
    LoanManager loanManager = new LoanManager(service);
    boolean endLoop = false;

    System.out.println("\n(6) - Delete a loan/Return book. \n==========================");
    System.out.print("Enter loan id (5 digits): ");
    int loanID = input.nextInt();

    do {
        if (loanManager.deleteLoan(loanID)) {
            System.out.println("\nYES - The Loan with  id \"" + loanID + "\" is now deleted, book has been returned!.");
            endLoop = true;

        } else {
            System.out.println("NO - The loan id \"" + loanID + "\" does NOT exist.");
            System.out.print("Try again (0 to exit): ");
            loanID = input.nextInt();

            if (loanID == 0) {
                endLoop = true;
            }
        }
    } while (endLoop == false);
}

    public static void printMainMenu() {
        System.out.println("\nLibrary Management System \n==========================");

        System.out.println("1. Add new member.");
        System.out.println("2. Delete member.");
        System.out.println("3. Search for member.");
        System.out.println("4. Search for book.");
        System.out.println("5. Add loan.");
        System.out.println("6. Delete loan.");
        System.out.println("7. Exit");
        System.out.print("\nEnter your choice: ");
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
}
