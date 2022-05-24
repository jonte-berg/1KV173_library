package model;

import sun.util.resources.LocaleData;

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


    public boolean updateBook(int isbn, int plusOrMinusOne) {

        int available = 0;

        String queryAvailable = "SELECT available " +
                                "FROM Book " +
                                "WHERE isbn = " + isbn + "";


        String queryUpdate = "UPDATE book " +
                             "SET available = (?) " +
                             "WHERE isbn = (?)";



        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {



            //Hämtar hur många böcker som finns tillgängligt just nu och antingen plussar på 1 eller minus 1.
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(queryAvailable);

                while (result.next()) {
                    available = result.getInt("available") + plusOrMinusOne;
                }



                //Uppdaterar book med det nya antalet som är available.
                PreparedStatement pStatment = conn.prepareStatement(queryUpdate);
                pStatment.setInt(1,  available);
                pStatment.setInt(2, isbn);

                //Gör själva uppdateringen i DB.
                int resultPS = pStatment.executeUpdate();



                //if successful
                if (resultPS>0) {
                    System.out.println("Book has been updated successfully.");
                }

                //if Fail
                else {
                    System.out.println("Unsuccessful book update.");
                }

        } catch (SQLException ex) {
            System.out.println("Something went wrong when updating a book...");
        }

        return true;
    }



    public boolean issueFine(int  memberID){

        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {

           Statement statement = conn.createStatement();
           ResultSet result = statement.executeQuery("Select total_Warnings FROM member where memberID="+memberID);
           int warnings=0;

           while(result.next()){
                warnings = result.getInt(1);
            }
            result= null;
           warnings++;
           PreparedStatement ps;

           //delete'a member
           if ((warnings)>=4){

                ps=conn.prepareStatement("DELETE from MEMBER WHERE memberID="+memberID);
                if (ps.executeUpdate()>0) {
                    System.out.println("Member reached warning limit, account is now terminated, contact a librarian for more information");

                    return true;
                }

            }
           //sista varningen
           else if ((warnings)==3){

               ps=conn.prepareStatement("UPDATE Member SET total_Warnings ="+warnings+ " WHERE memberID ="+memberID);
                if(ps.executeUpdate()>0){
                   System.out.println("Member now has 3 warnings, last chance before account termination");
                   return true;
               }
            }

           //skapa en suspension
           else if((warnings)==2){

               LocalDate today = LocalDate.now();
               ps=conn.prepareStatement("UPDATE Member SET total_Warnings ="+warnings+", " +
                                "suspendUntil ="+"'"+today.plusDays(15)+"'"+
                                " WHERE memberID="+memberID);

               if (ps.executeUpdate()>0) {
                    System.out.println("Member now has 2 warnings, 15 day suspension starts NOW");
                    return true;
                }
            }
            else{

                ps=conn.prepareStatement("UPDATE Member SET total_Warnings ="+warnings+ " WHERE memberID ="+memberID);
                if(ps.executeUpdate()>0) {
                    System.out.println("Member now has 1 warning");

                    return true;
                }

            }





            return false;

        } catch (SQLException ex) {
            System.out.println("Something went wrong with issuing a fine..");
        }

        return false;
    }

    @Override
    public boolean deleteLoan(int loanID) {



        loadDrivers();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


            Statement statement = conn.createStatement();
            int loanMember=0;  //vi lägger memberID i loanMember int - behövs senare IFALL overdue
            List<Integer> bookID = new ArrayList<Integer>();      // vi lägger book_ISBN i bookID int - behövs senare IFALL det ej finns andra exemplar AVAILABLE

            //vi hämtar memberID som är associerad med det angivna loanID, so far so good
            ResultSet result = statement.executeQuery("SELECT memberID FROM hasLoan WHERE loanID="+loanID);
                 while(result.next()){
                     loanMember=result.getInt(1);
                     System.out.println((result.getInt(1)));

            }

                 //vi hämtar book_isbn som ör associerad med det angivna loanID, so far so good -------KAN DET BLI MER än 1 result?!
            result=null;
            result=statement.executeQuery("Select book_ISBN FROM hasBook WHERE loanID="+loanID);

            while(result.next()){

                bookID.add(result.getInt(1));
               // System.out.println("isbn: "+result.getInt(1)+ " added to loan");

            }


            /* insert book++ code */




            result=null;
            LocalDate d1=null;
            result=statement.executeQuery("Select endDate FROM Loan WHERE loanID="+loanID);

            while(result.next()){
               d1= result.getDate("endDate").toLocalDate();
                System.out.println(result.getDate("endDate").toLocalDate());
            }
//btw den är reverse nu, dvs fine OM INTE FÖRSENAD (för test purp)
            if ((d1.isAfter(LocalDate.now())))
                issueFine(loanMember);


                updateDB(loanID);















            return true;
        } catch (SQLException ex) {

            System.out.println("(CATCH BLOCK) Something went wrong...");
        }


        return false;
    }



    public boolean updateDB(int loanID){

        loadDrivers();

     try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {


         System.out.println("VI ÄR REDO O DELET'a");

         String delete = "DELETE FROM hasloan WHERE loanID ="+loanID;
         PreparedStatement preparedStatement = conn.prepareStatement(delete);

         if(preparedStatement.executeUpdate()>0)
             System.out.println("Deleted from hasloan");
         else
             System.out.println("error deleting from hasloan");

         delete= "DELETE FROM hasbook WHERE loanID="+loanID;
        preparedStatement= conn.prepareStatement(delete);

         if(preparedStatement.executeUpdate()>0)
             System.out.println("Deleted from hasbook");
         else
             System.out.println("error deleting from hasbook");


         delete= "DELETE FROM Loan WHERE loanID = "+loanID;
         preparedStatement=conn.prepareStatement(delete);

         if(preparedStatement.executeUpdate()>0)
             System.out.println("Deleted from Loan");
         else
             System.out.println("error deleting from Loan");

        return true;
        } catch (SQLException ex) {

            System.out.println("Something went wrong...");
        }


        return false;
    }


    public static void loadDrivers() {
        try {     //Läser in drivrutinerna (behövs egentligen inte då det sker automatiskt, men kan vara bra att få ett tecken på att de är laddade)
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver did not load");
        }
    }

}
