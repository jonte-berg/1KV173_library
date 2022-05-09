package model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Loan {

    private int loanID;
    private int memberID;
    private List<Book> borrowedBooks;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean overdue;

    public Loan() {

    }

    public Loan(int membersID, Book [] booksToBorrow) {   //Tänker att man endast behöver detta för att göra ett lån resten sker med automatik.

        LocalDate today = LocalDate.now();  //Kan vi använda LocalDate istället? Verkar mycket smidigare att jobba med!
        int todayAsInt = today.getYear() + today.getMonthValue() + today.getDayOfMonth();

        this.loanID = membersID + todayAsInt;     //Kanske kan göras enklare men borde fungera OK.
        this.memberID = membersID;
        this.startDate = today;
        this.endDate = today.plusDays(15);
        this.overdue = false;
        this.borrowedBooks = Arrays.stream(booksToBorrow).toList(); //Ändrade till en Book [] istället för att det kanske är lättare at jobba med men att det sedan lagras som en List<Book> i själva klassen.

    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public Book [] getBooks() {
        Book [] allBorrowedBooks =  new Book[borrowedBooks.size()];
        return borrowedBooks.toArray(allBorrowedBooks);
    }

    public void setBooks(Book [] booksToBorrow) {
        this.borrowedBooks = Arrays.stream(booksToBorrow).toList();
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
