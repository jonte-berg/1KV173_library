package model;

import net.bytebuddy.asm.Advice;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*Här har vi en MainTest som vi kan göra olika tester för att visa/förklara saker på.*/


public class MainTest {

    public static void main(String[] args) throws SQLException {

        /*------------------------------------searchForMember()-------------------------------------------------------*/
        MemberService newServ4 = new MemberService();
        MemberManager mg1 = new MemberManager(newServ4);

        System.out.println(mg1.searchForMember(1001));
        System.out.println(mg1.searchForMember(1005));

        /*------------------------------------addMember()-------------------------------------------------------*/
       /*  MemberService newServ3 = new MemberService();
           Member newMember = new Member (1002,"John","Doe",0,3,0);

           newServ3.addMember(newMember);          /* Lägga till John Doe */

        /*------------------------------------deleteMember()-------------------------------------------------------*/

        /*  MemberService newServ5 = new MemberService();

            newServ5.deleteMember(1002);          /* Ta bort John Doe */


        /*------------------------------------getTheMember()-------------------------------------------------------*/
        MemberService newServ = new MemberService();

        System.out.println(newServ.getTheMember(1001));

        /*------------------------------------getAllMembers()-------------------------------------------------------*/

        MemberService newServ2 = new MemberService();

        for (Member memb: newServ2.getAllMembers()) {
            System.out.print(memb);
        }

        /*------------------------------------getLoanID()-------------------------------------------------------*/

        //Här är ett test för att se hur ett loanID kan se ut.
        //Det kommer skapas automatisk och innehåller memberID och en unik kod på 4 siffror som genereras i samma stund som lånet genomförs.

        List<Book> borrowing = new ArrayList<>();
        Book bok1 = new Book(112233, "Sagan om ringen", "Äventyr", 10, 10);
        borrowing.add(bok1);

        Loan aLoan = new Loan(2008, borrowing);

        System.out.println("\nLoanID: " + aLoan.getLoanID());




        /*------------------------------------getBooks()-------------------------------------------------------*/

        //Här är ett exempel på hur man kan hämta alla böcker som finns i ett specifikt lån.

        Book bok2 = new Book(445566, "Hobbit", "Äventyr", 5, 5);
        Book bok3 = new Book(778899, "Elon Musk", "Biografi", 10, 10);
        borrowing.add(bok2);
        borrowing.add(bok3);

        List<Book> borrowedBooks = aLoan.getBooks();

        for (Book b : borrowedBooks) {
            System.out.println("ISBN nr: " + b.getIsbn());
        }



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
        
        
        
       /*------------------------------------getLoanByMember()-------------------------------------------------------*/

        LoanService service3 = new LoanService();
        ArrayList<Loan> membersLoan = service3.getLoanByMember(1001);

        System.out.println("\n-----getLoanByMember()------");

        if (membersLoan != null) {
            for (Loan l : membersLoan) {
                System.out.println("MemberID: " + l.getMemberID());
                System.out.println("LoanID: " + l.getLoanID());
                System.out.println("StartDate: " + l.getStartDate());
                System.out.println("EndDate: " + l.getEndDate());
                System.out.println("Overdue: " + l.getOverdue());
                System.out.println("----------");

            }
        } else {
            System.out.println("Boken finne inte i databasen.");
        }

        /*------------------------------------addLoan()-------------------------------------------------------*/
        //fungerar som den ska
        LocalDate start= LocalDate.of(2022,06,10);
        LocalDate end = LocalDate.of(2022,06,25);
        Loan l1 = new Loan(6,4001, start,end,0);
       // service3.addLoan(l1);

    }
}
