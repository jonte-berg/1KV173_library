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
    public Loan[] getAllLoans(LocalDate startDate, LocalDate endDate) {
        Loan [] allLoans;
        int numberOfLoans = 0;

        //1. Connect to the database

        //2. SQL statement that count all loans.
        //numberOfLoans = result.getInt(1);
        allLoans = new Loan [numberOfLoans];

        //3. SQL statement that gets all loans in the database.

        //4. A while loop that instantiate all loans and inserts them in "allLoans" array.


        return allLoans;
    }

    @Override
    public Loan[] getLoanByMember(int membersID) throws SQLException {
        Loan [] membersLoan;
        int numberOfLoans = 0;
        int addedLoans = 0;

        //Conect to the database,


        loadDrivers();

        try (Connection connect = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {
            System.out.println("Connected");

            Statement statment = connect.createStatement();
            ResultSet result = statment.executeQuery("SELECT COUNT(*) FROM hasLoan WHERE member = membersID");
            numberOfLoans = result.getInt(1);



            //numberOfLoans = COUNT number of rows.
            membersLoan = new Loan [numberOfLoans];


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

                membersLoan[addedLoans] = tempLoan;
                addedLoans++;
            }
        }


        return membersLoan;
    }

    @Override
    public Book[] getAllBooks() {
        return new Book[0];
    }



    public static void loadDrivers() {
        try {                                                                           //Läser in drivrutinerna (behövs egentligen inte då det sker automatiskt, men kan vara bra att få ett tecken på att de är laddade)
            Class.forName("jdbc:mysql.Driver");
            //jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver did not load");
        }
    }

}
