package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;




public class MemberService implements IMemberService {

    @Override
    public ArrayList<Member>  getAllMembers() {

        loadDrivers();

        ArrayList<Member> allMembers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true", "gruppD", "Q1w2e3r4t5")) {
            System.out.println("Connected\n");

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM Member");

            while (result.next()) {
                Member member = new Member(result.getInt("memberID"), result.getString("sName"), result.getString("lName"), result.getInt("suspended"), result.getInt("maxLoans"), result.getInt("total_Warnings"));
                allMembers.add(member);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong...");
        }

        return allMembers;

    }

    @Override
    public Member getTheMember(int memberID) {

        Member memb=null ;
        String query = "SELECT * FROM Member WHERE memberID = " + memberID +"";

        loadDrivers();



        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);



            while (result.next()) {
                memb = new Member(
                        result.getInt("memberID"),
                        result.getString("sName"),
                        result.getString("lName"),
                        result.getInt("suspended"),
                        result.getInt("maxLoans"),
                        result.getInt("total_Warnings"));
            }

        } catch (SQLException ex) {

            System.out.println("Something went wrong...");
        }

        return memb;
    }

    public boolean addMember(Member newMember) {

        return false;
    }

    public boolean deleteMember(int memberID) {


        loadDrivers();


        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {

            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM member WHERE memberID ="+memberID+"");

            //System.out.println("Member with id: "+memberID+" is deleted");
            return true;

        } catch (SQLException ex) {

            System.out.println("Something went wrong...");
        }
        return false;
    }

    @Override
    public boolean suspendMember(LocalDate start, LocalDate end) {
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
