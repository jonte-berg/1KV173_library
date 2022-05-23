package model;

import org.junit.platform.commons.function.Try;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanManager implements ILoanManager{

    LoanService service = null;
    public LoanManager(LoanService aService) { service = aService; }


    @Override
    public boolean searchForBookISBN(int isbnNr) {

        return service.getBookById(isbnNr) != null;
    }



    @Override
    public boolean searchForBookTitle(String title) {

        return service.getBookByTitle(title) != null;
    }



    @Override
    public boolean addLoan(int membersID, List<Integer> books) throws SQLException {

        List<Book> listOfBooks = new ArrayList<>();

        //Skapar bok objekt och stoppar in i en List<Book>
        for (Integer i : books) {
            listOfBooks.add(service.getBookById(i));
        }


        //Lägger in lånet i databasen.
        Loan aNewLoan = new Loan(membersID, listOfBooks);
        service.addLoan(aNewLoan);

        return true;

        //Detta fungerar nu och den lägger in lånen och tillhörande information i alla tabller.
        //Dock behöver vi korrigera så att böcker uppdateras
        // Samt att members maxLoan inte överskrids (kanske sker i vår main?).


    }



    @Override //deleteman JONTE
    public boolean deleteLoan(int loanID) {
        return false;
    }






}
