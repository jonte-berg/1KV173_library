package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanManagerTest {


     LoanService loanService = mock(LoanService.class);
     LoanManager loanManager = new LoanManager();


    @BeforeEach
    void setUp() {
        System.out.println("Setting it up! ....");
        loanManager = new LoanManager(loanService);
    }


    @AfterEach
    void tearDown() {
        System.out.println("Running: tearDown! ....");
        loanManager = null;
        assertNull(loanManager);
    }



    @Test
    void searchForBookISBN_positive() {

       when(loanService.getBookById(1))
               .thenReturn( new Book(1, " Sagan om Boken", "Äventyr", 2,2));

        //finns
        assertEquals(true, loanManager.searchForBookISBN(1));
    }


    @Test
    void searchForBookISBN_negative() {

        when(loanService.getBookById(2))
                .thenReturn( null);

        //finns ingen bok med ID 2
        assertNotEquals(true, loanManager.searchForBookISBN(2));
    }


    @Test
    void searchForBookISBN_negativeNotAvailable() {

        when(loanService.getBookById(1))
                .thenReturn( new Book(1, " Sagan om Boken", "Äventyr", 2,0));

        //finns men available är mindre än 1.
        assertNotEquals(true, loanManager.searchForBookISBN(1));
    }



    @Test
    void searchForBookTitlePositive() {

        when(loanService.getBookByTitle("Sagan om ringen"))
                .thenReturn(new Book(1, "Sagan om ringen", "Äventyr", 2,2));

        //boken finns.
        assertEquals(true, loanManager.searchForBookTitle("Sagan om ringen"));
    }



    @Test
    void searchForBookTitleNegative() {

        when(loanService.getBookByTitle("Sagan om ringen"))
                .thenReturn(null);

        //boken finns inte.
        assertNotEquals(true, loanManager.searchForBookTitle("Sagan om ringen"));
    }


    @Test
    void searchForBookTitleNegativeNotAvailable() {

        when(loanService.getBookByTitle("Sagan om ringen"))
                .thenReturn(new Book(1, "Sagan om ringen", "Äventyr", 2,0));

        //boken finns men available är mindre än 1.
        assertNotEquals(true, loanManager.searchForBookTitle("Sagan om ringen"));
    }




    @Test //Kollar så att metoden i LoanManager returnerar true om LoanService returnerar true.
    void addLoanPositive() throws SQLException {
        List<Integer> books = new ArrayList<>();
        books.add(10006);

        when (loanService.addLoan(any(Loan.class))).thenReturn(true);

        assertEquals(true, loanManager.addLoan(10002, books));
    }



    @Test //Kollar så att metoden i LoanManager returnerar false om LoanService returnerar false.
    void addLoanNegative() throws SQLException {
        List<Integer> books = new ArrayList<>();
        books.add(10006);

        when (loanService.addLoan(any(Loan.class))).thenReturn(false);

        assertEquals(false, loanManager.addLoan(10002, books));
    }




    @Test
    void deleteLoanPositive() {
    }




    @Test
    void deleteLoanNegative() {
    }

}
