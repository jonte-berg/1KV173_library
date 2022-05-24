package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanManagerTest {


     LoanService loanService = mock(LoanService.class);
     LoanManager lm = new LoanManager();

    @BeforeEach
    void setUp() {
        System.out.println("Setting it up! ....");
        lm = new LoanManager(loanService);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Running: tearDown! ....");
        lm = null;
        assertNull(lm);
    }

    @Test
    void searchForBookISBN_positive() {

       when(loanService.getBookById(1))
               .thenReturn( new Book(1, " Sagan om Boken", "Äventyr", 2,2));

        //finns
        assertEquals(lm.searchForBookISBN(1),true);
    }

    @Test
    void searchForBookISBN_negative() {

        when(loanService.getBookById(2))
                .thenReturn( null);


        //finns ingen bok med ID 2
        assertNotEquals(lm.searchForBookISBN(2),true);
    }

    @Test
    void searchForBookTitlePositive() {
    }

    @Test
    void searchForBookTitleNegative() {
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
