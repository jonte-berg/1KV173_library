package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class Loan {

    private int loanID;
    private int memberID;
    private List<Book> borrowedBooks;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean overdue;

    public Loan() {

    }


    public Loan(int membersID, List<Book> booksToBorrow) {   //Tänker att man endast behöver detta för att göra ett lån resten sker med automatik.

        LocalDate today = LocalDate.now();

        this.loanID = createLoanID(membersID);
        this.memberID = membersID;
        this.startDate = today;
        this.endDate = today.plusDays(15);
        this.overdue = false;
        this.borrowedBooks = booksToBorrow;

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

    public Boolean getOverdue() {
        return overdue;
    }

    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }

}
