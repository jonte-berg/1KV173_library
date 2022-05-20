package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ILoanService {



    /*
    Get loans from database that is in the given startDate and endDate.
    @para startDate
    @para endDate
    @return Loan[]
     */

    ArrayList<Loan> getAllLoans(LocalDate startDate, LocalDate endDate);



    /*
    Get all loan that belongs to a specifik member.
    @para memberID
    @return Loan[]
     */

    ArrayList<Loan> getLoanByMember(int membersID) throws SQLException;



    /*
    Get all books that is in the database.
    @return ArrayList<Book>
     */

    ArrayList<Book> getAllBooks();



    /*
    get a specifik book by its ISBN number.
    @para isbnNr
    @return Book
     */

    Book getBookById(int isbnNr);



    /*
    get a specifik book by its Title.
    @para title
    @return Book
     */

    Book getBookByTitle(String title);



    /*
    Add a Loan to database and return a boolean, true if the loan was added (search lonID) of fals if it don't exist.
    @para newLoan
    @return boolean
    */

    boolean addLoan(Loan newLoan);



        /*
    Delete a Loan in the database and return a boolean, true if the loan was added (search lonID) of fals if it don't exist.
    @para loanID
    @return boolean
    */

    boolean addLoan(int loanID);





}
