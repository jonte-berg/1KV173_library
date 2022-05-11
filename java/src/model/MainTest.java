package model;

import java.util.ArrayList;
import java.util.List;

/*Här har vi en MainTest som vi kan göra olika tester för att visa/förklara saker på.*/


public class MainTest {

    public static void main(String[] args) {



        /*------------------------------------loanID-------------------------------------------------------*/

        //Här är ett test för att se hur ett loanID kan se ut.
        //Det kommer skapas automatisk och innehåller memberID och en unik kod på 4 siffror som genereras i samma stund som lånet genomförs.
        Book enBok = new Book();
        List<Book> borrowing = new ArrayList<>();
        Loan aLoan = new Loan(3008, borrowing);

        System.out.println("Unikt loanID: " + aLoan.getLoanID());









    }

}
