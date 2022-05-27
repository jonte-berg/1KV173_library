package model;

import org.junit.platform.commons.function.Try;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanManager implements ILoanManager {

    LoanService service = null;

    public LoanManager(LoanService aService) {
        service = aService;
    }

    public LoanManager() {
    }


    @Override
    public boolean searchForBookISBN(int isbnNr) {
        Book theBook = service.getBookById(isbnNr);

        if (theBook != null && theBook.getAvailable() > 0) {
            return true;
        } else {
            return false;
        }

    }



    @Override
    public boolean searchForBookTitle(String title) {

        //return service.getBookByTitle(title) != null;

        Book theBook = service.getBookByTitle(title);

        if (theBook != null && theBook.getAvailable() > 0) {
            return true;
        } else {
            return false;
        }
    }



    @Override
    public boolean addLoan(int membersID, List<Integer> books) throws SQLException {

        List<Book> listOfBooks = new ArrayList<>();

        //Skapar bok objekt och stoppar in i en List<Book>
        for (Integer i : books) {
            listOfBooks.add(service.getBookById(i));
        }

        //Lägger in lånet i databasen och kollar så att lånet lades till, true/false.
        Loan aNewLoan = new Loan(membersID, listOfBooks);

        if (service.addLoan(aNewLoan)) {
            return true;
        } else
            return false;

        //Samt att members maxLoan inte överskrids (kanske sker i vår main?).
    }



    @Override //deleteman JONTE
    public boolean deleteLoan(int loanID) {
        return false;
    }






}
