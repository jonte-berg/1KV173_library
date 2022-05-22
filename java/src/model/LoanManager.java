package model;

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
    public boolean addLoan(int membersID, List<Integer> books) {
        return false;
    }

    @Override //deleteman JONTE
    public boolean deleteLoan(int loanID) {
        return false;
    }

    @Override
    public void loanItems() {

    }

    @Override
    public void issueFine(int membersID) {

    }
}
