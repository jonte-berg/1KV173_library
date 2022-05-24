package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(loanManager.searchForBookISBN(1),true);
    }



    @Test
    void searchForBookISBN_negative() {

        when(loanService.getBookById(2))
                .thenReturn( null);

        //finns ingen bok med ID 2
        assertNotEquals(loanManager.searchForBookISBN(2),true);
    }



    @Test
    void searchForBookTitlePositive() {

        when(loanService.getBookByTitle("Sagan om ringen"))
                .thenReturn( new Book(1, "Sagan om ringen", "Äventyr", 2,2));

        //finns
        assertEquals(true, loanManager.searchForBookTitle("Sagan om ringen"));
    }



    @Test
    void searchForBookTitleNegative() {

        when(loanService.getBookByTitle("Sagan om ringen"))
                .thenReturn(null);

        //finns
        assertEquals(false, loanManager.searchForBookTitle("Sagan om ringen"));
    }

    @Test
    void addLoanPositive() {


    }

    @Test
    void addLoanNegative() {
    }


    @Test
    void deleteLoanPositive() {
    }

    @Test
    void deleteLoanNegative() {
    }

}
