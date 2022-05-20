package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
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
    void searchForBookISBN() {
        LoanService service = mock(LoanService.class);
        LoanManager manager = new LoanManager(service);
        Book expected = new Book(112233, "Sagan om ringen", "Äventyr", 10, 10);

        Book [] mocks = new Book[] {
                new Book(112233, "Sagan om ringen", "Äventyr", 10, 10),
                new Book(223344, "Hobbit", "Äventyr", 5, 10),
                new Book(334455, "Elon Musk", "Biografi", 3, 10),
                new Book(445566, "Harry Potter", "Äventyr", 15, 10)
        };


      //  when(service.getBookById(112233).thenReturn()




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
