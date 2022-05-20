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

        if (service.getTheMember(memberID)==null)
            return false;
        else
            service.deleteMember(memberID);

       return true;
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
