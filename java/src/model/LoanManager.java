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
        MemberService service = new MemberService();
        Member theMember = service.getTheMember(membersID);

        if (theMember.getId() == membersID) {
            theMember.setWarnings(theMember.getWarnings() + 1);

            //Skulle behöva en metod typ updateMember() i memberService som bara tar och uppdaterar members uppgifter.
        }

    }




    @Override
    public boolean suspendMember(int membersID) {
        MemberService service = new MemberService();
        Member theMember = service.getTheMember(membersID);

        if (theMember.getId() == membersID) {
            theMember.setSuspended(1);

            //Skulle behövt en updateMember() i memberService här också för att uppdatera i databasen.
            //Sen kom jag på att vi har ju inget sätt att ta bort en suspension. Borde finnas ett attribut som är typ suspendedUntil som håller en date.
            //så när någon vill göra ett lån som är suspended kollar den om suspended untill är mindre eller större än "dagens datum".
        }


        if (theMember.isSuspended() == 1) {
            return true;
        } else {
            return false;
        }
    }


}
