package model;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class LoanManagerTest {


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
    }

    @Test
    void loanItems() {
    }

    @Test
    void issueFine() {
    }
}
