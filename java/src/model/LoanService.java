package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanService implements ILoanService {

    @Override
    public Book getBookById(int isbnNr) {
        Book theBook = new Book();

        //Conect to the database,
        //check if isbnNr exist in the database.
        //if exist, then populate "theBook" with information from database.
        //else theBook = null


        return theBook;
    }

    @Override
    public Book getBookByTitle(String title) {
        Book theBook = new Book();

        //Conect to the database,
        //check if title exist in the database.
        //if exist, then populate "theBook" with information from database.
        //else theBook = null


        return theBook;
    }

    @Override
    public ArrayList<Loan> getAllLoans(LocalDate startDate, LocalDate endDate) {
        ArrayList<Loan> allLoans = new ArrayList<>();

        //1. Connect to the database

        //2. SQL statement that gets all loans in the database.

        //3. A while loop that instantiate all loans and inserts them in "allLoans" array.


        return allLoans;
    }

    @Override
    public ArrayList<Loan> getLoanByMember(int membersID) throws SQLException {
        ArrayList<Loan> membersLoan = new ArrayList<>();

        //Conect to the database,

        loadDrivers();

        try (Connection connect = DriverManager.getConnection(
                "jdbc:mysql://localhost/Musik?useSSL=false",
                "root", "RobinMySQL1!")) {

            System.out.println("Connected");

            Statement statment = connect.createStatement();
            ResultSet result = statment.executeQuery("SELECT * FROM loan WHERE hasloan.lonid=loan.loanid");


            //while loop that adds all the loans and its information to the "allLoans" array.
            while (result.next()) {
                Loan tempLoan = new Loan(
                        //result.getInt("loanID")
                        //result.getInt("memberID")
                        //result.getBook("Hur?"),
                        //result.getLocalDate("Hur?"),
                        //result.getLocalDate("Hur?"),
                        //result.getBoolean()
                );
                membersLoan.add(tempLoan);
            }
        }


        return membersLoan;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> allBooks = new ArrayList<>();


        return allBooks;
    }



    public static void loadDrivers() {
        try {                                                                           //Läser in drivrutinerna (behövs egentligen inte då det sker automatiskt, men kan vara bra att få ett tecken på att de är laddade)
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver did not load");
        }

    }

}
