package model;

import java.util.ArrayList;
import java.util.List;

/*Här har vi en MainTest som vi kan göra olika tester för att visa/förklara saker på.*/


public class MainTest {

    public static void main(String[] args) {



        /*------------------------------------getLoanID()-------------------------------------------------------*/

        //Här är ett test för att se hur ett loanID kan se ut.
        //Det kommer skapas automatisk och innehåller memberID och en unik kod på 4 siffror som genereras i samma stund som lånet genomförs.

        List<Integer> borrowing =  new ArrayList<>();
        borrowing.add(112233);
        borrowing.add(445566);
        borrowing.add(778899);

        Loan aLoan = new Loan(2008, borrowing);

        System.out.println("LoanID: " + aLoan.getLoanID());




        /*------------------------------------getBooks()-------------------------------------------------------*/

        //Här är ett exempel på hur man kan hämta alla böcker som finns i ett specifikt lån.

        List<Integer> borrowedBooks = aLoan.getBooks();

        for (int nr : borrowedBooks) {
            System.out.println("ISBN nr: " + nr);
        }






    }

}
