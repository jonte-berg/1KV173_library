package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Här har vi en MainTest som vi kan göra olika tester för att visa/förklara saker på.*/


public class MainTest {

    public static void main(String[] args) throws SQLException {


        /*------------------------------------getLoanByMemberId()-------------------------------------------------------*/

        LoanService membersLoan = new LoanService();

        for (Loan l : membersLoan.getLoanByMember(3)) {
            System.out.print(l);
        }              /* Ej klart */



        /*------------------------------------getallMembers()-------------------------------------------------------*/

        MemberService newServ = new MemberService();

        for (Member memb : newServ.getAllMembers()) {
            System.out.print(memb);
        }              /* Ej klart */

        /*------------------------------------getLoanID()-------------------------------------------------------*/

        //Här är ett test för att se hur ett loanID kan se ut.
        //Det kommer skapas automatisk och innehåller memberID och en unik kod på 4 siffror som genereras i samma stund som lånet genomförs.

        List<Book> borrowing = new ArrayList<>();
        Book bok1 = new Book(112233, "Sagan om ringen", "Äventyr", 10, true);
        borrowing.add(bok1);

        Loan aLoan = new Loan(2008, borrowing);

        System.out.println("\nLoanID: " + aLoan.getLoanID());




        /*------------------------------------getBooks()-------------------------------------------------------*/

        //Här är ett exempel på hur man kan hämta alla böcker som finns i ett specifikt lån.

        Book bok2 = new Book(445566, "Hobbit", "Äventyr", 5, true);
        Book bok3 = new Book(778899, "Elon Musk", "Biografi", 10, true);
        borrowing.add(bok2);
        borrowing.add(bok3);

        List<Book> borrowedBooks = aLoan.getBooks();

        for (Book b : borrowedBooks) {
            System.out.println("ISBN nr: " + b.getIsbn());
        }


        MemberService a = new MemberService();
       ArrayList<Member> b= a.getAllMembers();

        System.out.println(b.get(0).getlName());



        /*--------------------------------------------DB connection--------------------------------------------------------*/

        //Har inte fått rätt på kopplingen mellan Intellij/Java och databasen.

       // LoanService ls = new LoanService();
       // Loan [] testConnection = ls.getLoanByMember(2008);



    }
}
