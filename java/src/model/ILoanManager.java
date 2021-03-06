/*      Kurs: 1IK173
        Projekt
        Kursdeltagare: Dennis Schill, Robin Sjöberg, Jonathan Berg, Konstantinos Karidas
        Termin och datum: VT22 30/5   */

package model;

import java.sql.SQLException;
import java.util.List;

public interface ILoanManager {

    /*
    Search for a book in the database with the help of the ISBN number.
    @para ISBN nr
    @return boolean (true if book is available > 0. false if not available (don't exist or available < 1)
     */

    boolean searchForBookISBN(int isbnNr);

        /*
    Search for a book in the database with the help of the Title.
    @para ISBN nr
    @return boolean (true if book is available > 0. false if not available (don't exist or available < 1)
    Förbättringsförslag som vi kanske kan ta med i rapporten (returenera int istället, 1 = är tillgänglig, 0 = finns men ej tillgänglig och -1 = finns inte).
     */

    boolean searchForBookTitle(String title);

    /*
    Adds a loan to a specific member containing a List of books.
    @para membersID
    @para books
    @return boolean (true if the loan has been added. false if it didn't go through).
    */

    boolean addLoan(int membersID, List<Integer> books) throws SQLException;

    /*
    Delete a loan with a specific loanID.
    1. Update books.
    2. Update member.
    3. If loan is overdue -> issueFine() -> getWarnings ->
            if warnings = 2 -> suspendMember()
            if warnings = 4 -> deleteMember()
    @para loanID
    @return boolean (true if the loan has been deleted. false if it didn't go through).
    */

    boolean deleteLoan(int loanID);

}