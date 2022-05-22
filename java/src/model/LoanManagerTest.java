package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanManagerTest {


   // LoanService loanService = mock(LoanService.class);
    //LoanManager lm = new LoanManager(loanService);

    @BeforeEach
    void setUp() {
      //  System.out.println("Setting it up! ....");
       // lm = new LoanManager(loanService);
    }

    @AfterEach
    void tearDown() {
        //System.out.println("Running: tearDown! ....");

    }

    @Test
    void searchForBookISBN_positive() {
        LoanService service = mock(LoanService.class);
        LoanManager manager = new LoanManager(service);



       when(service.getBookById(1))
               .thenReturn( new Book(1, " Sagan om Boken", "Äventyr", 2,2));

        //finns
        assertEquals(manager.searchForBookISBN(1),true);


    }
    @Test
    void searchForBookISBN_negative() {
        LoanService service = mock(LoanService.class);
        LoanManager manager = new LoanManager(service);



        when(service.getBookById(2))
                .thenReturn( null);


        //finns ingen bok med ID 2
        assertNotEquals(manager.searchForBookISBN(2),true);


    }

    @Test
    void searchForBookTitle() {
    }

    @Test
    void addLoan() {
    }

    @Test
    void deleteLoan() {
       // LoanService service = mock(LoanService.class);
       // LoanManager manager = new LoanManager(service);
      //  Book expected = new Book(112233, "Sagan om ringen", "Äventyr", 10, 10);
       // Loan expectedLoan = new Loan()
    }

    @Test
    void loanItems() {
    }

    @Test
    void issueFine() {
    }
}
