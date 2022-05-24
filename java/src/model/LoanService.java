package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT * FROM hasloan, loan";

        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);


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
        String query = "SELECT hasloan.memberid, loan.loanid, startDate, endDate, overdue\n" +
                       "FROM hasloan, loan\n" +
                       "WHERE loan.loanid = hasloan.loanid\n" +
                       "AND hasloan.memberid = " + membersID + ";";    //To do - Läs även in böckerna som är i lånet.

        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);


            while (result.next()) {
                Loan temp = new Loan(
                        result.getInt("loanid"),
                        result.getInt("memberid"),
                        result.getDate("startDate").toLocalDate(),
                        result.getDate("endDate").toLocalDate(),
                        result.getInt("overdue"));
                membersLoan.add(temp);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong...");
        }

        return membersLoan;
    }






    @Override

    public boolean addLoan(Loan loan) {

        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


            PreparedStatement addLoan = conn.prepareStatement("INSERT INTO Loan VALUES (?,?,?,?)");

            addLoan.setInt(1,loan.getLoanID());
            addLoan.setDate(2, Date.valueOf(loan.getStartDate()));
            addLoan.setDate(3, Date.valueOf(loan.getEndDate()));
            addLoan.setInt(4,loan.getOverdue());

            //skickar in lånet i LOAN table
            int result = addLoan.executeUpdate();

            //if successfull
            if (result>0) {
                System.out.println("Loan inserted successfully");

                //preppa statement till hasLoan table
                addLoan = conn.prepareStatement("INSERT INTO hasloan VALUES (?,?)");
                addLoan.setInt(1,loan.getMemberID());
                addLoan.setInt(2,loan.getLoanID());

                //skickar in lånet i hasLoan table
                result = addLoan.executeUpdate();


                //preppa statement till hasBook table
                addLoan = conn.prepareStatement("INSERT INTO hasbook VALUES (?,?)");

                for (int i = 0; i < loan.getBooks().size(); i++) {
                    Book temp = loan.getBooks().get(i);
                    addLoan.setInt(1,loan.getLoanID());
                    addLoan.setInt(2, temp.getIsbn());

                    //skickar in loanID och ISBNnr i hasBook table
                    result = addLoan.executeUpdate();

                    //Uppdaterar book tabellen genom att ta minus 1 på "available".
                    updateBook(temp.getIsbn(), -1);

                }


                //returns true if successfull and false if not
                return result > 0;
            }
            //if Fail
            else
                System.out.println("unsuccessful insertion ");

        } catch (SQLException ex) {
                System.out.println("Something went wrong...");
        }

        return false;


    }


    public boolean updateBook(int isbn, int plusOneOrMinusOne) {

        String query = "UPDATE book " +
                       "SET available = (?) " +
                       "WHERE isbn = (?)";


        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


            Statement pStatment =  conn.createStatement();
            ResultSet resultSet = pStatment.executeQuery("SELECT available FROM Book WHERE isbn="+isbn);

            int available= resultSet.getByte("available")+plusOneOrMinusOne;


            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,  available);
            ps.setInt(2, isbn);


            //Gör uppdateringen på book.
            int result = ps.executeUpdate();


            //if successfull
            if (result>0) {
                System.out.println("Book has been updated successfully");
            }

            //if Fail
            else {
                System.out.println("Unsuccessful book update ");
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong when updating a book...");
        }

        return true;

    }





    @Override
    public boolean deleteLoan(int loanID) {

        /*detta blir rörigt...bear with me, bör nog dela upp "arbetet" i denna metoden till två metoder */

        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


            Statement statement = conn.createStatement();

            int loanMember;  //vi lägger memberID i loanMember int - behövs senare IFALL overdue
            List<Integer> bookID = null;      // vi lägger book_ISBN i bookID int - behövs senare IFALL det ej finns andra exemplar AVAILABLE

            //vi hämtar memberID som är associerad med det angivna loanID, so far so good
            ResultSet result = statement.executeQuery("SELECT memberID FROM hasLoan WHERE loanID="+loanID);
            loanMember=result.getInt("memberID");

            //vi hämtar book_isbn som ör associerad med det angivna loanID, so far so good -------KAN DET BLI MER än 1 result?!

            result=statement.executeQuery("Select book_ISBN FROM hasBook WHERE loanID="+loanID);

            while(result.next()){
                bookID.add(result.getInt("book_ISBN"));
            }


            //nu har vi tillgång till alla variabler vi kan behövas ha...nu börjar helvetet



            //steg 1 - ÄR LÅNET FÖRSENAT?
            // OBS (om vi gör en procedur som sätter overdueBoolean i DB till true så fort endDate har passerat kan vi förenkla detta!)
            result=statement.executeQuery("Select endDate FROM Loan WHERE loanID="+loanID);
            //true = slipper memberTable
            //ifall DB endDate ÄR INNAN nuvarande tid
            if ((result.getDate("endDate").toLocalDate().isAfter(LocalDate.now()))){




                //nu ska vi rensa allt som har med lånet att göra i DB's,
                // steg 1 ta bort ur kopplingstabell hasLoan:

                if(statement.execute("DELETE FROM hasloan WHERE loanID ="+loanID))
                    System.out.println("Loan deleted from hasLoan...");
                else
                    System.out.println("Something went wrong with deleting from hasLoan");

                // steg 2 ta bort ur kopplingstabell hasBook:
                if(statement.execute("Delete FROM hasBook WHERE loanID ="+ loanID))
                    System.out.println("Loan deleted from hasBook.....");
                else
                    System.out.println("Something went wrong with deleting from hasBook");


                //steg 3 ta bort ur LOAN och ++ i boktabell
               //  result = statement.executeQuery("SELECT available FROM Book WHERE isbn="+bookID);
                        //++ i boktabell
                 for (int i = 0; i < bookID.size();i++) {

                     Book temp = getBookById(bookID.get(i));


                    //Uppdaterar book tabellen genom att ta + 1 på "available".
                    updateBook(temp.getIsbn(), 1);

                }
                 //delete sen färdig..........








            }


            else{
                //lös varning i membertabell


            }





            return true;
        } catch (SQLException ex) {

            System.out.println("Something went wrong...");
        }


        return false;
    }



    public boolean updateDB(int loanID){

        loadDrivers();

     try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {
           /*
             //update member SET total_Warnings=1 where memberID=1001	1 row(s) affected

            //Rows matched: 1  Changed: 1  Warnings: 0	0.016 sec
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE Member SET suspended = ?, total_Warnings = ? WHERE memberID = ?");

            /*ps.setInt(1,member.isSuspended());
            ps.setInt(2,member.getWarnings());
            ps.setInt(3,member.getId());
            ps.execute();


            System.out.println("Member with ID: "+member.getId()+ " has been updated!");
            return true;
*/
        } catch (SQLException ex) {

            System.out.println("Something went wrong...");
        }


        return false;
    }


    public static void loadDrivers() {
        try {                                                                           //Läser in drivrutinerna (behövs egentligen inte då det sker automatiskt, men kan vara bra att få ett tecken på att de är laddade)
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver did not load");
        }
    }

}
