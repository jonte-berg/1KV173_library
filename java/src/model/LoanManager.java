package model;

import java.util.List;

public class LoanManager implements ILoanManager{

    LoanService service = null;
    public LoanManager(LoanService aService) { service = aService; }


    @Override
    public boolean searchForBookISBN(int isbnNr) {
        return false;
    }

    @Override
    public boolean searchForBookTitle(String title) {
        return false;
    }

    @Override
    public boolean addLoan(int membersID, List<Integer> books) {
        return false;
    }

    @Override
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
