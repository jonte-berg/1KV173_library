/*      Kurs: 1IK173
        Projekt
        Kursdeltagare: Dennis Schill, Robin Sjöberg, Jonathan Berg, Konstantinos Karidas
        Termin och datum: VT22 30/5   */

package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Loan {

    private int loanID;
    private int memberID;
    private List<Book> borrowedBooks;
    private LocalDate startDate;
    private LocalDate endDate;
    private int overdue;

    public Loan() {

    }



    public Loan(int membersID, List<Book> booksToBorrow) {   //SKAPA ETT NYTT LÅN

        LocalDate today = LocalDate.now();

        this.loanID = createLoanID(membersID);
        this.memberID = membersID;
        this.startDate = today;
        this.endDate = today.plusDays(15);
        this.overdue = 0;
        this.borrowedBooks = booksToBorrow;

    }


    public Loan(int theLoanID, int membersID, LocalDate theStartDate, LocalDate theEndDate, int isOverdue) {   //LÄS IN LÅN FRÅN DB

        this.loanID = theLoanID;
        this.memberID = membersID;
        this.startDate = theStartDate;
        this.endDate = theEndDate;
        this.overdue = isOverdue;


    }


    public int getLoanID() {
        return loanID;
    }

    public int createLoanID (int membersID) {
        //Skapar ett unikt loanID med hjälp av medlemmens ID + lånetillfällets specifika nanosekunder som avrundas till de fyra första siffrorna (for loopen).

        LocalTime time = LocalTime.now();
        String nanoStr = String.valueOf(time.getNano());
        StringBuilder uniqueID = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            uniqueID.append(nanoStr.charAt(i));
        }

        return Integer.parseInt(membersID + String.valueOf(uniqueID));
    }


    public List<Book> getBooks() {
        return borrowedBooks;
    }

    public void setBooks(List<Book> booksToBorrow) {
        this.borrowedBooks = booksToBorrow;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdueStatus) {
        this.overdue = overdueStatus;
    }

}
