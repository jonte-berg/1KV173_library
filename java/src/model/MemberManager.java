package model;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class MemberManager implements IMemberManager {

    MemberService service = null;

    public MemberManager(MemberService service) {
        this.service = service;
    }

    public MemberManager() {
    }

    @Override
    public boolean addMember(Member newMember) {

        Logger logger = LogManager.getLogger(MemberManager.class.getName());

        if (service.addMember(newMember)) {
            logger.info("A member has been added");
            return true;
        }
        else {
            logger.error("The member has failed to be added");
            return false;
        }
    }

    @Override
    public boolean deleteMember(int memberID) {

        if (service.getTheMember(memberID) == null)
            return false;
        else
            service.deleteMember(memberID);

        return true;
    }


    @Override
    public boolean searchForMember(int memberID) {

        ArrayList<Member> allMembers = service.getAllMembers();

        for (Member member : allMembers) {
            if (member.getId() == memberID) {
                return true;
            } else
                return false;
        }
        return false;
    }






    @Override
    public void issueFine(int membersID) {
        MemberService service = new MemberService();
        Member theMember = service.getTheMember(membersID);

        if (theMember.getId() == membersID) {
            theMember.setWarnings(theMember.getWarnings() + 1);

            //Skulle behöva en metod typ updateMember() i memberService som bara tar och uppdaterar members uppgifter.
        }

    }




    @Override
    public boolean suspendMember(int membersID) {
        MemberService service = new MemberService();
        Member theMember = service.getTheMember(membersID);

        if (theMember.getId() == membersID) {
            theMember.setSuspended(1);

            //Skulle behövt en updateMember() i memberService här också för att uppdatera i databasen.
            //Sen kom jag på att vi har ju inget sätt att ta bort en suspension. Borde finnas ett attribut som är typ suspendedUntil som håller en date.
            //så när någon vill göra ett lån som är suspended kollar den om suspended untill är mindre eller större än "dagens datum".



            if (theMember.isSuspended() == 1) {
                return true;
            }
        }

        return false;
    }






}
