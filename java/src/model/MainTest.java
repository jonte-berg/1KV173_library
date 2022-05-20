package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Här har vi en MainTest som vi kan göra olika tester för att visa/förklara saker på.*/


public class MainTest {

    public static void main(String[] args) throws SQLException {

        /*------------------------------------getTheMember()-------------------------------------------------------*/
        MemberService newServ = new MemberService();

        newServ.getTheMember(1234);

        /*------------------------------------getallMembers()-------------------------------------------------------*/

        MemberService newServ2 = new MemberService();

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
       ArrayList<Member> memberList= a.getAllMembers();

        System.out.println(memberList.get(0).getlName());
        System.out.println("test");



        //liten quick delete test
        // a.deleteMember(1000);


        /*------------------------------------getBookById()-------------------------------------------------------*/

        LoanService service = new LoanService();
        Book bookInDB = service.getBookById(100005);

        if (bookInDB != null) {
            System.out.println("\n-----getBookById()------");
            System.out.println("Titel: " + bookInDB.getTitle());
            System.out.println("ISBN: " + bookInDB.getIsbn());
        } else {
            System.out.println("Boken finne inte i databasen.");
        }



        /*------------------------------------getBookByTitle()-------------------------------------------------------*/

        LoanService service1 = new LoanService();
        Book bookByTitle = service1.getBookByTitle("Harry Potter och de vise");

        if (bookByTitle != null) {
            System.out.println("\n-----getBookByTitle()------");
            System.out.println("Titel: " + bookByTitle.getTitle());
            System.out.println("ISBN: " + bookByTitle.getIsbn());
        } else {
            System.out.println("Boken finne inte i databasen.");
        }



        /*------------------------------------getallBooks()-------------------------------------------------------*/

        LoanService service2 = new LoanService();
        ArrayList<Book> allBooks = service2.getAllBooks();

        System.out.println("\n-----getAllBooks()------");

        if (allBooks != null) {
            for (Book b : allBooks) {
                System.out.print("Titel: " + b.getTitle() + " - ");
                System.out.println("ISBN: " + b.getIsbn());
            }
        } else {
            System.out.println("Boken finne inte i databasen.");
        }


    }
}
