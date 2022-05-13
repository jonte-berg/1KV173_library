package model;

import java.util.List;

public interface ILoanManager {
    /*
+ searchForBook(String) : boolean
+ addLoan(int, List<Book>) :  boolean
+ deleteLoan(int) : boolean
+ loanItems() (vad tänkte vi här?)
+ issueFine() : void
    */



    /*
    Search for a book in the database with the help of the ISBN number.
    @para ISBN nr
    @return boolean (true if book is available > 0. false if not available (don't exist or available < 1)
     */

    boolean searchForBook(int ISBNnr);



    /*
    Adds a loan to a specific member containing a List of books.
    @para membersID
    @para books
    @return boolean (true if the loan has been added. false if it didn't go through).
    */

    boolean addLoan(int membersID, int [] books);



    /*
    Delete a loan with a specifik loanID.
    @para loanID
    @return boolean (true if the loan has been deleted. false if it didn't go through).
    */

    boolean deleteLoan(int loanID);



    //Fortsätt med att lägga till resterande.

}