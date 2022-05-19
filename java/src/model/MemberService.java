package model;

import java.sql.*;
import java.util.ArrayList;




public class MemberService implements IMemberService {

    @Override
    public ArrayList<Member>  getAllMembers() {

        loadDrivers();

        ArrayList<Member> allMembers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true", "gruppD", "Q1w2e3r4t5")) {
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
        String query = "SELECT * FROM member WHERE memberID = " + memberID +"";

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

    @Override
    public void updateMember(int memberID,Member member) {

        // denna är EJ i uppgiftsbeskrivning, men om vi har tråkigt kan vi lösa detta




    }

    @Override
    public void deleteMember(int memberID) {

        /*hmmm jag undrar om vi ska ha ngn form av check som kollar ifall memberID existerar i DB först
        kanske är onödigt eftersom jag tycker denna metod borde vara "låst" bakom att man "väljer" en användare i ex MemberArraylist,
        så då kommer det alltid finnas en member med det ID man skickar med... har ni några åsikter?
        metoden funkar btw, testade den i mainTest
        */
        loadDrivers();


        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://library-1ik173.mysql.database.azure.com:3306/library1ik173?useSSL=true",
                "gruppD",
                "Q1w2e3r4t5")) {

            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM member WHERE memberID ="+memberID+"");

            //System.out.println("Member with id: "+memberID+" is deleted");

        } catch (SQLException ex) {

            System.out.println("Something went wrong...");
        }



    }

    public static void loadDrivers() {
        try {                                                                           //Läser in drivrutinerna (behövs egentligen inte då det sker automatiskt, men kan vara bra att få ett tecken på att de är laddade)
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver did not load");
        }
    }
}
