/*      Kurs: 1IK173
        Projekt
        Kursdeltagare: Dennis Schill, Robin Sjöberg, Jonathan Berg, Konstantinos Karidas
        Termin och datum: VT22 30/5   */

package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public interface ILoanService {

    /*
    Get a specific book by its ISBN number.
    @para isbnNr
    @return Book
    */

    Book getBookById(int isbnNr);

    /*
    Get a specific book by its Title.
    @para title
    @return Book
    */

    Book getBookByTitle(String title);

    /*
    Get all books that is in the database.
    @return ArrayList<Book>
    */

    ArrayList<Book> getAllBooks();

    /*
    Get loans from database that is in the given startDate and endDate.
    @para startDate
    @para endDate
    @return ArrayList<Loan>
     */

    ArrayList<Loan> getAllLoans();

    /*
    Get all loan that belongs to a specific member.
    @para memberID
    @return ArrayList<Loan>
     */

    ArrayList<Loan> getLoanByMember(int membersID) throws SQLException;

     /*
    Add a loan.
    @para loan
    @return boolean
     */

    boolean addLoan(Loan loan);

    /*
     Delete a loan.
     @para loanID
     @return boolean
     */

    boolean deleteLoan(int loanID);



}
