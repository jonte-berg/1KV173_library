package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoanService implements ILoanService {

    @Override
    public Book getBookById(int isbnNr) {
        Book theBook = null;
        String query = "SELECT * FROM book WHERE isbn = " + isbnNr +"";

        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Book temp = new Book(
                        result.getInt("isbn"),
                        result.getString("title"),
                        result.getString("genre"),
                        result.getInt("copies"),
                        result.getInt("available")); //ska ändras i batabasen till available och quantitysLeft ska tas bort.
                theBook = temp;
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong...");
        }


        return theBook;
    }



    @Override
    public Book getBookByTitle(String title) {
        Book theBook = null;
        String query = "SELECT * FROM book WHERE title LIKE '" + title +"%'";

        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);


            while (result.next()) {
                Book temp = new Book(
                        result.getInt("isbn"),
                        result.getString("title"),
                        result.getString("genre"),
                        result.getInt("copies"),
                        result.getInt("available"));  //ska ändras i batabasen till available och quantitysLeft ska tas bort.
                theBook = temp;
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong...");
        }


        return theBook;
    }

    @Override
    public boolean addLoan(Loan loan) {
        return false;
    }

    @Override
    public boolean deleteLoan(int loanID) {
        return false;
    }


    @Override
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> allBooksInDB = new ArrayList<>();
        String query = "SELECT * FROM book";

        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);


            while (result.next()) {
                Book temp = new Book(
                        result.getInt("isbn"),
                        result.getString("title"),
                        result.getString("genre"),
                        result.getInt("copies"),
                        result.getInt("available"));  //ska ändras i batabasen till available och quantitysLeft ska tas bort.
                allBooksInDB.add(temp);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong...");
        }


        return allBooksInDB;
    }





    @Override
    public ArrayList<Loan> getAllLoans(LocalDate startDate, LocalDate endDate) {
        ArrayList<Loan> allLoan = new ArrayList<>();

        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM hasloan, loan");


            while (result.next()) {
                Loan loan = new Loan(
                        result.getInt("loanID"),
                        result.getInt("memberID"),
                        result.getDate("startDate").toLocalDate(),
                        result.getDate("endDate").toLocalDate(),
                        result.getInt("overdue"));
                allLoan.add(loan);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong...");
        }


        return allLoan;
    }

    @Override
    public ArrayList<Loan> getLoanByMember(int membersID) throws SQLException {
        ArrayList<Loan> membersLoan = new ArrayList<>();


        return membersLoan;
    }




    public static void loadDrivers() {
        try {                                                                           //Läser in drivrutinerna (behövs egentligen inte då det sker automatiskt, men kan vara bra att få ett tecken på att de är laddade)
            Class.forName("com.mysql.cj.jdbc.Driver");
            //jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true
            //System.out.println("Driver loaded");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver did not load");
        }
    }

}
