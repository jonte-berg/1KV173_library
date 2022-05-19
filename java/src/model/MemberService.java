package model;

import java.sql.*;
import java.util.ArrayList;




public class MemberService implements IMemberService {

    @Override
    public ArrayList<Member>  getAllMembers() {

try{Class.forName("com.mysql.cj.jdbc.Driver").newInstance();} catch (ClassNotFoundException e) {
    throw new RuntimeException(e);
} catch (InstantiationException e) {
    throw new RuntimeException(e);
} catch (IllegalAccessException e) {
    throw new RuntimeException(e);
}

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

        /* Code that retrieves from database */

        return null;
    }

    @Override
    public boolean updateMember(int memberID) {



        return false;
    }
}
