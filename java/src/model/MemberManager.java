package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class MemberManager implements IMemberManager {

    MemberService service = null;

    public MemberManager(MemberService service) {
        this.service = service;
    }

    public MemberManager() {
    }

    @Override
    public boolean addMember(Member newMember) {
        return false;
    }

    @Override
    public boolean deleteMember(int memberID) {

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
            return true;

        } catch (SQLException ex) {

            System.out.println("Something went wrong...");
        }
            return false;
    }


    @Override
    public boolean searchForMember(int memberID) {
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
